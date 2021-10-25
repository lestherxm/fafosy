/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fafosy.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author 50232
 */
public class ConnectionSQLServer {
    
    private Properties props;
    private InputStream in;
    private String Path;
    private Connection myConnection = null; 
    
    //constructor
    public ConnectionSQLServer(){
        if(myConnection == null){ //De esta forma me aseguro de no crear multiples conexiones
            try {
                getPropertiesSQLServer();
                String ConnectionData = LoadPropertiesSQLServer();
                myConnection = DriverManager.getConnection(ConnectionData);
                System.out.println("CONEXIÓN A BASE DE DATOS ESTABLECIDA");
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
            
    //ESTE MÉTODO SE INVOCA EN EL CONSTRUCTOR/ AL MOMENTO DE CREAR UN OBJETO CON ESTA CLASE
    //OBTIENE LOS DATOS CONTENIDOS EN EL ARCHIVO sqlserver.properties CONTENIDA EN LA CARPETA: resources/Config
    private void getPropertiesSQLServer(){
        try{
            props = new Properties();
            Path = "\\Config\\sqlserver.properties"; 
            in = getClass().getClassLoader().getResourceAsStream(Path);
            props.load(in);
            in.close();   
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    //ESTE MÉTODO DEVUELVE UN STRING CON LOS DATOS RECOGIDOS/ALMACENADOS EN EL OBJETO PROPERTIES
    private String LoadPropertiesSQLServer(){
         try {
            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String database = props.getProperty("database");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String loginTimeOut = props.getProperty("loginTimeOut");
            String ConnectionData = "jdbc:sqlserver://"+host+":"+port+";"
                                 + "database="+database+";"
                                 + "user="+user+";"
                                 + "password="+password+";"
                                 + "loginTimeOut="+loginTimeOut+";";
            return ConnectionData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //DEVUELVE LA CONEXIÓN ACTUAL 
    public Connection getConnectionToSQLServer(){
        return this.myConnection;
    }
    
    //INTENTA CERRAR LA CONEXIÓN.
    public void CloseConnectionSQLServer(){
        try {
            this.myConnection.close();
            System.out.println("CONEXION A BASE DE DATOS CERRADA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
