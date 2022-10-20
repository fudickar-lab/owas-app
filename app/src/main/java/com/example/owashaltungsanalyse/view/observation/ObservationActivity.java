package com.example.owashaltungsanalyse.view.observation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.presenter.observation.IObservationPresenter;
import com.example.owashaltungsanalyse.view.observation.posture.ObservationArmPostureFragment;
import com.example.owashaltungsanalyse.view.observation.posture.ObservationBackPostureFragment;
import com.example.owashaltungsanalyse.view.observation.posture.ObservationLegPostureFragment;
import com.example.owashaltungsanalyse.view.observation.posture.ObservationUseOfForceFragment;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class ObservationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LAUNCH_SHORTCUT_ACTIVITY = 1;
    private IObservationPresenter observationPresenter;
    private int legCode = 1;
    private int armCode = 1;
    private int backCode = 1;
    private int useOfForceCode = 1;
    private Button buttonSubmit;
    private Button buttonShowShortcuts;
    private FragmentManager fragmentManager;
    private TextView workerName;
    private TextView raterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_activty);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fragmentManager = getSupportFragmentManager();

        this.buttonSubmit = findViewById(R.id.buttonSubmit);
        this.buttonSubmit.setOnClickListener(this);


        this.workerName = findViewById(R.id.textViewWorkerName);
        this.workerName.setText(observationPresenter.getCurrentSessionInfo().getWorker());

        this.raterName = findViewById(R.id.textViewRaterName);
        this.raterName.setText(observationPresenter.getCurrentSessionInfo().getRater());

        this.buttonShowShortcuts = findViewById(R.id.buttonShortcuts);
        this.buttonShowShortcuts.setOnClickListener(this);

        if (observationPresenter.getLastSessionEntry() != null) {
            this.legCode = observationPresenter.getLastSessionEntry().getLeg();
            this.armCode = observationPresenter.getLastSessionEntry().getArm();
            this.backCode = observationPresenter.getLastSessionEntry().getBack();
            this.useOfForceCode = observationPresenter.getLastSessionEntry().getUseOfForce();
            this.buttonShowShortcuts.setEnabled(true);
        } else {
            this.buttonShowShortcuts.setEnabled(false);
        }

    }

    public IObservationPresenter getObservationPresenter() {
        return this.observationPresenter;
    }

    @Inject
    public void setObservationSuperPresenter(IObservationPresenter observationPresenter) {
        this.observationPresenter = observationPresenter;
    }

    public void switchToArmFragment() {
        this.buttonSubmit.setVisibility(View.GONE);
        Fragment fragment = new ObservationArmPostureFragment();
        switchToFragment(fragment);

    }

    public void switchToLegFragment() {
        this.buttonSubmit.setVisibility(View.GONE);
        Fragment fragment = new ObservationLegPostureFragment();
        switchToFragment(fragment);
    }

    public void switchToBackFragment() {
        this.buttonSubmit.setVisibility(View.GONE);
        Fragment fragment = new ObservationBackPostureFragment();
        switchToFragment(fragment);
    }

    public void switchToUseOfForceFragment() {
        this.buttonSubmit.setVisibility(View.GONE);
        Fragment fragment = new ObservationUseOfForceFragment();
        switchToFragment(fragment);
    }

    public void switchToFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPosture, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openChatHistory() {
        Intent intent = new Intent(this, ChatHistoryActivity.class);
        startActivity(intent);
    }

    public void showSubmitButton() {
        this.buttonSubmit.setVisibility(View.VISIBLE);
    }

    public int getLegCode() {
        return legCode;
    }

    public void setLegCode(int legCode) {
        this.legCode = legCode;
    }

    public int getArmCode() {
        return armCode;
    }

    public void setArmCode(int armCode) {
        this.armCode = armCode;
    }

    public int getBackCode() {
        return backCode;
    }

    public void setBackCode(int backCode) {
        this.backCode = backCode;
    }

    public int getUseOfForceCode() {
        return useOfForceCode;
    }

    public void setUseOfForceCode(int useOfForceCode) {
        this.useOfForceCode = useOfForceCode;
    }


    public Button getButtonSubmit() {
        return buttonSubmit;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSubmit) {

            observationPresenter.addChatMessage("" + this.legCode + this.armCode + this.backCode +
                    this.useOfForceCode, observationPresenter.getCurrentSessionInfo().getRater());
            observationPresenter.submitObservation(this.legCode, this.armCode, this.backCode,
                    this.useOfForceCode);
            finish();


        } else if (v.getId() == R.id.buttonShortcuts) {
            Intent intent = new Intent(this, ShortcutActivity.class);
            startActivityForResult(intent, LAUNCH_SHORTCUT_ACTIVITY);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SHORTCUT_ACTIVITY) {
            if (resultCode == ObservationActivity.RESULT_OK) {
                String result = data.getStringExtra("result");
                this.legCode = Integer.parseInt(String.valueOf(result.charAt(0)));
                this.armCode = Integer.parseInt(String.valueOf(result.charAt(1)));
                this.backCode = Integer.parseInt(String.valueOf(result.charAt(2)));
                this.useOfForceCode = Integer.parseInt(String.valueOf(result.charAt(3)));
            }
            if (resultCode == ObservationActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}