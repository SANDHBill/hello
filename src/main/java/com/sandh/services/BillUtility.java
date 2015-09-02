package com.sandh.services;

import com.sandh.domain.Bill;
import com.sandh.domain.Item;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 13/08/15.
 */
public class BillUtility {

    public static final String NOT_OWNED = "Not owned";

    public void claimItem(Bill bill,UUID id, String ownerName) {
        for (Item item : bill.getItems())
        {
            if ( id.equals(item.getId())) {
                item.addOwner(ownerName);
                return;
            }
        }
    }

    public void claimItemMultiple(Bill bill, UUID id, String... owners) {
        int n = owners.length;
        if ( n < 1) return;
        for(Item item : bill.getItems())
            if (item.getId().equals(id)) {
                for (String owner : owners) item.addOwner(owner);
                return;
            }
    }
    public double calculateTotal(Bill bill) {
        if(bill.getNumberItems() == 0) return 0;
        Item[] items = bill.getItems();
        List itemList = Arrays.asList(items);
        double sum =
        itemList.stream().mapToDouble(x -> ((Item) x).getPrice()).sum();
        return sum;
    }

    public Map calculateTotalPerPerson(Bill bill) {
        Item[] items = bill.getItems();
        List<Item> itemList = Arrays.asList(items);
        Map<String, Double> x = itemList.stream()
                .flatMap(a -> a.getOwners().stream()
                                .map(n -> new AbstractMap.SimpleEntry<>(n, a.getPrice() / a.getOwners().size()))
                ).collect(Collectors.groupingBy(
                        Map.Entry::getKey, Collectors.summingDouble(Map.Entry::getValue)
                ));

        double sumPaid = x.values().stream().mapToDouble(Double::doubleValue).sum();
        x.putIfAbsent(NOT_OWNED, calculateTotal(bill) - sumPaid);
        return x;
        //test pullrequest
    }
}







