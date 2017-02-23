package willy.individual.com.minilinkedinapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by zhenglu on 2/18/17.
 */

public class BasicInfo implements Parcelable{

    public String id;

    public String userName;

    public String email;

    // TODO Image

    public BasicInfo() {
        id = UUID.randomUUID().toString();
    }

    protected BasicInfo(Parcel in) {
        id = in.readString();
        userName = in.readString();
        email = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(userName);
        parcel.writeString(email);
    }

    public static final Creator<BasicInfo> CREATOR = new Creator<BasicInfo>() {
        @Override
        public BasicInfo createFromParcel(Parcel in) {
            return new BasicInfo(in);
        }

        @Override
        public BasicInfo[] newArray(int size) {
            return new BasicInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
