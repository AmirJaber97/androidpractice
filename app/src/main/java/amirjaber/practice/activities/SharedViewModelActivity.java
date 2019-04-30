package amirjaber.practice.activities;

import android.os.Bundle;

import amirjaber.practice.activities.fragments.FragmentA;
import amirjaber.practice.activities.fragments.FragmentB;
import androidx.appcompat.app.AppCompatActivity;

public class SharedViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_view_model);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new FragmentA())
                .add(R.id.container_b, new FragmentB())
                .commit();
    }
}
