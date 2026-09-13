package monopoly.componentigioco.casella;
import monopoly.Coordinate;


public class Societa extends Casella{
    public static void creaSocieta(Casella[][] caselle){
        for (int i=0; i<2; i++){
            new Societa(caselle);
        }
    }
    public Societa(Casella[][] caselle) {
        super();
        setCasella(caselle);
    }

    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo=NomiCaselle.SOCIETA;
        super.setNomeColore(tipo.getNome(0),tipo.getColore());
    }


}
