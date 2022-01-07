import java.util.Scanner;

public class Board {

	protected static String[] field = {"Αφετηρία",
								"Θήβα",
								"Μέγαρα",
								"Πήλος",
								"Φυλακή",
								"Βεργίνα",
								"Ελευσίνα",
								"Μυκήνες",
								"Ερωτήσεις",
								"Αθήνα",
								"Σπάρτη",
								"Δωδώνη",
								"Πήγαινε στην φυλακή",
								"Όλυμπος",
								"Δελφοί",
								"Αρχαία Ολυμπία" };

	// -1: Ό,τι δεν μπορεί να αγοραστεί, 0: δεν έχει αγοραστεί ακόμη, 1: αγοράστηκε το οικόπεδο, 2: αγοράστηκε η κατοικία 
	//Η 1η στήλη αντιστοιχεί στον 1ο παίκτη και η 2η στον 2ο παίκτη
	protected static int[][] availability = { {-1, -1}, 
											  {0, 0},
											  {0, 0},
											  {0, 0},
											  {-1, -1},
											  {0, 0},
											  {0, 0},
											  {0, 0},
											  {-1, -1},
											  {0, 0},
											  {0, 0},
											  {0, 0},
											  {-1, -1}, 
											  {0, 0},
											  {0, 0},
											  {0, 0}   };

	public Board() {
	
	}
	
	public static void board(int[] box,int i) {
		int n = box[i];
		System.out.println("Βρίσκεστε στο κουτάκι " + field[n]);
		switch (box[i]) {
			case 0 : 
				start(i);
			case 1 : 
				realEstate(i, n);
			case 2 : 
				realEstate(i, n);
			case 3 : 
				realEstate(i, n);
			case 4 : 
				prison(i);
			case 5 :
				realEstate(i, n);
			case 6 :
				realEstate(i, n);
			case 7 :
				realEstate(i, n);
			case 8 :
				if (Quiz.question()) {
					Mythopoly.budget[i] = Mythopoly.budget[i] + 50;
				}
			case 9 :
				realEstate(i, n);
			case 10 :
				realEstate(i, n);
			case 11 :
				realEstate(i, n);
			case 12 : 
				System.out.println("Πηγαίνετε στην φυλακή. Σας αφαιρούνται 200€");
				box[i] = 4; //Με γυρνάει πίσω στο κελί 4.
				prison(i);
			case 13 :
				realEstate(i, n);
			case 14 :
				realEstate(i, n);
			case 15 : 
				realEstate(i, n);
		}
	}

	public static void start(int i) {
		Mythopoly.budget[i] = Mythopoly.budget[i] + 200; 
	}

	public static void prison(int i) {
		Mythopoly.budget[i] = Mythopoly.budget[i] - 200; 
	}

	public static void realEstate(int i, int n) {
		if (availability[n][i] == -1) {
			System.err.println("Δεν μπορείτε να αγοράσετε το συγκεκριμένο πεδίο!");
		} else if ((availability[n][i]) == 0) {
			if (availability[n][i + 1] = 0) {
				System.out.println("Αν θέλετε να αγοράσετε το οικόπεδο πληκτρολογήστε "ΝΑΙ".);
				Scanner sc = new Scanner(System.in);
				String answer = sc.nextLine();  
				if ((answer.equals("ΝΑΙ")) || (answer.equals("ναι"))) {
					availability[n][i] = 1;
					Mythopoly.budget[i] = Mythopoly.budget[i]  - 100 ; 
				}
			} else if (availability[n][i] == 1) {
				System.out.println("Δεν μπορείτε να αγοράσετε αυτό το οικόπεδο. Ανήκει στον αντίπαλο. Πληρώνετε ενοίκιο.");
				Mythopoly.budget[i]
			}
		}
		
	}

	public static void realEstateOfAPlayer {
		
	}
}