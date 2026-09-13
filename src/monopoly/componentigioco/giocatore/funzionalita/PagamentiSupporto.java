package monopoly.componentigioco.giocatore.funzionalita;

import monopoly.componentigioco.Banca;
import monopoly.componentigioco.casella.Casella;
import monopoly.componentigioco.casella.NomiCaselle;
import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;

public class PagamentiSupporto implements Pagamenti{
    private int soldi;
    private final int[] nCaselleAcquistate;

    public PagamentiSupporto(){
        this.soldi = Costanti.IMPORTO_INIZIALE_GIOCATORE;
        Banca.addImporto(-Costanti.IMPORTO_INIZIALE_GIOCATORE);
        this.nCaselleAcquistate=new int[NomiCaselle.getUltimaPosizione()]; //lenght 8
        svuotaArrayCaselle();
    }


    private int calcoloSoldiResidui(int pedaggio) {
        int soldiDaAggiungere=-pedaggio;
        if (pedaggio>0 || (soldiDaAggiungere <this.soldi)) return soldiDaAggiungere;
        return this.soldi; //positivo
    }

    @Override
    public void pagamentoAffitto(Giocatore proprietario, int importo) {
        proprietario.addSoldi(calcoloSoldiResidui(importo));
        addSoldi(importo);
    }

    @Override
    public void pagamentoPedaggio(Casella casella) {
            int importo = casella.getPedaggio();
            Banca.addImporto(calcoloSoldiResidui(importo));
            addSoldi(importo);
    }


    public void svuotaArrayCaselle(){
        for (int i = 0; i <nCaselleAcquistate.length ; i++) {
            this.nCaselleAcquistate[i]=0;
        }
    }

    @Override
    public boolean compraMiglioramentiTerreno(int acquisto) {
        boolean acquistatoAvvenuto = false;
        if (acquisto <= this.soldi) {
            acquistatoAvvenuto = true;
            addSoldi(-acquisto);
        }
        return acquistatoAvvenuto;
    }


    public void addSoldi(final int soldi) {
        this.soldi += soldi;
    }

    @Override
    public int getSoldi() {
        return this.soldi;
    }
    @Override
    public void aggiuntaTerreno(int index){
        this.nCaselleAcquistate[index]++;
    }
    @Override
    public int getNCaselleAcquistate(int index) {
        return nCaselleAcquistate[index];
    }
    @Override
    public boolean isBancarotta(){return this.soldi<0; }
}
