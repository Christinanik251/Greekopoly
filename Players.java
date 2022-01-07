
public class Players {
	
	protected String player;

	//Κατασκευαστής που δέχεται σαν όρισμα το όνομα κάθε παίκτη
	public Players(String pl) {
		player = pl;
	}
	public String getPlayers() {
		return player;
	}
	public void setPlayers(String player) {
		this.player = player;
	}

	public int location (int dice, int[] box, int i, int round) {
		if ((box[i] + dice) > 15) { 
				box[i] = box[i] + dice - 16; 
				round = round + 1;
		} else {
				box[i] = box[i] + dice;
		}
		return round;
	}

}
