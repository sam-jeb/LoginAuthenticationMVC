package com.controller;
import java.io.*;
import org.apache.log4j.Logger;
import com.service.CreateEntry;
import com.service.LoginValidator;
import com.service.SignupValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RequestContoller", value = "/RequestContoller")
public class RequestContoller extends HttpServlet {
        Logger logger = Logger.getLogger(RequestContoller.class);
        static HttpServletRequest Request ;
        static HttpServletResponse Response;

        public void setRequest(HttpServletRequest request){

                this.Request=request;

        }

        public void setResponse(HttpServletResponse response){
                this.Response=response;
        }
        public static HttpServletRequest requestgetter(){
                return Request;
        }
        public static HttpServletResponse responsegetter(){
                return Response;
        }

        public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                setRequest(request);
                setResponse(response);
                System.out.println(request);

                String login="login";
                String signup="signup";
                String Email= request.getParameter("email");
                String Password= request.getParameter("pw");
                String fname= request.getParameter("fname");
                String lname= request.getParameter("lname");
                String RPassword= request.getParameter("rpw");
                String submitType=request.getParameter("source");

                LoginValidator loginvalidateobj = new LoginValidator();
                SignupValidator signupvalidateobj = new SignupValidator();
                CreateEntry dataobj = new CreateEntry();

                if (submitType.equals(login)){
                        boolean nullCheck= loginvalidateobj.loginnullChecker(Email,Password);//Check for null inputs
                        boolean validEmail= loginvalidateobj.emailChecker(Email);//Check if email is valid
                        if (nullCheck && validEmail){
                                boolean pwCheck= loginvalidateobj.passwordChecker(Email,Password);//password verification
                                if (pwCheck){
                                        String msg = "Authentication Successful";
                                        request.setAttribute("msg", msg);
                                        System.out.println("Login Successful");
                                        getServletContext().getRequestDispatcher("/User.jsp").forward(request, response);
                                }
                                else {
                                        System.out.println("Wrong Password");
                                }
                        }
                } else if (submitType.equals(signup)) {
                        boolean nullCheck= signupvalidateobj.signupnullChecker(fname,lname,Email,Password,RPassword);//Check for null inputs
                        boolean validEmail= signupvalidateobj.emailChecker(Email);//Check if email is valid
                        boolean pwCheck= signupvalidateobj.passwordChecker(Password,RPassword);//Check if both passwords entered are the same
                        if(nullCheck && validEmail && pwCheck){
                                //create new entry in DB
                                String msg="User Created Syccessfully";
                                request.setAttribute("msg", msg);
                                dataobj.DataHandler(fname,lname,Email,Password);//Data handler builds User object and creates new entry in DB
                                System.out.println("User Created Syccessfully");
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
