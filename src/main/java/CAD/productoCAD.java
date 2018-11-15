/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import DTO.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRE
 */
public class productoCAD extends ConnectionDB {
    
    
    private static List<producto> rs = null;

    public List<producto> listadoProducto()throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "SELECT * FROM ARTICULO WHERE ESTADO=1";
            
            Statement sentencia = miConexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
            this.rs = new ArrayList<producto>();
            
            while(resultado.next()){
               // System.out.println("id: "+resultado.getString(1)+ " nombre " + resultado.getString(2));
                producto prod = new producto();
                prod.setIdProducto(resultado.getString(1));
                prod.setNombre(resultado.getString(2));
                prod.setDescripcion(resultado.getString(3));
                prod.setCosto(resultado.getInt(4)); 
                prod.setIdcategoriaProducto(resultado.getInt(6)); 
                prod.setStock(resultado.getInt(8));
                
                this.rs.add(prod);                
            }
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
           
           
        return rs;
    }

    public int insertarProducto(producto prod)throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "INSERT INTO articulo (\"idProducto\", \"nombre\", \"descripcion\", \"costo\",\"idcategoriaProducto\",\"ESTADO\",\"STOCK\") VALUES\n" +
                         "(INDEX_REG.nextval, '"+prod.getNombre()+"','"+prod.getDescripcion()+"', '"+prod.getCosto()+"',"+prod.getIdcategoriaProducto()+",1,"+prod.getStock()+")";
            
            System.out.print(prod.getDescripcion());
            Statement sentencia = miConexion.createStatement();
            System.out.print(sql);
            
            int resultado = sentencia.executeUpdate(sql);
            getConexion().commit();
            
            return  resultado;
           
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
           
           
        return 0;
    }
       
    public int actualizarProducto(producto prod)throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "UPDATE ARTICULO\n" +
                            "SET \"nombre\" = '"+prod.getNombre()+"', \"descripcion\" = '"+prod.getDescripcion()+"', \"costo\" = "+prod.getCosto()+", \"STOCK\" = "+prod.getStock()+"\n" +
                            "WHERE \"idProducto\" = "+prod.getIdProducto();
                    
            PreparedStatement sentencia = miConexion.prepareStatement(sql);
            int retorno = sentencia.executeUpdate();
  
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
            System.out.println(sqle.getMessage());
        
        }finally{
            this.cerrar();
        }
           
           
        return 0;
    }
    
    public int eliminarProducto(int prod)throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "UPDATE ARTICULO\n" +
                            "SET \"ESTADO\" = 0" +
                            "WHERE \"idProducto\" = "+prod;
                    
            PreparedStatement sentencia = miConexion.prepareStatement(sql);
            int retorno = sentencia.executeUpdate();
  
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
            System.out.println(sqle.getMessage());
        
        }finally{
            this.cerrar();
        }
           
           
        return 0;
    }
}
