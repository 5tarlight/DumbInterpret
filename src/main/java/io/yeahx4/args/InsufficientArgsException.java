package io.yeahx4.args;

public final class InsufficientArgsException extends RuntimeException {
    public InsufficientArgsException() {
        super("Insufficient arguments provided.");
    }
}
