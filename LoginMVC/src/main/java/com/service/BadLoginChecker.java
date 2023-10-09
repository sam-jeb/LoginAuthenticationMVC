package com.service;

import com.controller.ErrorDispatcher;
import com.controller.RequestContoller;
import com.model.UserDAO;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.io.IOException;
import java.util.Date;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public class BadLoginChecker {
    private static Logger log= LogManager.getLogger(BadLoginChecker.class);
    ErrorDispatcher errorobj = new ErrorDispatcher();
    UserDAO dataobj = new UserDAO();
    Date date = new Date();
    long CooldownTime=86400;// Cooldown time of 24 hrs in seconds
    long currentattempttime=date.getTime();
    boolean AuthAttempts(int attempts, long lastattempt){

        long milliseconds=currentattempttime-lastattempt; //Difference of current time and last login attempt gives the time duration
        long timediff = TimeUnit.MILLISECONDS.toSeconds(milliseconds); //Convert milliseconds to seconds
        if (attempts<3){
            return true; // login attempts less than 3 , return true
        }
        else {
            if (timediff>CooldownTime)
            {
                return true; // login attempts more than 3 but if time difference time is more that cooldown time, return true
            }
            else {
                return false; // login attempts more than 3 but if time difference time is less that cooldown time, return false
            }
        }
    }

    void logindetailprinter(String email) throws ServletException, IOException {
            Document response=dataobj.Datagetter(email);

            String attempt= String.valueOf(response.get("attempts"));// Get number of attempts
            int attempts=3-Integer.parseInt(attempt);// Calculate attempts left

            if (attempts>0){
                log.error("User entered wrong password, has "+attempts+" left");
                String errormsg="You have "+attempts+" attempts left";
                errorobj.ErrorHandler(errormsg); //Print error msg in UI
                System.out.println(errormsg);
            }

            else if(attempts==0){

                System.out.println("You have been locked out");
                //Calculate time before legal attempt
                String lastattempt= String.valueOf(response.get("ldate"));
                long militime = currentattempttime-Long.parseLong(lastattempt);
                long hours = 24- (TimeUnit.MILLISECONDS.toHours(militime)%24+1);
                long minutes= 60-(TimeUnit.MILLISECONDS.toMinutes(militime)%60+1);
                long seconds=60-(TimeUnit.MILLISECONDS.toSeconds(militime)%60+1);
                String errormsg="You have been locked out, Please wait "+hours+" hours: "+minutes+" minutes:"+seconds+" seconds before trying again";
                errorobj.ErrorHandler(errormsg);//Print error msg in UI
                log.error("User locked out, has to wait "+hours+" hours: "+minutes+" minutes:"+seconds+" seconds before trying again");
                System.out.println("Please wait "+hours+" hours: "+minutes+" minutes:"+seconds+" seconds before trying again");
        }
    }

}
