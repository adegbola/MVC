/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachinemvc.dao;

import VendingMachinemvc.model.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Kelsey
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {

    //Item itemList = new Item();

    private Map<Long, Item> itemMap = new HashMap<>();
    public static final String VENDINGMACHINE_FILE = "VendingMachineItems.txt";
    public static final String DELIMITER = "::";
    List<Item> items = new ArrayList<>();

   
    private void loadVendingMachine() throws VendingMachineException {

        items.clear();
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(getClass().getClassLoader().getResource(VENDINGMACHINE_FILE).getFile())));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new VendingMachineException(
                    "-_- Could not load roster data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            if (currentTokens.length < 2) {
                continue;
            }

            Item currentItem = new Item();

            int itemId = Integer.parseInt(currentTokens[0]);
            currentItem.setItemId(itemId);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(new BigDecimal(currentTokens[2]));
            int numAvailable = Integer.parseInt(currentTokens[3]);
            currentItem.setNumOfItems(numAvailable);
            itemMap.put(currentItem.getItemId(), currentItem);
            items.add(currentItem);
        }
        // close scanner
        scanner.close();

    }

    private void writeInventoryFile() throws VendingMachineException {
        PrintWriter out = null;
  

        try {
            out = new PrintWriter(new FileWriter(getClass().getClassLoader().getResource(VENDINGMACHINE_FILE).getFile()));
        } catch (IOException e) {
            
        }
        
        for (Item currentItem : items) {
            // write the item object to the file
            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getItemPrice() + DELIMITER
                    + currentItem.getNumOfItems()
            );

            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineException {
        loadVendingMachine();

        return items;
    }

    @Override
    public Item getItemById(int itemId) throws VendingMachineException {
        loadVendingMachine();
        return itemMap.get((long) itemId);
    }

    @Override
    public void updateItem(String itemName) {
        try {
             int sc=Integer.parseInt(itemName);
            Item item = getItemById(sc);            
            item.setNumOfItems(item.getNumOfItems() - 1);
            items.set(items.indexOf(item), item);
            try {
                writeInventoryFile();
            } catch (VendingMachineException ex) {
                System.out.println("Error");
            }

        } catch (VendingMachineException ex) {

        }

    }

}
