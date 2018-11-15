/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import DTO.categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRE
 */
public class categoriaCAD extends ConnectionDB {
    
    
    private static List<String> rs = null;
    
    public List<String> listadoCategorias()throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "SELECT * FROM CATEGORIAPRODUCTO";
            
            Statement sentencia = miConexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
            
            
            this.rs = new ArrayList<String>();
            
            while(resultado.next()){
                System.out.println("id: "+resultado.getString(1)+ " nombre " + resultado.getString(2));
                //categoria cat = new categoria();
                //cat.setIdcategoriaProducto(resultado.getInt(1));
                //cat.setNombre(resultado.getString(2)); 
                this.rs.add(resultado.getString(2));                
            }
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
           
           
        return rs;
    }

    
}
