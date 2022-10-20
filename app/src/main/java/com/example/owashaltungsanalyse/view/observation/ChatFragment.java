package com.example.owashaltungsanalyse.view.observation;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.owashaltungsanalyse.R;

public class ChatFragment extends Fragment implements View.OnClickListener {
    private static final String AUTHOR = "Chat";
    private String message;
    private TextView textViewChat;
    private ObservationActivity observationActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_chat, container, false);

        this.textViewChat = root.findViewById(R.id.textViewChat);
        this.textViewChat.setOnClickListener(this);

        observationActivity = (ObservationActivity) getActivity();

        Resources res = getResources();
        if (observationActivity.getObservationPresenter().getLastSessionEntry() == null) {
            message = res.getString(R.string.welcome);
        } else {
            boolean notFoundCode = true;
            for (int code : res.getIntArray(R.array.code_list_no4)) {
                if (code == Integer.parseInt(observationActivity.getObservationPresenter().getLastSessionEntry().toOWASCode())) {
                    message = res.getString(R.string.alarmNo4) + " " +
                            observationActivity.getObservationPresenter().getLastSessionEntry().toOWASCode();
                    notFoundCode = false;
                }
            }
            if (notFoundCode) {
                message = res.getString(R.string.lastEntryStillGood) + " " +
                        observationActivity.getObservationPresenter().getLastSessionEntry().toOWASCode();
            }

        }

        this.textViewChat.setText(message);
        observationActivity.getObservationPresenter().addChatMessage(message, AUTHOR);

        return root;
    }

    @Override
    public void onClick(View v) {
        observationActivity.openChatHistory();
    }
}