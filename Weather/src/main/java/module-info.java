module a4.cmartinez136.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;


    opens a4.cmartinez136.weather to javafx.fxml;
    exports a4.cmartinez136.weather;
}