package com.reksoft.filetree;

import java.nio.file.Path;
import java.util.Optional;

public interface FileTree {
    Optional<String> tree(Path path);
}
