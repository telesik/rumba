/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * User: kiselyov
 * Date: 10.04.12
 */
public interface CortegeCollection<T extends Cortege> extends Collection<T>, Iterable<T> {
    <T> boolean contains(int num, T obj);

    CortegeCollection<T> extract(int num, Object key);

    CortegeCollection<T> extract(Corteges.Predicate<T> predicate);

    //    <C> CortegeCollection<T> view(int num, Corteges.Predicate<C> predicate);
    T findAny(int num, Object key);

    T findAny(Corteges.Predicate<T> predicate);

    <Vi> List<Vi> getColumnCopy(int num);

    <Vi> void fill(int num, Vi value); // be careful with types. Type is not bounded

    class LinkedHoldersList extends LinkedList<CortegeChain.ValueHolder> {
        @Override
        public int indexOf(Object o) {
            int index = 0;
            Iterator<CortegeChain.ValueHolder> iterator = iterator();
            while (iterator.hasNext()){
                CortegeChain.ValueHolder next = iterator.next();
                if(next.same(o)) return index;
            }
            return -1;
        }
    }
}
