import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
 
public class KolkoIKrzyzyk {
    public static void main(String[] args) {
        System.out.println("Kó³ko i krzy¿yk. Tablica ma 3x3. Podaj wspó³rzêdne pierwszego Twojego kó³ka");
       
        String[] wolnePola = {"00", "01", "02", "10", "11", "12", "20", "21", "22"};
        String[] wolnePolaTemp = new String[0];
        boolean graAktywna = true;
        String krzyzykGracz = "";
        boolean exit = false;
        boolean zwyciezcaGracz = false;
        boolean zwyciezcaKomputer = false;
//  Wype³niamy tablice 9x9 samymi zerami
        int[][] plansza = new int[3][3];
        for (int i=0; i<plansza.length; i++) {
            for (int j=0; j<plansza.length; j++) {
                plansza[i][j] = 0;
            }
        }
       
        String[][] prezentacjaPlanszy = new String[3][3];
        for (int i=0; i<prezentacjaPlanszy.length; i++) {
            for (int j=0; j<prezentacjaPlanszy.length; j++) {
                prezentacjaPlanszy[i][j] = "D";
                System.out.print(prezentacjaPlanszy[i][j]);
            }
            System.out.println();
        }
       
        while (graAktywna) {
            boolean poprawne = false;
            while (!poprawne) {        
                System.out.println("Podaj Twój ruch - Plansza ma wiersze i kolumny 0, 1 i 2 \n"
                		+ "Wspó³rzêdne na planszy wg wzoru: Xxy (np. X12): ");
                Scanner sc = new Scanner(System.in);
                krzyzykGracz = sc.nextLine();
                if (!(krzyzykGracz.substring(0, 1).equals("X"))){
                	System.out.println("B³¹d wprowadzenia. Wpisz X i wspolrzedne na planszy");
                } else {
                	krzyzykGracz = krzyzykGracz.substring(1);
	                for (int i=0; i<wolnePola.length; i++) {
	                    if (wolnePola[i].equals(krzyzykGracz)) {
	                        poprawne = true;
	                        break;
	                    }
	                }
                }
                if (krzyzykGracz.equals("exit")){
                    poprawne = true;
                    exit = true;
                } else if (!poprawne) {        
                    System.out.println("Poda³eœ nie poprawne wspó³rzêdne (Plansza ma wiersze 0, 1 i 2, oraz "
                                + "kolumny 0, 1 i 2. Jeœli chcesz przerwaæ grê, wpisz exit");  
                }
            }
            
            if (exit) {
            	graAktywna = false;
            	break;
            }
//  Usuwam z Wolnych pól wybrane przez gracza miejsce
            for (int i=0; i<wolnePola.length; i++) {
                if (wolnePola[i].equals(krzyzykGracz)) {
                    for (int k=i; k<wolnePola.length; k++) {
                        if (k<wolnePola.length-1) {
                            wolnePola[k] = wolnePola[k+1];
                        }
                    }
                    wolnePolaTemp = wolnePola;
                    wolnePola = new String[wolnePolaTemp.length -1];
                    for (int j=0; j<wolnePola.length; j++) {
                        wolnePola[j] = wolnePolaTemp[j];
                    }
                }
            }
           
            int ruchGraczaX = Integer.parseInt(krzyzykGracz.substring(0, 1));
            int ruchGraczaY = Integer.parseInt(krzyzykGracz.substring(1, 2));
            plansza[ruchGraczaX][ruchGraczaY] = 1;
            prezentacjaPlanszy[ruchGraczaX][ruchGraczaY] = "X";
//      Drukujemy plansze w konsoli z wstawoinym przez gracza znakiem X
            for (int i=0; i<prezentacjaPlanszy.length; i++) {
                for (int j=0; j<prezentacjaPlanszy.length; j++) {
                    System.out.print(prezentacjaPlanszy[i][j]);
                }
                System.out.println();
            }
           
//  Ruch komputera
            if (wolnePola.length != 0) {
	            int kolkoKomp = new Random().nextInt(wolnePola.length);
	            String wyborKompa = wolnePola[kolkoKomp];
	            System.out.println("Komputer wybra³: " + wyborKompa);
	            int ruchKompaX = Integer.parseInt(wyborKompa.substring(0, 1));
	            int ruchKompaY = Integer.parseInt(wyborKompa.substring(1, 2));
	            plansza[ruchKompaX][ruchKompaY] = -1;
	            prezentacjaPlanszy[ruchKompaX][ruchKompaY] = "O";
	//  Usuwam z Wolnych pól wybrane przez kompa miejsce
	            for (int i=0; i<wolnePola.length; i++) {
	                if (wolnePola[i].equals(wyborKompa)) {
	                    for (int k=i; k<wolnePola.length; k++) {
	                        if (k<wolnePola.length-1) {
	                            wolnePola[k] = wolnePola[k+1];
	                        }
	                    }
	                    wolnePolaTemp = wolnePola;
	                    wolnePola = new String[wolnePolaTemp.length -1];
	                    for (int j=0; j<wolnePola.length; j++) {
	                        wolnePola[j] = wolnePolaTemp[j];
	                    }
	                }
	            }
	           
	//   Drukujemy plansze w konsoli z wstawoinym przez komputer znakiem O
	            for (int i=0; i<prezentacjaPlanszy.length; i++) {
	                for (int j=0; j<prezentacjaPlanszy.length; j++) {
	                    System.out.print(prezentacjaPlanszy[i][j]);
	                }
	                System.out.println();
	            }
            }
           
//  Warukni zakonczenia gry
            if (((plansza[0][0] + plansza[0][1] + plansza[0][2])== 3) || ((plansza[1][0] + plansza[1][1] + plansza[1][2])== 3) || ((plansza[2][0] + plansza[2][1] + plansza[2][2])== 3) 
            		|| ((plansza[0][0] + plansza[1][0] + plansza[2][0])== 3) || ((plansza[0][1] + plansza[1][1] + plansza[2][1])== 3) || ((plansza[0][2] + plansza[1][2] + plansza[2][2])== 3) 
            		|| ((plansza[0][0] + plansza[1][1] + plansza[2][2])== 3) || ((plansza[2][0] + plansza[1][1] + plansza[0][2])== 3)){
                System.out.println("Wygra³ gracz!");
                graAktywna = false;
                break;
            } else if (((plansza[0][0] + plansza[0][1] + plansza[0][2])== -3) || ((plansza[1][0] + plansza[1][1] + plansza[1][2])== -3) || ((plansza[2][0] + plansza[2][1] + plansza[2][2])== -3)
            		|| ((plansza[0][0] + plansza[1][0] + plansza[2][0])== -3) || ((plansza[0][1] + plansza[1][1] + plansza[2][1])== -3) || ((plansza[0][2] + plansza[1][2] + plansza[2][2])== -3)
            		|| ((plansza[0][0] + plansza[1][1] + plansza[2][2])== -3) || ((plansza[2][0] + plansza[1][1] + plansza[0][2])== -3)){
                
            	System.out.println("Wygra³ komputer!");
                graAktywna = false;
                break;
            } else if (wolnePola.length==0) {
                System.out.println("remis");
                graAktywna = false;
                break;
            }
           
        }
    }
}