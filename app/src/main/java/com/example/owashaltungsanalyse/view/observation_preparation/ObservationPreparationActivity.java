package com.example.owashaltungsanalyse.view.observation_preparation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.model.observation.SwipeToDeleteCallback;
import com.example.owashaltungsanalyse.model.observation.WorkerListAdapter;
import com.example.owashaltungsanalyse.presenter.observation_preparation.IObservationPreparationPresenter;
import com.example.owashaltungsanalyse.view.observation.WaitingRoomActivity;
import com.google.android.material.snackbar.Snackbar;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class ObservationPreparationActivity extends AppCompatActivity implements View.OnClickListener {

    private IObservationPreparationPresenter observationPreparationPresenter;
    private EditText editTextRater;
    private EditText editTextWorker;
    private EditText editTextWorkplace;
    private RecyclerView recyclerViewWorkerList;
    private WorkerListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ItemTouchHelper itemTouchHelper;
    private Button buttonStartSession;
    private Button buttonEndAllSessions;
    private Button buttonAddWorkerToList;

    @Inject
    public void setObservationPreparationPresenter(IObservationPreparationPresenter observationPreparationPresenter) {
        this.observationPreparationPresenter = observationPreparationPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_preparation);

        this.editTextRater = findViewById(R.id.editTextRater);
        this.editTextWorker = findViewById(R.id.editTextWorker);
        this.editTextWorkplace = findViewById(R.id.editTextWorkplace);

        this.buttonEndAllSessions = findViewById(R.id.buttonEndAllSessions);
        this.buttonEndAllSessions.setOnClickListener(this);

        this.buttonStartSession = findViewById(R.id.buttonStartSession);
        this.buttonStartSession.setOnClickListener(this);
        this.buttonStartSession.setEnabled(false);


        this.buttonAddWorkerToList = findViewById(R.id.buttonAddWorkerToList);
        this.buttonAddWorkerToList.setOnClickListener(this);

        this.recyclerViewWorkerList = findViewById(R.id.recyclerViewWorkerList);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerViewWorkerList.setLayoutManager(layoutManager);
        this.adapter = new WorkerListAdapter(this);
        this.recyclerViewWorkerList.setAdapter(adapter);
        itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(this, observationPreparationPresenter, adapter));
        itemTouchHelper.attachToRecyclerView(recyclerViewWorkerList);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.adapter.setWorkerList(observationPreparationPresenter.getSessionInfoList());
        this.adapter.resetWorkerList();
        this.buttonStartSession.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonStartSession) {
            observationPreparationPresenter.startSession(this.adapter.getSelectedPosition());
            Intent waitingroom = new Intent(this, WaitingRoomActivity.class);
            waitingroom.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(waitingroom);
        } else if (v.getId() == R.id.buttonAddWorkerToList) {
            if (this.editTextWorker.getText().toString().isEmpty() || this.editTextRater.getText().toString().isEmpty()
                    || this.editTextWorkplace.getText().toString().isEmpty()) {
                Snackbar.make(findViewById(R.id.ConstraintLayoutObservationPreparation),
                        getResources().getText(R.string.observationPreparationError),
                        Snackbar.LENGTH_SHORT).show();
            } else {
                this.observationPreparationPresenter.addSessionInfo(editTextRater.getText().toString(),
                        editTextWorker.getText().toString(), editTextWorkplace.getText().toString());
                this.adapter.setWorkerList(observationPreparationPresenter.getSessionInfoList());
                this.editTextWorkplace.setText("");
                this.editTextWorker.setText("");
            }

        } else if (v.getId() == R.id.buttonEndAllSessions) {
            if (observationPreparationPresenter.getSessionInfoList() != null) {
                observationPreparationPresenter.getSessionInfoList().clear();
            }
            finish();
        }
    }

    public void recyclerViewSelectedEntryChanged() {
        this.buttonStartSession.setEnabled(this.adapter.getSelectedPosition() != -1);
    }


}