// κλάση που θα περιέχει την main
import java.util.Scanner;
import java.util.Random;

public class Mythopoly {
	
	protected static double budget[] = new double[] {1000,1000}; //Το υπόλοιπο του λογαριασμού κάθε παίκτη

	public static void main (String[] args) {
		int box[] = new int[] {0,0};//Η θέση κάθε παίκτη πάνω στο ταμπλό. Παίρνει τιμές 0-15
		int round = 1;//Ο γύρος στον οποίο βρίσκονται οι παίκτες
		//Εμφάνιση οδηγιών
		Instructions();
		//Εμφάνιση τοποθεσιών
		Board b = new Board();
		//Διάβασμα ονομάτων παικτών και δημιουργία αντικειμένων
		Scanner s = new Scanner (System.in);
		String pl1, pl2;
		System.out.println("Παρακαλώ δώστε όνομα για τον χρήστη 1.");
		pl1= s.nextLine();
		System.out.println("Παρακαλώ δώστε όνομα για τον χρήστη 2.");
		pl2= s.nextLine();
		Players player1 = new Players(pl1);
		Players player2 = new Players(pl2);
		
		int d;//Η τιμή που έφερε το ζάρι
		do {
			d = Dice(pl1);
			round = player1.location(d,box,1,round);//έχει ενημερωθεί το box
			d = Dice(pl2);
			round = player2.location(d,box,2,round);//έχει ενημερωθεί το box
		} while (round<=10 && budget[0]>0 && budget[1]<0);
	}

	public static void Instructions() {
		System.out.println("Καλώς ορίσατε στο παιχνίδι μας!");
		System.out.println("Πρόκειται για μια παραλλαγή του κλασικού επιτραπεζίου 'ΜΟΝΟΠΟΛΗ'.");
		System.out.println("Κάθε παίκτης ρίχνει ένα ζάρι και κάνει τον αντίστοιχο αριθμό βημάτων.");
		System.out.println("Με την έναρξη του παιχνιδιού οι δύο παίκτες έχουν ένα συγκεκριμένο ποσό στη διάθεση τους(1000€) το οποίο χρησιμοποιούν για να αγοράσουν ακίνητα σε δώδεκα τοποθεσίες στην Ελλάδα.");
		System.out.println("Οι παίκτες έχουν την επιλογή να αγοράσουν είτε οικόπεδα,είτε κατοικιές με το ανάλογο κόστος");
		System.out.println("Κάθε φορά που κάποιος παίκτης βρίσκεται σε ιδιοκτησία του αντιπάλου του θα πρέπει να καταβάλει το αντίστοιχο ενοίκιο, το ποσό αυτό θα αφαιρείται απο το λογαριασμό του");
		System.out.println("Όταν ένας παίκτης μπαίνει στη φυλακή αφαιρούνται αυτόματα από το διαθέσιμο του υπόλοιπο 200€");
		System.out.println("Δίνεται η δυνατότητα στους παίκτες να αυξήσουν το υπόλοιπο του λογαριασμού τους κατά 50€ στην περίπτωση που απαντήσουν σωστά σε μια ερώτηση");
		System.out.println("Επίσης κάθε φορά που οι παίκτες περνούν από την αφετηρία προστίθονται στον λογαριασμό τους 200€.");
		System.out.println("Το παιχνίδι τελειώνει εάν ολοκληρωθούν 10 γύροι ή εάν τα χρήματα ενός παίκτη δεν επαρκούν για την πληρωμή ενοικίου στον αντίπαλο.");
		System.out.println("Νικητής θα είναι εκείνος που στο τέλος θα έχει την περιουσία με τη μεγαλύτερη αξία.");		
	}
	//Η μέθοδος που χρησιμοποιείται για τη ρίψη του ζαριού
	public static int Dice(String pl) {
		System.out.println("Παρακαλώ να ρίξει το ζάρι ο παίκτης: " + pl);
		int min = 1;
		int max = 6;
		Random random = new Random();
		int value = random.nextInt(max + min) + min;
		System.out.println("Ο παίκτης " + pl + " έφερε " + value);
		return value;		
	}
}
