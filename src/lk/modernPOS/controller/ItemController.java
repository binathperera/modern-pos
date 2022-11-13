/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.modernPOS.dao.DAOfactory;
import lk.modernPOS.dao.custom.ItemDAO;
import lk.modernPOS.dto.ItemDTO;

/**
 *
 * @author Binath Perera
 */
public class ItemController {
    ItemDAO idao;
    public ItemController() throws ClassNotFoundException, SQLException {
        idao=(ItemDAO) DAOfactory.getInstance().getDAO(DAOfactory.DAOtype.ITEM);
    }
    public boolean addItem(ItemDTO it){
        return idao.add(it);
    }
    public boolean deleteItem(String code){
        return idao.delete(code);
    }
    public boolean updateItem(ItemDTO it){
        return idao.update(it);
    }
    public ItemDTO searchItem(String code){
        return idao.search(code);
    }
    public ArrayList<ItemDTO> getAllItems(){
        return idao.getAll();
    }
    public String[] getAllItemCodes(){
        ArrayList<ItemDTO> ar = idao.getAll();
        String[] codes=new String[ar.size()];
        for (int i = 0; i < ar.size(); i++) {
            codes[i]=ar.get(i).getCode();
        }
        return codes;
    }
}
