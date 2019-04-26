package xu.walt.com.aidl.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import javax.xml.transform.Source;

/**
 * Created by walt on 2019/4/24.
 * 序列化和反序列化
 */

public class Person implements Parcelable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            Person person = new Person();
            person.name = in.readString();
            return person;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
