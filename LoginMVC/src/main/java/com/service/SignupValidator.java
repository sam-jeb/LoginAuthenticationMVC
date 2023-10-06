package com.service;

import com.controller.ErrorDispatcher;
import com.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class SignupValidator implements IValidateChecker{
    Logger logger = Logger.getLogger(SignupValidator.class.getName());
    ErrorDispatcher errorobj = new ErrorDispatcher();

    public boolean signupnullChecker(String fname, String lname, String Email, String Password, String RPassword) throws ServletException, IOException {
        if (Email.isEmpty()|| Password.isEmpty() || fname.isEmpty() || lname.isEmpty() || RPassword.isEmpty()){
            //check for null entries
            String error="Please enter all the fields";
            errorobj.ErrorHandler(error);
            return false;
        }
        else {return true;}
    }
    @Override
    public boolean emailChecker(String email) throws ServletException, IOException {
        //check for valid email
        UserDAO emailobj= new UserDAO();
        Document response=emailobj.Datagetter(email);
        if(response==null){
            return true;
        }
        else{
            String error="Email already exist, Please Use a different email";
            errorobj.ErrorHandler(error);
            return false;
        }
    }

    @Override
    public boolean passwordChecker(String Password,String RPassword) throws ServletException, IOException {
        if(Objects.equals(Password, RPassword))
        //Check if both the password fields are same
        {
            return true;
        }
        else{
            String error="Both the passwords does not match";
            errorobj.ErrorHandler(error);
            return false;
        }

    }
}
