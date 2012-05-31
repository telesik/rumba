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
public class CortegeChain<V, T extends Cortege> extends AbstractCortege<V, T> {

    private Cortege nextValue;

    private CortegeChain() {
    }

    @Override
    public T setValue(V value) {
        this.valueHolder.setValue(value);
        return (T) this.nextValue;
    }

    @Override
    public <Vi> void setValue(int num, Vi value) {
        if (num < 1 || num > deep) throw new IllegalArgumentException("num should be between 1 and " + deep);
        Cortege next = this;
        for (int i = 1; i < num; i++) {
            next = next.nextElement();
        }
        next.setValue(value);
    }

    @Override
    public Object[] getValues() {
        Object[] values = new Object[deep];
        Cortege base = this;
        values[0] = base.getValueHolder().getValue();
        for (int i = 1; i < deep; i++) {
            base = base.nextElement();
            values[i] = base.getValueHolder().getValue();
        }
        return values;
    }

    @Override
    public Cortege<V, T> setValues(Object... values) {
        if (values.length > deep) throw new IllegalArgumentException("count of values should not be greater then " + deep);
        Cortege next = this;
        for (Object value : values) {
            next = next.setValue(value);
        }
        return this;
    }

    @Override
    public V getValue() {
        return valueHolder.getValue();
    }

    public T nextElement() {
        if (deep < 2) throw new IllegalStateException("the end of the cortege has been reached");
        return (T) nextValue;
    }

    public static <VV, TT extends Cortege> CortegeChain<VV, TT> create(int deep) throws IllegalArgumentException {
        if (deep < 1) throw new IllegalArgumentException("deep should be a positive integer");
        CortegeChain<VV, TT> cortegeChain = new CortegeChain<VV, TT>();
        cortegeChain.deep = deep--;
        if (deep > 0) {
            cortegeChain.nextValue = create(deep);
        }
        return cortegeChain;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Vi> Vi getValue(int num) throws ClassCastException {
        if (num < 1) throw new IllegalArgumentException("deep should be a positive integer");
        Cortege next = this;
        for (int i = 1; i < num; i++) {
            next = next.nextElement();
        }
        return (Vi) next.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CortegeChain)) return false;

        CortegeChain that = (CortegeChain) o;

        if (deep != that.deep) return false;
        if (nextValue != null ? !nextValue.equals(that.nextValue) : that.nextValue != null) return false;
        return valueHolder.equals(that.valueHolder);

    }

    @Override
    public int hashCode() {
        int result = valueHolder.hashCode();
        result = 31 * result + (nextValue != null ? nextValue.hashCode() : 0);
        result = 31 * result + deep;
        return result;
    }

    @Override
    public String toString() {
        return "CortegeChain{" +
                "value=" + valueHolder.getValue() + "(" + valueHolder.getValueClass() + ")" +
                ", nextValue=" + nextValue +
                '}';
    }

}
