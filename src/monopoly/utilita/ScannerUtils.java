package monopoly.utilita;

import monopoly.componentigioco.giocatore.Giocatore;

import java.util.Scanner;

public abstract class  ScannerUtils {
    private static final Scanner scanner=new Scanner(System.in);
    private static final int charMax=8;

    public static void chiudiScanner(){
        scanner.close();
    }

    public static String inputNomeGiocatore(final int numeroGiocatore){
        while (true){
            System.out.print("Inserisci il nome del giocatore " + numeroGiocatore + ": ");
            String nome = scanner.nextLine().strip(); // non posso mettere this perche static
            if ((nome.length()<=charMax) && (!nome.isBlank())){
                return nome;
            }
            System.out.println("Il nome deve avere al massimo " + charMax + " caratteri e non può essere vuoto");
        }
    }

    public static char inputSimboloGiocatore(final int numeroGiocatore){
        while (true) {
            System.out.print("Inserisci il simbolo del giocatore " + numeroGiocatore + ": ");
            String inputUtente = scanner.nextLine().strip().toUpperCase(); // non posso mettere this perche static
            if ((inputUtente.length()==1) && (!inputUtente.isBlank())){
                return inputUtente.charAt(0);
            }
            System.out.println("Inserisci soltanto e almeno un carattere");
        }
    }

    public static int readIntegerInRange(int min, int max) {
        while (true) {
            System.out.println("Inserisci un numero tra " + min + " e " + max + ": ");
            if (scanner.hasNextInt()) {
                int inputUser = scanner.nextInt();
                if (!(inputUser < min || inputUser > max)){
                    emptyTheScanner();
                    return inputUser;
                }
                System.out.println("Errore: il numero non è valido.");
            } else {
                System.out.println("Errore: non hai inserito un numero.");
            }
            emptyTheScanner();
        }
    }
    public static Giocatore leggiSimboloStringa(String[] stringa){

        if (stringa[0].length()>8 || stringa[0].isBlank() || stringa[1].isBlank()){
            return null;
        }


        return new Giocatore(stringa[0],stringa[1].strip().toLowerCase().charAt(0),Costanti.RIGHE-1,Costanti.CASELLE_PER_RIGA-1);
    }

    public static void emptyTheScanner() { scanner.nextLine(); }




}
