package monopoly.visuale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import monopoly.componentigioco.Dado;
import monopoly.utilita.Costanti;

import java.io.File;

import java.util.Random;

public class ControllerDado {
    @FXML
    private Button lanciaDadi;
    @FXML
    private Label scrittaGiocatore;

    @FXML
    private ImageView dadoImg;
    @FXML
    private ImageView dadoImg1;
    @FXML
    private static char ultimoSimbolo;

    public void initialize() {
        ultimoSimbolo=GiocoVisuale.getCurrentPlayer().getSimboloChar();
        this.scrittaGiocatore.setText("Simbolo giocatore in gioco: "+ultimoSimbolo);

    }

    public static void setUltimoSimbolo(char ultimoSimbolo) {
        ControllerDado.ultimoSimbolo = ultimoSimbolo;
    }

    private void creaDadoVisivo(int[] last){
        for (int i = 0; i <Costanti.NUMERO_DADI ; i++) {
            last[i]=new Random().nextInt(Costanti.NUMERO_DADO_MIN,Costanti.NUMERO_DADO_MAX+1);
            String dadoScelto="dice_"+ last[i]+".png";
            File file= new File("risorse/img/".replace("/",File.separator)+dadoScelto);
            if (i==1) dadoImg1.setImage(new Image(file.toURI().toString()));
            else dadoImg.setImage(new Image(file.toURI().toString()));
        }








    }

    @FXML
    public void rollDice(ActionEvent event){
        int numeroDiGiriDado=20;
        lanciaDadi.setDisable(true);
        int last[]= new int[2];
        for (int i = 0; i <numeroDiGiriDado ; i++) {
            creaDadoVisivo(last);

        }
        lanciaDadi.setDisable(false);
        Dado[] dadi = Dado.getDadi();
        for (int i = 0; i < Costanti.NUMERO_DADI; i++) {
            dadi[i].setUltimoLancio(last[i]);
        }

        MainVisuale.getGiocoVisuale().turnoVisuale();
        this.scrittaGiocatore.setText("Simbolo giocatore in gioco: "+ultimoSimbolo);



    }
}
