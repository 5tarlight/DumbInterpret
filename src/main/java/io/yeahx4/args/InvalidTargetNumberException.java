package io.yeahx4.args;

public class InvalidTargetNumberException extends RuntimeException {
    public InvalidTargetNumberException(boolean isEmpty) {
        super(isEmpty ? "Cannot find target" : "Target must be unique");
    }
}
