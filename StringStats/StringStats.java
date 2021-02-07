/*
 * Ohjelma, joka laskee esikäsittelemästään
 * merkkijonosta joitakin tunnuslukuja.
 */

public class StringStats {
public static void main(String[] args) {
 
         // Alustetaan vakiot
         final char EROTIN = ' ';
         final char kylla = 'y';
         final char ei = 'n';
         char merkki2;

         System.out.println("Hello! I calculate some string statistics.");

         do {
            System.out.println("Please, enter a string:");
            String merkkijono = In.readString();
            int jononPituus = merkkijono.length();
            String merkkijono1 = "";

            // Poistetaan kaikki välimerkit: pilkku, piste, puolipiste, kaksoispiste,
            // lainausmerkki, hipsut, jakomerkki, kaarisulkeet, huutomerkki ja kysymysmerkki.
            for (int i = 0; i < jononPituus; i = i + 1) {
               char merkki = merkkijono.charAt(i);
               if (merkki != ',' && merkki != '.'
               && merkki != ';' && merkki != ':' && 
               merkki != '\'' && merkki != '\"' && merkki != '/'
               && merkki != '(' && merkki != ')' && merkki!= '!' && merkki != '?' ) 
               merkkijono1 = merkkijono1 + merkki;
            }
            System.out.println("\"" + merkkijono1 + "\"");

            int min1 = 1001; // Ohjeissa sanotaan että syöte max 1000 merkkiä
            int min2 = 1001;
            int max1 = 0;
            int max2 = 0;
            int sananPituus = 0;
            int osienLkm = 1; //Oletetaan että sanoja aina vähintään 1
            int pituus = 0;
            //Saadaan ohitettua laskurien max2 ja min2 päivitys ekalla max1 ja max2 muunto kerralla.
            boolean pitkäLippu = false; 
            boolean lyhytLippu = false;

            for (int i = 0; i < merkkijono1.length(); i = i + 1) {  
               char a = merkkijono1.charAt(i);  
               if (a == EROTIN) {
                  osienLkm = osienLkm + 1; // Lasketaan osien lukumäärä
               }
               else { 
                  pituus = pituus + 1;
                  sananPituus = sananPituus + 1;
               }

               if (a == EROTIN || i + 1 == merkkijono1.length()){ 
               //Jos merkki on väli tai vika merkki niin päivitetään laskureita
                  if (sananPituus > max1){ // Pisin sana
                     if (pitkäLippu == true) {
                        max2 = max1;
                     }
                     max1 = sananPituus;
                     pitkäLippu = true;
                  }
                  else if (sananPituus > max2 && sananPituus < max1) {
                     max2 = sananPituus; // Toiseksi pisin sana
                  }
                  if (sananPituus < min1) { //Lyhin sana
                     if (lyhytLippu == true) {
                        min2 = min1;
                     }
                     min1 = sananPituus;
                     lyhytLippu = true;
                  }
                  else if (sananPituus < min2 && sananPituus > min1) { // Toiseksi lyhin sana
                     min2 = sananPituus;
                  }
                  sananPituus = 0;
               }
            }
            //Jos sanoja oli vain 1 niin tarvitaan ehdot:
            if (max2 == 0) {
               max2 = max1;
            }
            if (min2 == 1001) {
               min2 = min1;
            }

            int keskiarvo = (int)Math.round((double)pituus/(double)osienLkm); // Keskiarvo

            //Tulostetaan 
            System.out.println("- The number of parts is " + osienLkm + ".");
            System.out.println("- The sum of part lengths is " + pituus + ".");
            System.out.println("- The average length of parts is " + keskiarvo + ".");
            System.out.println("- The length of the shortest part is " + min1 + ".");
            System.out.println("- The length of the second shortest part is " + min2 + ".");
            System.out.println("- The length of the second longest part is " + max2 + ".");
            System.out.println("- The length of the longest part is " + max1 + ".");

            // Lippu
            boolean onkoTosi = true;

               do {
                  System.out.println("Continue (y/n)?");
                  merkki2 = In.readChar();
                  if (merkki2 == 'n') {
                  onkoTosi = false; 
                  }
                  else if (merkki2 == 'y' ) {
                  onkoTosi = false;    
                  }
                  else {
                     System.out.println("Error!"); 
               }
            }
            while (onkoTosi);

         }
         while(merkki2 == kylla);
         System.out.println("See you soon.");
    }
}