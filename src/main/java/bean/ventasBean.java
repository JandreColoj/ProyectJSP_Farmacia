
package bean;

import CAD.ventaCAD;
import DTO.detalleFactura;
import DTO.ventas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ANDRE
 */
@ManagedBean 
@RequestScoped
@Named(value = "ventasBean")

public class ventasBean {
    
    //private static List<producto> lista = new ArrayList();
    private static List<ventas> lista ;
    private static List<detalleFactura> detalle ;

    public List<detalleFactura> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<detalleFactura> detalle) {
        ventasBean.detalle = detalle;
    }

    public  List<ventas> getLista() {
        return lista;
    }

    public  void setLista(List<ventas> lista) {
        this.lista = lista;
    }

  
    @PostConstruct
    public void init() {
        this.listar();
    }
    
    public void listar(){        
        
        ventaCAD venta = new ventaCAD();

        try {
            this.lista = venta.listadoVenta(); 
            
        } catch (Exception ex) {

        }
      
    }
    
    public void detalleFactura(int factura){
    
        ventaCAD venta = new ventaCAD();

        try {
            this.detalle = venta.Detalle(factura); 
            
        } catch (Exception ex) {

        }
    
    }

    
}
