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

    public void claimItemMultiple(Bill bill, String itemName, String... owners) {
        Boolean itemFound = false;
        int n = owners.length;
        if ( n < 1) return;
        for(Item item : bill.getItems())
        {
            if (itemName.equals(item.getName()))
            {
                itemFound = true;
                if (item.getOwners().isEmpty()) {
                    double price = item.getPrice() / n;
                    bill.deleteItem(item);
                    for (int i = 0; i < n; i++) {
                        bill.addItem(new Item(itemName + "_" + String.valueOf(i + 1), price, owners[i]));
                    }
                    return;
                }
            }
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


        List<Item> items2 = new ArrayList<Item>();

        for (int i = 0; i < itemList.size(); i++) {
            List<String> owners = itemList.get(i).getOwners();
            double price = itemList.get(i).getPrice() / owners.size();
            String itemname = itemList.get(i).getName();
            for (String owner : owners) {
                Item item = new Item(itemname,price,owner);
                items2.add(item);
            }
        }

        Map x = items2.stream()
                .collect(Collectors.groupingBy(item -> item.getOwners().isEmpty() ? NOT_OWNED : item.getOwners().get(0),
                        Collectors.summingDouble(item -> item.getPrice())));
        x.putIfAbsent(NOT_OWNED, 0.0);

        return x;
        //test pullrequest
    }
}







