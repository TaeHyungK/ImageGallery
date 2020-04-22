package com.enter.taehyung.imagegallery.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageData implements Parcelable {
    private String alt;
    private String imagePath;

    public ImageData() {
    }

    public ImageData(Parcel in) {
        this.alt = in.readString();
        this.imagePath = in.readString();
    }

    public String getAlt() {
        return alt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.alt);
        dest.writeString(this.imagePath);
    }

    public static final Parcelable.Creator<ImageData> CREATOR = new Parcelable.Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel source) {
            return new ImageData(source);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

}
