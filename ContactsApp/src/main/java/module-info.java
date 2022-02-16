module a3.cmartinez136.contactsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens a3.cmartinez136.contactsapp to javafx.fxml;
    exports a3.cmartinez136.contactsapp;
}