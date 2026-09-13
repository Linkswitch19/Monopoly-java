package monopoly.componentigioco.carte;

import monopoly.componentigioco.Banca;
import monopoly.componentigioco.giocatore.Giocatore;

public class ModificaBudget extends Carta{
    private int valore;
    public ModificaBudget(String descrizione, String terzaInformazione) {
        super(descrizione);
        try {
            this.valore=Integer.parseInt(terzaInformazione.trim().toLowerCase());
        } catch (NumberFormatException e){
           this.valore=1;
        }
    }

    @Override
    public void azioneCarta(Giocatore giocatoreCorrente) {
        giocatoreCorrente.addSoldi(this.valore);
        Banca.addImporto(this.valore);
    }
}
