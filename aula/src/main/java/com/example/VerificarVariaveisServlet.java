package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verificarVariaveis")
public class VerificarVariaveisServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");
        String dbUrl2 = System.getProperty("DB_URL");
        String dbUsername2 = System.getProperty("DB_USERNAME");
        String dbPassword2 = System.getProperty("DB_PASSWORD");

        response.setContentType("text/plain");
        response.getWriter().println("DB_URL: " + dbUrl);
        response.getWriter().println("DB_USERNAME: " + dbUsername);
        response.getWriter().println("DB_PASSWORD: " + dbPassword);
        response.getWriter().println("DB_URL2: " + dbUrl2);
        response.getWriter().println("DB_USERNAME2: " + dbUsername2);
        response.getWriter().println("DB_PASSWORD2: " + dbPassword2);
    }
}