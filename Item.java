/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachinemvc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Kelsey
 */
public class Item {
    private int itemId;
    private String itemName;
    private int NumOfItems;
    private BigDecimal itemPrice;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getNumOfItems() {
        return NumOfItems;
    }

    public void setNumOfItems(int NumOfItems) {
        this.NumOfItems = NumOfItems;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
    
     public void updateItem(String itemname) {
        NumOfItems--;
    }
     
     @Override
    public boolean equals(Object object){
        return this.itemName.equals(((Item)object).getItemName());
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (int) (this.itemId ^ (this.itemId >>> 32));
        hash = 29 * hash + Objects.hashCode(this.itemName);
        hash = 29 * hash + this.NumOfItems;
        hash = 29 * hash + Objects.hashCode(this.itemPrice);
        return hash;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Item other = (Item) obj;
//        if (this.itemId != other.itemId) {
//            return false;
//        }
//        if (this.NumOfItems != other.NumOfItems) {
//            return false;
//        }
//        if (!Objects.equals(this.itemName, other.itemName)) {
//            return false;
//        }
//        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
//            return false;
//        }
//        return true;
//    }
@Override
    public String toString(){
    return"BigDecimal.v" + itemPrice +"|Num: " + NumOfItems + " " + "|Name: " + itemName;
    }
    
    
}
