//En este archivo se citan explicitamente los componentes que serán utilizados durante el proyecto
//more info. -> https://es.stackoverflow.com/questions/282078/qu%C3%A9-es-el-module-info-de-java-y-para-qu%C3%A9-se-utiliza
module com.artisanweb.system{
    requires javafx.controls; // para usar los controles de javafx
    requires javafx.fxml; // para trabajar con archivos fxml
    requires java.base; // para usar JAVA LANGUAGE como tal en el proyecto
    opens com.fafosy.controller;
    exports com.fafosy; //para hacer uso de todas las clases ubicadas en este paquete.
                                             //lo cual en sí es todo el proyecto como tal   
}
