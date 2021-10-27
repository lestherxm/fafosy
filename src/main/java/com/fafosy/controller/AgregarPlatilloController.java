/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fafosy.controller;

import com.fafosy.Main;
import com.fafosy.model.Categoria;
import com.fafosy.model.ConnectionSQLServer;
import com.fafosy.model.MessagesForUser;
import com.fafosy.model.Platillo;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 50232
 */
public class AgregarPlatilloController implements Initializable {

    Main main;
    @FXML
    private TextField TxtNombre;
    @FXML
    private Button BtnMuscarImg;
    @FXML
    private ComboBox<String> CbCategoria;
    @FXML
    private Button BtnAgregarPlatillo;
    @FXML
    private Button BtnRegresarAEditarMenu;
    @FXML
    private TextField TxtPrecio;
    
    private ObservableList Categorias = FXCollections.observableArrayList();
    
    //Permite usar los métodos contenidos en la clase principal
    public void setMain(Main main){
        this.main = main;
    }
    
    public void CargarCategorias(){
        //Se realizar la consulta a la base de datos
         String sql = "SELECT Id, Nombre FROM Categorias;";
         ConnectionSQLServer connection = new ConnectionSQLServer();
            try {
                Statement stmt = connection.getConnectionToSQLServer().createStatement();
                ResultSet rst = stmt.executeQuery(sql);
                    while(rst.next()){// mientras haya resultados hacer esto
                         Categorias.add(rst.getString("Nombre"));
                    }
            } catch (SQLException e) {
                Logger.getLogger(EditarMenuController.class.getName()).log(Level.SEVERE, null,e);
            }
        connection.CloseConnectionSQLServer();
        CbCategoria.setItems(Categorias);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          CargarCategorias();
    }    

    @FXML
    private void MetodoBuscarImg(ActionEvent event) {
         
    }

    @FXML
    private void MetodoAgregarPlatillo(ActionEvent event) {
        try {
            //creacion de objeto, se les setea los valores ingresados por el usuario
            Platillo platillo = new Platillo(0, TxtNombre.getText(), Float.parseFloat(TxtPrecio.getText()), null, 0);
                //Se valida que los datos sean correctos
                if((platillo.getNombre().isEmpty()) || (platillo.getPrecio()==0.00)||(CbCategoria.getValue() == null)){
                    throw (new Exception());
                }else{//Si es así, se busca el id de la categoría seleccionada en la DB
                    String sql = "SELECT Id FROM Categorias WHERE Nombre = ?;";
                    ConnectionSQLServer connection = new ConnectionSQLServer();
                        try {
                             PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                             pstmt.setString(1,CbCategoria.getValue());
                             ResultSet rs = pstmt.executeQuery();
                                if(rs.next()){
                                    platillo.setIdCategoria(rs.getInt("Id"));
                                         this.main.AgregarPlatillo(platillo);
                                         this.main.RegresarAEditarMenu();
                                }else{
                                    throw(new SQLException());
                                }
                        } catch (SQLException e) {
                            Logger.getLogger(EditarMenuController.class.getName()).log(Level.SEVERE, null,e);
                        }
                    connection.CloseConnectionSQLServer();
                }
        } catch (Exception e) {
            MessagesForUser.showAlert("¡HEY!", "ASEGÚRATE DE INGRESAR CORRECTAMENTE LOS DATOS", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void MetodoRegresarAEditarMenu(ActionEvent event) {
        this.main.RegresarAEditarMenu();
    }
    
}
