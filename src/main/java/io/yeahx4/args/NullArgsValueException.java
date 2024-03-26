package io.yeahx4.args;

public final class NullArgsValueException extends RuntimeException {
    public NullArgsValueException(String key) {
        super("The value of the key " + key + " is null.");
    }
}
