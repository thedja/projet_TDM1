package devmobile.projet_tdm1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Afifa RIH on 31/03/2015.
 */
public class ItemNavBarInfo implements Parcelable{
    int icon;
    int text;

    public ItemNavBarInfo(int icon, int text) {
        this.icon = icon;
        this.text = text;
    }

    public ItemNavBarInfo(Parcel in) {
        this.icon = in.readInt();
        this.text = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.icon);
        dest.writeInt(this.text);
    }

    public static final Creator<ItemNavBarInfo> CREATOR = new Creator<ItemNavBarInfo>() {

        public ItemNavBarInfo createFromParcel(Parcel in) {
            return new ItemNavBarInfo(in);
        }

        public ItemNavBarInfo[] newArray(int size) {
            return new ItemNavBarInfo[size];
        }
    };
}
