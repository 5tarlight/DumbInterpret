package io.yeahx4.args;

import java.io.Serializable;

public final class ArgsPair<K, V> implements ArgsData, Serializable {
    public final K key;
    public final V value;

    public ArgsPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

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
