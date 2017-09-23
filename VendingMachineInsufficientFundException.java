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
public class VendingMachineInsufficientFundException extends Exception {
     public VendingMachineInsufficientFundException(String message) {
        super(message);
    }

    public VendingMachineInsufficientFundException(String message,
            Throwable cause) {
        super(message, cause);
    }
    @Override
    public String toString()
    {
        return "Insufficient fund";
    }
    
}
