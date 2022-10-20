package com.example.owashaltungsanalyse.view.observation;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.model.observation.shortcuts.ShortcutsAdapter;
import com.example.owashaltungsanalyse.presenter.observation.IObservationPresenter;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@AndroidEntryPoint
public class ShortcutActivity extends AppCompatActivity {

    private IObservationPresenter observationPresenter;
    private RecyclerView recyclerViewShortcuts;
    private ShortcutsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Integer> legImageIds;
    private List<Integer> armImageIds;
    private List<Integer> backImageIds;
    private List<Integer> useOfForceImageIds;

    @Inject
    public void setObservationSuperPresenter(IObservationPresenter observationSuperPresenter) {
        this.observationPresenter = observationSuperPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcut);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewShortcuts = findViewById(R.id.RecyclerView_Shortcuts);

        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerViewShortcuts.setLayoutManager(layoutManager);
        this.adapter = new ShortcutsAdapter(this);
        this.recyclerViewShortcuts.setAdapter(adapter);
        this.adapter.setShortcuts(observationPresenter.getPossibleShortcuts());

        this.legImageIds = Arrays.asList(R.drawable.ic_lower_1, R.drawable.ic_lower_2, R.drawable.ic_lower_3,
                R.drawable.ic_lower_4, R.drawable.ic_lower_5, R.drawable.ic_lower_6, R.drawable.ic_lower_7);

        this.armImageIds = Arrays.asList(R.drawable.ic_upper_1, R.drawable.ic_upper_2, R.drawable.ic_upper_3);

        this.backImageIds = Arrays.asList(R.drawable.ic_back_1, R.drawable.ic_back_2, R.drawable.ic_back_3,
                R.drawable.ic_back_4);

        this.useOfForceImageIds = Arrays.asList(R.drawable.ic_weight_1, R.drawable.ic_weight_2, R.drawable.ic_weight_3);
    }

    public List<Integer> getLegImageIds() {
        return legImageIds;
    }

    public List<Integer> getArmImageIds() {
        return armImageIds;
    }

    public List<Integer> getBackImageIds() {
        return backImageIds;
    }

    public List<Integer> getUseOfForceImageIds() {
        return useOfForceImageIds;
    }

    public void recyclerViewSelectedEntryChanged(String owasCode) {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", owasCode);
        setResult(ObservationActivity.RESULT_OK, returnIntent);
        finish();

    }
}