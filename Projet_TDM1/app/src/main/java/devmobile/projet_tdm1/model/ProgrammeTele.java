package devmobile.projet_tdm1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProgrammeTele implements Parcelable{
	
	private Chaine chaine;
	private String thematique;
	private int heureDebut;
	private int heureFin;
	private String trancheHoraire;
	private String descriptif;
	private String videoId;
	private String iconId;
    private String titre;

    public ProgrammeTele(Chaine chaine, String thematique, int heureDebut,
			int heureFin, String descriptif, String videoId, String iconId, String titre) {

		super();
		this.chaine = chaine;
		this.thematique = thematique;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.descriptif = descriptif;
		this.videoId = videoId;
		this.iconId = iconId;
        this.titre = titre;

		
		if(heureDebut>=6 && heureFin<13){
			this.trancheHoraire = "Matinée";
		}else if(heureDebut>=13 && heureFin<19){
			this.trancheHoraire = "Aprés-midi";
		}else if((heureDebut>=19 && heureFin<24) || (heureDebut>=0 && heureFin<6)){
			this.trancheHoraire = "Soirée";
		}
	}

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Chaine getChaine() {
		return chaine;
	}
	public void setChaine(Chaine chaine) {
		this.chaine = chaine;
	}
	public String getThematique() {
		return thematique;
	}
	public void setThematique(String thematique) {
		this.thematique = thematique;
	}
	public int getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(int heureDebut) {
		this.heureDebut = heureDebut;
	}
	
	public int getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(int heureFin) {
		this.heureFin = heureFin;
	}
	
	public String getTrancheHoraire() {
		return trancheHoraire;
	}
	
	public void setTrancheHoraire(String trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}
	
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getIconId() {
		return iconId;
	}
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
