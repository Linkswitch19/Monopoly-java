package monopoly.componentigioco.casella;

public interface CaseHotel extends Acquistabile {
    boolean acquistoCasaHotel(int numeroAcquisti);
    int getNCase();
    boolean isHotelAcquistabile();
    int caseAcquistabili();


}
