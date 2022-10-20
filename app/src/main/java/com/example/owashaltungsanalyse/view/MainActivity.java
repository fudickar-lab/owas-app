package com.example.owashaltungsanalyse.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.view.data_analysis.DataRequestActivity;
import com.example.owashaltungsanalyse.view.observation_preparation.ObservationPreparationActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageViewLogo;
    private Button buttonObservation;
    private Button buttonDataAnalysis;
    private Button buttonInstruction;
    private int imageId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.buttonObservation = findViewById(R.id.buttonObservation);
        this.buttonObservation.setOnClickListener(this);

        this.buttonDataAnalysis = findViewById(R.id.buttonDataAnalysis);
        this.buttonDataAnalysis.setOnClickListener(this);

        this.buttonInstruction = findViewById(R.id.buttonInstruction);
        this.buttonInstruction.setOnClickListener(this);

        this.imageViewLogo = findViewById(R.id.imageViewLogo);
        imageId = getResources().getIdentifier("uol_logo_blau", "drawable", this.getPackageName());
        this.imageViewLogo.setImageResource(imageId);
    }

    public void onClick(View v) {
        if (R.id.buttonObservation == v.getId()) {
            Intent intent = new Intent(this, ObservationPreparationActivity.class);
            startActivity(intent);
        }
        if (R.id.buttonDataAnalysis == v.getId()) {
            Intent intent = new Intent(this, DataRequestActivity.class);
            startActivity(intent);
        }
        if ((R.id.buttonInstruction == v.getId())) {
            Intent intent = new Intent(this, InstructionActivity.class);
            startActivity(intent);
        }
    }

}