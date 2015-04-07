package devmobile.projet_tdm1.model;

import android.content.res.Resources;

import java.util.ArrayList;

public class Chaine {
	private String label;
	private String iconId;

	private ArrayList<ProgrammeTele> programmes = new ArrayList<ProgrammeTele>();
	
	
	public Chaine(String label, String iconId, ArrayList<ProgrammeTele> programmes){
		this.label = label;
		this.iconId = iconId;
		//this.programmes = programmes;
        this.programmes.add(new ProgrammeTele(this, "thematique", 12, 14, "descriptif", null, iconId, "titre"));
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

    public int getIcon(Resources resources) {
        //TODO : retun iconId
        return resources.getIdentifier(iconId, "drawable", "devmobile.projet_tdm1");
    }
}
