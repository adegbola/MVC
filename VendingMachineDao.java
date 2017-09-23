/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachinemvc.dao;

import VendingMachinemvc.model.Item;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kelsey
 */
public interface VendingMachineDao {

    public List<Item> getAllItems() throws VendingMachineException;;

    public Item getItemById(int itemId)throws VendingMachineException;
    void updateItem(String item);

}
