/**
 * Copyright (c) 2012 Kiselyov A.V., All Rights Reserved
 *
 * For information about the licensing and copyright of this document please
 * contact Alexey Kiselyov. at voilesik@gmail.com
 *
 * @Project: Cortege
 */

package com.rumba.cortege;

import junit.framework.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class CortegeHashSetTest extends TestCase {

    @Test
    public void testAddOneInstance() throws Exception {
        CortegeHashSet<Cortege<Long, Cortege<String, Cortege.End>>> cortegeHashSet = Corteges.newCortegeHashSet(2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeChain.create(2);
        cortegeHashSet.add(cortegeLS);
        cortegeHashSet.add(cortegeLS);
        cortegeHashSet.add(cortegeLS);
        Assert.assertEquals(cortegeHashSet.size(), 1);
        for (Cortege<Long, Cortege<String, Cortege.End>> cortege : cortegeHashSet) {
            System.out.println(cortege);
        }
    }

    @Test
    public void testAddEqualObjects() throws Exception {
        CortegeHashSet<Cortege<Long, Cortege<String, Cortege.End>>> cortegeHashSet = Corteges.newCortegeHashSet(2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS1 = CortegeChain.create(2);
        cortegeHashSet.add(cortegeLS1);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS2 = CortegeChain.create(2);
        cortegeHashSet.add(cortegeLS2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS3 = CortegeChain.create(2);
        cortegeHashSet.add(cortegeLS3);
        Assert.assertEquals(cortegeHashSet.size(), 1);
        for (Cortege<Long, Cortege<String, Cortege.End>> cortege : cortegeHashSet) {
            System.out.println(cortege);
        }

        Set<X> mySet = new HashSet<X>();
        X x1 = new X(1);
        mySet.add(x1);
        X x2 = new X(2);
        mySet.add(x2);
        X x3 = new X(3);
        mySet.add(x3);
        for (X x : mySet) {
            System.out.println(x);
        }
        System.out.println("---");
        x2.setI(1);
        for (X x : mySet) {
            System.out.println(x);
        }
        mySet = new HashSet(mySet);
        mySet.remove(new X(3));
        System.out.println("---");
        for (X x : mySet) {
            System.out.println(x);
        }
    }

    @Test
    public void testContains() throws Exception {

    }

    static class X {
        private int i;

        X(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof X)) return false;

            X x = (X) o;

            if (i != x.i) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return i;
        }

        @Override
        public String toString() {
            return "X{" +
                    "i=" + i +
                    '}';
        }
    }
}
