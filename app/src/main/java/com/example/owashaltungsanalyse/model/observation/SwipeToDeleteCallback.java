package com.example.owashaltungsanalyse.model.observation;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.owashaltungsanalyse.presenter.observation_preparation.IObservationPreparationPresenter;
import com.example.owashaltungsanalyse.view.observation_preparation.ObservationPreparationActivity;


/**
 * Zum löschen der Arbeiter*innen aus der Liste, durch swipen
 */
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    private final ObservationPreparationActivity observationPreparationActivity;
    private final IObservationPreparationPresenter presenter;
    private final WorkerListAdapter adapter;

    public SwipeToDeleteCallback(ObservationPreparationActivity observationPreparationActivity, IObservationPreparationPresenter observationPreparationPresenter, WorkerListAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.observationPreparationActivity = observationPreparationActivity;
        this.presenter = observationPreparationPresenter;
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        this.presenter.removeSessionInfo(position);
        this.adapter.setWorkerList(this.presenter.getSessionInfoList());
        this.adapter.setSelectedPosition(RecyclerView.NO_POSITION);
        this.observationPreparationActivity.recyclerViewSelectedEntryChanged();
    }
}
