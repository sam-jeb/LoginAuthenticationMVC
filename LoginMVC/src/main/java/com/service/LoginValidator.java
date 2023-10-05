package com.service;

import com.model.AuthUpdater;
import com.model.UserDAO;
import org.bson.Document;

import java.util.Objects;

public class LoginValidator implements IValidateChecker{

    public boolean loginnullChecker(String email, String pw) {
        if (email.isEmpty()|| pw.isEmpty()){ return false;}
        else {return true;}
    }
    @Override
    public boolean emailChecker(String email) {
        UserDAO emailobj = new UserDAO();
        Document response=emailobj.Datagetter(email);
        if (response==null ){
            System.out.println("Invalid Email");
            return false;
        }
        else {return true;}
    }
    @Override
    public boolean passwordChecker(String email, String pw) {
        UserDAO passwordobj = new UserDAO();
        Document response=passwordobj.Datagetter(email);
        String DBpw= String.valueOf(response.get("pw"));

        BadLoginChecker attempsobj = new BadLoginChecker();
        String attempts= String.valueOf(response.get("attempts"));
        String lastattempt=String.valueOf(response.get("ldate"));

        boolean maxlimit=attempsobj.AuthAttempts(Integer.parseInt(attempts),Long.parseLong(lastattempt));
        AuthUpdater loginupdaterobj= new AuthUpdater();

        if (Objects.equals(pw, DBpw)&& maxlimit){
            loginupdaterobj.LoginUpdater(email,true);
            return true;
        }
        else {
            loginupdaterobj.LoginUpdater(email,false);
            if(Objects.equals(pw, DBpw)){
                attempsobj.logindetailprinter(true,email);
            }
            else {
                attempsobj.logindetailprinter(false,email);
            }
            return false;
        }
    }

}
