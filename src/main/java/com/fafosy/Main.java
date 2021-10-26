package com.fafosy;

import com.fafosy.controller.*; //importacion de todos los controladores del proyecto.
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
                                             EditarMenuController EditarMenuCont = loader.getController();
                                             EditarMenuCont.setMain(this);
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
        cargarMenu();
            this.OpcionDeMenuStage.close();
    }
    
    public static void main(String[] args) {
        launch();
    }

}