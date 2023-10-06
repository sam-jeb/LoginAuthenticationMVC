package com.service;

import com.model.SingletonIdGenerator;
import com.model.UserDAO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.logging.Logger;

public class AuthUpdater {
    Logger logger = Logger.getLogger(AuthUpdater.class.getName());
    public void LoginUpdater(String email,boolean success){ // service that updates the DB
        UserDAO updateobj =new UserDAO();
        Document datab=updateobj.Datagetter(email);
        updateobj.DBupdater(success,datab);
    }
}
