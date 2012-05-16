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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CortegeQueueTest extends TestCase {

    @BeforeMethod
    public void before() {
    }

    @Test(expectedExceptions = IllegalArgumentException.class) // TODO: didn't catch exception :(
    public void testCreateWrongSize() throws Exception {
        try {
            Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeQueue.create(0);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    public void testCreate() throws Exception {
        Cortege<Long, Cortege<String, Cortege.End>> cortege = CortegeQueue.create(2);
    }

    @Test
    public void testSetGetValue() throws Exception {
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeQueue.create(2);
        cortegeLS.setValue(null);
        Assert.assertNull(cortegeLS.getValue());
        cortegeLS.setValue(4L);
        Assert.assertEquals(4L, cortegeLS.getValue().longValue());
        cortegeLS.nextElement().setValue(null);
        String value = cortegeLS.nextElement().getValue();
        Assert.assertNull(value);
        cortegeLS.nextElement().setValue("str");
        Assert.assertEquals("str", cortegeLS.nextElement().getValue());
        cortegeLS.setValue(1L).setValue("str2");
        Assert.assertEquals(1L, cortegeLS.getValue().longValue());
        Assert.assertEquals("str2", cortegeLS.nextElement().getValue());
        cortegeLS.setValues(1L, "str2");

        Long value1 = cortegeLS.getValue(1);
        Assert.assertEquals(cortegeLS.getValue(), value1);
        Assert.assertEquals(cortegeLS.nextElement().getValue(), cortegeLS.getValue(2));

        Cortege<String, Cortege.End> rightCortegeS = cortegeLS.nextElement();
    }

    @Test(expectedExceptions = IllegalStateException.class) // TODO: didn't catch exception :(
    public void testGetValueException() throws Exception {
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeQueue.create(2);
        try {
            Object value = cortegeLS.getValue(3);
            cortegeLS.nextElement().nextElement();
        } catch (IllegalStateException e) {
            return;
        }
        fail();
    }

    @Test
    public void testEquals() throws Exception {
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS1 = CortegeQueue.create(2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS2 = CortegeQueue.create(2);
        Assert.assertEquals(cortegeLS1, cortegeLS2);
        cortegeLS2.setValue(1L);
        Assert.assertNotEquals(cortegeLS1, cortegeLS2);
        cortegeLS1.setValue(1L);
        Assert.assertEquals(cortegeLS1, cortegeLS2);
        cortegeLS2.nextElement().setValue("str");
        Assert.assertNotEquals(cortegeLS1, cortegeLS2);
        cortegeLS1.nextElement().setValue("str");
        Assert.assertEquals(cortegeLS1, cortegeLS2);
        cortegeLS1.setValue(null);
        Assert.assertNotEquals(cortegeLS1, cortegeLS2);
        cortegeLS2.setValue(null);
        Assert.assertEquals(cortegeLS1, cortegeLS2);
    }

    @Test
    public void testHashCode() throws Exception {
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeQueue.create(2);
        int hashOfEmptyCortege = cortegeLS.hashCode();
        cortegeLS.setValue(1L);
        int hashOfCortege1 = cortegeLS.hashCode();
        Assert.assertNotEquals(hashOfEmptyCortege, hashOfCortege1);
        cortegeLS.setValue(null);
    }

    @Test
    public void testToString() throws Exception {
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS1 = CortegeQueue.create(2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS2 = CortegeQueue.create(2);
        Assert.assertEquals(cortegeLS1.toString(), cortegeLS2.toString());
        cortegeLS2.setValue(1L);
        Assert.assertNotEquals(cortegeLS1.toString(), cortegeLS2.toString());
        cortegeLS1.setValue(1L);
        Assert.assertEquals(cortegeLS1.toString(), cortegeLS2.toString());
        cortegeLS2.nextElement().setValue("str");
        Assert.assertNotEquals(cortegeLS1.toString(), cortegeLS2.toString());
        cortegeLS1.nextElement().setValue("str");
        Assert.assertEquals(cortegeLS1.toString(), cortegeLS2.toString());
        cortegeLS1.setValue(null);
        Assert.assertNotEquals(cortegeLS1.toString(), cortegeLS2.toString());
        cortegeLS2.setValue(null);
        Assert.assertEquals(cortegeLS1.toString(), cortegeLS2.toString());
    }

    @Test
    public void testGetDeep() throws Exception {
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS1 = CortegeQueue.create(2);
        Assert.assertEquals(cortegeLS1.getDeep(), 2);
        Assert.assertEquals(cortegeLS1.nextElement().getDeep(), 1);
    }
}
