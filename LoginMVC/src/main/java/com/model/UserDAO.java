package com.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserDAO {
    SingletonIdGenerator obj = SingletonIdGenerator.getInstance();
    MongoClient mongoClient =new MongoClient("localhost", 27017);
    MongoDatabase db = mongoClient.getDatabase("User");
    MongoCollection col = db.getCollection("Credentials");
    MongoCollection<Document> collection = db.getCollection("Credentials");
    Date date = new Date();
    long inserttime;
    int attempts=0;

//    Document data = new Document("_id", SingletonIdGenerator.id +1).append("fname",fname).append("lname",lname).append("email",email).append("pw",Password).append("ldate",date.getTime()).append("attempts",attempts);
//    col.insertOne(data);
    public void DataInsert(String firstname, String lastname, String email, String password){
        Document data = new Document("_id", SingletonIdGenerator.id +1).append("fname",firstname).append("lname",lastname).append("email",email).append("pw",password).append("ldate",date.getTime()).append("attempts",attempts);
        col.insertOne(data);
    }
    public Document Datagetter(String email){
        Document datab = collection.find(new Document("email",email)).first();
        return datab;
    }
}
