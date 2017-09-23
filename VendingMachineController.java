/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinemvc;

import VendingMachinemvc.dao.VendingMachineException;

import VendingMachinemvc.model.Item;
import VendingMachinemvc.service.VendingMachineService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import VendingMachinemvc.service.VendingMachineInsufficientFundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Kelsey
 */
@Controller
public class VendingMachineController {

    VendingMachineService Vending;
    String selectItem = "";
    BigDecimal totalAmount = new BigDecimal("0.00");

    String message;
    int dollar;
    int Quarter;
    int dime;
    int nickel;
    int penny;
    int itemId;

    @Inject
    public VendingMachineController(VendingMachineService Vending) {

        this.Vending = Vending;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayItemsPage(Model model) throws VendingMachineException {
        List<Item> items;
        items = Vending.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("total", totalAmount);
        model.addAttribute("Message", message);
        model.addAttribute("q", Quarter);
        model.addAttribute("d", dime);
        model.addAttribute("n", nickel);
        model.addAttribute("p", penny);
        model.addAttribute("itemId", itemId);
        return "index";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String getItem(HttpServletRequest request, Model model) {
        itemId = Integer.parseInt(request.getParameter("itemId"));
        return "redirect:/";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request, Model model) {
        if (request.getParameter("dollarAmount") != null) {
            BigDecimal addDollar = new BigDecimal(request.getParameter("dollarAmount"));
            totalAmount = totalAmount.add(addDollar).setScale(4, RoundingMode.HALF_UP);
        } else if (request.getParameter("quarterAmount") != null) {
            BigDecimal addQuarter = new BigDecimal(request.getParameter("quarterAmount"));
            totalAmount = totalAmount.add(addQuarter).setScale(4, RoundingMode.HALF_UP);
        } else if (request.getParameter("dimeAmount") != null) {
            BigDecimal addDime = new BigDecimal(request.getParameter("dimeAmount"));
            totalAmount = totalAmount.add(addDime).setScale(4, RoundingMode.HALF_UP);
        } else if (request.getParameter("nickelAmount") != null) {
            BigDecimal addNickel = new BigDecimal(request.getParameter("nickelAmount"));
            totalAmount = totalAmount.add(addNickel).setScale(4, RoundingMode.HALF_UP);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/getChange", method = RequestMethod.POST)
    public String getChange(HttpServletRequest request, Model model) {
        BigDecimal change = Vending.getChange(totalAmount);
        totalAmount = new BigDecimal("0.00");
        message = "Here is your change";

        model.addAttribute("message", message);
        model.addAttribute("message", dollar);
        model.addAttribute("Quarters", Quarter);
        model.addAttribute("Nickel", nickel);
        model.addAttribute("Dimes", dime);
        model.addAttribute("penny", penny);
        itemId = 0;
        change = new BigDecimal("0.00");

        return "redirect:/";
    }

    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String puchaseItem(HttpServletRequest request, Model model) throws VendingMachineException, VendingMachineInsufficientFundException {

        BigDecimal remainingMoney = new BigDecimal("0.00");

        itemId = Integer.parseInt(request.getParameter("itemBought"));
        if (itemId == 0) {

            message = "Please select an item.";
            return "redirect:/";
        }

        Item item = Vending.getItem(itemId);

        if (item.getNumOfItems() == 0) {
            message = "SOLD OUT!";
            return "redirect:/";
        }
        try {
            if (Vending.validate(item.getItemPrice(), totalAmount).compareTo(BigDecimal.ZERO) >= 0) {

                message = "Thank You for shopping with us. Please come back again";

                Vending.updateItem((Long.toString(item.getItemId())));

                Vending.getChange(totalAmount);
                dollar = Vending.getDollar();
                Quarter = Vending.getQuarters();
                dime = Vending.getDimes();
                nickel = Vending.getNickels();
                penny = Vending.getPennies();
                totalAmount = new BigDecimal("0.00");
            } else {
               
                message = "Not enough money to buy an Item!";
            }
        } catch (VendingMachineInsufficientFundException ex) {
             remainingMoney = item.getItemPrice().subtract(totalAmount).setScale(2);
            message = "Please add the remainder money" +" "+ remainingMoney;
            return "redirect:/";
        }
        return "redirect:/";
    }
}
