package devmobile.projet_tdm1.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Chaine implements Parcelable {
	private String label;
	private String iconId;
    private long chaineId;

	public Chaine(String label, String iconId, long chaineId){
		this.label = label;
		this.iconId = iconId;
        this.chaineId = chaineId;
    }

    public Chaine(Parcel in) {
        this.iconId = in.readString();
        this.label  = in.readString();
        this.chaineId = in.readLong();
    }

    public long getChaineId() {
        return chaineId;
    }
    public void setChainId(long chainId) {
        this.chaineId = chainId;
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
        dest.writeLong(this.chaineId);
    }

    public static final Creator<Chaine> CREATOR = new Creator<Chaine>() {

        public Chaine createFromParcel(Parcel in) {
            return new Chaine(in);
        }

        public Chaine[] newArray(int size) {
            return new Chaine[size];
        }
    };
}
