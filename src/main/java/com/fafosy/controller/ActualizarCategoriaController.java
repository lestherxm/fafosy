/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fafosy.controller;

import com.fafosy.Main;
import com.fafosy.model.Categoria;
import com.fafosy.model.MessagesForUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 50232
 */
public class ActualizarCategoriaController implements Initializable {

    Main main;
    @FXML
    private Pane ContenedorDatosLogin;
    @FXML
    private TextField TxtNombreCategoria;
    @FXML
    private Button BtnRegresar;
    @FXML
    private Button BtnActualizarCategoria;
    
    //Permite usar los métodos contenidos en la clase principal
    public void setMain(Main main){
        this.main = main;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MetodoRegresarAEditarMenu(ActionEvent event) {
        this.main.RegresarAEditarMenu();
    }

    public void cargarDatosAnteriores(Categoria categoria){
        this.TxtNombreCategoria.setText(categoria.getNombre());
    }
    
    @FXML
    private void MetodoActualizarCategoria(ActionEvent event) {
        if(this.TxtNombreCategoria.getText().isEmpty()){
             MessagesForUser.showAlert("!OYE!", "TIENES QUÉ DIGITAR EL NOMBRE DE LA CATEGORÍA", Alert.AlertType.ERROR);
        }else{
             this.main.ActualizarCategoria(new Categoria(0, this.TxtNombreCategoria.getText()));
        }
    }
    
}
