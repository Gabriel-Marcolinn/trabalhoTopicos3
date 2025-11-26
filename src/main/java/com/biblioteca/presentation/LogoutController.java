package com.biblioteca.presentation;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LogoutController {

    private static final Logger LOGGER =  Logger.getLogger(LogoutController.class.getName());

    @Inject
    HttpServletRequest request;

    public String logout () {
        try {
            request.logout();
            request.getSession().invalidate();
            return "/login.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, "Erro ao realizar o logout", e);
            return null;
        }
    }

}
