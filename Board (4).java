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

	
	public static void board(int[] box,int i) throws InterruptedException{
		int n = box[i];
		Thread.sleep(1000);
		System.out.println("Βρίσκεστε στο κουτάκι " + field[n] + ".");
		switch (box[i]) {
			case 0 : 
				start(i);
				break;
			case 1 : 
				realEstate(i, n);
				break;
			case 2 : 
				realEstate(i, n);
				break;
			case 3 : 
				realEstate(i, n);
				break;
			case 4 : 
				prison(i);
				break;
			case 5 :
				realEstate(i, n);
				break;
			case 6 :
				realEstate(i, n);
				break;
			case 7 :
				realEstate(i, n);
				break;
			case 8 :
				if (Quiz.question()) {
					System.out.println("Συγχαρητήρια απαντήσατε σωστά! Σας προστίθενται 50€ .");
					Greekopoly.budget[i] = Greekopoly.budget[i] + 50;
				}else {
					System.out.println("Λαθος απάντηση :(");
				}
				break;
			case 9 :
				realEstate(i, n);
				break;
			case 10 :
				realEstate(i, n);
				break;
			case 11 :
				realEstate(i, n);
				break;
			case 12 : 
				Thread.sleep(1000);
				System.out.println("Πηγαίνετε στην φυλακή. Σας αφαιρούνται 200€");
				box[i] = 4; //Με γυρνάει πίσω στο κελί 4.
				prison(i);
				break;
			case 13 :
				realEstate(i, n);
				break;
			case 14 :
				realEstate(i, n);
				break;
			case 15 : 
				realEstate(i, n);
				break;
		}
	}

	public static void start(int i) {
		Greekopoly.budget[i] = Greekopoly.budget[i] + 200; 
	}

	public static void prison(int i) {
		Greekopoly.budget[i] = Greekopoly.budget[i] - 200; 
	}

	// -1: Ό,τι δεν μπορεί να αγοραστεί, 0: δεν έχει αγοραστεί ακόμη, 1: αγοράστηκε το οικόπεδο, 2: αγοράστηκε η κατοικία 
	//Η 1η στήλη αντιστοιχεί στον 1ο παίκτη και η 2η στον 2ο παίκτη
	// n = τρέχων πεδίο

	public static void realEstate(int i, int n) throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		boolean check = true;

		if (availability[n][i] == -1) {
			Thread.sleep(1000);
			System.err.println("Δεν μπορείτε να αγοράσετε το συγκεκριμένο πεδίο!");
		} else if ((availability[n][i]) == 0) {
			//έλεγχος διαθεσιμότητας
			if (i == 0) {
				check = checkRealEstate(i + 1, n);
			} else if (i == 1) { 
				check = checkRealEstate(i - 1, n);
			}
			if (check == true) {
				int answer=0;
				Thread.sleep(1000);
				System.out.println("Αν θέλετε να αγοράσετε το οικόπεδο πληκτρολογήστε '1', "
						+ "διαφορετικά πληκτρολογήστε '2'.");
				do {
				    answer = sc.nextInt();
				    if (answer!=1 && answer!=2) {
				    	 System.out.println("Παρακαλώ πληκτρολογήστε έγκυρη απάντηση (1/2).");
				    }
				}while (answer!=1 && answer!=2);
				if (answer == 1) {
				    availability[n][i] = 1;
				    Greekopoly.budget[i] = Greekopoly.budget[i]  - 100; 
				    System.out.println("Αγοράσατε το οικόπεδο " + field[n] + "!");
				} 
			} else {
				System.out.println("Δεν μπορείτε να αγοράσετε αυτό το οικόπεδο. Ανήκει στον αντίπαλο. Πληρώνετε ενοίκιο 50€.");
				Greekopoly.budget[i] = Greekopoly.budget[i] - 50;
				switch (i) {
					case 0:
						Greekopoly.budget[i + 1] = Greekopoly.budget[i + 1] + 50;
						break;
					case 1:
						Greekopoly.budget[i - 1] = Greekopoly.budget[i - 1] + 50;
						break;
				}
			}
		} else if ((availability[n][i]) == 1) {
			Thread.sleep(1000);
			System.out.println("Αν θέλετε να αγοράσετε κατοικία πληκτρολογήστε πληκτρολογήστε '1',διαφορετικά πληκτολογήστε '2'.");
			int answer = sc.nextInt();
			if (answer == 1) {
				availability[n][i] = 2;
				Greekopoly.budget[i] = Greekopoly.budget[i]  - 200; 
				Thread.sleep(1000);
				System.out.println("Αγοράσατε την κατοικία στη περιοχή " + field[n] + ".");
			}
		} else if ((availability[n][i]) == 2) {
			Thread.sleep(1000);
			System.out.println("Δεν επιτρέπεται η αναβάθμιση.");
		}
	}

	//Έχουμε στείλει το i του αντιπάλου επομένως ελέγχουμε αν ο αντίπαλος έχει αγοράσει ή όχι κάποιο οικόπεδο.
	public static boolean checkRealEstate(int i, int n) {
		if (availability[n][i] == 0) {
			return true; //είναι διαθέσιμο προς αγορά 
		} else {
			return false;
		}
	}

}