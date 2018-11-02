/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDRE
 */
public class ConnectionDB{
    
    public Connection conexion;
    
    public Connection conectar(){
        
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String nombre_servidor = "127.0.0.1";
            String numero_puerto   = "1521";
            String sid = "XE";
            String url = "jdbc:oracle:thin:@"+nombre_servidor+":"+numero_puerto+":"+sid;
            
            //nombres y usuarios
            String usuario = "Jandre";
            String password = "54295430";
            
            conexion = DriverManager.getConnection(url,usuario,password);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return conexion;
       
    }

    public Connection getConexion(){
        return conexion;
    }
    
    public void cerrar(){
        
        try{
        
            if(conexion !=null){
        
                if(conexion.isClosed() == false){
                    conexion.close();
                }
            }
                    
        }catch(Exception e){
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
 
    /*
    public boolean escribir(String sql) { 
        
        try { 
            Statement sentencia; 
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql); 
            getConexion().commit(); 
            sentencia.close(); 

        } catch (SQLException e) { 
            e.printStackTrace(); 
            System.out.print("ERROR SQL"); 
            return false; 
        }         
        
        return true; 
    } 
    
    
    public ResultSet consultar(String sql) { 
        ResultSet resultado = null; 
        try { 
            Statement sentencia; 
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql); 
             
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return null; 
        }

        return resultado; 
    } 

    */
}