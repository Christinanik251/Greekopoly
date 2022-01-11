// κλάση που θα περιέχει την main
import java.util.Scanner;
import java.util.Random;

public class Greekopoly {
	
	protected static int budget[] = new int[] {1000,1000}; //Το υπόλοιπο του λογαριασμού κάθε παίκτη
	protected static int box[] = new int [] {0,0};//Η θέση κάθε παίκτη πάνω στο ταμπλό. Παίρνει τιμές 0-15
	public static void main (String[] args) throws InterruptedException {
		int round1 = 1;//Ο γύρος στον οποίο βρίσκονται οι παίκτες
		int round2 = 1;

		//Εμφάνιση οδηγιών
		Instructions();

		//Εμφάνιση τοποθεσιών
		Board b = new Board();

		//Διάβασμα ονομάτων παικτών και δημιουργία αντικειμένων
		Scanner s = new Scanner(System.in);
		String pl1, pl2;
		System.out.println("Παρακαλώ δώστε όνομα για τον χρήστη 1.");
		pl1 = s.nextLine();
		System.out.println("Παρακαλώ δώστε όνομα για τον χρήστη 2.");
		pl2 = s.nextLine();
		Players player1 = new Players(pl1);
		Players player2 = new Players(pl2);

		int d;//Η τιμή που έφερε το ζάρι
		do {
			d = Dice(pl1);
			round1 = player1.location(d,box,0,round1);//έχει ενημερωθεί το box
			Board.board(box,0);
			Thread.sleep(1000);
			System.out.println("Το υπόλοιπο του λογαριασμού σας είναι " + budget[0] + "€.");
			Thread.sleep(1000);
			d = Dice(pl2);
			round2 = player2.location(d,box,1,round2);//έχει ενημερωθεί το box
			Board.board(box,1);
			Thread.sleep(1000);
			System.out.println("Το υπόλοιπο του λογαριασμού σας είναι " + budget[1] + "€.");
			Thread.sleep(1000);
		} while ((round1 < 10) && (round2 < 10) && budget[0] > 0 && budget[1] > 0);

		if (round1 > round2) {
			findWinner(round1, pl1, pl2);
		} else {
			findWinner(round2, pl1, pl2);
		}
	}

	public static void Instructions() {
		System.out.println("Καλώς ορίσατε στο παιχνίδι μας!");
		System.out.println("Πρόκειται για μια παραλλαγή του κλασικού επιτραπεζίου 'ΜΟΝΟΠΟΛΗ'.");
		System.out.println("Κάθε παίκτης ρίχνει ένα ζάρι και κάνει τον αντίστοιχο αριθμό βημάτων.");
		System.out.println("Με την έναρξη του παιχνιδιού οι δύο παίκτες έχουν ένα συγκεκριμένο "
				+ "ποσό στη διάθεση τους(1000€) το οποίο χρησιμοποιούν για να αγοράσουν ακίνητα σε δώδεκα τοποθεσίες στην Ελλάδα.");
		System.out.println("Οι παίκτες έχουν την επιλογή να αγοράσουν είτε οικόπεδα,είτε κατοικιές με το ανάλογο κόστος");
		System.out.println("Κάθε φορά που κάποιος παίκτης βρίσκεται σε ιδιοκτησία του αντιπάλου του θα πρέπει "
				+ "να καταβάλει το αντίστοιχο ενοίκιο, το ποσό αυτό θα αφαιρείται απο το λογαριασμό του");
		System.out.println("Όταν ένας παίκτης μπαίνει στη φυλακή αφαιρούνται αυτόματα από το διαθέσιμο του υπόλοιπο 200€");
		System.out.println("Δίνεται η δυνατότητα στους παίκτες να αυξήσουν το υπόλοιπο του λογαριασμού τους κατά 50€ "
				+ "στην περίπτωση που απαντήσουν σωστά σε μια ερώτηση");
		System.out.println("Επίσης κάθε φορά που οι παίκτες περνούν από την αφετηρία προστίθονται στον λογαριασμό τους 200€.");
		System.out.println("Το παιχνίδι τελειώνει εάν ολοκληρωθούν 10 γύροι ή εάν τα χρήματα ενός παίκτη δεν επαρκούν "
				+ "για την πληρωμή ενοικίου στον αντίπαλο.");
		System.out.println("Νικητής θα είναι εκείνος που στο τέλος θα έχει την περιουσία με τη μεγαλύτερη αξία.");

		for (int k = 0; k <= 15; k++) {
			System.out.println( k + " " + Board.field[k]);
		}
	}
	//Η μέθοδος που χρησιμοποιείται για τη ρίψη του ζαριού
	public static int Dice(String pl) throws InterruptedException {
		System.out.println("Παρακαλώ ο παίκτης " + pl + " να πατήσει 1 για να ρίξει το ζάρι του.");
		int answer;
		Scanner sc = new Scanner(System.in);
		do {
		    answer = sc.nextInt();
		    if (answer!=1) {
		    	 System.out.println("Παρακαλώ πληκτρολογήστε έγκυρη απάντηση (1):");
		    }
		} while (answer!=1);
		Random ran = new Random();
		int value = (ran.nextInt(6)+1);
		System.out.println("Ο παίκτης " + pl + " έφερε " + value + ".");
		return value;
	}

	public static void findWinner(int round, String pl1, String pl2) {
		if (round >= 10) {
			int[] sum = new int[2];
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i <= 15; i++) {
					if (Board.availability[i][j] == 1 ) {
						sum[j] = sum[j] + 100;
					} else if (Board.availability[i][j] == 2) {
						sum[j] = sum[j] + 200;
					} else {
						sum[j] = sum[j] + 0;
					}
				}
				sum[j] = budget[j] + sum[j];
			}
			if (sum[0] > sum [1]) {
				System.out.println("Νικητής του παιχνιδιού είναι ο παίκτης '" + pl1 + "'.");
			} else if (sum[0] < sum [1]) {
				System.out.println("Νικητής του παιχνιδιού είναι ο παίκτης '" + pl2 + "'.");
			} else {
				System.out.println("Ισοπαλία.");
			}
		} else {
			if (budget[0] < 0) {
				System.out.println("Νικητής του παιχνιδιού είναι ο παίκτης '" + pl2 + "'.");
			} else {
				System.out.println("Νικητής του παιχνιδιού είναι ο παίκτης '" + pl1 + "'.");
			}
		}
	}
}


