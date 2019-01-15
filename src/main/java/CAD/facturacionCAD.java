/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import DTO.cliente;
import DTO.detalleFactura;
import DTO.usuario;
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
public class facturacionCAD extends ConnectionDB{
    
    
    public void registrar(cliente cli, List<detalleFactura> detalle, float total, int fac){

        this.conectar();
       
        try {
           
             
            String sql ="INSERT INTO FACTURA (\"CODFACTURA\", \"NUMFACTURA\", \"CODCLIENTE\",\"TOTALVENTA\",\"ESTADO\")"
                    + "  values (INDEX_FACTURA.nextval,INDEX_FACTURA.nextval,'"+cli.getIdCliente()+"','"+total+"',1)";
            
            Statement ps = this.getConexion().createStatement();
            
            int resultado = ps.executeUpdate(sql);
            getConexion().commit();
            
            for (detalleFactura det : detalle) {
                
                 sql ="INSERT INTO DETALLEFACTURA (\"CODDETALLE\", \"CODFACTURA\", \"CODPRODUCTO\",\"NOMPRODUCTO\",\"CANTIDAD\",\"PRECIO\",\"TOTAL\")"
                    + "  values (INDEX_DETALLE.nextval,'"+fac+"','"+det.getIdProd()+"','"+det.getNombre()+"',"+det.getCantidad()+",'"+det.getCosto()+"','"+det.getCosto()+"')";
            
                 System.out.println(sql);
                 System.out.println(det.getNombre());
                 ps = this.getConexion().createStatement();

                 resultado = ps.executeUpdate(sql);
                getConexion().commit();

            }

        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
            System.out.println(sqle.getMessage());
        } finally{
            this.cerrar();
        }


    }
    
  
    
    
     public int noFactura()throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
            String sql = " SELECT * FROM FACTURA  ORDER BY  \"CODFACTURA\" DESC ";
            
            Statement sentencia = miConexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            System.out.println(sql);
            
            int result = 0;
            
            while(resultado.next()){
                result = resultado.getInt(1);
                break;
            }
            
            return result;
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
           
           
        return 0;
    }


    
}
