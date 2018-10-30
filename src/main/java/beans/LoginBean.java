/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import javax.inject.Named;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jandre
 */
@ManagedBean
@RequestScoped
@Named(value = "LoginBean")

public class LoginBean{

    public LoginBean() {
    }
    
    private String user;
    private String password;
    private String message;
    
    static final String miusuario ="Jandre";
    static final String mipassword ="123";
    

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

    public void setMessage(String mensaje) {
        this.message = mensaje;
    }
    
    
    public String verificarUser() throws IOException{
        String redireccion=null;
        FacesContext context = FacesContext.getCurrentInstance();
                
        if(this.miusuario.equals(this.user)){
           redireccion = "irPassword";
        }else{
            context.addMessage(null, new FacesMessage("Mensaje", "El usuario es incorrecto!!"));
            
        }

        
        return redireccion;
    }
    
    public String verificarPassword(){
         String redireccion=null;
         FacesContext context = FacesContext.getCurrentInstance();        
         
        if(this.mipassword.equals(this.password)){
            redireccion = "irHome";
            this.message = "Bienvenido "+this.miusuario;
        }else{
            context.addMessage(null, new FacesMessage("Mensaje", "La contraseña es incorrecta!!"));
           // this.message = "La contraseña es incorrecta";
        }
        
        return redireccion;
    }
}