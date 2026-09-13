package monopoly.componentigioco.giocatore;

import monopoly.Coordinate;
import monopoly.componentigioco.Banca;
import monopoly.componentigioco.Tabellone;
import monopoly.componentigioco.carte.VaiA;
import monopoly.componentigioco.casella.*;
import monopoly.componentigioco.giocatore.funzionalita.MovimentoGiocatore;
import monopoly.componentigioco.giocatore.funzionalita.MovimentoGiocatoreSupporto;
import monopoly.componentigioco.giocatore.funzionalita.Pagamenti;
import monopoly.componentigioco.giocatore.funzionalita.PagamentiSupporto;
import monopoly.utilita.Costanti;
import monopoly.utilita.Colori;

import java.util.Objects;


/**
 * <i>Classe che gestisce le funzionalità del giocatore</i>
 */

public class Giocatore  implements MovimentoGiocatoreSupporto,Pagamenti {

    /**
     * Nome giocatore.
     */
    private String nome;
    /**
     * Stringa contenente il simbolo + colore del giocatore.
     */
    private String simbolo;
    /**
     * Simbolo giocatore senza colore.
     */
    private char simboloChar;

    /**
     * Stringa contenente solo il colore
     */
    private  String colore;

    private boolean imprigionato;

    private int tentativiPerPrigione = Costanti.TENTATIVI_PRIGIONE;


    /**
     * Si occupa di contenere tutti i metodi e attributi riguardante il movimento del giocatore
     */
    private final MovimentoGiocatoreSupporto movimentoGiocatore; // se faccio cosi invece di usare direttamente la classe limito le funzioni
    private final Pagamenti pagamentiGiocatore;


    /**
     * Il costruttore si occupa di generare correttamente il Giocatore.
     * @param nome nome giocatore
     * @param simbolo simbolo del giocatore
     * @param yMax coordinata y della casella iniziale
     * @param xMax  coordinata x della casella iniziale
     * @see #impostaCaratteristiche(String, char) impostaCaratteristiche(String nome,char simbolo)

     */
    public Giocatore(String nome, char simbolo, int yMax, int xMax) {
        this.movimentoGiocatore= new MovimentoGiocatore(xMax,yMax);
        this.pagamentiGiocatore=new PagamentiSupporto();
        impostaCaratteristiche(nome,simbolo);

    }

    public char getSimboloChar() {
        return simboloChar;
    }

    public void svuotaArrayCaselle(){
        this.pagamentiGiocatore.svuotaArrayCaselle();

    }

    /**
     * Metodo utilizzato nel costruttore per controllare e settare le caratteristiche del giocatore.
     * @param nome nome Giocatore
     * @param simbolo simbolo giocatore
     * @see #controlloNome(String) metodo di controllo per il nome 
     * @see #controlloSimbolo(char) metodo di controllo per il simbolo.
     */
   private void impostaCaratteristiche(String nome,char simbolo){
       this.nome = controlloNome(nome) ? "Nome sconosciuto" : nome;
       this.simboloChar = controlloSimbolo(simbolo) ? 'X' : simbolo;
       impostaColore();
   }

    /**
     * Metodo utilizzato nel costruttore per richiedere un colore random per il giocatore. <p>
     * Alla fine salva nell'attributo simbolo tutto il necessario per generare un simbolo colorato.
     * @see Colori#sceltaColore() Metodo utilizzato per generare il colore.
     */
   public void impostaColore(){
       String colore = Colori.sceltaColore();
       this.colore=colore;
       this.simbolo = colore+this.simboloChar+Costanti.ANSI_RESET;
   }

    /**
     * Controlla se un nome è valido per creare un giocatore.
     * @param nome Stringa da controllare
     * @return return {@code True} se {@code nome} non è valido , {@code False} se valido.
     */
    private boolean controlloNome(String nome){
        return nome == null || nome.isBlank();
    }
    /**
     * Controlla se un simbolo è valido per creare un giocatore.
     * @param simbolo char da controllare
     * @return return {@code True} se {@code simbolo} non è valido , {@code False} se valido.
     */
    private boolean controlloSimbolo(char simbolo){
        return simbolo==' ' ;
    }

    public String getSimbolo() {
        return simbolo;
    }
    public int getSoldi() {
        return this.pagamentiGiocatore.getSoldi();
    }

    /**
     * @param soldi importo da aggiungere al giocatore o togliere nel caso di importo negativo.
     */
    public void addSoldi(final int soldi) {
        this.pagamentiGiocatore.addSoldi(soldi);
    }

    public boolean compraMiglioramentiTerreno(int acquisto){
       return this.pagamentiGiocatore.compraMiglioramentiTerreno(acquisto);
    }

    /**
     * <p>Metodo che si occupa di controllare quando un giocatore va in bancarotta.</p>

     */
    private void controlloSoldi(Casella casella,Tabellone tabellone){
        if(this.pagamentiGiocatore.getSoldi() <= 0){
            System.out.println("Il giocatore "+ nome +" ha perso" );
            cancellaGiocatore(casella, tabellone);
        }
    }

    /**
     * <p>Metodo che cancella il giocatore dal tabellone.</p>

     */
    private void cancellaGiocatore(Casella casella,Tabellone tabellone){
        casella.getGiocatoriPresenti().remove(this.getSimbolo());
        resettaCaselleGiocatore(tabellone);
    }
    private void resettaCaselleGiocatore(Tabellone tabellone){
        svuotaArrayCaselle();
        Casella[][] caselle = tabellone.getCaselle();
        for (Casella[] righe : caselle) {
            for (Casella casella : righe) {
                if (casella instanceof Acquistabile acquistabile){
                    if (this.equals(acquistabile.getProprietario())){
                        acquistabile.resetAcquisti();
                    }
                }
            }
        }
    }

    public boolean isBancarotta(){return this.pagamentiGiocatore.isBancarotta(); }
    public String getNome() {
        return nome;
    }

    @Override
    public int getX() {
        return this.movimentoGiocatore.getX();
    }
    @Override
    public int getY() {
        return this.movimentoGiocatore.getY();
    }

    @Override
    public void spostaGiocatore(Coordinate coordinate) {
        this.movimentoGiocatore.spostaGiocatore(coordinate);
    }

    /**
     * <p>Il metodo si occupa di gestire l'aggiornamento delle coordinate del giocatore salvate in {@link #movimentoGiocatore}.</p>
     * <p>Il metodo {@link #cambioCoordinate()} è importante per aggiungere i soldi del via al giocatore (oltre che ad aggiornare le coordinate): dato che controlla se un giocatore passa per il via.</p>
     * @param dado numero uscito dal dado
     * @see MovimentoGiocatore
     */
    public void spostamentoGiocatore(int dado){
        for (int i = dado; i >0 ; i--) {
            if (cambioCoordinate() && i>1){ // controlla se completa un giro durante il turno
                addSoldi(Costanti.IMPORTO_DEL_VIA);
                Banca.addImporto(-Costanti.IMPORTO_DEL_VIA);
            }
        }
    }

    /**
     * Il metodo aggiorna le coordinate utilizzando i metodi in {@link #movimentoGiocatore}
     * @return {@code True} se il giocatore completa un giro
     * @see MovimentoGiocatore#cambioCoordinate()
     */
    @Override
    public boolean cambioCoordinate(){
        return this.movimentoGiocatore.cambioCoordinate();
    }

    /**
     * Metodo che si occupa di gestire l'aggiornamento dei soldi.

     * @see Banca#addImporto(int)
     * @see #addSoldi(int)
     */
    public void pagamentoPedaggio(Casella casella){ // solo per la banca
        if (isCasella(casella) && !this.imprigionato) {
            this.pagamentiGiocatore.pagamentoPedaggio(casella);
        }
    }

    public void pagamentoAffitto(Giocatore proprietario,int importo){
        this.pagamentiGiocatore.pagamentoAffitto(proprietario,importo);
    }

    /**
     * @param passi numero ottenuto dai dadi.
     * @see #spostamentoGiocatore(int) Metodo per cambiare le coordinate del giocatore
     */
    public void updatePosizione(int passi, Tabellone tabellone){
        Casella casella;

        if (isTabellone(tabellone) && isCasella((casella= Tabellone.getCasella(getY(),getX()))) ) {
            pulisciCasella(casella);
            spostamentoGiocatore(passi);
            casella = Tabellone.getCasella(getY(),getX());
            casella.aggiungiCarattere(this.simbolo);
        }
    }

    public void pulisciCasella(Casella casella ){
        casella.togliCarattere();
    }


    public void azioneCasella(Tabellone tabellone){
        if (isTabellone(tabellone)){
            Casella casella = casellaCorrente(tabellone);
            if (isCasella(casella)) {
                casella.azioneCasella(this);
                controlloSoldi(casella, tabellone);
            }
        }
    }

    private Casella casellaCorrente(Tabellone tabellone){
        Casella casella=null;
        if (isTabellone(tabellone)){
            casella= Tabellone.getCasella(getY(),getX());
        }
        return casella;
    }

    public void aggiuntaTerreno(int index){
        this.pagamentiGiocatore.aggiuntaTerreno(index);
    }

    public int getNCaselleAcquistate(int index) {
        return this.pagamentiGiocatore.getNCaselleAcquistate(index);
    }

    public void spostaGiocatoreInPrigione(Casella casella) {
        pulisciCasella(casella);
        setImpostazioniPrigione();
        spostaGiocatore(new Coordinate(this.movimentoGiocatore.getYMax(),0));
    }

    public void setImpostazioniPrigione() {
        this.imprigionato = true;
        this.tentativiPerPrigione = Costanti.TENTATIVI_PRIGIONE;
    }

    @Override
    public int getYMax() {
        return this.movimentoGiocatore.getYMax();
    }

    public String getColore() {
        return colore;
    }

    public boolean isImprigionato() {
        return imprigionato;
    }
    public void riduciTurniPrigione(){
        this.tentativiPerPrigione--;
    }
    public int getTentativiPerPrigione(){
        return this.tentativiPerPrigione;
    }

    public void setImprigionato(boolean imprigionato) {
        this.imprigionato = imprigionato;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(simboloChar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giocatore giocatore = (Giocatore) o;
        if ( (giocatore.nome == null) || (giocatore.simboloChar=='\u0000')) {

            return false;
        }

        return this.simboloChar == giocatore.simboloChar;
    }


    public void spostaGiocatoreConCarta(VaiA carta) {
        Casella casellaCorrente = Tabellone.getCasella(getY(),getX());
        Coordinate coordinateDiArrivo = carta.getCoordinateDiArrivo();
        pulisciCasella(casellaCorrente);
        spostaGiocatoreConCarta(coordinateDiArrivo);
        Tabellone.getCasella(getY(),getX()).aggiungiCarattere(getSimbolo());
        if (Tabellone.getCasella(getY(),getX()) instanceof Prigione) {
            setImpostazioniPrigione();
            return;
        }
        Tabellone.getCasella(getY(),getX()).azioneCasella(this);

    }

    private void spostaGiocatoreConCarta(Coordinate coordinateDiArrivo){
        do {
            spostamentoGiocatore(1);
            if (!(coordinateDiArrivo.getY() == Costanti.RIGHE-1 && coordinateDiArrivo.getX() == 0) && !(coordinateDiArrivo.getX() == Costanti.CASELLE_PER_RIGA-1 && coordinateDiArrivo.getY() == Costanti.RIGHE-1)) { // non aggiunge i soldi se deve andare in prigione
                if (getX() == Costanti.CASELLE_PER_RIGA-1 && getY() == Costanti.RIGHE-1) //aggiunge i soldi del via
                    addSoldi(100);
            }
        } while (getY() != coordinateDiArrivo.getY() || getX() != coordinateDiArrivo.getX());
    }
}