module a8.cmartinez136.javafxtodo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens a8.cmartinez136.javafxtodo to javafx.fxml;
    exports a8.cmartinez136.javafxtodo;
}