package com.example.owashaltungsanalyse.model.observation.shortcuts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.model.observation.ISessionEntry;
import com.example.owashaltungsanalyse.view.observation.ShortcutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter für die genutzte RecyclerView der Shortcuts
 */
public class ShortcutsAdapter extends RecyclerView.Adapter<ShortcutsAdapter.ShortcutsViewHolder> {
    private final ShortcutActivity shortcutActivity;
    private List<ISessionEntry> shortcuts;

    public ShortcutsAdapter(Context context) {
        shortcutActivity = (ShortcutActivity) context;
        this.shortcuts = new ArrayList<>();
    }

    public void setShortcuts(List<ISessionEntry> shortcuts) {
        this.shortcuts = shortcuts;
        notifyDataSetChanged();
    }

    @Override
    public ShortcutsAdapter.ShortcutsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shortcuts_picture_row, parent, false);
        return new ShortcutsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShortcutsAdapter.ShortcutsViewHolder holder, int position) {
        holder.leg.setImageResource(shortcutActivity.getLegImageIds().get(shortcuts.get(position).getLeg() - 1));
        holder.arm.setImageResource(shortcutActivity.getArmImageIds().get(shortcuts.get(position).getArm() - 1));
        holder.back.setImageResource(shortcutActivity.getBackImageIds().get(shortcuts.get(position).getBack() - 1));
        holder.useOfForce.setImageResource(shortcutActivity.getUseOfForceImageIds().get(shortcuts.get(position).getUseOfForce() - 1));
    }

    @Override
    public int getItemCount() {
        if (shortcuts == null) {
            return 0;
        }
        return shortcuts.size();
    }


    class ShortcutsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView leg;
        protected ImageView arm;
        protected ImageView back;
        protected ImageView useOfForce;

        public ShortcutsViewHolder(View itemView) {
            super(itemView);
            leg = itemView.findViewById(R.id.textView_leg);
            arm = itemView.findViewById(R.id.textView_arm);
            back = itemView.findViewById(R.id.textView_back);
            useOfForce = itemView.findViewById(R.id.textView_useOfForce);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }
            shortcutActivity.recyclerViewSelectedEntryChanged(shortcuts.get(getAdapterPosition()).toOWASCode());

        }
    }
}