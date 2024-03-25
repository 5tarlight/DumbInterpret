package io.yeahx4.args;

import java.io.Serializable;

final public class ArgsValue<T> implements ArgsData, Serializable {
    public final T value;

    public ArgsValue(T value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArgsValue<?>) {
            return ((ArgsValue<?>) obj).value.equals(this.value);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
