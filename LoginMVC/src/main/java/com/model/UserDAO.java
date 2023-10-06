package com.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class UserDAO {
    Logger logger = Logger.getLogger(UserDAO.class.getName());
    IdDAO Idobj = new IdDAO();
    SingletonIdGenerator obj = SingletonIdGenerator.getInstance();
    MongoClient mongoClient =new MongoClient("localhost", 27017);
    MongoDatabase db = mongoClient.getDatabase("User");
    MongoCollection col = db.getCollection("Credentials");
    MongoCollection<Document> collection = db.getCollection("Credentials");
    Date date = new Date();

    int attempts=0;

//    Document data = new Document("_id", SingletonIdGenerator.id +1).append("fname",fname).append("lname",lname).append("email",email).append("pw",Password).append("ldate",date.getTime()).append("attempts",attempts);
//    col.insertOne(data);
    public void DataInsert(String firstname, String lastname, String email, String password){
        //Inserts data into the DB
        Document data = new Document("_id", Idobj.IdGetter()+1).append("fname",firstname).append("lname",lastname).append("email",email).append("pw",password).append("ldate",date.getTime()).append("attempts",attempts);
        col.insertOne(data);
    }
    public Document Datagetter(String email){
        //Returns the Document with the email reference
        Document datab = collection.find(new Document("email",email)).first();
        return datab;
    }
    public void DBupdater(Boolean success,Document data){
        String email=data.getString("email");
        String attempt= String.valueOf(data.get("attempts"));
        int failedattempt=Integer.parseInt(attempt);
        // Reset the attempts in DB
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
        //update the attempt in DB
        else{
            Document query = new Document();
            query.append("email",email);
            Document setData = new Document();
            if(failedattempt<3){
                setData.append("attempts", failedattempt+1).append("ldate", date.getTime());
            }
            Document update = new Document();
            update.append("$set", setData);
            //To update single Document
            collection.updateOne(query, update);
        }
    }
}
