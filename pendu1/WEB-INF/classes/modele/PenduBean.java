package modele;

import java.util.List;

public class PenduBean 
{
	String mot;
	List<Character> lettresJouees;
	boolean[] lettresTrouvees;
	
	public char[] getMot() { // Pour simplifier l'utilisation dans la JSP
		return mot.toCharArray();
	}
	public void setMot(String mot) {
		this.mot = mot;
	}
	public List<Character> getLettresJouees() {
		return lettresJouees;
	}
	public void setLettresJouees(List<Character> lettresJouees) {
		this.lettresJouees = lettresJouees;
	}
	public boolean[] getLettresTrouvees() {
		return lettresTrouvees;
	}
	public void setLettresTrouvees(boolean[] lettresTrouvees) {
		this.lettresTrouvees = lettresTrouvees;
	}
	
}
