
package bean;

import CAD.clienteCAD;
import CAD.productoCAD;
import DTO.cliente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ANDRE
 */
@ManagedBean 
@RequestScoped
@Named(value = "ClienteBean")

public class ClienteBean {
    
    //private static List<producto> lista = new ArrayList();
    private static List<cliente> lista;

    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String nit;
    
    private String nombreEdit;
    private String apellidoEdit;
    private String telefonoEdit;
    private String direccionEdit;
    
    private int id;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockEdit() {
        return stockEdit;
    }

    public void setStockEdit(int stockEdit) {
        this.stockEdit = stockEdit;
    }
    private int stockEdit;


    public String getNombreEdit() {
        return nombreEdit;
    }

    public String getApellidoEdit() {
        return apellidoEdit;
    }

    public void setApellidoEdit(String apellidoEdit) {
        this.apellidoEdit = apellidoEdit;
    }

    public String getTelefonoEdit() {
        return telefonoEdit;
    }

    public void setTelefonoEdit(String telefonoEdit) {
        this.telefonoEdit = telefonoEdit;
    }

    public String getDireccionEdit() {
        return direccionEdit;
    }

    public void setDireccionEdit(String direccionEdit) {
        this.direccionEdit = direccionEdit;
    }

    public void setNombreEdit(String nombreEdit) {
        this.nombreEdit = nombreEdit;
    }
    
    public List<cliente> getLista() {
        return lista;
    }

    public void setLista(List<cliente> lista) {
        this.lista = lista;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    @PostConstruct
    public void init() {
        this.listar();
    }
    
    public void listar(){        
        
        clienteCAD cliente = new clienteCAD();

        try {
            this.lista = cliente.listadoClientes(); 
            
        } catch (Exception ex) {

        }
      
    }
    
    
    public void insertar(){
        
        clienteCAD cli = new clienteCAD();
        
        cliente cliente = new cliente();

        int response;
         
        cliente.setNombre(this.nombre);
        cliente.setApellidos(this.apellidos);
        cliente.setDireccion(this.direccion);
        cliente.setTelefono(this.telefono);
        cliente.setNit(this.nit);
        
        this.nombre = "";
        this.apellidos = "";
        this.direccion ="";
        this.telefono = "";
        this.nit ="";

        try {
            response = cli.insertarCliente(cliente);
        }catch (Exception ex) {
        }
      
        this.listar();
    }
    
    
    public void actualizar(RowEditEvent event){
        cliente c = (cliente) event.getObject();
        
        clienteCAD cli = new clienteCAD();
        cliente cliente = new cliente();
        
        cliente.setNombre(this.nombreEdit);
        cliente.setApellidos(this.apellidoEdit);
        cliente.setDireccion(this.direccionEdit);
        cliente.setTelefono(this.telefonoEdit);   
        cliente.setIdCliente(c.getIdCliente());
        
        try {
            cli.actualizarCliente(cliente);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Actualizado", "Actualizado correctamente"));
        }catch (Exception ex) {
        }
        
        this.listar();
    }
    
    public void cancelar(RowEditEvent event){
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cancelado", "Cancelado"));
    }
    
    public void eliminar(int id){
        
        clienteCAD cli = new clienteCAD();
        
        try {
            cli.eliminarCliente(id);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminado", "eliminado correctamente"));
        }catch (Exception ex) {
        }
        
         this.listar();
    }
    
    
    //facturacion
    
}
