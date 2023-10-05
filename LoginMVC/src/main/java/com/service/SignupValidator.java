package com.service;

import com.model.UserDAO;
import org.bson.Document;

import java.util.Objects;

public class SignupValidator implements IValidateChecker{

    public boolean signupnullChecker(String fname,String lname,String Email,String Password,String RPassword) {
        System.out.println(Email+Password);
        if (Email.isEmpty()|| Password.isEmpty() || fname.isEmpty() || lname.isEmpty() || RPassword.isEmpty()){ return false;}
        else {return true;}
    }
    @Override
    public boolean emailChecker(String email) {
        UserDAO emailobj= new UserDAO();
        Document response=emailobj.Datagetter(email);
        if(response==null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean passwordChecker(String Password,String RPassword) {
        if(Objects.equals(Password, RPassword))
        {
            return true;
        }
        else{
            return false;
        }

    }
}
