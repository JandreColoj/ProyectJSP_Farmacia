/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAD;

import DTO.cliente;
import DTO.detalleFactura;
import DTO.ventas;
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
public class ventaCAD extends ConnectionDB {
    
    
    private static List<ventas> rs = null;
    private static List<detalleFactura> rf = null;

    public List<ventas> listadoVenta()throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
             /*String sql = "SELECT FACTURA.CODFACTURA, FACTURA.\"TOTALVENTA\", CLIENTE.\"nombre\", CLIENTE.NIT, CLIENTE.\"direccion\" FROM FACTURA\n" +
                        "INNER JOIN \n" +
                        "DETALLEFACTURA ON FACTURA.NUMFACTURA = DETALLEFACTURA.CODFACTURA\n" +
                        "INNER JOIN\n" +
                        "CLIENTE ON FACTURA.CODCLIENTE = CLIENTE.\"idCliente\" WHERE FACTURA.ESTADO=1";*/
            
            String sql = "SELECT FACTURA.CODFACTURA, FACTURA.\"TOTALVENTA\", CLIENTE.\"nombre\", CLIENTE.NIT, CLIENTE.\"direccion\" FROM FACTURA\n" +
                        "INNER JOIN\n" +
                        "CLIENTE ON FACTURA.CODCLIENTE = CLIENTE.\"idCliente\" WHERE FACTURA.ESTADO=1 ORDER BY  \"CODFACTURA\" DESC";
            
            Statement sentencia = miConexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            System.out.println(sql);
            
            this.rs = new ArrayList<ventas>();
            
            while(resultado.next()){
               // System.out.println("id: "+resultado.getString(1)+ " nombre " + resultado.getString(2));
                ventas ven = new ventas();
                ven.setFactura(resultado.getInt(1));
                ven.setTotal(resultado.getFloat(2));
                ven.setNombre(resultado.getString(3));
                ven.setNit(resultado.getString(4));
                ven.setDireccion(resultado.getString(5));
                
                this.rs.add(ven);                
            }
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
           
           
        return rs;
    }
    
    
    public List<detalleFactura> Detalle(int factura) throws  Exception{      
        
        try {
            
            Connection miConexion = this.conectar();
            
                String sql = "SELECT *\n" +
                             "FROM DETALLEFACTURA\n" +
                             "INNER JOIN\n" +
                             "ARTICULO ON DETALLEFACTURA.CODPRODUCTO = ARTICULO.\"idProducto\" WHERE DETALLEFACTURA.CODFACTURA="+factura;
            
            
            Statement sentencia = miConexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
            System.out.println("detalle de la factura");
            System.out.println(sql);
            
            this.rf = new ArrayList<detalleFactura>();
            
            while(resultado.next()){
                System.out.println("cod producto: "+resultado.getString(1)+ " nombre " + resultado.getString(4));
                detalleFactura det = new detalleFactura();
                det.setIdProd(resultado.getString(1));
                det.setNombre(resultado.getString(4));
                det.setDescripcion(resultado.getString(10));
                det.setCantidad(resultado.getInt(5));
                det.setCosto(resultado.getFloat(6));
                this.rf.add(det);              
            }
            
        } catch (Exception sqle) {
            Throwable t = sqle.getCause();
        
        }finally{
            this.cerrar();
        }
           
           
        return rf;
    }

}
