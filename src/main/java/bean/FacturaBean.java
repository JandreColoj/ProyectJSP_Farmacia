/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import CAD.clienteCAD;
import CAD.facturacionCAD;
import DTO.cliente;
import DTO.detalleFactura;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ANDRE
 */
@ManagedBean 
@RequestScoped
@Named(value = "FacturaBean")

public class FacturaBean {
    
    private static cliente cliente;
    private static float granTotal=0;
    private static List<detalleFactura> detalle = new ArrayList<detalleFactura>();
    private static int numerofactura;
    
    @PostConstruct
    public void init() {
       this.factura();
    }

    public int getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(int numerofactura) {
        this.numerofactura = numerofactura;
    }

    public float getGranTotal() {
        return granTotal;
    }

    public void setGranTotal(float granTotal) {
        this.granTotal = granTotal;
    }


        
    public  List<detalleFactura> getDetalle() {
        return detalle;
    }

    public  void setDetalle(List<detalleFactura> detalle) {
        this.detalle = detalle;
    }
    

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }
    
     public void agregarDatosCliente(int id, String nombre, String apellido, String telefono, String nit){
         
        cliente = new cliente();
         
        this.cliente.setIdCliente(id);
        this.cliente.setNombre(nombre);
        this.cliente.setApellidos(apellido);
        this.cliente.setTelefono(telefono);
        this.cliente.setNit(nit);
        
        
    }
     
     public void agregarProducto(String id, String nombre, String descrip, float costo){
         
         detalleFactura d = new detalleFactura();
         
         d.setIdProd(id);
         d.setNombre(nombre);
         d.setDescripcion(descrip);
         d.setCosto(costo);
         
         this.detalle.add(d);
         this.totalFactura();
         
     }
     
     public void totalFactura(){
     
         for (detalleFactura factura : this.detalle) {
            this.setGranTotal(this.granTotal+factura.getCosto());
         }
     }
     
     public void registrar(){
        
         facturacionCAD fac = new facturacionCAD();
        
         try {
             
            fac.registrar(this.cliente,this.detalle,this.granTotal,this.numerofactura);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Registrado", "registrado correctamente"));
            
            this.cliente = null; 
            this.detalle  = new ArrayList<detalleFactura>();
            this.granTotal = 0;
            this.factura();
            
        }catch (Exception ex) {
        }
        
         
     }
     
    public void factura(){
        
        facturacionCAD fac = new facturacionCAD();
        
        try {
            this.numerofactura = fac.noFactura();
    
            
        }catch (Exception ex) {
        }
        
    }
    
}
