package com.example.owashaltungsanalyse.view.data_analysis;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.model.data_analysis.analysisprotocols.SessionCodesAnalysisProtocol;
import com.example.owashaltungsanalyse.presenter.data_analysis.IDataAnalysisPresenter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.snackbar.Snackbar;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AndroidEntryPoint
public class DataAnalysisActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "DataAnalysisActivity";
    private static final int CREATE_FILE = 1;
    private IDataAnalysisPresenter dataAnalysisPresenter;

    @Inject
    public void setDataAnalysisPresenter(IDataAnalysisPresenter dataAnalysisPresenter) {
        this.dataAnalysisPresenter = dataAnalysisPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PieChart pieChart = findViewById(R.id.piechart);

        List<PieEntry> entriesForPieChart = new ArrayList<>();


        if (this.dataAnalysisPresenter.getCurrentAnalysisProtocol() instanceof SessionCodesAnalysisProtocol) {
            // zeigt das Diagramm in der Datenanalyse an
            SessionCodesAnalysisProtocol protocol = ((SessionCodesAnalysisProtocol) this.dataAnalysisPresenter.getCurrentAnalysisProtocol());
            float totalAmount = 0;
            for (Map.Entry<String, Integer> entry : protocol.getOwasCodeCounted().entrySet()) {
                totalAmount = totalAmount + entry.getValue();
            }
            List<Map.Entry<String, Integer>> entryList = new ArrayList<>(protocol.getOwasCodeCounted().entrySet());
            entryList.sort(Map.Entry.comparingByValue());
            Collections.reverse(entryList);
            float restValue = 0;
            for (Map.Entry<String, Integer> entry : entryList) {
                float value = entry.getValue() / totalAmount;
                if (entriesForPieChart.size() < getResources().getIntArray(R.array.colors_list).length - 1) {
                    entriesForPieChart.add(new PieEntry(value, entry.getKey() + ": " + Math.round(value * 100) + "%"));
                } else {
                    restValue += value;
                }
            }
            if (restValue > 0) {
                entriesForPieChart.add(new PieEntry(restValue, "Rest: " + Math.round(restValue * 100) + "%"));
            }

            PieDataSet dataSet = new PieDataSet(entriesForPieChart, "Owas Codes");
            dataSet.setDrawValues(false);
            PieData data = new PieData(dataSet);
            pieChart.setData(data);
            dataSet.setColors(getResources().getIntArray(R.array.colors_list));
            pieChart.animateXY(5000, 5000);
            pieChart.getLegend().setEnabled(false);
            pieChart.getDescription().setEnabled(false);
            pieChart.invalidate();
        }

        Button buttonExport = findViewById(R.id.buttonExport);
        buttonExport.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonExport) {
            createFile();
        }
    }

    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/csv");
        intent.putExtra(Intent.EXTRA_TITLE, "owas.csv");

        startActivityForResult(intent, CREATE_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == CREATE_FILE && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                Uri uri = resultData.getData();
                createDocument(uri);
            }
        }
    }

    private void createDocument(Uri uri) {
        try (ParcelFileDescriptor pfd = this.getContentResolver().openFileDescriptor(uri, "w")) {
            if (pfd != null) {
                FileDescriptor fileDescriptor = pfd.getFileDescriptor();
                this.dataAnalysisPresenter.export(fileDescriptor);
                Snackbar.make(findViewById(R.id.ConstraintLayout_DataAnalysis), getResources().getText(R.string.success),
                        Snackbar.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "pfd null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
