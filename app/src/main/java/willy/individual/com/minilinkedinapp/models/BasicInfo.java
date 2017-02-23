package willy.individual.com.minilinkedinapp.models;

import android.net.Uri;
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

    public Uri imageUri;

    public BasicInfo() {
        id = UUID.randomUUID().toString();
    }

    protected BasicInfo(Parcel in) {
        id = in.readString();
        userName = in.readString();
        email = in.readString();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(userName);
        parcel.writeString(email);
        parcel.writeParcelable(imageUri, i);
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
