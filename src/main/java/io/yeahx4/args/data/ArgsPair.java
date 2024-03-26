package io.yeahx4.args.data;

import java.io.Serializable;

public record ArgsPair<K, V>(K key, V value) implements ArgsData, Serializable {

    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArgsPair<?, ?> pair) {
            return pair.key.equals(this.key) && pair.value.equals(this.value);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.key.toString() + " : " + this.value.toString();
    }
}
