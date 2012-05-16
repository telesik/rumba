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
 * Test
 *
 * @author alexeyk (voilesik@gmail.com)
 */
public class Example {

    public static void main(String[] args) {
        CortegeSet<Cortege<Long, Cortege<String, Cortege.End>>> cortegeHashSet = Corteges.newCortegeHashSet(2);
        for (int i = 0; i < 5; i++) {
            Cortege<Long, Cortege<String, Cortege.End>> cortegeIS = CortegeQueue.create(2);
            cortegeIS.setValue((long) i).setValue("" + i);
            cortegeHashSet.add(cortegeIS);
        }
        for (Cortege cortege : cortegeHashSet) {
            System.out.println(cortege);
        }

        cortegeHashSet.add(CortegeQueue.<Long, Cortege<String, Cortege.End>>create(2));

        Cortege<Long, Cortege<String, Cortege.End>> cortegeIS = CortegeQueue.create(2);

        System.out.println(cortegeHashSet.contains(cortegeIS));
        cortegeIS.setValue(null).setValue("3");
        System.out.println(cortegeIS);
        System.out.println(cortegeHashSet.contains(cortegeIS));

        System.out.println(cortegeHashSet.contains(0, 3L));

        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS1 = CortegeQueue.create(3);
        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS2 = CortegeQueue.create(3);
        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS3 = CortegeQueue.create(3);
        CortegeQueue<String, CortegeQueue<Long, CortegeQueue<String, Cortege.End>>> cortegeSLS = CortegeQueue.create(3);

        cortegeLLS1.setValue(1L);
        cortegeLLS1.nextElement().setValue(11L);
        cortegeLLS1.nextElement().nextElement().setValue("111");
        cortegeLLS2.setValue(2L);
        cortegeLLS2.nextElement().nextElement().setValue("222");
        cortegeLLS3.setValue(3L);
        cortegeLLS3.nextElement().setValue(33L);
        cortegeLLS3.nextElement().nextElement().setValue("111");

        CortegeHashSet<Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>>> cortegeSet = Corteges.newCortegeHashSet(cortegeLLS1.getDeep());

        System.out.println(cortegeSet.contains(cortegeLLS1));
        cortegeSet.add(cortegeLLS1);
        cortegeSet.add(cortegeLLS2);
        System.out.println(cortegeSet.contains(cortegeLLS1));

        for (Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortege : cortegeSet) {
            System.out.println(cortege);
        }

        System.out.println(cortegeSet.contains(2, "111"));

//        Collection<Cortege> corteges = cortegeSet.extract(2, "111");
    }
}