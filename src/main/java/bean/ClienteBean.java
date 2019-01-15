
package bean;

import CAD.clienteCAD;
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
    
    private static String nombreEdit;
    private static String apellidoEdit;
    private static String telefonoEdit;
    private static String direccionEdit;
    private static String nitEdit;
    private static int id;  
    private static int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }



        
    public String getNitEdit() {
        return nitEdit;
    }

    public void setNitEdit(String nitEdit) {
        this.nitEdit = nitEdit;
    }


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
    
    
    public void actualizar(){
        
        clienteCAD cli = new clienteCAD();
        cliente cliente = new cliente();
        
        cliente.setNombre(this.getNombreEdit());
        cliente.setApellidos(this.getApellidoEdit());
        cliente.setDireccion(this.getDireccionEdit());
        cliente.setTelefono(this.getTelefonoEdit());   
        cliente.setIdCliente(this.getIdCliente());
        
        
        try {
            cli.actualizarCliente(cliente);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Actualizado correctamente"));
            
           
        }catch (Exception ex) {
        }
        
        this.listar();
    }
    
    public void cancelar(RowEditEvent event){
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cancelado", "Cancelado"));
    }
    
    public void eliminar(){
        
        clienteCAD cli = new clienteCAD();
        
        try {
            cli.eliminarCliente(this.getIdCliente());
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminado", "eliminado correctamente"));
        }catch (Exception ex) {
        }
        
         this.listar();
    }
    
    
    public void btnEditar(cliente cli){
        
        this.setNombreEdit(cli.getNombre());
        this.setApellidoEdit(cli.getApellidos());
        this.setTelefonoEdit(cli.getTelefono());
        this.setDireccionEdit(cli.getDireccion());
        this.setNitEdit(cli.getNit());
        this.setIdCliente(cli.getIdCliente());
        this.setId(cli.getIdCliente());
        
    }
    
    
    public void btnEliminar(int idCliente){
        this.setIdCliente(idCliente);
    }
    //facturacion
    
}
