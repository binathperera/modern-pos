/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.modernPOS.connection.DBconnection;
import lk.modernPOS.customDTO.Custom;
import lk.modernPOS.dao.DAOfactory;
import lk.modernPOS.dao.custom.OrderDAO;
import lk.modernPOS.dao.custom.OrderDetailDAO;
import lk.modernPOS.dto.CustomDTO;
import lk.modernPOS.dto.ItemDTO;
import lk.modernPOS.dto.OrderDTO;
import lk.modernPOS.dto.OrderDetailDTO;

/**
 *
 * @author Binath Perera
 */
public class OrderController {
    private OrderDAO odao;
    private OrderDetailDAO oddao;
    Connection con;
    public OrderController() throws ClassNotFoundException, SQLException {
        odao=(OrderDAO) DAOfactory.getInstance().getDAO(DAOfactory.DAOtype.ORDER);
        oddao=(OrderDetailDAO) DAOfactory.getInstance().getDAO(DAOfactory.DAOtype.ORDERDETAIL);
        con=DBconnection.getInstance().getConnection();
    }
    
    public boolean addOrder(CustomDTO ob) throws SQLException{
        con.setAutoCommit(false);
        boolean orderStat = odao.add(new OrderDTO(ob.getOrderId(),ob.getOrderDate(), ob.getCustomerId()));
        boolean orderDetailStat = addOrderDetails(ob.getOrderId(),ob.getItems());
        if(orderDetailStat==false||orderStat==false){
            con.rollback();
        }else{
            con.commit();
        }
        con.setAutoCommit(true);
        return true;
    }
    public boolean deleteOrder(String id){
        return odao.delete(id);
    }
    public boolean deleteOrderDetail(String id){
        return oddao.delete(id);
    }
    public ArrayList<OrderDTO> getAllOrders(){
        return odao.getAll();
    }
    public String[] getAllOrderCodes(){
        ArrayList<OrderDTO> ar = odao.getAll();
        String[] codes=new String[ar.size()];
        for (int i = 0; i < codes.length; i++) {
            codes[i]=ar.get(i).getId();
        }
        return codes;
    }
    public ArrayList<OrderDetailDTO> getAllOrdersDetailsForOrder(String id){
        return oddao.getAll(id);
    }  

    private boolean addOrderDetails(String orderId, ArrayList<ItemDTO> ar) {
        for(ItemDTO i:ar){
            boolean add = oddao.add(new OrderDetailDTO(orderId,i.getCode(), i.getQtyOnHand(), i.getUnitPrice()));
            if(add==false){
                return false;
            }
        }
        return true;
    }
}
