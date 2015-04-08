package devmobile.projet_tdm1.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Chaine implements Parcelable{
	private String label;
	private String iconId;

	private ArrayList<ProgrammeTele> programmes = new ArrayList<ProgrammeTele>();
	
	
	public Chaine(String label, String iconId, ArrayList<ProgrammeTele> programmes){
		this.label = label;
		this.iconId = iconId;
		//this.programmes = programmes;
        this.programmes.add(new ProgrammeTele(this, "thematique", 12, 14, "descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif descriptif", "skyknights", iconId, "titre", false));
	}

    public Chaine(Parcel in) {
        this.iconId = in.readString();
        this.label  = in.readString();
        in.readTypedList(programmes, ProgrammeTele.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iconId);
        dest.writeString(this.label);
        dest.writeTypedList(this.programmes);
    }

    public static final Parcelable.Creator<Chaine> CREATOR = new Parcelable.Creator<Chaine>() {

        public Chaine createFromParcel(Parcel in) {
            return new Chaine(in);
        }

        public Chaine[] newArray(int size) {
            return new Chaine[size];
        }
    };
}
