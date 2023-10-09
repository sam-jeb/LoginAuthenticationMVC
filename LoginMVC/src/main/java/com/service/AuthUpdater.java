package com.service;

import com.bean.User;
import com.model.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;

import java.util.logging.Logger;

public class AuthUpdater {
    private static org.apache.logging.log4j.Logger log= LogManager.getLogger(User.class);
    public void LoginUpdater(String email,boolean success){ // service that updates the DB
        UserDAO updateobj =new UserDAO();
        log.info("Document updater object created");
        Document datab=updateobj.Datagetter(email);
        updateobj.DBupdater(success,datab);
    }
}
