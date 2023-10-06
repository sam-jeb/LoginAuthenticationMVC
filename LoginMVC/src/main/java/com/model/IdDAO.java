package com.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class IdDAO {

    MongoClient mongoClient =new MongoClient("localhost", 27017);
    MongoDatabase db = mongoClient.getDatabase("User");
    MongoCollection col = db.getCollection("Credentials");

    long IdGetter(){
        //return unique ID for DB
        long count = col.countDocuments();
        return count;
    }

}
