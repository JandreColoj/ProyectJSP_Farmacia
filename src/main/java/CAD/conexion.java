/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ANDRE
 */
public class conexion {
    
    public static void main(String[] args){
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String nombre_servidor = "127.0.0.1";
            String numero_puerto   = "1521";
            String sid = "XE";
            String url = "jdbc:oracle:thin:@"+nombre_servidor+":"+numero_puerto+":"+sid;
            
            //nombres y usuarios
            String usuario = "Jandre";
            String password = "54295430";
            
            Connection conexion = DriverManager.getConnection(url,usuario,password);
            
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM ARTICULO");
            System.out.println("consulta");
            
            while(resultado.next()){
                System.out.println(resultado.getString(1)+ " " + resultado.getString(2));
            }
            
            System.out.println("despues de la consulta");
            
            sentencia.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

}