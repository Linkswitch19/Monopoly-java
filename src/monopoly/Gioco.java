package monopoly;

import monopoly.componentigioco.Dado;
import monopoly.componentigioco.carte.Carta;
import monopoly.componentigioco.casella.CaselleCarte;
import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.componentigioco.Tabellone;
import monopoly.menus.MenuGioco;
import monopoly.menus.MenuInterfaccia;
import monopoly.schermate.Schermata;
import monopoly.schermate.SchermataFinale;
import monopoly.schermate.SchermataIniziale;
import monopoly.utilita.Costanti;
import monopoly.utilita.ScannerUtils;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;


public class Gioco {
    private Tabellone tabellone;

    private static LinkedList<Giocatore> giocatori = new LinkedList<>();
    private final MenuInterfaccia menuGioco= new MenuGioco(); // cosi non si possono usare altri metodi di menuGioco che non sono presenti nella interfaccia
    private Schermata schermataCorrente= new SchermataIniziale();

    public static LinkedList<Giocatore> getGiocatori() {
        return giocatori;
    }

    public Gioco() {
        System.out.println(this.schermataCorrente);
        ScannerUtils.emptyTheScanner();
        avviaGioco();
    }
    public Gioco(boolean visuale){
        creaGioco();
    }
    public static Giocatore getCurrentPlayer(){
        return giocatori.getFirst();
    }



    public void avviaGioco(){

        creaGioco();
        creaGiocatori();
        gameFlow();
    }
    private void creaGioco(){
        creaTabellone();
        CaselleCarte.creaCarte();
        creaDado();
    }

    protected void cambiaGiocatore(Giocatore giocatore){
        giocatori.removeFirst();
        giocatori.addLast(giocatore);
    }


    public void gameFlow() {
        System.out.println(tabellone);
        int nGiocatori=Costanti.NUMERO_GIOCATORI;

        while (nGiocatori > 1) {
            Giocatore giocatoreCorrente=giocatori.getFirst();
            menuTurno(giocatoreCorrente);

            if (giocatoreCorrente.isBancarotta()) {
                nGiocatori--;
                giocatori.removeFirst();


            } else cambiaGiocatore(giocatoreCorrente);
        }
        fineGioco(giocatori.getFirst());
    }

    private void creaDado() {
       Dado.creaDadi();
    }


    public void creaGiocatori() {
        giocatori.addAll(new SchermataIniziale().creaGiocatori(this.tabellone));
    }

    public Tabellone getTabellone() {
        return tabellone;
    }

    protected void menuTurno(Giocatore currentGiocatore) {
        this.menuGioco.menu(currentGiocatore);
        if (isGiocatore(currentGiocatore)){
            Dado.lancioDadi();
            turno(currentGiocatore);

        }

    }

    public MenuGioco getMenuGioco() {
        return  (MenuGioco) menuGioco;
    }

    public void turno(Giocatore currentGiocatore){
       if (isGiocatore(currentGiocatore)) {
            if (!aggiornamentoPosizione(currentGiocatore,true)){
                aggiornamentoPosizione(currentGiocatore,false);
           }
        }
    }

    private Boolean aggiornamentoPosizione(Giocatore currentGiocatore, boolean possibilityUscita){
        boolean movimento=false;

        if (isGiocatore(currentGiocatore)&& !currentGiocatore.isBancarotta()){
            if (!isGiocatoreInPrigione(currentGiocatore)){
                currentGiocatore.updatePosizione(Dado.sommaDadi(), tabellone);

               printDadoTabellone();

                movimento=true;

            }
            if (possibilityUscita || movimento ){
                currentGiocatore.azioneCasella(tabellone); // fa prima l'azione della prigione dei dadi e poi aggiorna la posizione con il simbolo inserito alla fine
                if (!possibilityUscita ) printDadoTabellone();

            }
        }
        return movimento;

    }
    private void printDadoTabellone(){
        System.out.println(this.tabellone);
        if (!Dado.visivo) Dado.printDadi();

    }


    private void creaTabellone() {
        this.tabellone = new Tabellone();
        tabellone.crea();
    }


    protected void fineGioco(Giocatore giocatore) {
        this.schermataCorrente=new SchermataFinale(giocatore);
        System.out.println(this.schermataCorrente);
        ScannerUtils.chiudiScanner();
    }

    public boolean isGiocatore(Giocatore giocatore){
        return giocatore!=null;
    }

    private boolean isGiocatoreInPrigione(Giocatore giocatore){
        return giocatore.isImprigionato();
    }


}

