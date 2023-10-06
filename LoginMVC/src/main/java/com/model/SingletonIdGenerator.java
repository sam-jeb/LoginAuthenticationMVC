package com.model;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.logging.Logger;

public class SingletonIdGenerator {
    Logger logger = Logger.getLogger(SingletonIdGenerator.class.getName());
    private static volatile SingletonIdGenerator instance; //to prevent partially constructed objects
    static long id;
    private SingletonIdGenerator(){}
    public static SingletonIdGenerator getInstance(){
        synchronized (SingletonIdGenerator.class) { //to prevent the access from multiple threads at the same time
            if (instance == null) { //check for creation of only one instance
                instance = new SingletonIdGenerator();
                MongoClient mongoClient =new MongoClient("localhost", 27017);
                MongoDatabase db = mongoClient.getDatabase("User");
                MongoCollection col = db.getCollection("Credentials");
                id=col.countDocuments();
            }
        }

        return instance;
    }
}
