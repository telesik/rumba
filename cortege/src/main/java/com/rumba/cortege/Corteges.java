/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

import java.util.Collections;

/**
 * Factory class
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public class Corteges {

    public static <T extends Cortege> CortegeHashSet<T> newCortegeHashSet(int deep) {
        return new CortegeHashSet<T>(deep);
    }

    public static <T extends Cortege> CortegeHashSet<T> newCortegeHashSet(T firstCortege, T... corteges) {
        CortegeHashSet<T> cortegeHashSet = new CortegeHashSet<T>(firstCortege.getDeep());
        cortegeHashSet.add(firstCortege);
        Collections.addAll(cortegeHashSet, corteges);
        return cortegeHashSet;
    }

    public static <T extends Cortege> CortegeLinkedList<T> newCortegeLinkedList(int deep) {
        return new CortegeLinkedList<T>(deep);
    }

    public static <T extends Cortege> CortegeLinkedList<T> newCortegeLinkedList(T firstCortege, T... corteges) {
        CortegeLinkedList<T> cortegeLinkedList = new CortegeLinkedList<T>(firstCortege.getDeep());
        cortegeLinkedList.add(firstCortege);
        Collections.addAll(cortegeLinkedList, corteges);
        return cortegeLinkedList;
    }

    public static interface Predicate<T extends Cortege> {
        boolean apply(T cortege);
    }

    public static abstract class Predicate2<T extends Cortege, K> implements Predicate<T> {
        protected K key;

        public void setKey(final K key) {
            this.key = key;
        }

        public K getKey() {
            return key;
        }
    }
}
