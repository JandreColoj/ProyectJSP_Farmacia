/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import DTO.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ANDRE
 */
public class usuarioCad extends ConnectionDB{
    
    public boolean validausuer(String nombre, String pass)throws  Exception{
        
        boolean response=false;
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = "SELECT * FROM USUARIO WHERE USUARIO='"+nombre+"' AND PASS='"+pass+"'";
            
            Statement sentencia = miConexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
            while(resultado.next()){
                System.out.println("nombre: "+resultado.getString(1)+ " pass " + resultado.getString(2));
                response = true;
            }
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
        return response;
    }

    
    public void registrar(usuario us){

        try {
            this.conectar();
            PreparedStatement ps = this.getConexion().prepareStatement("INSERT INTO USUARIO (id,usuario,pass) values (?,?,?)");
            ps.setString(1, "20");
            ps.setString(1, us.getUsuario());
            ps.setString(2, us.getPass());

            ps.executeUpdate();

        } catch (Exception e) {
            throw null;
        } finally{
            this.cerrar();
        }


    }
    
}
