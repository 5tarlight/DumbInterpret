package io.yeahx4.args;

import java.io.IOException;

public class TargetFileIoException extends RuntimeException {
    public TargetFileIoException(IOException io) {
        super("Could not read target file: " + io.getMessage());
    }
}
