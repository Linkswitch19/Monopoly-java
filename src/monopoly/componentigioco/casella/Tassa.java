package monopoly.componentigioco.casella;

import monopoly.Coordinate;
import monopoly.componentigioco.Tabellone;
import monopoly.utilita.Costanti;

import java.util.Random;

public abstract class Tassa extends Casella {

    public static void creaTasse(Casella[][] caselle){
        new TassaDiLusso(caselle);
        new TassaPatrimoniale(caselle);
    }

}
