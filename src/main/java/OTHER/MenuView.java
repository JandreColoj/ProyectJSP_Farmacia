/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTHER;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author ANDRE
 */

@ManagedBean
public class MenuView {
     
    private MenuModel model;
 
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
         
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dashboard");

        DefaultMenuItem item = new DefaultMenuItem("Escritorio");
        item.setUrl("home.xhtml");
        item.setIcon("ui-icon-home");
        firstSubmenu.addElement(item);
        
        DefaultMenuItem item1 = new DefaultMenuItem("Ventas");
        item1.setUrl("ventas.xhtml");
        item1.setIcon("ui-icon-cart");
        firstSubmenu.addElement(item1);
        
        DefaultMenuItem item2 = new DefaultMenuItem("Productos");
        item2.setUrl("productos.xhtml");
        item2.setIcon("ui-icon-suitcase");
        firstSubmenu.addElement(item2);
         
        DefaultMenuItem item3 = new DefaultMenuItem("Clientes");
        item3.setUrl("clientes.xhtml");
        item3.setIcon("ui-icon-person");
        firstSubmenu.addElement(item3);
         
        DefaultMenuItem item5 = new DefaultMenuItem("Facturacion");
        item5.setUrl("facturacion.xhtml");
        item5.setIcon("ui-icon-tag");
        firstSubmenu.addElement(item5);
        
        model.addElement(firstSubmenu);
         
        //Second submenu
        /*DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
 
        item = new DefaultMenuItem("Save");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{menuView.save}");
        item.setUpdate("messages");
        secondSubmenu.addElement(item);
         
        item = new DefaultMenuItem("Delete");
        item.setIcon("ui-icon-close");
        item.setCommand("#{menuView.delete}");
        item.setAjax(false);
        secondSubmenu.addElement(item);
         
        item = new DefaultMenuItem("Redirect");
        item.setIcon("ui-icon-search");
        item.setCommand("#{menuView.redirect}");
        secondSubmenu.addElement(item);
 
        model.addElement(secondSubmenu);*/
    }
 
    public MenuModel getModel() {
        return model;
    }   
     
    public void save() {
        addMessage("Success", "Data saved");
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}