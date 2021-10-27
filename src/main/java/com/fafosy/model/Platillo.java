/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fafosy.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author 50232
 */
public class Platillo {
    //VARIABLES DE CLASE
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Nombre;
    private SimpleFloatProperty Precio;
    private ImageView Foto;
    private SimpleIntegerProperty IdCategoria;

    //CONSTRUCTOR VACIO: INICIALIZA LAS VARIABLES DE CLASE
    public Platillo(){
        this.Id = new SimpleIntegerProperty();
        this.Nombre = new SimpleStringProperty();
        this.Precio = new SimpleFloatProperty();
        this.Foto = new ImageView();
        this.IdCategoria = new SimpleIntegerProperty();
    }
    
    //CONSTRUCTOR QUE RECIBE DATOS: INICIALIZA LAS VARIABLES DE CLASE Y LES SETEA UN VALOR
    public Platillo(int Id, String Nombre, Float Precio, ImageView Foto, int IdCategoria) {
        this.Id = new SimpleIntegerProperty(Id);
        this.Nombre = new SimpleStringProperty(Nombre);
        this.Precio = new SimpleFloatProperty(Precio);
        this.Foto = Foto;
        this.IdCategoria = new SimpleIntegerProperty(IdCategoria);
    }
    
    //Getters
    public int getId(){
         return this.Id.get();
    }
    public String getNombre(){
         return this.Nombre.get();
    }
    public Float getPrecio(){
         return this.Precio.get();
    }
    public ImageView getFoto(){
        return this.Foto;
    }
    public int getIdCategoria(){
         return this.IdCategoria.get();
    }
    
    //Setters
    public void setId(int Id){
        this.Id.set(Id);
    }
    public void setNombre(String Nombre){
        this.Nombre.set(Nombre);
    }
    public void setPrecio(Float Precio){
        this.Precio.set(Precio);
    }
    public void setFoto(ImageView Foto){
        this.Foto = Foto;
    }
    public void setIdCategoria(int IdCategoria){
        this.IdCategoria.set(IdCategoria);
    }

    @Override
    public String toString() {
        return "PLATILLO {"+this.Id+", "+this.Nombre+", "+this.Precio+", "+this.IdCategoria+"}";
    }
    
}
