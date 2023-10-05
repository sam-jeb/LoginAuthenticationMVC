package com.controller;
import java.io.*;

import com.service.CreateEntry;
import com.service.LoginValidator;
import com.service.SignupValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RequestContoller", value = "/RequestContoller")
public class RequestContoller extends HttpServlet {

        private String message;

        public void init() {
            message = "Hello World!";
        }
        //
        public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
                System.out.println("ping1");
                String login="login";
                String signup="signup";
                String Email= request.getParameter("email");
                String Password= request.getParameter("pw");
                String fname= request.getParameter("fname");
                String lname= request.getParameter("lname");
                String RPassword= request.getParameter("rpw");
                LoginValidator loginvalidateobj = new LoginValidator();
                SignupValidator signupvalidateobj = new SignupValidator();
                String submitType=request.getParameter("source");

                if (submitType.equals(login)){
                        boolean nullCheck= loginvalidateobj.loginnullChecker(Email,Password);//Check for null inputs
                        boolean validEmail= loginvalidateobj.emailChecker(Email);//Check if email is valid
                        if (nullCheck && validEmail){
                                boolean pwCheck= loginvalidateobj.passwordChecker(Email,Password);//password verification
                                if (pwCheck){
                                        System.out.println("Login Successful");
                                }
                                else {
                                        System.out.println("Wrong Password");
                                }
                        }
                } else if (submitType.equals(signup)) {
                        boolean nullCheck= signupvalidateobj.signupnullChecker(fname,lname,Email,Password,RPassword);//Check for null inputs
                        boolean validEmail= signupvalidateobj.emailChecker(Email);//Check if email is valid
                        boolean pwCheck= signupvalidateobj.passwordChecker(Password,RPassword);//Check if both passwords entered are the same
                        if(!nullCheck){
                                System.out.println("Please fill all the inputs");
                        }
                        else if(!validEmail){
                                System.out.println("Invalid Email, Please use a different Email");
                        }
                        else if(!pwCheck){
                                System.out.println("Both Passwords does not match");
                        }
                        if(nullCheck && validEmail && pwCheck){
                                CreateEntry dataobj = new CreateEntry();
                                dataobj.DataHandler(fname,lname,Email,Password);
                                System.out.println("User Created Syccessfully");
                        }
                }
        }
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                getServletContext().getRequestDispatcher("/Signup.jsp").forward( request,  response);
                System.out.println("gethere");
        }
        public void destroy() {
        }
}
