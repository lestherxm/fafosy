package com.fafosy;

import com.fafosy.controller.*; //importacion de todos los controladores del proyecto.
import com.fafosy.model.Categoria;
import com.fafosy.model.Platillo;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * JavaFX Main
 */
public class Main extends Application {
    //variables de clase
    String $Path = "/View/Fxml/";
    
    //escenarios de la app
    private Stage LoginStage;
    private Stage MenuStage;
    private Stage OpcionDeMenuStage; //Este escenario es usado por cada una de las opciones del Menu.fxml
    private Stage OpcionEditarMenuStage; //Este escenario es usado por cada una de las opciones de EditarMenu.fxml
    
    //controladores de la app 
    private EditarMenuController EditarMenuCont;
    
    @Override
    public void start(Stage stage) throws IOException {
            this.LoginStage = stage; //se carga el escenario de la app
                 cargarLogin();
    }

    // @Metodo_de_carga: Login.fxml
    // lectura del archivo fxml, setear la clase main al controlador de dicho archivo y mostrar la ventana como tal 
    public void cargarLogin(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource($Path+"Login.fxml")); //carga de archivo
                Parent root = loader.load(); //carga de archivo
                    Scene scene = new Scene(root); //se setea el archivo cargado a una escena
                    this.LoginStage.setScene(scene); //se setea la escena al escenario
                        LoginController LoginCont = loader.getController(); //se obtiene el controlador del archivo .fxml
                        LoginCont.setMain(this); //se ejecuta el metodo setMain del controlador del Login
                            this.LoginStage.setResizable(false);
                            this.LoginStage.show(); //se muestra el secenario/ventana del login
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
     // @Metodo_de_carga: Menu.fxml
    public void cargarMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource($Path+"Menu.fxml"));
                Parent root = loader.load();
                    Scene scene = new Scene(root);
                        this.MenuStage = new Stage();
                        this.MenuStage.setScene(scene);
                            MenuController MenuCont = loader.getController();
                            MenuCont.setMain(this);
                                this.MenuStage.setResizable(false);
                                this.MenuStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // @Metodo_de_carga: Opciones del Menu.fxml
    public void cargarOpcionDelMenu(String FileName){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource($Path+FileName));
                Parent root = loader.load();
                    Scene scene = new Scene(root);
                        this.OpcionDeMenuStage = new Stage();
                        this.OpcionDeMenuStage.setScene(scene);
                        this.OpcionDeMenuStage.initOwner(this.MenuStage); //Se le indica el escenario de donde "viene"
                        this.OpcionDeMenuStage.initModality(Modality.WINDOW_MODAL); //se le indica el comportamiento de la ventana
                             this.OpcionDeMenuStage.setResizable(false); // para que el usuario no pueda modificar a pantalla completa ya que no es responsive la App
                             this.OpcionDeMenuStage.show(); //se muestra el escenario/vetana como tal
                                    //Enlazar El controlador con base en el archivo .fxml cargado
                                     switch(FileName){
                                         case "TomarOrden.fxml":
                                             TomarOrdenController TomarOrdenCont = loader.getController();
                                             TomarOrdenCont.setMain(this);
                                         break;
                                         case "OrdenesXAtender.fxml":
                                             OrdenesXAtenderController OrdenesXATenderCont = loader.getController();
                                             OrdenesXATenderCont.setMain(this);
                                         break;
                                         case "OrdenesXEntregar.fxml":
                                             OrdenesXEntregarController OrdenesXEntregarCont = loader.getController();
                                             OrdenesXEntregarCont.setMain(this);
                                         break;
                                         case "EditarMenu.fxml":
                                             this.EditarMenuCont = loader.getController();
                                             this.EditarMenuCont.setMain(this);
                                         break;
                                         case "TrabajoEnProceso.fxml":
                                             TrabajoEnProcesoController TrabajoEnProcesoCont = loader.getController();
                                             TrabajoEnProcesoCont.setMain(this);
                                         break;
                                     }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
    // ESTE METODO CON BASE EN EL NOMBRE DE ARCHIVO QUE RECIBE CARGA EL MISMO Y LO SETEA AL ESCENARIO OpcionDelStageEditarMenu
    public void CargarOpcionDelStageEditarMenu(String FileName, Categoria categoria, Platillo platillo){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource($Path+FileName));
                Parent root = loader.load();
                    Scene scene = new Scene(root);
                        this.OpcionEditarMenuStage = new Stage();
                        this.OpcionEditarMenuStage.setScene(scene);
                        this.OpcionEditarMenuStage.initOwner(this.OpcionDeMenuStage); //Se le indica el escenario de donde "viene"
                        this.OpcionEditarMenuStage.initModality(Modality.WINDOW_MODAL); //se le indica el comportamiento de la ventana
                             this.OpcionEditarMenuStage.setResizable(false); // para que el usuario no pueda modificar a pantalla completa ya que no es responsive la App
                             this.OpcionEditarMenuStage.show(); //se muestra el escenario/vetana como tal
                                    //Enlazar El controlador con base en el archivo .fxml cargado
                                     switch(FileName){
                                         case "AgregarCategoria.fxml":
                                              AgregarCategoriaController AgregarCategoriaCont = loader.getController();
                                              AgregarCategoriaCont.setMain(this);
                                         break;
                                         case "ActualizarCategoria.fxml":
                                              ActualizarCategoriaController ActualizarCategoriaCont = loader.getController();
                                              ActualizarCategoriaCont.setMain(this);
                                              ActualizarCategoriaCont.cargarDatosAnteriores(categoria);
                                         break;
                                         case "AgregarPlatillo.fxml":
                                              AgregarPlatilloController AgregarPlatilloCont = loader.getController();
                                              AgregarPlatilloCont.setMain(this);
                                         break;
                                         case "ActualizarPlatillo.fxml":
                                              ActualizarPlatilloController ActualizarPlatilloCont = loader.getController();
                                              ActualizarPlatilloCont.setMain(this);
                                              ActualizarPlatilloCont.CargarDatosAnteriores(platillo);
                                         break;
                                     }
        } catch (IOException ex) {
            System.out.println("ERROR AL CARGAR EL ARCHIVO Y SETEAR CONTROLADOR");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    // METODO PARA IR AL MENU UNA VEZ SE HA PASADO EL LOGIN
    public void IrAlMenuDesdeLogin(){
        cargarMenu();
            this.LoginStage.close();
    }
    
    // METODO QUE ME PERMITE IR DEL MENU HACIA EL LOGIN
    public void SalirOptionMenu(){
        cargarLogin(); //se carga el escenario del login.
            this.MenuStage.close(); //se cierra el escenario del menu
    }
    
    // METODO QUE PERMITE REGRESAR AL MENU (FUNCIONAL PARA "UN NIVEL DESPUÉS" DEL MENÚ)
    // LO UTILIZAN LOS ESCENARIOS CREADOS CON @OpcionDeMenuStage
    public void RegresarAlMenu(){
         this.OpcionDeMenuStage.close();
    }
    
    //ESTE METODO PERMITE REGRESAR A EditarMenu.fxml (FUNCIONAL PARA UN NIVEL DESPUÉS DE DICHO ARCHIVO)
    // LO UTILIZAN LOS ESCENARIOS CREADOS CON @OpcionEditarMenuStage
    public void RegresarAEditarMenu(){
         this.OpcionEditarMenuStage.close();
    }
    
    // ESTE METODO ES LLAMADO DESDE @AgregarCategoriaController.java
    // APUNTA AL CONTROLLADOR @EditMenuController PARA QUE HAGA EL @INSERT
    public void AgregarCategoria(Categoria categoria){
         this.EditarMenuCont.AgregarCategoria(categoria);
         this.OpcionEditarMenuStage.close();
    }
    
    // ESTE METODO ES LLAMADO DESDE @ActualizarCategoriaController.java
    // APUNTA AL CONTROLLADOR @EditMenuController PARA QUE HAGA EL @UPDATE
    public void ActualizarCategoria(Categoria categoria){
         this.EditarMenuCont.ActualizarCategoria(categoria);
         this.OpcionEditarMenuStage.close();
    }
    
    // ESTE METODO PERMITE AGREGAR UN NUEVO PLATILLO, ES LLAMADO DESDE @AgegarPlatilloController
    // PARA EJECUTAR EL CODIGO DE INSERCION EN EditarMenuController Y DAR UNA SENSACION DE INSERSION
    // MAS RÁPIDA.
    public void AgregarPlatillo(Platillo platillo){
         EditarMenuCont.AgregarPlatillo(platillo);
    }
    
    public void ActualizarPlatillo(Platillo platillo){
        EditarMenuCont.ActualizarPlatillo(platillo);
    }
    
    public static void main(String[] args) {
        launch();
    }

}