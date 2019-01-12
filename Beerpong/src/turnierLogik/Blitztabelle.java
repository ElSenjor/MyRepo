package turnierLogik;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Blitztabelle {
	List<Team> teams;
	Blitztabelle(Spielplan spielplan){
		teams = Arrays.asList(spielplan.getTeams());
		
	}
	
	void update() {
		Collections.sort(teams);
	}

	String getString() {
		String ret = "<html>";
		for(int i=0; i< teams.size(); i++) {
			ret = ret + teams.get(i).getString()+"<br>";
		}
		ret=ret+"</html>";
		return ret;
	}
	void printBlitztabelle() {
		System.out.println(this.getString());
	}
}
