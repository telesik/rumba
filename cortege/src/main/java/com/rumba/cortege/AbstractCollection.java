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
 * AbstractCollection cortege
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public abstract class AbstractCollection<T extends Cortege> implements CortegeCollection<T> {

    private LinkedHoldersList[] columns;

    public <Vi> void fill(int num, Vi value) {
        for (CortegeChain.ValueHolder valueHolder : columns[num - 1]) {
            valueHolder.setValue(value);
        }
    }
}
