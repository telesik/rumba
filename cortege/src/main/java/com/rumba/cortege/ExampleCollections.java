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
public class ExampleCollections {

    public static void main(String[] args) {
        // Создание экземпляра CortegeHashSet
        CortegeSet<Cortege<Long, Cortege<String, Cortege.End>>> cortegeHashSetLS = Corteges.newCortegeHashSet(2);
        for (long i = 0; i < 5; i++) {
            Cortege<Long, Cortege<String, Cortege.End>> cortegeLS = CortegeQueue.create(2);
            cortegeLS.setValue(i).setValue("" + i);
            cortegeHashSetLS.add(cortegeLS);
        }
        for (Cortege cortege : cortegeHashSetLS) {
            System.out.println(cortege);
        }

        cortegeHashSetLS.add(CortegeQueue.<Long, Cortege<String, Cortege.End>>create(2));

        Cortege<Long, Cortege<String, Cortege.End>> cortegeIS = CortegeQueue.create(2);

        System.out.println(cortegeHashSetLS.contains(cortegeIS));
        cortegeIS.setValue(null).setValue("3");
        System.out.println(cortegeIS);
        System.out.println(cortegeHashSetLS.contains(cortegeIS));

        System.out.println(cortegeHashSetLS.contains(1, 3L));

        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS1 = CortegeQueue.create(3);
        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS2 = CortegeQueue.create(3);
        Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortegeLLS3 = CortegeQueue.create(3);
        CortegeQueue<String, CortegeQueue<Long, CortegeQueue<String, Cortege.End>>> cortegeSLS = CortegeQueue.create(3);

        cortegeLLS1.setValue(1L);
        cortegeLLS1.nextElement().setValue(11L);
        cortegeLLS1.nextElement().nextElement().setValue("AAA");
        cortegeLLS2.setValue(2L);
        cortegeLLS2.nextElement().nextElement().setValue("BBB");
        cortegeLLS3.setValue(3L);
        cortegeLLS3.nextElement().setValue(33L);
        cortegeLLS3.nextElement().nextElement().setValue("AAA");

        CortegeHashSet<Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>>> cortegeSetLLS = Corteges.newCortegeHashSet(cortegeLLS1.getDeep());

        System.out.println(cortegeSetLLS.contains(cortegeLLS1));
        cortegeSetLLS.add(cortegeLLS1);
        cortegeSetLLS.add(cortegeLLS2);
        cortegeSetLLS.add(cortegeLLS3);
        System.out.println(cortegeSetLLS.contains(cortegeLLS1));

        for (Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortege : cortegeSetLLS) {
            System.out.println(cortege);
        }

        System.out.println(cortegeSetLLS.contains(3, "AAA"));

        cortegeSetLLS.fill(1, 5L);
        cortegeSetLLS.fill(2, 8L);
        cortegeSetLLS.fill(3, "XXX");

        for (Cortege<Long, Cortege<Long, Cortege<String, Cortege.End>>> cortege : cortegeSetLLS) {
            System.out.println(cortege);
        }

//        Collection<Cortege> corteges = cortegeSetLLS.extract(2, "111");
    }
}