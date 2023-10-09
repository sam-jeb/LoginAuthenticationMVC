package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class
ErrorDispatcher extends RequestContoller{

    public void ErrorHandler(String error) throws ServletException, IOException {
        String login="login";
        String signup="signup";
        HttpServletRequest ErrorRequest=RequestContoller.requestgetter();
        HttpServletResponse ErrorResponse=RequestContoller.responsegetter();
        String submitType=ErrorRequest.getParameter("source");
        ErrorRequest.setAttribute("error", error);
        if (submitType.equals(login)) {
            ErrorRequest.getServletContext().getRequestDispatcher("/index.jsp").forward(ErrorRequest, ErrorResponse);
        } else if (submitType.equals(signup)) {
            ErrorRequest.getServletContext().getRequestDispatcher("/Signup.jsp").forward(ErrorRequest, ErrorResponse);
        }
    }
}
