package amirjaber.practice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import amirjaber.practice.activities.fragments.Fragment2;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static amirjaber.practice.activities.utils.ToastUtils.longToast;

public class MainActivity4 extends FragmentActivity implements Fragment2.OnFragmentInteractionListener {

    private FrameLayout frameLayout;
    private EditText etPassToFrag;
    private SlidrInterface slidrInterface;
    private Button bPass, bBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        longToast(getApplicationContext(), "Pass data to fragment from activity");
        slidrInterface = Slidr.attach(this);
        slidrInterface.unlock();
        init();
        initListeners();
    }

    private void init() {
        frameLayout = findViewById(R.id.fragment_container);
        etPassToFrag = findViewById(R.id.et_pass_to_frag);
        bPass = findViewById(R.id.b_pass);
        bBack = findViewById(R.id.b_back);
    }

    private void initListeners() {
        bPass.setOnClickListener(v -> {
            String text = etPassToFrag.getText().toString();
            passToFragment(text);
        });
        bBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void passToFragment(String text) {
        Fragment2 fragment2 = Fragment2.newInstance(text);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.left_to_right, R.anim.right_to_left, R.anim.left_to_right, R.anim.right_to_left);
        ft.addToBackStack(null);
        ft.add(R.id.fragment_container, fragment2, "FRAGMENT_2").commit();
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        etPassToFrag.setText(sendBackText);
        onBackPressed();
    }
}
