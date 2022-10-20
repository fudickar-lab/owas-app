package com.example.owashaltungsanalyse.view.data_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;
import com.example.owashaltungsanalyse.presenter.data_analysis.IDataRequestPresenter;
import com.google.android.material.snackbar.Snackbar;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AndroidEntryPoint
public class DataRequestActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "DataRequestActivity";

    @Inject
    IDataRequestPresenter dataRequestPresenter;
    private Button buttonDataRequestDeleteInput;
    private Button buttonDataRequestSubmit;
    private Spinner spinnerAnalysisStrategies;
    private AutoCompleteTextView autoCompleteTextViewRater;
    private AutoCompleteTextView autoCompleteTextViewWorker;
    private AutoCompleteTextView autoCompleteTextViewWorkplace;
    private AutoCompleteTextView autoCompleteTextViewDate;
    private final List<String> dates = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    private final List<String> workers = new ArrayList<>();
    private final List<String> workplaces = new ArrayList<>();
    private final List<String> raters = new ArrayList<>();
    private List<AutoCompleteTextView> autoCompleteTextViewList = new ArrayList<>();
    private List<ISessionInfo> sessionInfos;
    private ArrayAdapter<String> workplaceArrayAdapter;
    private ArrayAdapter<String> localDateTimeArrayAdapter;
    private ArrayAdapter<String> workerArrayAdapter;
    private ArrayAdapter<String> raterArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_request);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.buttonDataRequestDeleteInput = findViewById(R.id.buttonDataRequestCancel);
        this.buttonDataRequestDeleteInput.setOnClickListener(this);

        this.buttonDataRequestSubmit = findViewById(R.id.buttonGetDataAnalysis);
        this.buttonDataRequestSubmit.setOnClickListener(this);


        this.spinnerAnalysisStrategies = findViewById(R.id.spinnerAnalysisStrategy);


        List<String> analysisStrategyNames = this.dataRequestPresenter.getAnalysisStrategyNames();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, analysisStrategyNames);
        this.spinnerAnalysisStrategies.setAdapter(spinnerAdapter);

        sessionInfos = dataRequestPresenter.loadAllSessionInfos();

        this.localDateTimeArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, dates);
        this.autoCompleteTextViewDate = findViewById(R.id.autoCompleteTextViewDataRequestDate);
        this.autoCompleteTextViewDate.setAdapter(localDateTimeArrayAdapter);

        this.workerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, workers);
        this.autoCompleteTextViewWorker = findViewById(R.id.editTextWorkerDataRequest);
        this.autoCompleteTextViewWorker.setAdapter(workerArrayAdapter);

        this.workplaceArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, workplaces);
        this.autoCompleteTextViewWorkplace = findViewById(R.id.editTextDataRequestWorkplace);
        this.autoCompleteTextViewWorkplace.setAdapter(workplaceArrayAdapter);

        this.raterArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, raters);
        this.autoCompleteTextViewRater = findViewById(R.id.editTextDataRequestRater);
        this.autoCompleteTextViewRater.setAdapter(raterArrayAdapter);

        loadStringsForAutocomplete();

        this.autoCompleteTextViewList = Arrays.asList(autoCompleteTextViewRater, autoCompleteTextViewWorker,
                autoCompleteTextViewWorkplace, autoCompleteTextViewDate);

        setAllOnOnTouchListener(autoCompleteTextViewList);

        if (sessionInfos.isEmpty()) {
            Snackbar.make(findViewById(R.id.ConstraintLayoutDataRequest), getResources().getText(R.string.emptyDatabase),
                    Snackbar.LENGTH_SHORT).show();
            this.buttonDataRequestSubmit.setEnabled(false);
            this.buttonDataRequestDeleteInput.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonGetDataAnalysis) {
            if (checkInputs()) {
                boolean hasResult = dataRequestPresenter.calculateAnalysisProtocol(
                        this.autoCompleteTextViewRater.getText().toString(),
                        this.autoCompleteTextViewWorker.getText().toString(),
                        this.autoCompleteTextViewWorkplace.getText().toString(),
                        this.autoCompleteTextViewDate.getText().toString(),
                        this.spinnerAnalysisStrategies.getSelectedItemPosition());
                if (hasResult) {
                    Intent intent = new Intent(this, DataAnalysisActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(findViewById(R.id.ConstraintLayoutDataRequest), getResources().getText(R.string.error),
                            Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(findViewById(R.id.ConstraintLayoutDataRequest), getResources().getText(R.string.erroranswers),
                        Snackbar.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.buttonDataRequestCancel) {
            this.autoCompleteTextViewRater.setText("");
            this.autoCompleteTextViewWorker.setText("");
            this.autoCompleteTextViewWorkplace.setText("");
            this.autoCompleteTextViewDate.setText("");
        }
    }

    public boolean checkInputs() {
        String rater = autoCompleteTextViewRater.getText().toString();
        String worker = autoCompleteTextViewWorker.getText().toString();
        String workplace = autoCompleteTextViewWorkplace.getText().toString();
        String date = autoCompleteTextViewDate.getText().toString();

        for (ISessionInfo iSessionInfo : this.sessionInfos) {
            if ((rater.isEmpty() || rater.equals(iSessionInfo.getRater()))
                    && (worker.isEmpty() || worker.equals(iSessionInfo.getWorker()))
                    && (workplace.isEmpty() || iSessionInfo.getWorkplace().equals(workplace))
                    && (date.isEmpty() || iSessionInfo.getTimestamp().format(this.formatter).equals(date))) {
                return true;
            }
        }
        return false;
    }

    public void loadStringsForAutocomplete() {
        for (ISessionInfo sessionInfo : this.sessionInfos) {
            if (!raters.contains(sessionInfo.getRater())) {
                raters.add(sessionInfo.getRater());
            }
            if (!workers.contains(sessionInfo.getWorker())) {
                workers.add(sessionInfo.getWorker());
            }
            if (!workplaces.contains(sessionInfo.getWorkplace())) {
                workplaces.add(sessionInfo.getWorkplace());
            }
            dates.add(sessionInfo.getTimestamp().format(formatter));
        }
    }

    public void setAllOnOnTouchListener(List<AutoCompleteTextView> autoCompleteTextViewList) {
        for (AutoCompleteTextView autoCompleteTextView : autoCompleteTextViewList) {
            autoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    autoCompleteTextView.showDropDown();
                    return false;
                }
            });
        }

    }

}
