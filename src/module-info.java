module monopoly {

    requires javafx.controls;
    requires javafx.fxml;


    opens monopoly.visuale to javafx.fxml;
    exports monopoly.visuale;

}