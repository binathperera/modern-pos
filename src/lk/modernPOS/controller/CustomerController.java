/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.modernPOS.dao.DAOfactory;
import lk.modernPOS.dao.custom.CustomerDAO;
import lk.modernPOS.dto.CustomerDTO;

/**
 *
 * @author Binath Perera
 */
public class CustomerController {

    CustomerDAO cdao;

    public CustomerController() throws ClassNotFoundException, SQLException {
        cdao = (CustomerDAO) DAOfactory.getInstance().getDAO(DAOfactory.DAOtype.CUSTOMER);
    }

    public boolean addCustomer(CustomerDTO cus) {
        return cdao.add(cus);
    }

    public boolean deleteCustomer(String id) {
        return cdao.delete(id);
    }

    public CustomerDTO searchCustomer(String id) {
        return cdao.search(id);
    }

    public boolean updateCustomer(CustomerDTO cus) {
        return cdao.update(cus);
    }

    public ArrayList<CustomerDTO> getAllCustomers() {
        return cdao.getAll();
    }

    public String[] getAllCustomerIds() {
        ArrayList<CustomerDTO> ar = cdao.getAll();
        String ids[] = new String[ar.size()];
        for (int i = 0; i < ar.size(); i++) {
            ids[i] = ar.get(i).getId();
        }
        return ids;
    }
}
