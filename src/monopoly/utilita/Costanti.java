package monopoly.utilita;

public abstract class  Costanti {
    public static final int RIGHE = 11; // minimo 6x5
    public static final int CASELLE_PER_RIGA = 11;
    public static final int LARGHEZZA_CASELLA = 30;
    public static final int IMPORTO_DEL_VIA = 100;
    public static final int IMPORTO_PEDAGGIO_MIN = -150;
    public static final int IMPORTO_PEDAGGIO_MAX = -50;
    public static final int NUMERO_GIOCATORI = 4; //fino a 10 giocatori data l'ampiezza della casella
    public static final int NUMERO_DADO_MIN = 1;
    public static final int NUMERO_DADO_MAX = 6;
    public static final int NUMERO_DADI=2;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final int ANSI_MIN = 63;
    public static final int ANSI_MAX = 80;
    public static final int IMPORTO_INIZIALE_GIOCATORE = 2000;
    public static final int IMPORTO_INIZIALE_BANCA = 1000000;
    public static final int IMPORTO_PER_BANCAROTTA = 1000000;
    public static final int RIGHE_CASELLA = 7; //righe dei dettagli della casella
    public static final String COLORE_SFONDO = "\u001B[1;48;5;255m";
    public static final String COLORE_SFONDO_NULL = "\u001B[1;48;5;253m";
    public static final String RESET_SFONDO = "\u001b[49m";
    public static final int IMPORTO_TASSA_LUSSO = -200;
    public static final double PERCENTUALE_TASSA_PATRIMONIALE = 0.1;
    public static final int TENTATIVI_PRIGIONE = 3;
    public static final int IMPORTO_PER_USCIRE_PRIGIONE = 50;

}
