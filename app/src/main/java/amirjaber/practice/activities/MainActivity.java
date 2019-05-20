package amirjaber.practice.activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import maes.tech.intentanim.CustomIntent;

import static amirjaber.practice.activities.utils.ToastUtils.longToast;
import static amirjaber.practice.activities.utils.ToastUtils.shortToast;

public class MainActivity extends AppCompatActivity {


    /*
    So.. what is this?
    Basically a set of best practices and use of modern Java..
    So here's what every class represents and does :)
    I'll mention everything except normal intents ;)
    Activity 1: Pass data from this activity to Activity 2 using intents expecting a result;
    Activity 2: Here we show the passed intent data from Activity 1;
        We also pass an int as result to Activity 1;
        We also implement two listener methods from 2 fragments in this Activity,
        so that the two fragments can communicate (onAttach and onDetach methods used in the fragments);
     */

    public static final String EXTRA_TEXT = "amirjaber.practice.activities.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "amirjaber.practice.activities.EXTRA_NUMBER";


    private Button bOpenActivity2, bOpenActivityParcelable, bOpenActivity3, bOpenActivity4, bOpenActivityMvvm, bOpenActivityBottomNav, bOpenActivityQuiz;
    private EditText etName, etNumber, etNum1, etNum2;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Activity 1");
        longToast(getApplicationContext(), "pass data using Intent");
        init();
    }

    private void init() {
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        etNum1 = findViewById(R.id.et_num1);
        etNum2 = findViewById(R.id.et_num2);
        resultText = findViewById(R.id.tv_result);
        bOpenActivity2 = findViewById(R.id.b_open_activity2);
        bOpenActivityParcelable = findViewById(R.id.b_open_activityParcelable);
        bOpenActivity3 = findViewById(R.id.b_open_activity3);
        bOpenActivity4 = findViewById(R.id.b_open_activity4);
        bOpenActivityMvvm = findViewById(R.id.b_open_mvvm_activity);
        bOpenActivityBottomNav = findViewById(R.id.b_open_bottom_nav_activity);
        bOpenActivityQuiz = findViewById(R.id.b_open_quiz_activity);

        setListeners();
    }

    private void setListeners() {
        bOpenActivity2.setOnClickListener(v -> openActivity2PassData());
        bOpenActivityParcelable.setOnClickListener(v -> {
            Intent goToActivity = new Intent(getApplicationContext(), RecyclerActivity.class);
            startActivity(goToActivity);
        });
        bOpenActivity3.setOnClickListener(v -> {
            Intent goToActivity = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(goToActivity);
        });
        bOpenActivity4.setOnClickListener(v -> {
            Intent goToActivity = new Intent(getApplicationContext(), MainActivity4.class);
            startActivity(goToActivity);
        });
        bOpenActivityMvvm.setOnClickListener(v -> {
            Intent goToActivity = new Intent(getApplicationContext(), MvvmActivity.class);
            startActivity(goToActivity);
        });
        bOpenActivityBottomNav.setOnClickListener(v -> {
            Intent goToActivity = new Intent(getApplicationContext(), BottomNavActivity.class);
            startActivity(goToActivity);
        });
        bOpenActivityQuiz.setOnClickListener(v -> {
            Intent goToActivity = new Intent(getApplicationContext(), StartQuizActivity.class);
            startActivity(goToActivity);
        });
    }

    private void openActivity2PassData() {
        if (etNum1.getText().toString().equals("") || etNum2.getText().toString().equals("")) {
            shortToast(MainActivity.this, "Please insert numbers into the small boxes");
            return;
        }

        String etName = this.etName.getText().toString();
        String etNumber = this.etNumber.getText().toString();
        int firstNumber = Integer.parseInt(etNum1.getText().toString());
        int secondNumber = Integer.parseInt(etNum2.getText().toString());

        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        intent.putExtra(EXTRA_TEXT, etName);
        intent.putExtra(EXTRA_NUMBER, etNumber);
        intent.putExtra("number1", firstNumber);
        intent.putExtra("number2", secondNumber);

        startActivityForResult(intent, 1);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        CustomIntent.customType(this, "fadein-to-fadeout"); // this lib will override above statement and xml config
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data != null ? data.getIntExtra("result", 0) : 0;
                resultText.setText("Result from Activity 2: " + result);
            }
            if (requestCode == RESULT_CANCELED) {
                resultText.setText("Nothing selected");
            }
        }
    }
}
