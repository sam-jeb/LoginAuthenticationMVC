package com.service;

import jakarta.servlet.ServletException;

import java.io.IOException;

public interface IValidateChecker {

    boolean emailChecker(String email) throws ServletException, IOException;

    boolean passwordChecker(String email, String pw) throws ServletException, IOException;
}
