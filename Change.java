/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachinemvc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Kelsey
 */
public class Change {

    final private BigDecimal QUARTER_MONEY = new BigDecimal("0.25");
    final private BigDecimal DIME_MONEY = new BigDecimal("0.10");
    final private BigDecimal NICKEL_MONEY = new BigDecimal("0.05");
    final private BigDecimal PENNY_MONEY = new BigDecimal("0.01");
    final private BigDecimal DOLLAR_MONEY = new BigDecimal("1.00");
    private BigDecimal totalAmount;
    private int dollar;
    private int quarters;
    private int dimes;
    private int nickel;
    private int penny;

    public BigDecimal getChange(BigDecimal itemMoney) {
        this.dollar = 0;
        this.quarters = 0;
        this.dimes = 0;
        this.nickel = 0;
        this.penny = 0;
        this.totalAmount = itemMoney;
        dollar = totalAmount.setScale(0, RoundingMode.DOWN).intValue();
        BigDecimal change = itemMoney.subtract(new BigDecimal(String.valueOf(dollar)));

        change = change.multiply(new BigDecimal("100"));
        int changeInt = change.intValue();
        while (changeInt >= 100) {
            this.dollar++;
            changeInt = changeInt % 100;
        }

        while (changeInt >= 25) {
            this.quarters++;
            changeInt = changeInt - 25;
        }
        while (changeInt >= 10) {
            this.dimes++;
            changeInt = changeInt - 10;
        }
        while (changeInt >= 5) {
            this.nickel++;
            changeInt = changeInt - 5;
        }
        while (changeInt >= 1) {
            this.penny++;
            changeInt = changeInt - 1;
        }

        return null;

    }

    public int getQuarters() {
        return this.quarters;
    }

    public int getDimes() {
        return this.dimes;
    }

    public int getNickel() {
        return this.nickel;
    }

    public int getpenny() {
        return this.penny;
    }

    public int getDollar() {
        return this.dollar;
    }
}
