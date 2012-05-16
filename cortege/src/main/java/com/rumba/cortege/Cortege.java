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
 * Interface cortege
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public interface Cortege<V, T extends Cortege> {
    T setValue(V value);

    T nextElement();

    <Vi> Vi getValue(int index) throws ClassCastException; // be careful with types. Type is not bounded

    int getDeep();

    <Vi> void setValue(int index, Vi value); // be careful with types. Type is not bounded

    Cortege<V, T> setValues(Object... values); // be careful with types. Type is not bounded!!!

    V getValue();

    static abstract class End<V, T extends Cortege<V, T>> implements Cortege<V, T> {
    }
}
