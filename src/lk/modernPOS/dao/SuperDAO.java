/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.modernPOS.dao;

import java.util.ArrayList;
import lk.modernPOS.dto.SuperDTO;

/**
 *
 * @author Binath Perera
 * @param <T>
 */
public interface SuperDAO<T extends SuperDTO>{
    public boolean add(T ob);
    public boolean delete(String id);
    public boolean update(T ob);
    public T search(String id);
    public ArrayList<T> getAll();
}
