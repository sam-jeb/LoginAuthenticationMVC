package com.controller;
import java.io.*;

import com.model.UserDAO;

import com.service.CreateEntry;
import com.service.LoginValidator;
import com.service.SignupValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

@WebServlet(name = "RequestContoller", value = "/RequestContoller")
public class RequestContoller extends HttpServlet {
        private static Logger log= LogManager.getLogger(RequestContoller.class);
        static HttpServletRequest Request ;
        static HttpServletResponse Response;
        public void setRequest(HttpServletRequest request){this.Request=request;}
        public void setResponse(HttpServletResponse response){this.Response=response;}
        public static HttpServletRequest requestgetter(){return Request;}
        public static HttpServletResponse responsegetter(){return Response;}
        public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                log.info("DoPost called");
                setRequest(request);
                setResponse(response);
                String login="login";
                String signup="signup";
                String Email= request.getParameter("email");
                String Password= request.getParameter("pw");
                String fname= request.getParameter("fname");
                String lname= request.getParameter("lname");
                String RPassword= request.getParameter("rpw");
                String submitType=request.getParameter("source");
                log.info("submit type:"+submitType);
                LoginValidator loginvalidateobj = new LoginValidator();
                SignupValidator signupvalidateobj = new SignupValidator();
                CreateEntry dataobj = new CreateEntry();
                UserDispatcher userobj= new UserDispatcher();
                log.debug("Parameters initialized and objects initialized");
                if (submitType.equals(login)){
                        boolean nullCheck= loginvalidateobj.loginnullChecker(Email,Password);//Check for null inputs
                        log.debug("nullCheck- status : "+nullCheck);
                        boolean validEmail= loginvalidateobj.emailChecker(Email);//Check if email is valid
                        log.debug("Valid email- status: "+validEmail);
                        if (nullCheck && validEmail){
                                log.debug("Check for correct password");
                                boolean pwCheck= loginvalidateobj.passwordChecker(Email,Password);//password verification
                                log.debug("password check- status: "+pwCheck);
                                if (pwCheck){
                                        userobj.UserDisplay(Email);
                                        log.info("Login Successful");
                                }

                        }
                } else if (submitType.equals(signup)) {
                        boolean nullCheck= signupvalidateobj.signupnullChecker(fname,lname,Email,Password,RPassword);//Check for null inputs
                        log.debug("nullCheck- status : "+nullCheck);
                        boolean validEmail= signupvalidateobj.emailChecker(Email);//Check if email is valid
                        log.debug("Valid email- status: "+validEmail);
                        boolean pwCheck= signupvalidateobj.passwordChecker(Password,RPassword);//Check if both passwords entered are the same
                        log.debug("password check- status: "+pwCheck);
                        if(nullCheck && validEmail && pwCheck){
                                //create new entry in DB

                                dataobj.DataHandler(fname,lname,Email,Password);//Data handler builds User object and creates new entry in DB
                                request.setAttribute("fname", fname);
                                request.setAttribute("lname", lname);
                                request.setAttribute("email", Email);
                                String msg="User Created Successfully";
                                log.info(msg);
                                request.setAttribute("msg", msg);
                                getServletContext().getRequestDispatcher("/User.jsp").forward(request, response);
                        }
                }
        }
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                getServletContext().getRequestDispatcher("/Signup.jsp").forward( request,  response);
        }
        public void destroy() {
        }


}
