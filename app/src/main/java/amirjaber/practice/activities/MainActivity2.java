package amirjaber.practice.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import amirjaber.practice.activities.fragments.Fragment1;
import amirjaber.practice.activities.fragments.FragmentBlue;
import androidx.appcompat.app.AppCompatActivity;

import static amirjaber.practice.activities.utils.ToastUtils.longToast;

public class MainActivity2 extends AppCompatActivity implements Fragment1.Fragment1Listener, FragmentBlue.FragmentBlueListener {

    final Handler handler = new Handler();
    Fragment1 fragment1;
    FragmentBlue fragmentBlue;
    private TextView tvName, tvNumber, tvNumPreview;
    private Button bAdd, bSubtract, bGoToSVMActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        setTitle("Activity 2");
        longToast(getApplicationContext(), "pass onResult data back to previous activity");

        setupFragments();
        init();
        setText();
    }

    private void setupFragments() {
        fragment1 = Fragment1.newInstance("Text From Bundle");
        fragmentBlue = new FragmentBlue();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment1).replace(R.id.fl_container_2, fragmentBlue).commit();
        handler.postDelayed(() -> longToast(getApplicationContext(),
                "Pass data from Fragment to another using an interface called in the activity"),
                4000);

    }

    private void init() {
        tvName = findViewById(R.id.tv_name);
        tvNumber = findViewById(R.id.tv_number);
        tvNumPreview = findViewById(R.id.tv_num_preview);
        bAdd = findViewById(R.id.b_add);
        bSubtract = findViewById(R.id.b_subtract);
        bGoToSVMActivity = findViewById(R.id.to_shared_vm_activity);

    }

    @SuppressLint("SetTextI18n")
    private void setText() {
        Intent intent = getIntent();
        String textName = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        String textNumber = intent.getStringExtra(MainActivity.EXTRA_NUMBER);
        int firstNumber = intent.getIntExtra("number1", 0);
        int secondNumber = intent.getIntExtra("number2", 0);

        tvName.setText(textName);
        tvNumber.setText(textNumber);
        tvNumPreview.setText("Numbers: " + firstNumber + ", " + secondNumber);

        initListeners(firstNumber, secondNumber);
    }

    private void initListeners(final int firstNumber, final int secondNumber) {
        bAdd.setOnClickListener(v -> {
            int result = firstNumber + secondNumber;

            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", result);

            setResult(RESULT_OK, resultIntent);
            finish();
        });

        bSubtract.setOnClickListener(v -> {
            int result = firstNumber - secondNumber;

            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", result);

            setResult(RESULT_OK, resultIntent);
            finish();
        });

        bGoToSVMActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, SharedViewModelActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onInput1Sent(CharSequence input) {
        fragmentBlue.updateEditText(input);
    }

    @Override
    public void onInputBlueSent(CharSequence input) {
        fragment1.updateEditText(input);
    }
}
