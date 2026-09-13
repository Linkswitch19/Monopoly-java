package monopoly.componentigioco;

import monopoly.utilita.Costanti;

import java.util.Objects;
import java.util.Random;

public class Dado {
    public static Dado[] dadi;
    private int ultimoLancio;
    public static boolean visivo=false;

    public Dado(int ultimoLancio) {
        this.ultimoLancio = ultimoLancio;
    }
    public static void printDadi(){
        for (Dado dado : dadi) {
            System.out.println(dado);
        }
    }
    public static boolean confrontaDadi(){
        boolean uguali=true;
        for (Dado dado : dadi) {
            if (!(Dado.dadi[0].equals(dado))){
                uguali=false;
                break;
            }
        }
        return uguali;
    }

    public void setUltimoLancio(int ultimoLancio) {
        visivo=true;
        this.ultimoLancio = ultimoLancio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dado dado = (Dado) o;
        return this.ultimoLancio == dado.ultimoLancio;
    }


    public static int sommaDadi(){
        int somma=0;
        for (Dado dado : dadi) {
            somma+=dado.ultimoLancio;
        }
        return somma;
    }
    public static void creaDadi(){
        Dado.dadi=new Dado[Costanti.NUMERO_DADI];
        for (int i = 0; i < Dado.dadi.length; i++) {
            dadi[i]=new Dado(1);
        }
    }
    public static void lancioDadi(){
        for (Dado dado : dadi) {
            dado.lancioDado();
        }
    }


    private void lancioDado(){
        Random random = new Random();
        this.ultimoLancio = random.nextInt(Costanti.NUMERO_DADO_MIN,Costanti.NUMERO_DADO_MAX+1); // il secondo numero non è compreso
    }



    public static Dado[] getDadi(){
        return Dado.dadi;
    }

    @Override
    public String toString(){
        if (visivo) return "";
        return switch (this.ultimoLancio) {
            case 1 -> ("""
                    * * * * * *
                    *         *
                    *    ●    *
                    *         *
                    * * * * * *
                                            
                    E' uscito 1
                    """);
            case 2 -> ("""
                    * * * * * *
                    * ●       *
                    *         *
                    *       ● *
                    * * * * * *
                                            
                    E' uscito 2
                    """);
            case 3 -> ("""
                    * * * * * *
                    * ●       *
                    *    ●    *
                    *       ● *
                    * * * * * *
                                            
                    E' uscito 3
                    """);
            case 4 -> ("""
                    * * * * * *
                    * ●     ● *
                    *         *
                    * ●     ● *
                    * * * * * *
                                            
                    E' uscito 4
                    """);
            case 5 -> ("""
                    * * * * * *
                    * ●     ● *
                    *    ●    *
                    * ●     ● *
                    * * * * * *
                                            
                    E' uscito 5
                    """);
            case 6 -> ("""
                    * * * * * *
                    * ●     ● *
                    * ●     ● *
                    * ●     ● *
                    * * * * * *
                                            
                    E' uscito 6
                    """);
            default -> ""+this.ultimoLancio;
        };
    }
}
