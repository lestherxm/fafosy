/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fafosy.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author 50232
 */
public class Categoria {
    //Variables de Clase @Categoria
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Nombre;

    //Constructor Vacio
    public Categoria() {
        this.Id = new SimpleIntegerProperty();
        this.Nombre = new SimpleStringProperty();
    }
    //Constructor que recibe datos
    public Categoria(int Id, String Nombre) {
        this.Id = new SimpleIntegerProperty(Id);
        this.Nombre = new SimpleStringProperty(Nombre);
    }
    //Getters
    public int getId(){
         return this.Id.get();
    }
    public String getNombre(){
         return this.Nombre.get();
    }

    //Setters
    public void setId(int Id){
        this.Id.set(Id);
    }
    public void setNombre(String Nombre){
        this.Nombre.set(Nombre);
    }

    @Override
    public String toString() {
        return "CATEGORIA {"+this.Id+", "+this.Nombre+"}";
    }
   
}
