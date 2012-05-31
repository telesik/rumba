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
import java.util.List;
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
        mySet = new HashSet<X>(mySet);
        mySet.remove(new X(3));
        System.out.println("---");
        for (X x : mySet) {
            System.out.println(x);
        }
    }

    @Test
    public void testRowColumnModification() throws Exception {
        CortegeHashSet<Cortege<Long, Cortege<String, Cortege.End>>> cortegeHashSet = Corteges.newCortegeHashSet(2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS0 = CortegeChain.create(2);
        cortegeLS0.setValue(1L).setValue("first");
        cortegeHashSet.add(cortegeLS0);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS1 = CortegeChain.create(2);
        cortegeLS1.setValue(2L).setValue("second");
        cortegeHashSet.add(cortegeLS1);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS2 = CortegeChain.create(2);
        cortegeLS2.setValue(3L).setValue("third");
        cortegeHashSet.add(cortegeLS2);
        for (Cortege<Long, Cortege<String, Cortege.End>> cortege : cortegeHashSet) {
            System.out.println(cortege);
        }
        cortegeLS0.setValue(10L).setValue("new first");
        cortegeLS1.setValue(20L);
        cortegeLS2.setValue(30L);
        List<Long> column1 = cortegeHashSet.getColumnCopy(1);
        System.out.println(column1);
        Assert.assertEquals(column1.get(0), cortegeLS0.getValue());
        Assert.assertEquals(column1.get(1), cortegeLS1.getValue());
        Assert.assertEquals(column1.get(2), cortegeLS2.getValue());
        List<String> column2 = cortegeHashSet.getColumnCopy(2);
        Assert.assertEquals(column2.get(0), cortegeLS0.getValue(2));
        Assert.assertEquals(column2.get(1), cortegeLS1.getValue(2));
        Assert.assertEquals(column2.get(2), cortegeLS2.getValue(2));

        System.out.println(column2);
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
