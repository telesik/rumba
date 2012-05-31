/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of CortegeSet
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public class CortegeHashSet<T extends Cortege> extends HashSet<T> implements CortegeSet<T> {

    private List<T> rows;
    private LinkedHoldersList[] columns;

    CortegeHashSet(int deep) {
        rows = new LinkedList<T>();
        columns = new LinkedHoldersList[deep];
        for (int index = 0; index < deep; index++) {
            columns[index] = new LinkedHoldersList();
        }
    }

    @Override
    public <T> boolean contains(int num, T obj) {
        return columns[num - 1].contains(obj);
    }

    @Override
    public CortegeSet<T> extract(final int num, final Object key) {
        return extract(new Corteges.Predicate<T>() {
            @Override
            public boolean apply(T cortege) {
                return cortege.getValueHolder(num).same(key);
            }
        });
    }

    @Override
    public CortegeSet<T> extract(Corteges.Predicate<T> predicate) {
        CortegeSet<T> foundCorteges = Corteges.newCortegeHashSet(columns.length);
        if (rows.isEmpty()) return foundCorteges;
        for (T row : rows) {
            if (predicate.apply(row))
                foundCorteges.add(row);
        }
        return foundCorteges;
    }

    @Override
    public T findAny(int num, Object key) {
        return findFirstByColumn(key, columns[num - 1]);
    }

    @Override
    public T findAny(Corteges.Predicate<T> predicate) {
        for (T row : rows) {
            if (predicate.apply(row)) return row;
        }
        return null;
    }

    @Override
    public <Vi> List<Vi> getColumnCopy(int num) {
        LinkedList<Vi> columnCopy = new LinkedList<Vi>();
        for (AbstractCortege.ValueHolder<Vi> valueHolder : columns[num - 1]) {
            columnCopy.add(valueHolder.value);
        }
        return columnCopy;
    }

    @Override
    public <Vi> void fill(int num, Vi value) {
        for (CortegeChain.ValueHolder valueHolder : columns[num - 1]) {
            valueHolder.setValue(value);
        }
    }

    @Override
    public boolean add(T cortege) {
        if (cortege == null) throw new IllegalArgumentException("null is not allowed here. Use empty cortege instead");
        boolean isAdded = super.add(cortege);
        if (isAdded) {
            rows.add(cortege);
            Cortege base = cortege;
            columns[0].add(base.getValueHolder());
            for (int columnId = 1; columnId < columns.length; columnId++) {
                base = base.nextElement();
                columns[columnId].add(base.getValueHolder());
            }
        }
        return isAdded;
    }

    @Override
    public boolean remove(Object cortege) {
        int index = rows.indexOf(cortege);
        if (index != -1) {
            remove(index);
            super.remove(cortege);
            rows.remove(cortege);
        }
        return index != -1;
    }

    private void remove(int index) {
        rows.remove(index);
        for (LinkedHoldersList column : columns) {
            column.remove(index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<T> iterator = rows.iterator();
            int currentIndex;

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                currentIndex++;
                return iterator.next();
            }

            @Override
            public void remove() {
                iterator.remove();
                CortegeHashSet.this.remove(currentIndex);
            }
        };
    }

    private T findFirstByColumn(Object key, LinkedHoldersList column) {
        int index = column.indexOf(key);
        return index == -1 ? null : rows.get(index);
    }
}
