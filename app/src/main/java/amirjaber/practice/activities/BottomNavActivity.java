package amirjaber.practice.activities;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import amirjaber.practice.activities.dialogs.SampleDialog;
import amirjaber.practice.activities.fragments.FragmentFavNav;
import amirjaber.practice.activities.fragments.FragmentHomeNav;
import amirjaber.practice.activities.fragments.FragmentSearchNav;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BottomNavActivity extends AppCompatActivity{

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new FragmentHomeNav();
                        break;
                    case R.id.nav_fav:
                        selectedFragment = new FragmentFavNav();
                        break;
                    case R.id.nav_search:
                        selectedFragment = new FragmentSearchNav();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHomeNav()).commit();
    }

}
