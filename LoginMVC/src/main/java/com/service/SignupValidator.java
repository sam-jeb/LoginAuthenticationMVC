package com.service;

import com.controller.ErrorDispatcher;
import com.controller.RequestContoller;
import com.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.io.IOException;
import java.util.Objects;

public class SignupValidator implements IValidateChecker{
    private static Logger log= LogManager.getLogger(SignupValidator.class);
    ErrorDispatcher errorobj = new ErrorDispatcher();

    public boolean signupnullChecker(String fname, String lname, String Email, String Password, String RPassword) throws ServletException, IOException {
        if (Email.isEmpty()|| Password.isEmpty() || fname.isEmpty() || lname.isEmpty() || RPassword.isEmpty()){
            //check for null entries
            String error="Please enter all the fields";
            log.error("Null entry exception detected !!");
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
            log.error("Email already exist in DB");
            String error="Email already exist, Please Use a different email";
            errorobj.ErrorHandler(error);
            return false;
        }
    }

    @Override
    public boolean passwordChecker(String Password,String RPassword) throws ServletException, IOException {
        if(Objects.equals(Password, RPassword) && Password.length()>5)
        //Check if both the password fields are same
        {
            return true;
        }
        else{
            log.error("passwords exception detected !!");
            if(!Objects.equals(Password, RPassword)){

                String error="Both the passwords does not match";
                errorobj.ErrorHandler(error);
            }
            else{

                String error="password should be at least 6 characters long";
                errorobj.ErrorHandler(error);
            }
            return false;
        }

    }
}
