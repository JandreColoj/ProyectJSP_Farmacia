/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author ANDRE
 */
public class producto {
    
    private String idProducto;
    private String nombre;
    private String descripcion;
    private double costo;
    private Date caducidad;
    private int idcategoriaProducto;
    private int stock;
   
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public int getIdcategoriaProducto() {
        return idcategoriaProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    public void setIdcategoriaProducto(int idcategoriaProducto) {
        this.idcategoriaProducto = idcategoriaProducto;
    }
   
        
    
}
