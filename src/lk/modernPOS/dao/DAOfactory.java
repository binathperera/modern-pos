/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.dao;

import java.sql.SQLException;
import lk.modernPOS.connection.DBconnection;
import lk.modernPOS.dao.custom.impl.CustomerDAOimpl;
import lk.modernPOS.dao.custom.impl.ItemDAOimpl;
import lk.modernPOS.dao.custom.impl.OrderDAOimpl;
import lk.modernPOS.dao.custom.impl.OrderDetailDAOimpl;


/**
 *
 * @author Binath Perera
 */
public class DAOfactory {
    public enum DAOtype{CUSTOMER,ITEM,ORDER,ORDERDETAIL}
    private static DAOfactory d;
    
    private static CustomerDAOimpl cdao;
    private static OrderDAOimpl odao;
    private static OrderDetailDAOimpl oddao;
    private static ItemDAOimpl idao;
    
    private DAOfactory() throws ClassNotFoundException{
        cdao=new CustomerDAOimpl();
        odao=new OrderDAOimpl();
        idao=new ItemDAOimpl();
        oddao=new OrderDetailDAOimpl();
    }
    public static DAOfactory getInstance() throws ClassNotFoundException, SQLException{
        if(d==null || DBconnection.getInstance().getConnection().isClosed()){//Checks wheather the d is null or that the connection has been closed by the client.
            d=new DAOfactory();
        }
        return d;
    }
    public SuperDAO getDAO(DAOtype e){
        switch(e){
            case CUSTOMER:return cdao;
            case ORDER: return odao;
            case ITEM: return idao;
            case ORDERDETAIL: return oddao;
            default :return null;
        }
    }
}
