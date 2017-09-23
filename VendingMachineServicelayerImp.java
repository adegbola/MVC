/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachinemvc.service;

import VendingMachinemvc.dao.VendingMachineDao;
import VendingMachinemvc.dao.VendingMachineException;
import VendingMachinemvc.model.Change;
import VendingMachinemvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Kelsey
 */
public class VendingMachineServicelayerImp implements VendingMachineService {

    VendingMachineDao dao;
    Change money = new Change();

    @Inject
    public VendingMachineServicelayerImp(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(int id) throws VendingMachineException {
        return dao.getItemById(id);

    }

    @Override
    public void updateItem(String itemName) throws VendingMachineException {
        dao.updateItem(itemName);
    }

    @Override
    public BigDecimal validate(BigDecimal itemCost, BigDecimal money) throws VendingMachineInsufficientFundException {
        if (money.compareTo(itemCost) < 0) {
            throw new VendingMachineInsufficientFundException("Error: Insufficient money");
        }
        BigDecimal diff = money.subtract(itemCost);
        return diff;
    }

    @Override
    public void checkInventory(Item item) throws NoItemInventoryException {
        int Store = item.getNumOfItems();
        if (Store == 0) {
            throw new NoItemInventoryException();
        }

    }

    @Override
    public int getQuarters() {
        return money.getQuarters();
    }

    @Override
    public int getDimes() {
        return money.getDimes();
    }

    @Override
    public int getNickels() {
        return money.getNickel();
    }

    @Override
    public int getPennies() {
        return money.getpenny();
    }
    @Override
    public int getDollar(){
        return money.getDollar();
    }

    @Override
    public BigDecimal getChange(BigDecimal change) {
       // Change change = new Change(money);
        return money.getChange(change);
    }

    

}
