package lk.modernPOS.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Binath Perera
 */
public class DBconnection {
    private static DBconnection ob;
    private static Connection connection;
    private DBconnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Properties p=new Properties();
        try {
            p.load(new FileInputStream("SystemResources/DBProperties.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection = DriverManager.getConnection("jdbc:mysql://"+p.getProperty("ip")+"/"+p.getProperty("databaseName"),""+p.getProperty("userName"),""+p.getProperty("password"));        
    }
    public static DBconnection getInstance() throws ClassNotFoundException,NullPointerException{
        try{
            if(ob==null ){
                ob=new DBconnection();
            }
        }catch (SQLException ex) {
            ob=null;
        } 
        return ob;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
