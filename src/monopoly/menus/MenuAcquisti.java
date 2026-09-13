package monopoly.menus;

import monopoly.componentigioco.casella.Acquistabile;
import monopoly.componentigioco.casella.Casella;
import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.ScannerUtils;

public class MenuAcquisti implements MenuAcquistiInterfaccia {
    private Giocatore giocatoreCorrente;
    private Acquistabile terreno;
    private int nLine;
    private boolean giaPagato=false;

    public void menu(Giocatore currentGiocatore,Acquistabile terreno) {
        resetProprieta();
        if (isGiocatoreAndTerreno(currentGiocatore, terreno)){
            this.terreno=terreno;
            if (this.terreno.getProprietario()==null) menu(currentGiocatore);
        }
    }

    @Override
    public void resetProprieta() {
        this.giocatoreCorrente=null;
        this.terreno=null;
        this.nLine=0;
        this.giaPagato=false;
    }

    @Override
    public void menu(Giocatore currentGiocatore) {
        int scelta=1;
        this.giaPagato=false;
        if (isGiocatore(currentGiocatore) ){
            this.giocatoreCorrente = currentGiocatore;
            String stringaMenu=toString(); // lo faccio cosi perche mi serve effettuarlo senza stamparlo
            if (this.nLine>1 ) {
                System.out.print(stringaMenu); //dato che l'ho gia calcolato uso questa
                scelta = ScannerUtils.readIntegerInRange(1, 2);
                if (scelta == 2) {
                    this.giaPagato=this.terreno.acquistoTerreno(currentGiocatore);
                }
            }
        }
    }
    @Override
    public boolean pagamentoGiaEffettuato(){
        return this.giaPagato ;
    }
    private String sceltaStringa(int soldiGiocatore){
       String stringaTerreno="";
        int prezzoTerreno= this.terreno.getPrezzoTerreno();

        if (soldiGiocatore >= prezzoTerreno ){
            this.nLine++;
            stringaTerreno="%d Comprare terreno al prezzo di %dCHF\n".formatted(this.nLine,prezzoTerreno);
        }
        return stringaTerreno;
    }

     @Override
     public String toString(){
        this.nLine=1;
        StringBuilder menu = new StringBuilder("MENU ACQUISTO/PAGAMENTO TERRENO/PEDAGGIO\n%d Pagare pedaggio alla banca di %dCHF \n".formatted(nLine,Math.abs(((Casella) this.terreno).getPedaggio())));
        int soldiGiocatore= giocatoreCorrente.getSoldi();
        menu.append(sceltaStringa(soldiGiocatore));
        return menu.toString();
     }
}
