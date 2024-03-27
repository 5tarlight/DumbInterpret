package io.yeahx4.util;

import java.io.File;

public final class Filenames {
    public enum FileType {
        SOURCE,
        UNKNOWN
    }

    public static String getExtension(File file) {
        String filename = file.getName();
        int lastDotIndex = filename.lastIndexOf(".");

        if (lastDotIndex == -1) {
            return "";
        } else {
            return filename.substring(lastDotIndex + 1);
        }
    }

    public static FileType getFileType(File file) {
        String extension = getExtension(file);

        if (extension.equals("i")) {
            return FileType.SOURCE;
        } else {
            return FileType.UNKNOWN;
        }
    }
}
