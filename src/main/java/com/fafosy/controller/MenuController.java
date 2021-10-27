/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fafosy.controller;

import com.fafosy.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 50232
 */
public class MenuController implements Initializable {

    Main main;
    @FXML
    private Label LabelNombreDeUsuario;
    @FXML
    private Label LabelRolDeUsuario;
    @FXML
    private Button BtnOrdenesXEntregar;
    @FXML
    private Button BtnMiCuenta;
    @FXML
    private Button BtnTomarOrden;
    @FXML
    private Button BtnEditarMenu;
    @FXML
    private Button BtnEstadisticas;
    @FXML
    private Button BtnOrdenesXAtender;
    @FXML
    private Button BtnGestionarUsuarios;
    @FXML
    private Button BtnSalir;
    
    //Este método permite usar los métodos contenidos en la clase principal
    public void setMain(Main main){
        this.main = main;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void MetodoOrdenesXEntregar(ActionEvent event) {
        //this.main.cargarOpcionDelMenu("OrdenesXEntregar.fxml");
        this.main.cargarOpcionDelMenu("TrabajoEnProceso.fxml");
    }

    @FXML
    private void MetodoMiCuenta(ActionEvent event) {
        this.main.cargarOpcionDelMenu("TrabajoEnProceso.fxml");
    }

    @FXML
    private void MetodoTomarOrden(ActionEvent event) {
        //this.main.cargarOpcionDelMenu("TomarOrden.fxml");
        this.main.cargarOpcionDelMenu("TrabajoEnProceso.fxml");
    }

    @FXML
    private void MetodoEditarMenu(ActionEvent event) {
        this.main.cargarOpcionDelMenu("EditarMenu.fxml");
    }

    @FXML
    private void MetodoEstadisticas(ActionEvent event) {
        this.main.cargarOpcionDelMenu("TrabajoEnProceso.fxml");
    }

    @FXML
    private void MetodoOrdenesXAtender(ActionEvent event) {
        this.main.cargarOpcionDelMenu("TrabajoEnProceso.fxml");
        //this.main.cargarOpcionDelMenu("OrdenesXAtender.fxml");
    }

    @FXML
    private void MetodoGestionarUsuarios(ActionEvent event) {
        this.main.cargarOpcionDelMenu("TrabajoEnProceso.fxml");
    }

    @FXML
    private void MetodoSalir(ActionEvent event) {
          this.main.SalirOptionMenu();
    }
    
}
