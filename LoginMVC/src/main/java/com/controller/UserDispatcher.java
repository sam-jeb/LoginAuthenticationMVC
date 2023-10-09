package com.controller;

import com.bean.User;
import com.model.UserDAO;
import com.service.CreateEntry;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;

public class UserDispatcher extends RequestContoller {
    CreateEntry dataobj = new CreateEntry();
    public void UserDisplay(String email) throws ServletException, IOException {

        String msg = "Authentication Successful";
        HttpServletRequest UserRequest = RequestContoller.requestgetter();
        HttpServletResponse UserResponse = RequestContoller.responsegetter();
        UserRequest.setAttribute("msg", msg);

        User userobj = dataobj.DataSender(email);
        String firstname = userobj.getFirstname();
        String lastname = userobj.getLastname();
        String Email = userobj.getEmail();

        UserRequest.setAttribute("fname", firstname);
        UserRequest.setAttribute("lname", lastname);
        UserRequest.setAttribute("email", Email);
        UserRequest.getServletContext().getRequestDispatcher("/User.jsp").forward(UserRequest, UserResponse);
    }

}

