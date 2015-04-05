package devmobile.projet_tdm1.model;

import java.util.ArrayList;

public class Chaine {
	private String label;
	private String iconId;

	private ArrayList<ProgrammeTele> programmes;
	
	
	public Chaine(String label, String iconId, ArrayList<ProgrammeTele> programmes){
		this.label = label;
		this.iconId = iconId;
		this.programmes = programmes;
	}

	public String getIconId() {
		return iconId;
	}


	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public ArrayList<ProgrammeTele> getProgrammes() {
		return programmes;
	}


	public void setProgrammes(ArrayList<ProgrammeTele> programmes) {
		this.programmes = programmes;
	}
	
	
}
