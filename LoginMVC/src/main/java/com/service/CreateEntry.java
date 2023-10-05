package com.service;

import com.model.UserDAO;

public class CreateEntry {
    public void DataHandler(String fname, String lname, String email, String password){
        UserDAO datainserstobj = new UserDAO();
        datainserstobj.DataInsert(fname,lname,email,password);
    }
}
