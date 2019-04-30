package amirjaber.practice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import amirjaber.practice.activities.dtos.ParcelableDto;
import androidx.appcompat.app.AppCompatActivity;

import static amirjaber.practice.activities.utils.ToastUtils.longToast;

public class ParcelableDtoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);
        setTitle("Parcelable Activity 2");
        longToast(getApplicationContext(), "pass parcelable object");

        Intent intent = getIntent();
        ParcelableDto parcelableDto = intent.getParcelableExtra("Parcelable Item");

        int imageRes = parcelableDto.getImageResource();
        String line1 = parcelableDto.getText1();
        String line2 = parcelableDto.getText2();

        ImageView tvImage = findViewById(R.id.iv_image);
        tvImage.setImageResource(imageRes);

        TextView tvText1 = findViewById(R.id.tv_text1);
        tvText1.setText(line1);

        TextView tvText2 = findViewById(R.id.tv_text2);
        tvText2.setText(line2);


    }
}
