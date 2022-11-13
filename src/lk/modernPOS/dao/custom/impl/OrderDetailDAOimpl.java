/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.modernPOS.connection.DBconnection;
import lk.modernPOS.dao.custom.OrderDetailDAO;
import lk.modernPOS.dto.OrderDetailDTO;

/**
 *
 * @author Binath Perera
 */
public class OrderDetailDAOimpl implements OrderDetailDAO{
    private Connection con;
    public OrderDetailDAOimpl() throws ClassNotFoundException{
        con=DBconnection.getInstance().getConnection();
    }
    
    @Override
    public boolean add(OrderDetailDTO ob) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT into orderdetail values(?,?,?,?)");
            ps.setObject(1,ob.getOrderId());
            ps.setObject(2,ob.getItemCode());
            ps.setObject(3,ob.getQty());
            ps.setObject(4,ob.getUnitPrice());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
         try {
             String sql="DELETE from OrderDetail where itemCode='"+id+"'";
             Statement stm = con.createStatement();
             return stm.executeUpdate(sql)>0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(OrderDetailDTO od) {
        try {
            String sql="UPDATE customer SET itemCode=?,qty=?,UnitPrice=? WHERE orderId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,od.getItemCode());
            ps.setObject(2,od.getQty());
            ps.setObject(3,od.getUnitPrice());
            ps.setObject(4,od.getOrderId());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public ArrayList<OrderDetailDTO> getAll(String orderId) {
        try {
            String sql="SELECT*from orderdetail where orderId='"+orderId+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<OrderDetailDTO> ar=new ArrayList<>();
            while(rs.next()){
                ar.add(new OrderDetailDTO(
                        String.valueOf(rs.getObject(1)),
                        String.valueOf(rs.getObject(2)),
                        Integer.parseInt(String.valueOf(rs.getObject(3))),
                        Double.parseDouble(String.valueOf(rs.getObject(4))))
                );
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}