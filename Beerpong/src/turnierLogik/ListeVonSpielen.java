package turnierLogik;

public class ListeVonSpielen {
	private Spiel[] spiele;
	public final int length;
	
	public ListeVonSpielen(Spiel[] spiele){
		this.spiele = spiele;
		length = spiele.length;
	}
	
	public String getStringTeamA(int nr) {
		if(spiele[nr]!=null) {
			return spiele[nr].getStringTeamA();
		}
		else {
			return "<leer>";
		}
	}
	
	public String getStringTeamB(int nr) {
		if(spiele[nr]!=null) {
			return spiele[nr].getStringTeamB();
		}
		else {
			return "<leer>";
		}
	}
	
	public Spiel getSpiel(int nr) {
		return spiele[nr];
	}
	
	
}
