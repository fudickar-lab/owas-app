package com.example.owashaltungsanalyse.view.observation.posture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.owashaltungsanalyse.R;
import com.example.owashaltungsanalyse.view.observation.ObservationActivity;

import java.util.Arrays;
import java.util.List;

public class ObservationBackPostureFragment extends Fragment implements View.OnClickListener {

    private ImageButton buttonBackCode1;
    private ImageButton buttonBackCode2;
    private ImageButton buttonBackCode3;
    private ImageButton buttonBackCode4;
    private List<ImageButton> buttonList;
    private ObservationActivity observationActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_observation_back_posture, container, false);

        this.buttonBackCode1 = root.findViewById(R.id.buttonBackCode1);
        this.buttonBackCode2 = root.findViewById(R.id.buttonBackCode2);
        this.buttonBackCode3 = root.findViewById(R.id.buttonBackCode3);
        this.buttonBackCode4 = root.findViewById(R.id.buttonBackCode4);

        this.buttonBackCode4.setOnClickListener(this);
        this.buttonBackCode3.setOnClickListener(this);
        this.buttonBackCode2.setOnClickListener(this);
        this.buttonBackCode1.setOnClickListener(this);

        buttonList = Arrays.asList(this.buttonBackCode1, this.buttonBackCode2, this.buttonBackCode3,
                this.buttonBackCode4);

        observationActivity = (ObservationActivity) getActivity();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (observationActivity.getObservationPresenter().getLastSessionEntry() != null) {
            int back = observationActivity.getObservationPresenter().getLastSessionEntry().getBack();
            buttonList.get(back - 1).setSelected(true);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonBackCode1) {
            observationActivity.setBackCode(1);

        } else if (v.getId() == R.id.buttonBackCode2) {
            observationActivity.setBackCode(2);

        } else if (v.getId() == R.id.buttonBackCode3) {
            observationActivity.setBackCode(3);

        } else if (v.getId() == R.id.buttonBackCode4) {
            observationActivity.setBackCode(4);

        }
        observationActivity.getButtonSubmit().setVisibility(View.VISIBLE);
        getActivity().getSupportFragmentManager().popBackStack();
    }
}