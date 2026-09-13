package monopoly.schermate;

import monopoly.componentigioco.Tabellone;
import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;
import monopoly.utilita.ScannerUtils;

import java.util.LinkedHashSet;


public class SchermataIniziale extends Schermata{

    @Override
    public String toString() {
        String colore = "\u001B[1;38;5;183m";
        return colore + """
                
                 _____ ______   ________  ________   ________  ________  ________  ___           ___    ___\s
                |\\   _ \\  _   \\|\\   __  \\|\\   ___  \\|\\   __  \\|\\   __  \\|\\   __  \\|\\  \\         |\\  \\  /  /|
                \\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\        \\ \\  \\/  / /
                 \\ \\  \\\\|__| \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\   ____\\ \\  \\\\\\  \\ \\  \\        \\ \\    / /\s
                  \\ \\  \\    \\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\____    \\/  /  / \s
                   \\ \\__\\    \\ \\__\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\ \\__\\    \\ \\_______\\ \\_______\\__/  / /   \s
                    \\|__|     \\|__|\\|_______|\\|__| \\|__|\\|_______|\\|__|     \\|_______|\\|_______|\\___/ /    \s
                                                                                               \\|___|/     \s
                                                                                                           \s
                                                                                                           \s
                Premere invio per Iniziare...""" + Costanti.ANSI_RESET;
    }
    public LinkedHashSet<Giocatore> creaGiocatori(Tabellone tabellone) {
        LinkedHashSet<Giocatore> giocatori = new LinkedHashSet<>();

        for (int i = 0; i < Costanti.NUMERO_GIOCATORI; i++) {
            tabellone.modificaCasella(inserisciGiocatore(i,giocatori).getSimbolo(), Costanti.RIGHE-1, Costanti.CASELLE_PER_RIGA-1);
        }
        return giocatori;
    }

    private Giocatore inserisciGiocatore(int i,LinkedHashSet<Giocatore> giocatori){
        char simbolo;
        Giocatore giocatore;
        String nome = ScannerUtils.inputNomeGiocatore(i+1);
        do {
            simbolo = ScannerUtils.inputSimboloGiocatore(i + 1);
            giocatore =new Giocatore(nome,simbolo,Costanti.RIGHE-1,Costanti.CASELLE_PER_RIGA-1);

        } while (!giocatori.add(giocatore));


        return giocatore;
    }
}
