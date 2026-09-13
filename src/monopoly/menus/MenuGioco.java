package monopoly.menus;

import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.ScannerUtils;

public class MenuGioco implements MenuInterfaccia {
    @Override
    public String toString(){
        return """
                1. Mostra il capitale del giocatore corrente
                2. Tira i dadi
                """;
    }
    @Override
    public void menu(Giocatore currentGiocatore){
        if (isGiocatore(currentGiocatore)){
            int scelta;
            do {
                System.out.println("E' il turno di " + currentGiocatore.getNome());
                System.out.print(this);
                scelta = ScannerUtils.readIntegerInRange(1,2);
                if (scelta == 1) {
                    System.out.println("Il tuo capitale ammonta a " + currentGiocatore.getSoldi());
                    System.out.println();
                }
            } while (scelta != 2);
        }
    }

    public void menu(Giocatore currentGiocatore,Boolean visivo){
        if (isGiocatore(currentGiocatore)){
            System.out.println("E' il turno di " + currentGiocatore.getNome());
            System.out.println("Il tuo capitale ammonta a " + currentGiocatore.getSoldi());
            System.out.println("Schiaccia sulla finestra per tirare il dado");
            System.out.println();

        }
    }
}
