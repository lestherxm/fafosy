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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 50232
 */
public class EditarMenuController implements Initializable {

    Main main;
    @FXML
    private Button BtnRegresarAlMenu;
    @FXML
    private TableView<Categoria> TablaCategoria;
    @FXML
    private TableColumn<Categoria,String> ItemCategoria;
    @FXML
    private Button BtnAgregarCategoria;
    @FXML
    private Button BtnActualizarCategoria;
    @FXML
    private Button BtnEliminarCategoria;
    @FXML
    private TableView<Platillo> TablaPlatillos;
    @FXML
    private TableColumn<Platillo, String> NombrePlatillo;
    @FXML
    private TableColumn<Platillo, String> FotoPlatillo;
    @FXML
    private TableColumn<Platillo, Float> PrecioPlatillo;
    @FXML
    private Button BtnAgregarPlatillo;
    @FXML
    private Button BtnActualizarPlatillo;
    @FXML
    private Button BtnEliminarPlatillo;
    
    //Estructura de datos que almacenará los datos de las Categorias
    ObservableList ListaCategorias = FXCollections.observableArrayList();
    ObservableList ListaPlatillosDeCategoriaX = FXCollections.observableArrayList();
    
    //Objetos que almacenarán los datos de de los elemtos seleccionados de la tabla
    Categoria CategoriaSeleccionada;
    Platillo PlatilloSeleccionado;
    
    //Permite usar los métodos contenidos en la clase principal
    public void setMain(Main main){
        this.main = main;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObtenerCategorias();
             TablaConClaseYLista();
                 AplicarSelectionModel();
    }    

    @FXML
    private void MetodoRegresarAlMenu(ActionEvent event) {
        this.main.RegresarAlMenu();
    }

    @FXML
    private void AbrirAgregarCategoria(ActionEvent event) {
        this.main.CargarOpcionDelStageEditarMenu("AgregarCategoria.fxml",null,null);
    }

    public void AgregarCategoria(Categoria categoria){

        String sql = "INSERT INTO Categorias(Nombre) VALUES(?);";
        ConnectionSQLServer connection = new ConnectionSQLServer();
            try {
                 PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                 pstmt.setString(1, categoria.getNombre());
                 int answer = pstmt.executeUpdate();
                     if(answer>0){
                         ObtenerCategorias();
                         //MessagesForUser.showAlert("!ATENCIÓN!", "SE HA REGISTRADO LA CATEGORÍA", Alert.AlertType.INFORMATION);
                     }else{
                         throw(new SQLException());
                     }
                     
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
                MessagesForUser.showAlert("!ERROR!", "NO SE HA PODIDO INGRESAR LA CATEGORÍA", Alert.AlertType.ERROR);
            }
        connection.CloseConnectionSQLServer();
            
    }
    
    @FXML
    private void AbrirActualizarCategoria(ActionEvent event) {
        this.main.CargarOpcionDelStageEditarMenu("ActualizarCategoria.fxml",CategoriaSeleccionada,null);
    }

    public void ActualizarCategoria(Categoria categoria){
        if(CategoriaSeleccionada.getNombre().equals(categoria.getNombre())){
             MessagesForUser.showAlert("¡HEY!","NO HUBO CAMBIOS EN EL NOMBRE DE LA CATEGORÍA", Alert.AlertType.INFORMATION);
        }else{
             String sql = "UPDATE Categorias SET Nombre = ? WHERE Id = ?;";
             ConnectionSQLServer connection = new ConnectionSQLServer();
                 try {
                     PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                     pstmt.setString(1, categoria.getNombre());
                     pstmt.setInt(2, CategoriaSeleccionada.getId());
                     int answer = pstmt.executeUpdate();
                        if(answer>0){
                             ObtenerCategorias();
                             //MessagesForUser.showAlert("!ATENCIÓN!", "SE HA ACTUALIZADO LA CATEGORÍA", Alert.AlertType.INFORMATION);
                        }else{
                             throw(new SQLException());
                        }
                
                 } catch (SQLException e) {
                     System.out.println(e.getMessage());
                     MessagesForUser.showAlert("!ERROR!", "NO SE HA PODIDO ACTUALIZAR LA CATEGORÍA", Alert.AlertType.ERROR);
                 }
             connection.CloseConnectionSQLServer();
        }
        
    }
    
    @FXML
    private void MetodoEliminarCategoria(ActionEvent event) {
        if(MessagesForUser.showConfirmation("¡ATENCIÓN!", "SE VA A ELIMINAR LA CATEGORÍA SELECCIONADA", Alert.AlertType.CONFIRMATION)==ButtonType.OK){
             String sql = "DELETE FROM Categorias WHERE Id= ?;";
             ConnectionSQLServer connection = new ConnectionSQLServer();
                 try {
                     PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                     pstmt.setInt(1, CategoriaSeleccionada.getId());
                     int answer = pstmt.executeUpdate();
                         if(answer>0){ 
                             ObtenerCategorias();
                             //MessagesForUser.showAlert("!ATENCIÓN!", "SE HA ELIMINADO LA CATEGORÍA", Alert.AlertType.INFORMATION);
                         }else{
                             throw(new SQLException());
                         }
                    } catch (SQLException e) {
                     System.out.println(e.getMessage());
                     MessagesForUser.showAlert("!ERROR!", "NO SE HA PODIDO ELIMINAR LA CATEGORÍA SELECCIONADA", Alert.AlertType.ERROR);
                 }
             connection.CloseConnectionSQLServer();
        }
    }

    @FXML
    private void AbrirAgregarPlatillo(ActionEvent event) {
        this.main.CargarOpcionDelStageEditarMenu("AgregarPlatillo.fxml",null,null);
    }
    
    public void AgregarPlatillo(Platillo platillo){
        String sql = "INSERT INTO Platillos(Nombre,Precio,IdCategoria) VALUES(?,?,?);";
        ConnectionSQLServer connection = new ConnectionSQLServer();
            try {
                 PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                 pstmt.setString(1, platillo.getNombre());
                 pstmt.setFloat(2, platillo.getPrecio());
                 pstmt.setInt(3, platillo.getIdCategoria());
                 int answer = pstmt.executeUpdate();
                     if(answer>0){
                         ObtenerCategorias();
                         //MessagesForUser.showAlert("!ATENCIÓN!", "SE HA REGISTRADO EL PLATILLO CORRECTAMENTE", Alert.AlertType.INFORMATION);
                     }else{
                         throw(new SQLException());
                     }
                     
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
                MessagesForUser.showAlert("!ERROR!", "NO SE HA PODIDO INGRESAR EL PLATILLO", Alert.AlertType.ERROR);
            }
        connection.CloseConnectionSQLServer();
    }
    
    @FXML
    private void AbrirActualizarPlatillo(ActionEvent event) {
        this.main.CargarOpcionDelStageEditarMenu("ActualizarPlatillo.fxml",null,PlatilloSeleccionado);
    }

    public void ActualizarPlatillo(Platillo platillo){
        if((PlatilloSeleccionado.getNombre().equals(platillo.getNombre())) &&(Objects.equals(PlatilloSeleccionado.getPrecio(), platillo.getPrecio()))&&(PlatilloSeleccionado.getIdCategoria() == platillo.getIdCategoria())){
             MessagesForUser.showAlert("¡HEY!","NO HUBO CAMBIOS EN LOS DATOS DEL PLATILLO", Alert.AlertType.INFORMATION);
        }else{
             String sql = "UPDATE Platillos SET Nombre = ?, Precio = ?, IdCategoria=? WHERE Id = ?;";
             ConnectionSQLServer connection = new ConnectionSQLServer();
                 try {
                     PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                     pstmt.setString(1, platillo.getNombre());
                     pstmt.setFloat(2, platillo.getPrecio());
                     pstmt.setInt(3, platillo.getIdCategoria());
                     pstmt.setInt(4, PlatilloSeleccionado.getId());
                     int answer = pstmt.executeUpdate();
                        if(answer>0){
                             ObtenerCategorias();
                             //MessagesForUser.showAlert("!ATENCIÓN!", "SE HA ACTUALIZADO LA EL PLATILLO CON ÉXITO", Alert.AlertType.INFORMATION);
                        }else{
                             throw(new SQLException());
                        }
                
                 } catch (SQLException e) {
                     System.out.println(e.getMessage());
                     MessagesForUser.showAlert("!ERROR!", "NO SE HA PODIDO ACTUALIZAR EL PLATILLO", Alert.AlertType.ERROR);
                 }
            connection.CloseConnectionSQLServer();
        }
    }
    
    @FXML
    private void MetodoEliminarPlatillo(ActionEvent event) {
        if(MessagesForUser.showConfirmation("¡ATENCIÓN!", "SE VA A ELIMINAR EL PLATILLO SELECCIONADO", Alert.AlertType.CONFIRMATION)==ButtonType.OK){
             String sql = "DELETE FROM Platillos WHERE Id= ?;";
             ConnectionSQLServer connection = new ConnectionSQLServer();
                 try {
                     PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                     pstmt.setInt(1, PlatilloSeleccionado.getId());
                     int answer = pstmt.executeUpdate();
                         if(answer>0){ 
                             ObtenerCategorias();
                             //MessagesForUser.showAlert("!ATENCIÓN!", "SE HA ELIMINADO EL PLATILLO", Alert.AlertType.INFORMATION);
                         }else{
                             throw(new SQLException());
                         }
                    } catch (SQLException e) {
                     System.out.println(e.getMessage());
                     MessagesForUser.showAlert("!ERROR!", "NO SE HA PODIDO ELIMINAR EL PLATILLO", Alert.AlertType.ERROR);
                 }
            connection.CloseConnectionSQLServer();
        }
    }
    
    // ESTE METODO SE ENCARGA DE RELACIONAR LAS COLUMNAS DE LAS TABLAS CON UNA RESPECTIVA PROPIEDAD DE UNA CLASE Y
    // RELACIONAR DICHAS TABLAS CON UNA LISTA OBSERVABLE QUE CONTIENE OBJETOS DE TIPO CATEGORIA  Y PLATILLOS
    public void TablaConClaseYLista(){
        //Tabla categoria   
        ItemCategoria.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
           TablaCategoria.setItems(ListaCategorias);
        //Tabla platillos
        NombrePlatillo.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        FotoPlatillo.setCellValueFactory(new PropertyValueFactory<>("Foto"));
        PrecioPlatillo.setCellValueFactory(new PropertyValueFactory<>("Precio"));
            TablaPlatillos.setItems(ListaPlatillosDeCategoriaX);
    }
    
    // ESTE METODO SE ENCARGA DE CONSULTAR A LA BASE DE DATOS LAS TUPLAS DE LA TABLA CATEGORIA 
    public void ObtenerCategorias(){
        //Se limpia los posibles datos anteriores de la lista Categorias
        ListaCategorias.clear();
        ListaPlatillosDeCategoriaX.clear();
        
        //Se realizar la consulta a la base de datos
        String sql = "SELECT Id,Nombre FROM Categorias;";
        ConnectionSQLServer connection = new ConnectionSQLServer();
            try {
                Statement stmt = connection.getConnectionToSQLServer().createStatement();
                ResultSet rst = stmt.executeQuery(sql);
                    while(rst.next()){// mientras haya resultados hacer esto
                        Categoria categoria = new Categoria();
                        categoria.setId(rst.getInt("Id"));
                        categoria.setNombre(rst.getString("Nombre"));
                            ListaCategorias.add(categoria);
                                 AjustePostInsertOrUpdate();
                    }
            } catch (SQLException e) {
                Logger.getLogger(EditarMenuController.class.getName()).log(Level.SEVERE, null,e);
            }
        connection.CloseConnectionSQLServer();
    }
    
    public void AjustePostInsertOrUpdate(){
        CategoriaSeleccionada = null; //el seleccionado pasa a ser null
        PlatilloSeleccionado = null;
        BtnActualizarCategoria.setDisable(true);
        BtnEliminarCategoria.setDisable(true);
        BtnActualizarPlatillo.setDisable(true);
        BtnEliminarPlatillo.setDisable(true);
    }
    
    //ESTE METODO SE ENCARGA DE OBTENER LOS PLATILLOS CON BASE A LA CATEGORIA SELECCIOANDA.
    public void ObtenerPlatillosDeCategoriaX(){
        if(CategoriaSeleccionada!=null){
            //Se limpia los posibles datos anteriores de la lista de platillos
            ListaPlatillosDeCategoriaX.clear();
            //Se realiza la consulta a la base de datos
            String sql = "SELECT Id, Nombre, Foto, Precio, IdCategoria FROM Platillos WHERE IdCategoria = ?;";
            ConnectionSQLServer connection = new ConnectionSQLServer();
                 try {
                      PreparedStatement pstmt = connection.getConnectionToSQLServer().prepareStatement(sql);
                      pstmt.setInt(1, CategoriaSeleccionada.getId());
                      ResultSet rst = pstmt.executeQuery();
                         while(rst.next()){//mientras haya datos hacer esto
                             Platillo platillo = new Platillo();
                             platillo.setId(rst.getInt("Id"));
                             platillo.setNombre(rst.getString("Nombre"));
                             ImageView Foto_ = new ImageView(new Image(rst.getBinaryStream("Foto")));
                             platillo.setFoto(Foto_);
                             platillo.setPrecio(rst.getFloat("Precio"));
                                 ListaPlatillosDeCategoriaX.add(platillo);
                         }
                 } catch (SQLException e) {
                    Logger.getLogger(EditarMenuController.class.getName()).log(Level.SEVERE, null,e);
                }
            connection.CloseConnectionSQLServer();
        }
    }

    private void AplicarSelectionModel() {
        //Listener para la tabla categoria
        TablaCategoria.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                CategoriaSeleccionada = TablaCategoria.getSelectionModel().getSelectedItem();
                System.out.println(CategoriaSeleccionada);
                //Se habilitan los botones para actualizar y eliminar los elementos
                BtnActualizarCategoria.setDisable(false);
                BtnEliminarCategoria.setDisable(false);
                //Obtener los platillos de la categoria seleccionada
                ObtenerPlatillosDeCategoriaX();
            } 
        });
        
        TablaPlatillos.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                PlatilloSeleccionado = TablaPlatillos.getSelectionModel().getSelectedItem();
                System.out.println(PlatilloSeleccionado);
                BtnActualizarPlatillo.setDisable(false);
                BtnEliminarPlatillo.setDisable(false);
            }
            
        });
        
    }
    
}
