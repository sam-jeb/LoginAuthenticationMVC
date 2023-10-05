package com.service;

import com.model.UserDAO;
import org.bson.Document;

import java.util.Date;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public class BadLoginChecker {
    UserDAO dataobj = new UserDAO();

    long CooldownTime=1440;
    Date date = new Date();
    long currentattempttime=date.getTime();
    boolean AuthAttempts(int attempts, long lastattempt){
        long milliseconds=currentattempttime-lastattempt;
        long checkminutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        if (attempts<3){
            return true;
        }
        else {
            if (checkminutes>CooldownTime)
            {
                return true;
            }
            else {
                return false;
            }
        }
    }

    void logindetailprinter(boolean failtype,String email){
        if(!failtype){
            Document response=dataobj.Datagetter(email);
            String attempt= String.valueOf(response.get("attempts"));
            int attempts=3-Integer.parseInt(attempt);
            System.out.println("Password Wrong");
            if (attempts>0){
                System.out.println("You have "+attempts+" attempts left");
            }
            else if(attempts==0){
                System.out.println("You have been locked out, Please try again after 24hrs");
            }
        }
        else {
            Document response=dataobj.Datagetter(email);
            String lastattempt= String.valueOf(response.get("ldate"));
            long militime = currentattempttime-Long.parseLong(lastattempt);
            long hours =24-TimeUnit.MILLISECONDS.toHours(militime);
            long minutes=1440-TimeUnit.MILLISECONDS.toMinutes(militime);
            long seconds= 86400-TimeUnit.MILLISECONDS.toSeconds(militime);
            System.out.println("Please wait "+hours+"hours:"+minutes+"minutes:"+seconds +"seconds");
        }
    }
}
