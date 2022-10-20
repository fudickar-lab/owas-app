package com.example.owashaltungsanalyse.view.observation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.model.observation.ChatMessage;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class ChatHistoryFragment extends Fragment implements View.OnClickListener {
    private TextView chatHistory;
    private ChatHistoryActivity chatHistoryActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        this.chatHistory = root.findViewById(R.id.textViewChat);
        this.chatHistory.setOnClickListener(this);


        chatHistoryActivity = (ChatHistoryActivity) getActivity();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        StringBuilder chat = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        for (ChatMessage message : chatHistoryActivity.getObservationSuperPresenter().getChat().getChatMessages()) {
            chat.append(message.getTimestamp().format(formatter))
                    .append(" ").append(message.getAuthor())
                    .append(": ").append(message.getMessage())
                    .append("\n");

        }
        this.chatHistory.setText(chat.toString());

    }

    @Override
    public void onClick(View v) {
        chatHistoryActivity.closeChatHistory();
    }
}