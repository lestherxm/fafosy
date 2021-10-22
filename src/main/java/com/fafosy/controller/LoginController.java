/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fafosy.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

// IMPORTACION DE CLASES NECESARIAS PARA EL LOGIN
import com.fafosy.model.ConnectionSQLServer;
import com.fafosy.model.MessagesForUser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author 50232
 */
public class LoginController implements Initializable {

    @FXML TextField TxtEmail;
    @FXML PasswordField TxtPass;
    @FXML Button BtnLogin;
    
    @FXML
    public void Login(){
        //SE OBTIENEN LOS DATOS SETEADOS POR EL USUARIO DESDE LA UI Y SE ALMACENAN EN VARIABLES
        String Email = TxtEmail.getText();
        String Pass  = TxtPass.getText();
        
        //EN CASO DE QUE EL USUARIO HAYA DIJITADO ALGO, VALIDAR DATOS PARA LOGEARSE.
        if(!Email.isEmpty() && !Pass.isEmpty()){
            try {
                String sql = "SELECT Email, CONVERT(VARCHAR(255),DECRYPTBYPASSPHRASE(?,Pass)) FROM Usuarios WHERE Email = ?";
                ConnectionSQLServer con = new ConnectionSQLServer();
                PreparedStatement pstmt = con.getConnectionToSQLServer().prepareStatement(sql);
                pstmt.setString(1, Email);
                pstmt.setString(2, Email);
                ResultSet result = pstmt.executeQuery();
                    if(result.next()){
                        System.out.println("SE ENCONTRÓ EL CORREO");
                        String PassGetted = result.getString(2);
                            if(PassGetted.equals(Pass)){
                                System.out.println("CORREO Y PASS COINCIDEN, ENTRA");
                            }else{
                                MessagesForUser.showAlert("¡ATENCIÓN!","LA CONTRASEÑA QUE HAS INGRESADO ES INCORRECTA.", AlertType.INFORMATION);
                            }     
                    }else{
                        MessagesForUser.showAlert("¡ATENCIÓN!","EL CORREO ELECTRÓNICO QUE HAS INGRESADO NO EXISTE.", AlertType.INFORMATION);
                    }
                  con.CloseConnectionSQLServer();
                } catch (SQLException sqle) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, sqle);
                }
        }else{
             MessagesForUser.showAlert("¡ATENCIÓN!", "TIENES QUE LLENAR TODOS LOS CAMPOS REQUERIDOS.", AlertType.WARNING);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
