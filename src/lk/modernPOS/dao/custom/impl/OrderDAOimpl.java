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
import lk.modernPOS.dao.custom.OrderDAO;
import lk.modernPOS.dto.OrderDTO;

/**
 *
 * @author Binath Perera
 */
public class OrderDAOimpl implements OrderDAO{
    private Connection con;
    public OrderDAOimpl() throws  ClassNotFoundException {
        con=DBconnection.getInstance().getConnection();
    }
    
    @Override
    public boolean add(OrderDTO or) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT into orders values(?,?,?)");
            ps.setObject(1,or.getId());
            ps.setObject(2,or.getDate());
            ps.setObject(3,or.getCustomerId());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
         try {
            String sql="DELETE from orders where id='"+id+"'";
            Statement stm = con.createStatement();
            return stm.executeUpdate(sql)>0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(OrderDTO or) {
        try {
            String sql="UPDATE orders SET date=?,customerId=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,or.getDate());
            ps.setObject(2,or.getCustomerId());
            ps.setObject(4,or.getId());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public OrderDTO search(String id) {
        try {
            String sql="SELECT*from orders where code='"+id+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                return new OrderDTO(
                        String.valueOf(rs.getObject(1)),
                        String.valueOf(rs.getObject(2)),
                        String.valueOf(rs.getObject(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<OrderDTO> getAll() {
        try {
            String sql="SELECT*from orders";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<OrderDTO> ar=new ArrayList();
            while(rs.next()){
                ar.add(new OrderDTO(
                        String.valueOf(rs.getObject(1)),
                        String.valueOf(rs.getObject(2)),
                        String.valueOf(rs.getObject(3))
                ));
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    
}
