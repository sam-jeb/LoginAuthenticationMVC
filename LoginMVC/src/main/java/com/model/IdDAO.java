package com.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
public class IdDAO {
    static long id;
    MongoClient mongoClient =new MongoClient("localhost", 27017);
    MongoDatabase db = mongoClient.getDatabase("User");
    MongoCollection col = db.getCollection("Credentials");
}
