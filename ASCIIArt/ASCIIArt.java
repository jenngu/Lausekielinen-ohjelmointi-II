/* 
 * Ohjelma käsittelee ASCII- merkkeinä esitettyä grafiikkaa
 * erilaisten syötteiden avulla. 
 */
// Otetaan käyttöön tietojenkäsittelyssä avustavia luokkia
import java.io.*;
import java.util.*;

public class ASCIIArt17 {
    public static void main(String[] args) {
        // Luodaan vakiot
        final String print = "print";
        final String info = "info";
        final String reset = "reset";
        final String rotater = "rotater";
        final String rotatel = "rotatel"; 
        // Tulostetaan tervehdys
        System.out.println("-----------------------");
        System.out.println("| A S C I I A r t 1 7 |");
        System.out.println("-----------------------");
        // Varmistetaan, että komentoriviparametrejä on yksi
        if (args.length != 1) {
            // Virheilmoitus
            System.out.println("Invalid command-line argument!");
            // Hyvästit
            System.out.println("Bye, see you soon.");
        }
        else {
            // Luetaan tiedoston nimi komentoriviparametrista
            String tiedostoName = args[0];
            // Kututaan reset-operaatiota, joka täyttää taulukon
            char[][] taulukko = resetArray(tiedostoName);
            // Varmistetaan, että taulukkoon on varattu muistia
            if (taulukko != null) {
                // Alustetaan muuttuja, johon tallennetaan komento käyttäjältä
                String command = "";
            
                // Tehdään while-silmukka, johon liitetään komentoja
                // (print, reset, info, recolour, rotater, rotatel, quit),
                // kunnes käyttäjä ilmoittaa quit. 
                while (!command.equals("quit")) {
                    System.out.println("Please, enter a command:");
                    command = In.readString();
                    
                    // Käyttäjän antamat komennot
                    if (command.equals(print)) {
                        printArray(taulukko);
                    }
                    if (command.equals(info)) {
                        info(taulukko);
                    }    
                    if (command.equals(reset)) {
                        taulukko = resetArray(tiedostoName);
                    }    
                    if (command.equals(rotater)) {
                        taulukko = rotater(taulukko);
                    }    
                    if (command.equals(rotatel)) {
                        taulukko = rotatel(taulukko);
                    }    
                    if (command.length() > 10) {
                        char merkki1 = command.charAt(9);
                        char merkki2 = command.charAt(11);
                        recolour(taulukko, merkki1, merkki2);
                    }
                }
                System.out.println("Bye, see you soon.");
            }
            else {
                System.out.println("Invalid command-line argument!");
                System.out.println("Bye, see you soon.");
            }
        }
    }
    // Oma operaatio täyttää taulukon tekstitiedoston riveillä.
    public static char[][] resetArray(String tiedostoName) {
      if (tiedostoName != null) {
        // Varaudutaan virheeseen
        try {
            // Luodaan tiedosto-olio ja liitetään siihen viite
            File tiedosto = new File(tiedostoName);
            // Liitetään tiedostoon lukija
            Scanner lukija = new Scanner(tiedosto);
            int rivit  = 0;
            int sarakkeet = 0;
            String merkit = "";
            
            // Luetaan tiedoston rivejä yksi kerrallaan ja 
            // tulostetaan ne näytölle
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                merkit = merkit + rivi + "\n";
                sarakkeet = rivi.length();

                rivit = rivit + 1;
            }
            // Suljetaan lukija 
            lukija.close();
            
            String[] array = merkit.split("\n");
            
            // Luodaan taulukko
            char[][] taulukko = new char[rivit][sarakkeet];
            // Täytetään taulukko
            for (int i = 0; i < rivit; i = i + 1) {
                for (int y = 0; y < sarakkeet; y = y + 1) {
                    taulukko[i][y] = array[i].charAt(y);
                }
            }
            // Palautetaan taulukko
            return taulukko;
        }
        // Siepataan lähes kaikki virheet
        catch (Exception e) {
            return null;
        }
      }
      else {
        return null; 
      }
    }
    // Operaatio, joka tulostaa taulukon
    public static void printArray(char[][] t) {
        // Tulostetaan, jos taulukolle on varattu muistia
        if (t != null) {
            // Rivien ja sarakkeiden lukumäärät.
            int rivimäärä = t.length;
            int sarakemäärä = t[0].length;

            // Tulostetaan rivit.
            for (int rivi = 0; rivi < rivimäärä; rivi = rivi + 1) {
                for (int sarake = 0; sarake < sarakemäärä; sarake = sarake + 1) {
                    System.out.print(t[rivi][sarake]);  
                }
                System.out.println("");
            }
        }
    }
    // Operaatio, joka tulostaa tekstitiedoston tiedot näytölle
    public static void info(char[][] taulukko) {
      if (taulukko != null) {
        // Alustetaan taulukon sarakkeiden ja rivien lukumäärä
        int rivit = taulukko.length;
        int sarakkeet = taulukko[0].length;
        // Ilmoitetaan koko:
        System.out.println(rivit + " x " + sarakkeet);
        
        String merkit = "#@&$%x*o|!;:',. ";
        // Luodaan taulukot merkeille ja niiden lukumäärille
        char[] merkkiTaulukko = new char[16];
        int[] lkmTaulukko = new int[16];
        
        // Silmukka sijoittaa merkit taulukon ensimmäiselle riville
        for (int i = 0; i < 16; i = i + 1) {
            int lkm = 0;
            char merkki = merkit.charAt(i);
            merkkiTaulukko[i] = merkki;
            char verrattava;
            
            // Silmukka laskee merkkien lukumäärän kuvassa
            for (int y = 0; y < rivit; y = y + 1) {
                for (int a = 0; a < sarakkeet; a = a + 1) {
                    verrattava = taulukko[y][a];
                    // Vertaillaan merkkejä 
                    if (verrattava == merkki) {
                       lkm = lkm + 1;
                    }
                }
            }
            // Sijoitetaan merkkien määrä taulukkoon
            lkmTaulukko[i] = lkm;
        }    
        // Tulostetaan taulukko
        for (int x = 0; x < 16; x = x + 1) {
           System.out.println(merkkiTaulukko[x] + " " + lkmTaulukko[x]);
        }
      }
    }  
    // Operaatio, joka kääntää tekstitiedoston kuvan myötäpäivään
    public static char[][] rotater(char[][] taulukko) {
      if (taulukko != null) {
          // Liitetään alkuperäisen taulukon rivien ja sarakkeiden määrät muuttujiin
          int rivi = taulukko.length;
          int sarakkeet = taulukko[0].length;
          // Luodaan uusi taulukko, joka palautetaan paluuarvona
          char[][] turnedArray = new char[sarakkeet][rivi];
        
          // Tehdään silmukka, joka sijoittaa alkuperäisen taulukon merkit 
          // yksitellen uuteen taulukkoon. 
          for (int i = 0; i < rivi; i = i + 1) {  
              for (int a = 0; a < sarakkeet; a = a + 1) {
                  // Silmukka joka sijoittaa merkin uuteen taulukkoon alkaen viimeisestä sarakkeesta
                turnedArray[a][rivi - 1 - i] = taulukko[i][a];
              }
          }
          return turnedArray;
      }    
      else {
        return null; 
      }   
    }
    // Operaatio joka kääntää tekstitedoston kuvan vastapäivään
    public static char[][] rotatel(char[][] taulukko) {
      if (taulukko != null) {
        // Liitetään alkuperäisen taulukon rivien ja sarakkeiden määrät muuttujiin
        int rivi = taulukko.length;
        int sarakkeet = taulukko[0].length;
        // Luodaan uusi palautettava taulukko
        char[][] turnedArray = new char[sarakkeet][rivi];
        
        // Sijoitetaan alkuperäisen taulukon merkit uuteen taulukkoon yksi kerrallaan
        for (int i = 0; i < sarakkeet; i = i + 1) {
            for (int a = 0; a < rivi; a = a + 1) {
                turnedArray[sarakkeet - 1 - i][a] = taulukko[a][i];
            }
        }
        return turnedArray;
      }
      else {
        return null;
      }
    }
    // Operaatio, joka muuttaa tietyn merkin värin toiseksi
    public static void recolour(char[][] taulukko, char merkki1, char merkki2) {
      if (taulukko != null) {
        // Silmukka vertailee taulukon merkkejä käyttäjän antamaan merkkiin. 
        for (int i = 0; i < taulukko.length; i = i + 1) {
            for (int a = 0; a < taulukko[0].length; a = a + 1) {
                char merkki = taulukko[i][a];
                if (merkki == merkki1) {
                    taulukko[i][a] = merkki2;
                }    
            }
        }
      }
    }
}