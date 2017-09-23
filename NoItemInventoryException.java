/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachinemvc.service;

/**
 *
 * @author Kelsey
 */
public class NoItemInventoryException extends Exception {
    public NoItemInventoryException() {
        super();
    }

    /*public NoItemInventoryException(String message,Throwable cause) {
        super(message, cause);
    }

   /** NoItemInventoryException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    @Override
    public String toString()
    {
        return "No item in Inventory";
    }

}
