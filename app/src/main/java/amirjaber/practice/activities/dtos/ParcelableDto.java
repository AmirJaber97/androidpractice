package amirjaber.practice.activities.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelableDto implements Parcelable {
    public static final Creator<ParcelableDto> CREATOR = new Creator<ParcelableDto>() {
        @Override
        public ParcelableDto createFromParcel(Parcel in) {
            return new ParcelableDto(in);
        }

        @Override
        public ParcelableDto[] newArray(int size) {
            return new ParcelableDto[size];
        }
    };
    private int imageResource;
    private String text1;
    private String text2;

    public ParcelableDto(int imageResource, String text1, String text2) {
        this.imageResource = imageResource;
        this.text1 = text1;
        this.text2 = text2;
    }

    private ParcelableDto(Parcel in) {
        imageResource = in.readInt();
        text1 = in.readString();
        text2 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResource);
        dest.writeString(text1);
        dest.writeString(text2);
    }
}
