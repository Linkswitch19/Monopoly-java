package monopoly.visuale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import monopoly.Gioco;
import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;
import java.io.IOException;
import java.util.Objects;


public class GiocoVisuale extends Gioco {
    public static Stage stage;


    public GiocoVisuale(Stage primaryStage)throws IOException  {
        super(true);
        stage=primaryStage;
        stage.setResizable(false);
        Image icon = new Image(getClass().getResourceAsStream("komi.jpg"));
        primaryStage.getIcons().add(icon);
        inizio();

    }

    private void inizio()throws IOException {
        int x =600;
        int y =400;
        FXMLLoader fxmlLoader = new FXMLLoader(MainVisuale.class.getResource("PrimaSchermata.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), x, y);
        stage.setTitle("Monopoly");
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void creaGiocatori() {

        for (Giocatore giocatore  : getGiocatori() ) {
            super.getTabellone().modificaCasella(giocatore.getSimbolo(), Costanti.RIGHE-1, Costanti.CASELLE_PER_RIGA-1);
        }

    }

    @Override
    public void avviaGioco(){
        creaGiocatori();
        System.out.println(super.getTabellone());
        super.getMenuGioco().menu(getGiocatori().getFirst(),true);
    }

    public void turnoVisuale(){
        stage.hide();
        gameFlow();
    }

    @Override
    protected void menuTurno(Giocatore currentGiocatore) {
       turno(currentGiocatore);
    }

    private void cambiaGiocatoreVisuale(Giocatore giocatore){
        if (getCurrentPlayer().isBancarotta()){
            getGiocatori().removeFirst();
        } else super.cambiaGiocatore(giocatore);
        ControllerDado.setUltimoSimbolo(getGiocatori().getFirst().getSimboloChar());
    }

    @Override
    public void gameFlow(){
        boolean tolto = false;
        if (getGiocatori().size() > 1){
            Giocatore giocatoreCorrente=getCurrentPlayer();
            if (giocatoreCorrente.isBancarotta()) {
                getGiocatori().removeFirst();
                tolto=true;
            } else menuTurno(giocatoreCorrente);
            cambiaGiocatoreVisuale(giocatoreCorrente);
            if (getGiocatori().size() > 1  )super.getMenuGioco().menu(getGiocatori().getFirst(),true);
            else schermataFinale();

            stage.show();
        }else schermataFinale();
  }

    private void schermataFinale(){
        Parent pane = null;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SchermataVincitore.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.getScene().setRoot(pane);
        stage.show();

    }
}
