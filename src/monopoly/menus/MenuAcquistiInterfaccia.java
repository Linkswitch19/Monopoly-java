package monopoly.menus;

import monopoly.componentigioco.casella.Acquistabile;
import monopoly.componentigioco.giocatore.Giocatore;

public interface MenuAcquistiInterfaccia extends MenuInterfaccia {
    void menu(Giocatore currentGiocatore, Acquistabile terreno);
    void resetProprieta();
    default boolean isTerreno(Acquistabile terreno){
        return terreno!=null;
    }
    default boolean isGiocatoreAndTerreno(Giocatore giocatore,Acquistabile terreno){
        return isTerreno(terreno) && isGiocatore(giocatore);
    }
    boolean pagamentoGiaEffettuato();

}
