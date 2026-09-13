package monopoly.visuale;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerFine {
    @FXML
    private Label vincitoreNome;

    @FXML
    public void initialize() {
        String nome= GiocoVisuale.getCurrentPlayer().getSimboloChar()+"";
        vincitoreNome.setText("Vincitore Giocatore : "+nome);
    }
}
