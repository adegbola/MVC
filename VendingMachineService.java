/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachinemvc.service;

import VendingMachinemvc.dao.VendingMachineException;
import VendingMachinemvc.model.Change;
import VendingMachinemvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Kelsey
 */
public interface VendingMachineService {

    List<Item> getAllItems() throws VendingMachineException;

    Item getItem(int id) throws VendingMachineException;

    void updateItem(String itemName) throws VendingMachineException;

    //BigDecimal getChange(String itemName, String deposit) throws VendingMachineException;

    BigDecimal validate(BigDecimal itemCost, BigDecimal money) throws VendingMachineInsufficientFundException;

    void checkInventory(Item item) throws NoItemInventoryException;

    public int getQuarters();

    public int getDimes();

    public int getNickels();

    public int getPennies();
    public int getDollar();

    public BigDecimal getChange(BigDecimal change);


}
