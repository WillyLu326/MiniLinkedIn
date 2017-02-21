package willy.individual.com.minilinkedinapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by zhenglu on 2/20/17.
 */

public class Project implements Parcelable{

    public String id;

    public String projectName;

    public String summary;

    public Date startDate;

    public Date endDate;

    public List<String> tools;


    protected Project(Parcel in) {
        id = in.readString();
        projectName = in.readString();
        summary = in.readString();
        startDate = new Date(in.readLong());
        endDate = new Date(in.readLong());
        tools = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(projectName);
        parcel.writeString(summary);
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(endDate.getTime());
        parcel.writeStringList(tools);
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
