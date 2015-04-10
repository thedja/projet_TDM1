package devmobile.projet_tdm1.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ProgrammeTele implements Parcelable{
    private static final String TAG = "MYTAG_ProgTele";

	private Chaine chaine;
	private String thematique;
	private int heureDebut;
	private int heureFin;
	private String trancheHoraire;
	private String descriptif;
	private String videoId;
	private String iconId;
    private String titre;
    private boolean favoris;

    public ProgrammeTele(Chaine chaine, String thematique, int heureDebut,
			int heureFin, String descriptif, String videoId, String iconId, String titre, boolean favoris) {

		super();
		this.chaine = chaine;
		this.thematique = thematique;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.descriptif = descriptif;
		this.videoId = videoId;
		this.iconId = iconId;
        this.titre = titre;
        this.favoris = favoris;

		
		if(heureDebut>=6 && heureFin<=13){
			this.trancheHoraire = "Matinee";
		}else if(heureDebut>=13 && heureFin<=19){
			this.trancheHoraire = "Apres-midi";
		}else if((heureDebut>=19 && heureFin<=24) || (heureDebut>=0 && heureFin<=6)){
			this.trancheHoraire = "Soiree";
		}
	}

    public void setFavoris(boolean favoris) {
        this.favoris = favoris;
    }

    public boolean isFavoris() {

        return favoris;
    }

    public ProgrammeTele(Parcel in) {
        this.thematique = in.readString();
        this.descriptif = in.readString();
        this.videoId = in.readString();
        this.iconId = in.readString();
        this.titre = in.readString();
        this.trancheHoraire = in.readString();
        this.heureDebut = in.readInt();
        this.heureFin = in.readInt();
        this.chaine = (Chaine)  in.readParcelable(Chaine.class.getClassLoader());
        this.favoris =  (boolean) in.readValue(Boolean.class.getClassLoader());
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

    public int getIcon(Resources resources) {
        return resources.getIdentifier(iconId, "raw", "devmobile.projet_tdm1");
    }

    public int getVideo(Resources resources) {
        return resources.getIdentifier(videoId, "raw", "devmobile.projet_tdm1");
    }

    public String getHoraire() {
        return heureDebut + "h - "+heureFin+"h";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thematique);
        dest.writeString(this.descriptif);
        dest.writeString(this.videoId);
        dest.writeString(this.iconId);
        dest.writeString(this.titre);
        dest.writeString(this.trancheHoraire);
        dest.writeInt(this.heureDebut);
        dest.writeInt(this.heureFin);
        dest.writeParcelable (this.chaine, flags);
        dest.writeValue(this.favoris);
    }

    public static final Creator<ProgrammeTele> CREATOR = new Creator<ProgrammeTele>() {

        public ProgrammeTele createFromParcel(Parcel in) {
            return new ProgrammeTele(in);
        }

        public ProgrammeTele[] newArray(int size) {
            return new ProgrammeTele[size];
        }
    };


    public void copy(ProgrammeTele programmeTele) {
        this.chaine = programmeTele.chaine;
        this.thematique = programmeTele.thematique;
        this.heureDebut = programmeTele.heureDebut;
        this.heureFin = programmeTele.heureFin;
        this.descriptif = programmeTele.descriptif;
        this.videoId = programmeTele.videoId;
        this.iconId = programmeTele.iconId;
        this.titre = programmeTele.titre;
        this.favoris = programmeTele.favoris;

        Log.i("ProgrammeTele", titre + " copy finished");
    }
}
