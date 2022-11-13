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
import lk.modernPOS.dao.custom.CustomerDAO;
import lk.modernPOS.dto.CustomerDTO;

/**
 *
 * @author Binath Perera
 */
public class CustomerDAOimpl implements CustomerDAO{
    private final Connection con;
    public CustomerDAOimpl() throws ClassNotFoundException{
        con=DBconnection.getInstance().getConnection();
    }
    @Override
    public boolean add(CustomerDTO cus){
        try {
            PreparedStatement ps = con.prepareStatement("INSERT into Customer values(?,?,?,?)");
            ps.setObject(1,cus.getId());
            ps.setObject(2,cus.getName());
            ps.setObject(3,cus.getAddress());
            ps.setObject(4,cus.getSalary());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String sql="DELETE from customer where id='"+id+"'";
            Statement stm = con.createStatement();
            return stm.executeUpdate(sql)>0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(CustomerDTO cus) {
        try {
            String sql="UPDATE customer SET name=?,address=?,salary=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1,cus.getName());
            ps.setObject(2,cus.getAddress());
            ps.setObject(3,cus.getSalary());
            ps.setObject(4,cus.getId());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public CustomerDTO search(String id) {
        try {
            String sql="SELECT*from customer where id='"+id+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                return new CustomerDTO(
                String.valueOf(rs.getObject(1)),
                String.valueOf(rs.getObject(2)),
                String.valueOf(rs.getObject(3)),
                Double.parseDouble(String.valueOf(rs.getObject(4))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() {
        try {
            String sql="SELECT*from customer";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<CustomerDTO> ar=new ArrayList();
            while(rs.next()){
                ar.add(new CustomerDTO(
                        String.valueOf(rs.getObject(1)),
                        String.valueOf(rs.getObject(2)),
                        String.valueOf(rs.getObject(3)),
                        Double.parseDouble(String.valueOf(rs.getObject(4))))
                );
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
