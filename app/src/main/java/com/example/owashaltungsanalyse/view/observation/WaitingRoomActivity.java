package com.example.owashaltungsanalyse.view.observation;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.presenter.observation.IWaitingRoomPresenter;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class WaitingRoomActivity extends AppCompatActivity implements View.OnClickListener {
    private int counter;
    private IWaitingRoomPresenter waitingRoomPresenter;
    private Button buttonEndCurrentSession;
    private Button buttonGoOn;
    private TextView textViewCurrentWorker;
    private TextView textViewTimer;
    private TextView textViewRater;
    private TextView textViewCounter;
    private ProgressBar progressBarTimer;
    private CountDownTimer countDownTimer;
    private boolean firstStartOfActivity;

    @Inject
    public void setWaitingRoomPresenter(IWaitingRoomPresenter waitingRoomPresenter) {
        this.waitingRoomPresenter = waitingRoomPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.buttonEndCurrentSession = findViewById(R.id.buttonEndCurrentSession);
        this.buttonEndCurrentSession.setOnClickListener(this);

        this.buttonGoOn = findViewById(R.id.button_Go_On);
        this.buttonGoOn.setOnClickListener(this);

        this.textViewCurrentWorker = findViewById(R.id.textViewShowCurrentWorker);
        this.textViewCurrentWorker.setText(waitingRoomPresenter.getCurrentSessionInfo().getWorker());
        this.textViewRater = findViewById(R.id.textViewRaterWaitingroom);
        this.textViewRater.setText(waitingRoomPresenter.getCurrentSessionInfo().getRater());
        this.textViewCounter = findViewById(R.id.textViewCounter);
        this.textViewTimer = findViewById(R.id.textViewTimer);


        this.progressBarTimer = findViewById(R.id.progressBarTimer);
        progressBarTimer.setProgress(counter);

        firstStartOfActivity = true;

        Intent intent = new Intent(this, ObservationActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onStart() {
        super.onStart();
        this.textViewTimer.setText(getResources().getText(R.string.timer).toString() + ": ");
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }
        counter = 0;
        this.countDownTimer = new CountDownTimer(getResources().getInteger(R.integer.timerLength)
                * 1000, getResources().getInteger(R.integer.countDownInterval) * 1000) {
            public void onTick(long millisUntilFinished) {
                textViewCounter.setText("" + millisUntilFinished / 1000);
                counter++;
                progressBarTimer.setProgress((counter * 100) / ((getResources().getInteger(R.integer.timerLength) * 1000) / (getResources().getInteger(R.integer.countDownInterval) * 1000)));
            }

            public void onFinish() {
                textViewTimer.setText(getResources().getText(R.string.timerOver).toString());
                progressBarTimer.setProgress(100);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), RingtoneManager.
                        getActualDefaultRingtoneUri(WaitingRoomActivity.this, RingtoneManager.TYPE_NOTIFICATION));
                mp.start();
            }
        };
        if (!firstStartOfActivity) {
            countDownTimer.start();
        } else {
            firstStartOfActivity = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (waitingRoomPresenter.getCurrentSessionInfo() != null) {
            waitingRoomPresenter.endCurrentSession();
        }
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonEndCurrentSession) {
            finish();
        } else if (v.getId() == R.id.button_Go_On) {
            countDownTimer.cancel();
            Intent intent = new Intent(this, ObservationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}