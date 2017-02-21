package willy.individual.com.minilinkedinapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhenglu on 2/18/17.
 */

public class Education implements Parcelable{

    public String id;
    public String schoolName;
    public String major;
    public Date startDate;
    public Date endDate;
    public List<String> courses;

    public Education() {
        id = UUID.randomUUID().toString();
    }

    protected Education(Parcel in) {
        id = in.readString();
        schoolName = in.readString();
        major = in.readString();
        startDate = new Date(in.readLong());
        endDate = new Date(in.readLong());
        courses = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(schoolName);
        parcel.writeString(major);
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(endDate.getTime());
        parcel.writeStringList(courses);
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel in) {
            return new Education(in);
        }

        @Override
        public Education[] newArray(int size) {
            return new Education[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
