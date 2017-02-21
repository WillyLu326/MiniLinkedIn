package willy.individual.com.minilinkedinapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by zhenglu on 2/20/17.
 */

public class Experience implements Parcelable{

    public String id;

    public String companyName;

    public String jobTitle;

    public Date startDate;

    public Date endDate;

    public List<String> projects;

    protected Experience(Parcel in) {
        id = in.readString();
        companyName = in.readString();
        jobTitle = in.readString();
        startDate = new Date(in.readLong());
        endDate = new Date(in.readLong());
        projects = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(companyName);
        parcel.writeString(jobTitle);
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(endDate.getTime());
        parcel.writeStringList(projects);
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
