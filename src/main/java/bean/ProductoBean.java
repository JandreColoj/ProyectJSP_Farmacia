
package bean;

import CAD.categoriaCAD;
import CAD.productoCAD;
import DTO.categoria;
import DTO.producto;
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
@Named(value = "ProductoBean")

public class ProductoBean {
    
    //private static List<producto> lista = new ArrayList();
    private static List<producto> lista;
    private static List<String> listaCat;


    private static producto producto;    
    private String nombre;
    private String descripcion;
    private double costo;
    private int stock;
    private String idcategoriaProducto;
    
    private static String nombreEdit;
    private static String descripcionEdit;
    private static double costoEdit;
    private static int stockEdit;
    
    private static String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStockEdit() {
        return stockEdit;
    }

    public void setStockEdit(int stockEdit) {
        this.stockEdit = stockEdit;
    }

    public String getDescripcionEdit() {
        return descripcionEdit;
    }

    public void setDescripcionEdit(String descripcionEdit) {
        this.descripcionEdit = descripcionEdit;
    }

    public double getCostoEdit() {
        return costoEdit;
    }

    public void setCostoEdit(double costoEdit) {
        this.costoEdit = costoEdit;
    }

    public String getNombreEdit() {
        return nombreEdit;
    }

    public void setNombreEdit(String nombreEdit) {
        this.nombreEdit = nombreEdit;
    }

    public  List<String> getListaCat() {
        return listaCat;
    }

    public void setListaCat(List<String> listaCat) {
        ProductoBean.listaCat = listaCat;
    }
    
    public List<producto> getLista() {
        return lista;
    }

    public void setLista(List<producto> lista) {
        this.lista = lista;
    }
    
    public static producto getProducto() {
        return producto;
    }

    public static void setProducto(producto producto) {
        ProductoBean.producto = producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIdcategoriaProducto() {
        return idcategoriaProducto;
    }

    public void setIdcategoriaProducto(String idcategoriaProducto) {
        this.idcategoriaProducto = idcategoriaProducto;
    }
   

    @PostConstruct
    public void init() {
        this.listar();
    }
    
    
    public void listar(){        
        
        productoCAD prod = new productoCAD();
        categoriaCAD cat = new categoriaCAD();

        try {
            this.lista = prod.listadoProducto(); 
            this.listaCat = cat.listadoCategorias(); 
            
        } catch (Exception ex) {
           
        }
      
    }
    
    
    public void insertar(){
        
         productoCAD prod = new productoCAD();
         producto producto = new producto();
         
         int response;
         
        producto.setNombre(this.nombre);
        producto.setDescripcion(this.descripcion);
        producto.setCosto(this.costo);
        producto.setStock(this.stock);
        producto.setIdcategoriaProducto(1);

        System.out.println(this.idcategoriaProducto);
        
        this.setNombre("");
        this.setDescripcion("");
        this.setCosto(0);
        this.setStock(0);
        
        try {
            response = prod.insertarProducto(producto);
        }catch (Exception ex) {
        }
      
        this.listar();
    }
    
    public void actualizar(){
        
        productoCAD prod = new productoCAD();
        producto producto = new producto();
        
        producto.setNombre(this.getNombreEdit());
        producto.setDescripcion(this.getDescripcionEdit());
        producto.setCosto(this.getCostoEdit());
        producto.setIdProducto(this.getId());
        producto.setStock(this.getStockEdit());
        
        
        try {
            prod.actualizarProducto(producto);
            
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
    
    public void eliminar(){
        
        productoCAD prod = new productoCAD();
        
        try {
            prod.eliminarProducto(this.getId());
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Eliminado", "eliminado correctamente"));
        }catch (Exception ex) {
        }
        
         this.listar();
    }
    
    public void btnEditar(producto pro){
        
         this.setNombreEdit(pro.getNombre());
         this.setDescripcionEdit(pro.getDescripcion());
         this.setCostoEdit(pro.getCosto());
         this.setStockEdit(pro.getStock());
         this.setId(pro.getIdProducto());
         
         System.out.print(this.getNombre());
        
    }
    
    public void btnEliminar(String idProducto){
        this.setId(idProducto);
    }
}
