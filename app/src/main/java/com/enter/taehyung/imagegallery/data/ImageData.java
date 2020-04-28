package com.enter.taehyung.imagegallery.data;

import android.os.Parcel;
import android.os.Parcelable;

// FIXME Parcelable이 어떤건지 아냐고 물어봄
//  직렬화를 해주는 애고 안드로이드에 직렬화를 해주는 클래스가 Serializable, Parcelable이 있는데
//  파셀러블은 직렬화될 변수나 순서를 직접 정해줘서 Serializable 보다 빠르고, Serializable은 리플렉션 사용해서 더 느리다함
//  이 데이터를 Bundle에 안담으면 굳이 Parcelable 할 필요도 없어진다고 말하심.
public class ImageData implements Parcelable {
    private int idx; // origin sort 를 위해 저장 처리 필요.
    private String title;
    private String imagePath;

    public ImageData() {
    }

    public ImageData(Parcel in) {
        this.idx = in.readInt();
        this.title = in.readString();
        this.imagePath = in.readString();
    }

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setTitle(String title) {
        this.title = title;
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
        dest.writeInt(this.idx);
        dest.writeString(this.title);
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
