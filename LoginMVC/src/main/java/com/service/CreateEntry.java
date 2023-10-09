package com.service;

import com.bean.User;
import com.controller.RequestContoller;
import com.model.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;

import java.util.logging.Logger;

public class CreateEntry {
    private static org.apache.logging.log4j.Logger log= LogManager.getLogger(CreateEntry.class);

    public void DataHandler(String fname, String lname, String email, String password){
        UserDAO datainserstobj = new UserDAO();
        log.info("data handle object created");
        User data= new User.Builder(fname,lname,email,password).build();//Build User object
        datainserstobj.DataInsert(data.getFirstname(),data.getLastname(),data.getEmail(),data.getPassword());//DB call to create a new entry
    }
    public User DataSender(String  email){
        UserDAO dataobj =new UserDAO();
        Document response=dataobj.Datagetter(email);
        String firstname=String.valueOf(response.get("fname"));
        String lastname=String.valueOf(response.get("lname"));
        String Email=String.valueOf(response.get("email"));
        String password=String.valueOf(response.get("pw"));
        User data= new User.Builder(firstname,lastname,Email,password).build();
        log.info("User bean object returned");
        return data;
    }
}
