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
            
    //This method runs on @getConnectionToSQLServer method [Uing SQLServer]
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
    
    //This method runs on @getConnectionToSQLServer method [Uing SQLServer]
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
    
    //This method runs on all the app when the user do changes in the system (CRUD) [Uing SQLServer]
    public Connection getConnectionToSQLServer(){
        return this.myConnection;
    }
    
    //This method runs on all the app when the user do changes in the system (CRUD) [Uing SQLServer]
    public void CloseConnectionSQLServer(){
        try {
            this.myConnection.close();
            System.out.println("CONEXION A BASE DE DATOS CERRADA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
