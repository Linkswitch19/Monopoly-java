package monopoly.componentigioco.casella;


import monopoly.Coordinate;
import monopoly.menus.MenuAcquisti;
import monopoly.menus.MenuAcquistiInterfaccia;
import monopoly.menus.MenuAcquistoMiglioramenti;
import monopoly.componentigioco.giocatore.Giocatore;

import java.util.Random;

public class Proprieta extends Casella implements CaseHotel {
    private int nCase=0;
    private boolean hotel=false;
    private int prezzoCasa;
    private int prezzoHotel;
    private int prezzoTerreno;
    private Giocatore proprietario=null;
    private PrintCasellaProprieta print= new PrintCasellaProprieta();
    private int nArrayGiocatore;
    private static int[] nCaselleCategoria=new int[NomiCaselle.getUltimaPosizione()];
    private static final MenuAcquistiInterfaccia[] menuAcquisti = {new MenuAcquisti(),new MenuAcquistoMiglioramenti()};

    public Proprieta(int y, int x) {
        super();
        setNArrayGiocatore();
        setCoordinate(new Coordinate(y,x));
    }

    public int getNCaselleCategoria() {
        if (this.nArrayGiocatore>=nCaselleCategoria.length){
            return 0;
        }
        return Proprieta.nCaselleCategoria[this.nArrayGiocatore];
    }

    private void setNArrayGiocatore(){

        if (NomiHelper.getFakeCaselle()){
            this.nArrayGiocatore=CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA;
        } else {
            this.nArrayGiocatore=NomiHelper.nArrayUltimoColore();
            Proprieta.nCaselleCategoria[this.nArrayGiocatore]++;
        }
    }
    public int getNumeroArrayGiocatore(){
        return this.nArrayGiocatore;
    }


    @Override
    public String dettagliCasella(int d) {
        return dettagliCasellaExtra(d);
    }
    private String dettagliCasellaExtra(int d){
        StringBuilder dettagli=new StringBuilder();
        dettagli.append(CostantiCaselle.SPAZIO);

        return switch (d) {
            case 2 ->
                this.print.printRigaDue(this.proprietario,getPedaggio(),this.prezzoTerreno);
            case 3 ->
                    this.print.printRigaTre(this.proprietario, infoCasellaPrezziEdifici(), super.getPedaggio(), dettagli);
            case 4 ->
                    this.print.printRigaQuattro(this.proprietario, infoCasellaPrezziEdifici(), infoCasellaNumeroEdifici(), dettagli,getNCase());
            default -> super.dettagliCasella(d);
        };
    }

    @Override
    public void setPedaggioDefault() {
        super.setPedaggioDefault();
        setPrezzi();
    }
    private void setProprietario(Giocatore proprietario) {
        this.proprietario=proprietario;
    }

    private void setPrezzi(){  // da migliorare
       this.prezzoTerreno=generaPrezzo(CostantiCaselle.PREZZO_TERRENO_MIN,CostantiCaselle.PREZZO_TERRENO_MAX);
       this.prezzoCasa=generaPrezzo(CostantiCaselle.PREZZO_CASA_MIN,CostantiCaselle.PREZZO_CASA_MAX);
       this.prezzoHotel=generaPrezzo(calcoloPrezzoMinimoHotel(),CostantiCaselle.PREZZO_HOTEL_MAX);
    }

    private int generaPrezzo(int min,int max){
        return creaSeedRandom().nextInt(min,max);
    }
    private int calcoloPrezzoMinimoHotel(){ // non avrebbe senso che l'hotel costi meno di una casa
        int prezzoMinimoHotel=CostantiCaselle.PREZZO_HOTEL_MIN;
        int aumentoPrezzo=10; // per avere una differenza sicura tra casa/hotel
        if (this.prezzoCasa>prezzoMinimoHotel){
            prezzoMinimoHotel=this.prezzoCasa+aumentoPrezzo;
        }
        return prezzoMinimoHotel;
    }
    private Random creaSeedRandom(){
         return new Random();
    }

    public boolean acquistoTerreno(Giocatore proprietario) {
        boolean acquistoTerreno=false;
        if (!isProprietario()) {
            acquistoTerreno=controlloAcquistoEffettuato(proprietario,this.prezzoTerreno);
            if (acquistoTerreno){
                setProprietario(proprietario);
            }
        }
        return  acquistoTerreno;
    }
    public boolean acquistoCasaHotel(int numeroAcquisti) {
        boolean acquistoAvvenuto=false;
        if (isProprietario() && (!this.hotel)) {
            if ((this.nCase<CostantiCaselle.MAX_CASE)) {
                for (int i = 0; i <numeroAcquisti ; i++) { // da migliorare
                    if (!acquistoAvvenuto){
                        acquistoAvvenuto=addCasa();
                    } else {
                        addCasa();
                    }
                }
            } else if(acquistareHotel(numeroAcquisti)){
                acquistoAvvenuto=addHotel();
            }
        }
        return acquistoAvvenuto;
    }
    private boolean acquistareHotel(int numeroAcquisti){
        return ((numeroAcquisti<=CostantiCaselle.MAX_HOTEL) && (this.nCase>=CostantiCaselle.MAX_CASE));
    }
    private boolean addHotel(){
        boolean acquistoAvvenuto=controlloAcquistoEffettuato(this.proprietario,this.prezzoHotel);
        if (acquistoAvvenuto){
            this.nCase = 0;
            this.hotel = true;
        }
        return acquistoAvvenuto;

    }
    private boolean addCasa(){
        boolean acquistoAvvenuto=controlloAcquistoEffettuato(this.proprietario,this.prezzoCasa);
        if (acquistoAvvenuto){
            this.nCase++;
        }
        return acquistoAvvenuto;
    }

    private boolean controlloAcquistoEffettuato(Giocatore proprietario,int prezzo){
        return proprietario.compraMiglioramentiTerreno(prezzo);
    }
    private boolean isProprietario(){
        return this.proprietario!=null;
    }

    public Giocatore getProprietario() {
        return proprietario;
    }

    public int getPrezzoTerreno() {
        return prezzoTerreno;
    }

    @Override
    public boolean isHotelAcquistabile() {
        boolean risposta=false;
        if (isProprietario()){
            risposta=(this.nCase>CostantiCaselle.MAX_CASE-1) && (!this.hotel) && (this.proprietario.getSoldi()>=this.prezzoHotel);
        }
        return risposta;
    }

    public int caseAcquistabili(){
        int houses =0;
        if (isProprietario()){
            houses= calcoloCaseAcquistabili(maxAcquistabili());
        }
        return houses;

    }
    private int calcoloCaseAcquistabili(int maxAcquistabili) {
        int soldiGiocatore= this.proprietario.getSoldi();

        if (soldiGiocatore < this.prezzoCasa*maxAcquistabili) {
            return calcoloCaseAcquistabili(--maxAcquistabili);
        } else {
            return maxAcquistabili;
        }
    }
    private int maxAcquistabili(){
        return CostantiCaselle.MAX_CASE-this.nCase;
    }

    public void resetAcquisti(){
        this.proprietario=null;
        this.nCase=0;
        this.hotel=false;
    }

    private String infoCasellaPrezziEdifici() { // non ci sta CHF, come possiamo ridurre la lunghezza di questa riga?
        StringBuilder info = new StringBuilder();
        info.append(CostantiCaselle.SPAZIO+CostantiCaselle.CHAR_CASA).append(this.prezzoCasa).append("CHF ");
        info.append(CostantiCaselle.CHAR_HOTEL).append(this.prezzoHotel).append("CHF");
        return info.toString();
    }

    private String infoCasellaNumeroEdifici() {
        StringBuilder info = new StringBuilder();
        if (this.nCase>0) info.append(CostantiCaselle.CHAR_CASA.repeat(Math.max(0, this.nCase)));
        if (this.hotel)info.append(CostantiCaselle.CHAR_HOTEL);
        return info.toString();
    }

    public int getNCase(){
        if (!this.hotel){
            return this.nCase;
        } else {
            return 1;
        }

    }
    @Override
    public void azioneCasella(Giocatore giocatorePagante){
        if (this.proprietario==null){
            if (!menuAcquistoTerreno(giocatorePagante)){
                super.azioneCasella(giocatorePagante);
            } else azioneCasella(giocatorePagante); // se si vuole far compare le case nello stesso turno
        } else if (!(this.proprietario.equals(giocatorePagante))){
            giocatorePagante.pagamentoAffitto(this.proprietario,getPedaggio());
        } else {
            acquistaHotelCase();
        }
    }

    private boolean controlloCaseAcquistate(){
        boolean risposta=false;
        if (this.getNumeroArrayGiocatore()==CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA){
            return true;
        }
        risposta=proprietario.getNCaselleAcquistate(this.nArrayGiocatore)>=this.getNCaselleCategoria();
        return risposta;
    }

    private void acquistaHotelCase(){
        if (this.proprietario!=null && controlloCaseAcquistate()){
            Proprieta.menuAcquisti[1].menu(this.proprietario, this);
            menuAcquisti[1].pagamentoGiaEffettuato();
        }
    }

    @Override
    public int getPedaggio() {
        int pedaggio=super.getPedaggio();
        if (this.proprietario!=null){
            pedaggio-=calcoloAffitto();
        }
        return pedaggio;
    }
    private int calcoloAffitto(){
        int affitto=0;
        if (this.hotel){
            affitto=CostantiCaselle.AUMENTO_PREZZO_HOTEL;
        } else {
            affitto=CostantiCaselle.AUMENTO_PREZZO_CASA*this.nCase;
        }
        return affitto;
    }



    private boolean menuAcquistoTerreno(Giocatore giocatorePagante){
        boolean risposta=false;
        menuAcquisti[0].menu(giocatorePagante, this);
        risposta = ((menuAcquisti[0]).pagamentoGiaEffettuato());
        if (risposta) aggiungiTerreno(giocatorePagante);
        return risposta;
    }
    private void aggiungiTerreno(Giocatore giocatoreCorrente){
        if (this.nArrayGiocatore<=NomiCaselle.getUltimaPosizione()){
            giocatoreCorrente.aggiuntaTerreno(this.nArrayGiocatore);
        }
    }
}
