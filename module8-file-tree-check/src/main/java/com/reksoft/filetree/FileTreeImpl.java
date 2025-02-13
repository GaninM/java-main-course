package com.reksoft.filetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FileTreeImpl implements FileTree {

  @Override
  public Optional<String> tree(Path path) {
    if (path == null || !Files.exists(path)) {
      return Optional.empty();
    }

    try {
      if (Files.isRegularFile(path)) {
        long size = Files.size(path);
        return Optional.of(path.getFileName() + " " + size + " bytes");
      } else if (Files.isDirectory(path)) {
        DirectoryInfo directoryInfo = calculateDirectoryInfo(path);
        return Optional.of(formatDirectoryTree(directoryInfo));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  private DirectoryInfo calculateDirectoryInfo(Path directory) throws IOException {
    DirectoryInfo info = new DirectoryInfo(directory.getFileName().toString());
    Stream<Path> streamDirectory = Files.list(directory);
    List<Path> children = streamDirectory.sorted((path1, path2) -> {
      boolean isDir1 = Files.isDirectory(path1);
      boolean isDir2 = Files.isDirectory(path2);

      if (isDir1 && !isDir2) {
        return -1; // Папки идут первыми
      } else if (!isDir1 && isDir2) {
        return 1; // Файлы идут после папок
      } else {
        // Если оба элемента одного типа, сортируем по имени
        return path1.getFileName().toString().compareToIgnoreCase(path2.getFileName().toString());
      }
    }).toList();

    streamDirectory.close();

    // Сортируем: сначала папки, потом файлы, и всё по имени
    for (Path child : children) {
      if (Files.isDirectory(child)) {
        DirectoryInfo childInfo = calculateDirectoryInfo(child);
        info.addChild(childInfo);
      } else if (Files.isRegularFile(child)) {
        long size = Files.size(child);
        info.addChild(new FileInfo(child.getFileName().toString(), size));
      }
    }

    return info;
  }

  private String formatDirectoryTree(DirectoryInfo directoryInfo) {
    StringBuilder result = new StringBuilder();
    // Обрабатываем корневой элемент отдельно
    result.append(directoryInfo.getName())
        .append(" ")
        .append(directoryInfo.getSize())
        .append(" bytes")
        .append("\n");

    // Рекурсивно обрабатываем дочерние элементы
    List<FileSystemInfo> children = directoryInfo.getChildren();
    for (int i = 0; i < children.size(); i++) {
      formatDirectoryTree(children.get(i), result, "", i == children.size() - 1);
    }
    return result.toString();
  }

  private void formatDirectoryTree(FileSystemInfo info, StringBuilder result, String prefix, boolean isLast) {
    result.append(prefix);
    if (isLast) {
      result.append("└─ ");
      prefix += "   ";
    } else {
      result.append("├─ ");
      prefix += "│  ";
    }
    result.append(info.getName()).append(" ").append(info.getSize()).append(" bytes").append("\n");

    if (info instanceof DirectoryInfo directoryInfo) {
      List<FileSystemInfo> children = directoryInfo.getChildren();
      for (int i = 0; i < children.size(); i++) {
        formatDirectoryTree(children.get(i), result, prefix, i == children.size() - 1);
      }
    }
  }

  private abstract static class FileSystemInfo {
    protected String name;
    protected long size;

    public FileSystemInfo(String name, long size) {
      this.name = name;
      this.size = size;
    }

    public String getName() {
      return name;
    }

    public long getSize() {
      return size;
    }
  }

  private static class FileInfo extends FileSystemInfo {
    public FileInfo(String name, long size) {
      super(name, size);
    }
  }

  private static class DirectoryInfo extends FileSystemInfo {
    private final List<FileSystemInfo> children = new ArrayList<>();

    public DirectoryInfo(String name) {
      super(name, 0);
    }

    public void addChild(FileSystemInfo child) {
      children.add(child);
      size += child.getSize();
    }

    public List<FileSystemInfo> getChildren() {
      return children;
    }
  }
}
