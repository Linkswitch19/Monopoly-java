package monopoly.componentigioco.casella;


import monopoly.Coordinate;
import monopoly.componentigioco.Dado;
import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;
import monopoly.Gioco;

import java.util.LinkedList;

public class Prigione extends Casella {

    private final PrintCasellaPrigione print= new PrintCasellaPrigione();



    public Prigione() {
        super();
        setCoordinate(new Coordinate(Costanti.RIGHE-1,0));
    }

    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo = NomiCaselle.SINGOLE;
        super.setNomeColore(tipo.getNome(0),tipo.getColore());
    }

    @Override
    public void setPedaggioDefault() {
        super.setPedaggio(Costanti.IMPORTO_PER_USCIRE_PRIGIONE);
    }


    @Override
    public void azioneCasella(Giocatore giocatoreCorrente) {
        boolean uscita;
        if (super.isGiocatore(giocatoreCorrente) && giocatoreCorrente.isImprigionato() ){
            giocatoreCorrente.riduciTurniPrigione();
            uscita= Dado.confrontaDadi();
            giocatoreCorrente.setImprigionato(false);
            if (!uscita){

                if (giocatoreCorrente.getTentativiPerPrigione() <= 0) {
                    super.azioneCasella(giocatoreCorrente);
                    System.out.println("Sei uscito dalla prigione pagando");

                } else {
                    System.out.println("Turni per uscire dalla prigione " + giocatoreCorrente.getTentativiPerPrigione());
                    giocatoreCorrente.setImprigionato(true);

                }
            }



        }


    }


    public void togliCarattere(){
        if (!Gioco.getCurrentPlayer().isBancarotta()) {
            super.getGiocatoriPresenti().remove(Gioco.getCurrentPlayer().getSimbolo());
        }

    }

    @Override
    public String dettagliCasella(int d) {
        return switch (d) {
            case 2 ->
                    this.print.printRigaDueEQuattro("Giocatori in transito:");
            case 3 ->
                    this.print.printRigaTre(super.getGiocatoriPresenti(),super.getColore(), Gioco.getGiocatori());
            case 4 ->
                    this.print.printRigaDueEQuattro("Giocatori in prigione:");
            case 5 ->
                    this.print.printRigaCinque(super.getGiocatoriPresenti(),super.getColore(), Gioco.getGiocatori());
            default -> super.dettagliCasella(d);
        };
    }

}
