/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.dto;

import java.util.ArrayList;

/**
 *
 * @author Binath Perera
 */
public class CustomDTO {
    private ArrayList<ItemDTO> items;
    private String orderDate;
    private String customerId;
    private String orderId;

    public CustomDTO(ArrayList<ItemDTO> items, String orderDate, String customerId, String orderId) {
        this.items = items;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.orderId = orderId;
    }
    
    /**
     * @return the items
     */
    public ArrayList<ItemDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<ItemDTO> items) {
        this.items = items;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
