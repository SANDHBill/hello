package com.sandh.services;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by user on 13/08/15.
 */
public class BillUtilityTest {
    public static final String SHOULD_PAY = " should pay ";
    private final BillTestScenario billTestScenario = new BillTestScenario();
    private BillUtility billUtility = new BillUtility();

    @Test
    public void calculateTotalTest() {
        BillTestScenario.BillScenario billScenario = billTestScenario.simpleBill();
        double total = billUtility.calculateTotal(billScenario.getBill());
        Assert.assertEquals("Total should be ",26,total,0.00001);
    }

    private void runAsserts(BillTestScenario.BillScenario billScenario)
    {
        Map participants = billUtility.calculateTotalPerPerson(billScenario.getBill());
        for (Map.Entry<String, Double> assertion : billScenario.getAsserts().entrySet())
        {
            String name = assertion.getKey();
            Object share = participants.get(name);
            Assert.assertNotNull(name + " is missing",share);
            Assert.assertTrue(name + SHOULD_PAY, (double) share == assertion.getValue());
        }
    }

    @Test
    public void splitBill(){
        BillTestScenario.BillScenario billScenario = billTestScenario.simpleBill();
        runAsserts(billScenario);
    }

    @Test
    public void splitSharedBill(){
        BillTestScenario.BillScenario billScenario = billTestScenario.simpleBillShared();
        runAsserts(billScenario);
    }

    @Test
    public void ownedByHamedLaterSharedByShahram(){
        BillTestScenario.BillScenario billScenario = billTestScenario.ownedByHamedLaterSharedByShahram();
        runAsserts(billScenario);
    }

    @Test
      public void sameItemnameOwnedbyDifferentGroups(){
        BillTestScenario.BillScenario billScenario = billTestScenario.sameItemnameOwnedbyDifferentGroups();
        runAsserts(billScenario);
    }
}

