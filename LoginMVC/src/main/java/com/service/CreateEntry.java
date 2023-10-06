package com.service;

import com.bean.User;
import com.model.UserDAO;
import java.util.logging.Logger;

public class CreateEntry {
    Logger logger = Logger.getLogger(CreateEntry.class.getName());
    public void DataHandler(String fname, String lname, String email, String password){
        UserDAO datainserstobj = new UserDAO();
        User data= new User.Builder(fname,lname,email,password).build();//Build User object
        datainserstobj.DataInsert(data.getFirstname(),data.getLastname(),data.getEmail(),data.getPassword());//DB call to create a new entry
    }
}
