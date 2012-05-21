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

public class CortegeLinkedListTest extends TestCase {

    @Test
    public void testAdd() throws Exception {
        CortegeLinkedList<Cortege<Long, Cortege<String, Cortege.End>>> cortegeLinkedList = Corteges.newCortegeLinkedList(2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeChain.create(2);
        cortegeLinkedList.add(cortegeLS);
        cortegeLinkedList.add(cortegeLS);
        cortegeLinkedList.add(cortegeLS);
        Assert.assertEquals(cortegeLinkedList.size(), 3);
        for (Cortege<Long, Cortege<String, Cortege.End>> cortege : cortegeLinkedList) {
            System.out.println(cortege);
        }
    }

    @Test
    public void testAddEqualObjects() throws Exception {
        CortegeLinkedList<Cortege<Long, Cortege<String, Cortege.End>>> cortegeLinkedList = Corteges.newCortegeLinkedList(2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS1 = CortegeChain.create(2);
        cortegeLinkedList.add(cortegeLS1);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS2 = CortegeChain.create(2);
        cortegeLinkedList.add(cortegeLS2);
        Cortege<Long, Cortege<String, Cortege.End>> cortegeLS3 = CortegeChain.create(2);
        cortegeLinkedList.add(cortegeLS3);
        Assert.assertEquals(cortegeLinkedList.size(), 3);
        for (Cortege<Long, Cortege<String, Cortege.End>> cortege : cortegeLinkedList) {
            System.out.println(cortege);
        }
    }

    @Test
    public void testContains() throws Exception {

    }

}
