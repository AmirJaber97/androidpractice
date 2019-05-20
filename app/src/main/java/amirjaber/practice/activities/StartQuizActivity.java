package amirjaber.practice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartQuizActivity extends AppCompatActivity {

    Button bStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        init();
    }

    private void init() {
        bStartQuiz = findViewById(R.id.b_start_quiz);
        bStartQuiz.setOnClickListener(v -> {
            startQuiz();
            //
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(StartQuizActivity.this, QuizActivity.class);
        startActivity(intent);
    }
}
