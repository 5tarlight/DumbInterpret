package io.yeahx4.args.data;

import java.io.Serializable;

public record ArgsValue<T>(T value) implements ArgsData, Serializable {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArgsValue<?> value) {
            return value.value.equals(this.value);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
