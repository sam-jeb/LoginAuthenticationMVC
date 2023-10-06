package com.bean;

public class User {
    String firstname;
    String lastname;
    String email;
    String password;
    public User(Builder builder) {
        this.firstname= builder.fname;
        this.lastname= builder.lname;
        this.email= builder.email;
        this.password= builder.password;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder{
        String fname;
        String lname;
        String email;
        String password;

        public Builder(String fname, String lname, String email, String password){
            this.fname=fname;
            this.lname=lname;
            this.email=email;
            this.password=password;
        }

        public User build(){
            return new User(this);
        }
    }
}
