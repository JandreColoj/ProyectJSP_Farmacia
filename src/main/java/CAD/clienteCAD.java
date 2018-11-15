/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import DTO.cliente;
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
public class clienteCAD extends ConnectionDB {
    
    
    private static List<cliente> rs = null;

    public List<cliente> listadoClientes()throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "SELECT * FROM CLIENTE WHERE \"ESTADO\"=1";
            
            Statement sentencia = miConexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            System.out.println(sql);
            
            this.rs = new ArrayList<cliente>();
            
            while(resultado.next()){
               // System.out.println("id: "+resultado.getString(1)+ " nombre " + resultado.getString(2));
                cliente cli = new cliente();
                cli.setIdCliente(resultado.getInt(1));
                cli.setNombre(resultado.getString(2));
                cli.setApellidos(resultado.getString(3));
                cli.setTelefono(resultado.getString(4)); 
                cli.setDireccion(resultado.getString(5)); 
                cli.setDireccionActual(resultado.getString(6));
                cli.setNit(resultado.getString(8));
                
                this.rs.add(cli);                
            }
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
           
           
        return rs;
    }

    public int insertarCliente(cliente cli)throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "INSERT INTO CLIENTE (\"idCliente\",\"nombre\", \"apellidos\", \"telefono\", \"direccion\", \"NIT\") \n" +
                          "VALUES (INDEX_CLIENTE.nextval, '"+cli.getNombre()+"', '"+cli.getApellidos()+"','"+cli.getTelefono()+"','"+cli.getDireccion()+"','"+cli.getNit()+"')";
            
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
       
    public int actualizarCliente(cliente cli)throws  Exception{      
            
        int retorno = 0;
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "UPDATE CLIENTE" +
                         " SET \"nombre\" = ?, \"apellidos\" = ?, \"telefono\" = ?, \"direccion\" = ?" +
                         " WHERE \"idCliente\" = "+cli.getIdCliente();
            System.out.println(sql);
            
            PreparedStatement sentencia = miConexion.prepareStatement(sql);
            sentencia.setString(1, cli.getNombre());
            sentencia.setString(2, cli.getApellidos());
            sentencia.setString(3, cli.getTelefono());
            sentencia.setString(4, cli.getDireccion());
            
            sentencia.execute();
            System.out.println(sql);
            
            retorno = 1;
  
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
            System.out.println(sqle.getMessage());
        
        }finally{
            this.cerrar();
        }
           
           
        return 0;
    }
    
    public int eliminarCliente(int CLI)throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "UPDATE CLIENTE\n" +
                            "SET \"ESTADO\" = 0" +
                            "WHERE \"idCliente\" = "+CLI;
                    
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
