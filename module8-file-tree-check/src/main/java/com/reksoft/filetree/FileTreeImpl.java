package com.reksoft.filetree;

import java.nio.file.Path;
import java.util.Optional;

public class FileTreeImpl implements FileTree {

  @Override
  public Optional<String> tree(Path path) {
    return Optional.empty();
  }
}
