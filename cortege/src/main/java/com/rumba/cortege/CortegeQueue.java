/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

/**
 * Container that should be used for associated entities
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public class CortegeQueue<V, T extends Cortege> implements Cortege<V, T> {
    private V value;
    private Cortege nextValue;
    private int deep;

    private CortegeQueue() {
    }

    @Override
    public T setValue(V value) {
        this.value = value;
        return (T) this.nextValue;
    }

    @Override
    public <Vi> void setValue(int deep, Vi value) {
        if (deep < 1) throw new IllegalArgumentException("deep should be a positive integer");
        Cortege next = this;
        for (int i = 1; i < deep; i++) {
            next = next.nextElement();
        }
        next.setValue(value);

    }

    @Override
    public Cortege<V, T> setValues(Object... values) {
        Cortege next = this;
        for (Object value : values) {
            next = next.setValue(value);
        }
        return this;
    }

    @Override
    public V getValue() {
        return value;
    }

    public T nextElement() {
        if (deep < 2) throw new IllegalStateException("the end of the cortege has been reached");
        return (T) nextValue;
    }

    public static <VV, TT extends Cortege> CortegeQueue<VV, TT> create(int deep) throws IllegalArgumentException {
        if (deep < 1) throw new IllegalArgumentException("deep should be a positive integer");
        CortegeQueue<VV, TT> cortegeQueue = new CortegeQueue<VV, TT>();
        cortegeQueue.deep = deep--;
        if (deep > 0) {
            cortegeQueue.nextValue = create(deep);
        }
        return cortegeQueue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Vi> Vi getValue(int index) throws ClassCastException {
        if (index < 1) throw new IllegalArgumentException("deep should be a positive integer");
        Cortege next = this;
        for (int i = 1; i < index; i++) {
            next = next.nextElement();
        }
        return (Vi) next.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CortegeQueue)) return false;

        CortegeQueue that = (CortegeQueue) o;

        if (deep != that.deep) return false;
        if (nextValue != null ? !nextValue.equals(that.nextValue) : that.nextValue != null) return false;
        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (nextValue != null ? nextValue.hashCode() : 0);
        result = 31 * result + deep;
        return result;
    }

    @Override
    public String toString() {
        return "CortegeQueue{" +
                "value=" + value + "(" + (value != null ? value.getClass() : "") + ")" +
                ", nextValue=" + nextValue +
                '}';
    }

    public int getDeep() {
        return deep;
    }
}
