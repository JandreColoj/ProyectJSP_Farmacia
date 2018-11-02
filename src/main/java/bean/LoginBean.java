/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import CAD.usuarioCad;
import DTO.usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jandre
 */
@ManagedBean
@RequestScoped
@Named(value = "LoginBean")

public class LoginBean{
    
    private String user;
    private String password;
    private String message;
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*
    static final String miusuario ="Jandre";
    static final String mipassword ="123";
    
    public String verificarPassword(){
         String redireccion=null;
         FacesContext context = FacesContext.getCurrentInstance();        
         
        if(this.mipassword.equals(this.password)){
            redireccion = "homeGo";
            this.message = "Bienvenido "+this.miusuario;
        }else{
            context.addMessage(null, new FacesMessage("Mensaje", "La contraseña es incorrecta!! mofield"));
           // this.message = "La contraseña es incorrecta";
        }
       
        return redireccion;
    }
    */
   
    
    public String verificarUser(){
        
        String redireccion=null;
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            
            usuarioCad user = new usuarioCad();
            boolean result  = user.validausuer(this.user, this.password);
            
            if(result){
                return "homeGo";
            }else{
                context.addMessage(null, new FacesMessage("Mensaje", "Usuario o contraseña invalida"));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return redireccion;
    }
    
    
}