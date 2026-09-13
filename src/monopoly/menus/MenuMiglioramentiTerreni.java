package monopoly.menus;

import monopoly.componentigioco.casella.Acquistabile;
import monopoly.componentigioco.casella.CaseHotel;
import monopoly.componentigioco.giocatore.Giocatore;

public interface MenuMiglioramentiTerreni extends MenuAcquistiInterfaccia {
    void menu(Giocatore currentGiocatore, CaseHotel terreno);
    default void menu(Giocatore currentGiocatore, Acquistabile terreno){
        if (terreno instanceof CaseHotel) menu(currentGiocatore,(CaseHotel) terreno);
    }
}
