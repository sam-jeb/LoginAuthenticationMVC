package com.service;

import com.controller.ErrorDispatcher;
import com.model.UserDAO;
import jakarta.servlet.ServletException;
import org.bson.Document;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class LoginValidator implements IValidateChecker{
    Logger logger = Logger.getLogger(LoginValidator.class.getName());
    ErrorDispatcher errorobj = new ErrorDispatcher(); //obj for error dispatcher
    public boolean loginnullChecker(String email, String pw) throws ServletException, IOException {
        //Check for null entries
        if (email.isEmpty()|| pw.isEmpty()){
            errorobj.ErrorHandler("Enter all fields");
            return false;
        }
        else {return true;}
    }
    @Override
    public boolean emailChecker(String email) throws ServletException, IOException {
        UserDAO emailobj = new UserDAO();
        Document response=emailobj.Datagetter(email);
        // Check for valid email
        if (response==null ){
            errorobj.ErrorHandler("Invalid Email");
            System.out.println("Invalid Email");
            return false;
        }
        else {return true;}
    }
    @Override
    public boolean passwordChecker(String email, String pw) throws ServletException, IOException {
        UserDAO passwordobj = new UserDAO();
        BadLoginChecker attempsobj = new BadLoginChecker();
        AuthUpdater loginupdaterobj= new AuthUpdater();
        Document response=passwordobj.Datagetter(email);//DB Call to get the document with response
        String DBpw= String.valueOf(response.get("pw"));//Get Password from DB
        String attempts= String.valueOf(response.get("attempts"));//Get number of bad attempts
        String lastattempt=String.valueOf(response.get("ldate"));//Get last login attempt

        boolean logincriteria=attempsobj.AuthAttempts(Integer.parseInt(attempts),Long.parseLong(lastattempt));
        //Check of valid login criteria

        if (Objects.equals(pw, DBpw)&& logincriteria){
            loginupdaterobj.LoginUpdater(email,true);// Both Password and Login criteria pass, Update DB by resetting the attempts
            return true;
        }
        /*
            Login Credentials or Password fails, Update DB and print error message
         */
        else {
            loginupdaterobj.LoginUpdater(email,false); //Update attempts and last attempt in DB
            attempsobj.logindetailprinter(email); //Print error in UI
            return false;

        }
    }

}
