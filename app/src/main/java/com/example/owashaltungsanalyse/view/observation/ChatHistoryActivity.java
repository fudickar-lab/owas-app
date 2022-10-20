package com.example.owashaltungsanalyse.view.observation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.presenter.observation.IObservationPresenter;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class ChatHistoryActivity extends AppCompatActivity {

    @Inject
    IObservationPresenter observationSuperPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public IObservationPresenter getObservationSuperPresenter() {
        return this.observationSuperPresenter;
    }

    public void closeChatHistory() {
        finish();
    }
}