package monopoly.utilita;

import java.util.Random;

/**
 * Classe colori per i giocatori
 */

public abstract class Colori  {

    private static int [] coloriUsati= new int[Costanti.ANSI_MAX-Costanti.ANSI_MIN+1];
    private static int cntColori=0;


    public static String sceltaColore(){ // ripetuto false se non vuoi che si ripetano i colori
        Random random = new Random();
        String colore ="\u001B[1;38;5;";  // bold;background/coloreScritta;? ; colore
        int numeroColore;
        do {
            numeroColore = random.nextInt(Costanti.ANSI_MIN, Costanti.ANSI_MAX + 1);
        } while (isColoreUtilizzato(numeroColore));

        coloriUsati[++cntColori] = numeroColore;
        colore = colore + numeroColore + "m";
        colore+=Costanti.COLORE_SFONDO; // da migliorare in futuro se serve per lo sfondo
        return colore;
    }
    private static void resetContatoreColori(){
        if (cntColori>=coloriUsati.length-1){
            cntColori=0;
            coloriUsati= new int[Costanti.ANSI_MAX-Costanti.ANSI_MIN+1];
        }
    }

    private static Boolean isColoreUtilizzato(int coloreScelto){
        boolean utilizzato=false;
        resetContatoreColori();
        for (int colore : coloriUsati) {
            if (colore==coloreScelto){
                utilizzato=true;
                break;
            }
        }
        return utilizzato;
    }
}
