package monopoly.componentigioco.casella;

import monopoly.componentigioco.giocatore.Giocatore;

public interface Acquistabile {
    boolean acquistoTerreno(Giocatore proprietario);
    Giocatore getProprietario();
    int getPrezzoTerreno();
    void resetAcquisti();

}
