package amirjaber.practice.activities;

import amirjaber.practice.activities.models.Question;
import amirjaber.practice.activities.sql.QuizDbHelper;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView tvScore;
    private TextView tvQuestionCount;
    private TextView tvCountdown;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button bConfirmNext;

    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        init();
    }

    private void init() {
        tvQuestion = findViewById(R.id.tv_view_questions);
        tvScore = findViewById(R.id.tv_view_score);
        tvQuestionCount = findViewById(R.id.tv_question_count);
        tvCountdown = findViewById(R.id.tv_view_countdown);
        rg = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        bConfirmNext = findViewById(R.id.b_confirm_next);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();

    }
}
