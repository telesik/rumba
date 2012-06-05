/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

import java.io.Serializable;

/**
 * Abstract cortege
 *
 * @author alexeyk (voilesik@gmail.com)
 */
abstract class AbstractCortege<V, T extends Cortege> implements Cortege<V, T>, Serializable {
    protected ValueHolder<V> valueHolder = new ValueHolder<V>(null);
    protected int deep;

    @Override
    public ValueHolder getValueHolder() {
        return valueHolder;
    }

    @Override
    public ValueHolder getValueHolder(int num) {
        if (num < 1 || num > deep) throw new IllegalArgumentException("num should be between 1 and " + deep);
        Cortege base = this;
        for (int n = 1; n < num; n++) base = base.nextElement();
        return base.getValueHolder();
    }

    public int getDeep() {
        return deep;
    }

    protected static class ValueHolder<V> implements Serializable {
        V value;

        ValueHolder(V value) {
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ValueHolder)) return false;
            ValueHolder<V> that = (ValueHolder<V>) o;
            return same(that.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        public String getValueClass() {
            return value == null ? null : value.getClass().toString();
        }

        public boolean same(Object sample) {
            return sample == value || (value != null && value.equals(sample));
        }
    }
}
