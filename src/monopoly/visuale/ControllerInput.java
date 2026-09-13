package monopoly.visuale;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;
import monopoly.utilita.ScannerUtils;


import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Objects;


public class ControllerInput {

    @FXML
    private Button registrazioneCompleta;
    @FXML
    private TextField inserisciNome;
    @FXML
    private TextField inserisciSimbolo;
    @FXML
    private Label numeroGiocatore;
    private int nPlayer=1;

    private LinkedHashSet<Giocatore> giocatoriNonDoppi= new LinkedHashSet<>();



    @FXML
    public void creaGiocatore(ActionEvent event) throws IOException {
        if (this.inserisciSimbolo.getText().isBlank()||this.inserisciNome.getText().isBlank()) return;
        Giocatore giocatore=ScannerUtils.leggiSimboloStringa(new String[]{this.inserisciNome.getText().strip(),this.inserisciSimbolo.getText().strip()});
        if (giocatoriNonDoppi.add(giocatore)){
            this.numeroGiocatore.setText("Inserisci giocatore "+ ++nPlayer);
            if (this.nPlayer>Costanti.NUMERO_GIOCATORI) {
                GiocoVisuale.getGiocatori().addAll(giocatoriNonDoppi);
                MainVisuale.getGiocoVisuale().avviaGioco();
                Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DadoVisuale.fxml")));
                GiocoVisuale.stage.getScene().setRoot(pane);
            }
        }
    }


}
