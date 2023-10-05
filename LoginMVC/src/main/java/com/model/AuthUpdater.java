package com.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

public class AuthUpdater {
    SingletonIdGenerator obj = SingletonIdGenerator.getInstance();
    MongoClient mongoClient =new MongoClient("localhost", 27017);
    MongoDatabase db = mongoClient.getDatabase("User");
    MongoCollection col = db.getCollection("Credentials");
    MongoCollection<Document> collection = db.getCollection("Credentials");
    Date date = new Date();
    public void LoginUpdater(String email,boolean success){
        Document datab = collection.find(new Document("email",email)).first();
        String attempts=String.valueOf(datab.get("attempts"));
        System.out.println("success:"+success);
        int failedattempt=Integer.parseInt(attempts);
        if(success){
            Document query = new Document();
            query.append("email",email);
            Document setData = new Document();
            setData.append("attempts", 0).append("ldate", date.getTime());
            Document update = new Document();
            update.append("$set", setData);
            //To update single Document
            collection.updateOne(query, update);

        }
        else{
            Document query = new Document();
            query.append("email",email);
            Document setData = new Document();
            if(failedattempt<3){
                setData.append("attempts", failedattempt+1).append("ldate", date.getTime());
            }
            else {
                setData.append("attempts", failedattempt+1);
            }
            Document update = new Document();
            update.append("$set", setData);
            //To update single Document
            collection.updateOne(query, update);
        }

    }
}
