package amirjaber.practice.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

import static amirjaber.practice.activities.utils.ToastUtils.longToast;
import static android.content.Intent.ACTION_TIME_TICK;

public class MainActivity3 extends AppCompatActivity {

    private TextView tvCounterText, tvRandomText, tvCurrentTime, tvTimer;
    private Button bStartTimer, bStopTimer;
    private CountDownTimer countDownTimer;
    private long timeLeftInMs = 600000;
    private BroadcastReceiver minuteUpdateReceiver;
    private SlidrInterface slidrInterface;
    private int counter;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        longToast(getApplicationContext(), "Number updates from Broadcast, every minute");
        slidrInterface = Slidr.attach(this);
        slidrInterface.unlock();
        init();
    }

    private void init() {
        tvCounterText = findViewById(R.id.tv_counter);
        tvRandomText = findViewById(R.id.tv_random);
        tvCurrentTime = findViewById(R.id.tv_current_time);
        bStartTimer = findViewById(R.id.b_start_timer);
        bStopTimer = findViewById(R.id.b_stop_timer);
        tvTimer = findViewById(R.id.tv_timer);

        tvRandomText.setText("wait for it..");
        setListeners();

        Timer timerAsync = new Timer();
        TimerTask timerTaskAsync = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    Log.i("Background Perform",
                            "-------> Text from Background Perform");
                    DateFormat df = new SimpleDateFormat("h:mm:ss a");
                    String date = df.format(Calendar.getInstance().getTime());
                    tvCurrentTime.setText(date);

                });
            }
        };
        timerAsync.schedule(timerTaskAsync, 0, 200);


    }

    private void setListeners() {
        bStartTimer.setOnClickListener(v -> startPause());
        bStopTimer.setOnClickListener(v -> {
            resetAndStopTimer();
            bStopTimer.setVisibility(View.GONE);

        });
    }

    private void startPause() {
        if (timerRunning) {
            pauseTimer();
        } else {
            startTimer();
            bStopTimer.setVisibility(View.VISIBLE);
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMs = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                System.out.println("Time Over!");
            }
        }.start();

        bStartTimer.setText("PAUSE");
        timerRunning = true;
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        bStartTimer.setText("START");
        timerRunning = false;
    }

    private void resetAndStopTimer() {
        countDownTimer.cancel();
        timeLeftInMs = 600000;
        updateTimer();
        timerRunning = false;
        bStartTimer.setText("START");
    }

    private void updateTimer() {
        int mins = (int) timeLeftInMs / 60000;
        int secs = (int) timeLeftInMs % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + mins;
        timeLeftText += ":";
        if (secs < 10) timeLeftText += "0";
        timeLeftText += secs;
        tvTimer.setText(timeLeftText);
    }

    private void startMinuteUpdater() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TIME_TICK);
        minuteUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                counter++;
                tvCounterText.setText("" + counter);
                if (counter == 1) {
                    tvRandomText.setText("minute of your life wasted.");
                } else if (counter == 4) {
                    tvRandomText.setText("Psst, you suck.");
                } else if (counter == 7) {
                    tvRandomText.setText("you're still here? what a joke..");
                } else if (counter == 20) {
                    tvRandomText.setText("go be useful.. do the dishes.");
                } else {
                    tvRandomText.setText("minutes of your life wasted.");
                }
            }
        };
        registerReceiver(minuteUpdateReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMinuteUpdater();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(minuteUpdateReceiver);
    }
}
