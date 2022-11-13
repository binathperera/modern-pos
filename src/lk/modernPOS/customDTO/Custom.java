/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.customDTO;

import java.util.ArrayList;
import lk.modernPOS.dto.ItemDTO;

/**
 *
 * @author Binath Perera
 */
public class Custom {
    private String customerId;
    private String date;
    private String orderId;
    private ArrayList<ItemDTO> ar;

    public Custom(String customerId, String date, String orderId, ArrayList<ItemDTO> ar) {
        this.customerId = customerId;
        this.date = date;
        this.orderId = orderId;
        this.ar = ar;
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
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

    /**
     * @return the ar
     */
    public ArrayList<ItemDTO> getAr() {
        return ar;
    }

    /**
     * @param ar the ar to set
     */
    public void setAr(ArrayList<ItemDTO> ar) {
        this.ar = ar;
    }
    
}
