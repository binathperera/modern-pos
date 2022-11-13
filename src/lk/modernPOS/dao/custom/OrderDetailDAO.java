/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.dao.custom;

import java.util.ArrayList;
import lk.modernPOS.dao.SuperDAO;
import lk.modernPOS.dto.OrderDetailDTO;

/**
 *
 * @author Binath Perera
 */
public interface OrderDetailDAO extends SuperDAO<OrderDetailDTO>{

    /**
     *
     * @param id
     * @return
     */
    @Override
    default OrderDetailDTO search(String id){return null;}
    @Override
    default ArrayList<OrderDetailDTO> getAll(){return null;}

    /**
     *
     * @param id
     * @return
     */
    ArrayList<OrderDetailDTO> getAll(String id);
}
