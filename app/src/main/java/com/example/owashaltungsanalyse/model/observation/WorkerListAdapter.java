package com.example.owashaltungsanalyse.model.observation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.view.observation_preparation.ObservationPreparationActivity;

import java.util.ArrayList;
import java.util.List;

public class WorkerListAdapter extends RecyclerView.Adapter<WorkerListAdapter.WorkerListViewHolder> {

    private final ObservationPreparationActivity observationPreparationActivity;
    private List<ISessionInfo> workerList;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public WorkerListAdapter(Context context) {
        this.observationPreparationActivity = (ObservationPreparationActivity) context;
        this.workerList = new ArrayList<>();
    }

    public void setWorkerList(List<ISessionInfo> workerList) {
        this.workerList = workerList;
        this.notifyDataSetChanged();
    }

    @Override
    public WorkerListAdapter.WorkerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.workerlist_row, parent, false);
        return new WorkerListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkerListAdapter.WorkerListViewHolder holder, int position) {
        holder.workplace.setText(workerList.get(position).getWorkplace());
        holder.worker.setText(workerList.get(position).getWorker());
        holder.itemView.setActivated(getSelectedPosition() == position);
        holder.itemView.setBackgroundColor(getSelectedPosition() == position ? R.color.uolAccent : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        if (workerList == null) {
            return 0;
        }
        return workerList.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public void resetWorkerList() {
        setSelectedPosition(RecyclerView.NO_POSITION);
        notifyDataSetChanged();
    }

    class WorkerListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView worker;
        protected TextView workplace;

        public WorkerListViewHolder(View itemView) {
            super(itemView);
            worker = itemView.findViewById(R.id.textView_worker);
            workplace = itemView.findViewById(R.id.textView_workplace);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(getSelectedPosition());
            setSelectedPosition(getAdapterPosition());
            notifyItemChanged(getSelectedPosition());
            observationPreparationActivity.recyclerViewSelectedEntryChanged();
        }
    }
}