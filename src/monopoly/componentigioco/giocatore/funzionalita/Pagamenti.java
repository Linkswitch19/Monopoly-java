package monopoly.componentigioco.giocatore.funzionalita;


import monopoly.componentigioco.casella.Casella;
import monopoly.componentigioco.giocatore.Giocatore;

public interface Pagamenti {
    void pagamentoAffitto(Giocatore proprietario, int importo);
    void pagamentoPedaggio(Casella casella);
    boolean compraMiglioramentiTerreno(int acquisto);
    void addSoldi(final int soldi);
    int getSoldi();
    void aggiuntaTerreno(int index);
    int getNCaselleAcquistate(int index);
    void svuotaArrayCaselle();
    boolean isBancarotta();
}
