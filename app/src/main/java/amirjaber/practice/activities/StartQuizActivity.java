package amirjaber.practice.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartQuizActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedprefs";
    public static final String KEY_HIGHSCORE = "highscorekey";

    private TextView tvHighscore;
    private Button bStartQuiz;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        init();
        loadHighscore();
    }

    private void init() {
        tvHighscore = findViewById(R.id.tv_view_highscore);
        bStartQuiz = findViewById(R.id.b_start_quiz);
        bStartQuiz.setOnClickListener(v -> {
            startQuiz();
            //
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(StartQuizActivity.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        tvHighscore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int score) {
        highscore = score;
        tvHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
