package io.yeahx4.args;

public final class NullArgsException extends RuntimeException {
    public NullArgsException(String key) {
        super("The value of the key " + key + " is null.");
    }
}
