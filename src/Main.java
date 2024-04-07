import static tools.utility.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String[] operazioni = {"VODAFONE",
                "[1] Inserimento",
                "[2] Visualizzazione",
                "[3] Ricerca",
                "[4] Fine"};
       boolean Sitel=true;
        final int nMax = 3;
        int contrattiVenduti = 0;
        Contatto[] gestore = new Contatto[nMax];

        Scanner keyboard = new Scanner(System.in);

        boolean fine = true;
        do {
            switch (menu(operazioni, keyboard)) {
                case 1:

                    if (contrattiVenduti < nMax) {
                        //firma contratto
                        gestore[contrattiVenduti]=leggiPersona(Sitel, keyboard, gestore, contrattiVenduti);
                        contrattiVenduti++;
                    } else {
                        System.out.println("Non ci sono più contratti da vendere");
                    }
                    break;
                case 2: {
                    visualizza(gestore, contrattiVenduti);
                    break;
                }

                case 3: {
                    break;
                }

                default:
                    fine = false;
                    break;
            }
        } while (fine);
    }

    private static void visualizza(Contatto[] gestore, int contrattiVenduti){
       for(int i=0 ; i<=contrattiVenduti; i++) {
           System.out.println(gestore[i].toString());
       }
    }
    private static Contatto leggiPersona(boolean Sitel, Scanner keyboard, Contatto[] gestore, int contrattiVenduti) {
        // Sitel è true quando dobbiamo leggere il numero di telefono
        String[] tipoC = {"Telefono","1]abitazione", "2]cellulare", "3]aziendale"};
        boolean userAlreadyInsert;

        // Istanziato un oggetto di tipo contatto
        Contatto persona = new Contatto();

        // The cycle continue while userAlreadyInsert is true
        do {
            userAlreadyInsert = false;
            // Ask the contact name
            System.out.println("Inserisci il nome: ");
            persona.nome = keyboard.nextLine();

            // Ask the contact surname
            System.out.println("Inserisci il cognome: ");
            persona.cognome = keyboard.nextLine();

            // check if a contact has the same name and surname of the contact which we want to insert
            for (int i = 0; i < contrattiVenduti; i++) {
                // if a user has the same name and surname of the contact which we want to insert set userAlreadyInsert var to true
                if (gestore[i].nome.equals(persona.nome) && gestore[i].cognome.equals(persona.cognome)) {
                    System.out.println("ATTENZIONE: Un altro contatto ha gia' questo nome e cognome.");
                    userAlreadyInsert = true;
                }
            }
        } while (userAlreadyInsert);


        System.out.println("Inserisci il numero di telefono: ");
        // check if the user wants to put his phone number
        if (Sitel) {
            persona.telefono = keyboard.nextLine();  //Vado a leggere il numero di telefono
            //I valori assegnati all'attributo sono compresi nel range
            switch (menu(tipoC, keyboard)) {
                case 1 -> persona.tipo = tipoContratto.abitazione;
                case 2 -> persona.tipo = tipoContratto.cellulare;
                default -> persona.tipo = tipoContratto.aziendale;
            }
        }

        return persona;
    }
}