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
import lk.modernPOS.dao.custom.ItemDAO;
import lk.modernPOS.dto.ItemDTO;

/**
 *
 * @author Binath Perera
 */
public class ItemDAOimpl implements ItemDAO{
    private Connection con;
    public ItemDAOimpl() throws ClassNotFoundException {
        con=DBconnection.getInstance().getConnection();
    }
    
    @Override
    public boolean add(ItemDTO it) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT into item values(?,?,?,?)");
            ps.setObject(1,it.getCode());
            ps.setObject(2,it.getDescription());
            ps.setObject(3,it.getUnitPrice());
            ps.setObject(4,it.getQtyOnHand());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
         try {
            String sql="DELETE from item where code='"+id+"'";
            Statement stm = con.createStatement();
            return stm.executeUpdate(sql)>0;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(ItemDTO it) {
        try {
            String sql="UPDATE item SET description=?,unitPrice=?,qtyOnHand=? WHERE code=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,it.getDescription());
            ps.setObject(2,it.getUnitPrice());
            ps.setObject(3,it.getQtyOnHand());
            ps.setObject(4,it.getCode());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ItemDTO search(String id) {
        try {
            String sql="SELECT*from item where code='"+id+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                return new ItemDTO(
                        String.valueOf(rs.getObject(1)),
                        String.valueOf(rs.getObject(2)),
                        Double.parseDouble(String.valueOf(rs.getObject(3))),
                        Integer.parseInt(String.valueOf(rs.getObject(4))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    @Override
    public ArrayList<ItemDTO> getAll() {
        try {
            String sql="SELECT*from item";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<ItemDTO> ar=new ArrayList();
            while(rs.next()){
                ar.add(new ItemDTO(
                        String.valueOf(rs.getObject(1)),
                        String.valueOf(rs.getObject(2)),
                        Double.parseDouble(String.valueOf(rs.getObject(3))),
                        Integer.parseInt(String.valueOf(rs.getObject(4)))
                ));
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
