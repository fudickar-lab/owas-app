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


public class ObservationArmPostureFragment extends Fragment implements View.OnClickListener {
    private List<ImageButton> buttonList;
    private ImageButton buttonArmCode1;
    private ImageButton buttonArmCode2;
    private ImageButton buttonArmCode3;
    private ObservationActivity observationActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_observation_arm_posture, container, false);

        this.buttonArmCode1 = root.findViewById(R.id.imageButtonArmCode1);
        this.buttonArmCode2 = root.findViewById(R.id.imageButtonArmCode2);
        this.buttonArmCode3 = root.findViewById(R.id.imageButtonArmCode3);

        this.buttonArmCode1.setOnClickListener(this);
        this.buttonArmCode2.setOnClickListener(this);
        this.buttonArmCode3.setOnClickListener(this);

        buttonList = Arrays.asList(this.buttonArmCode1, this.buttonArmCode2, this.buttonArmCode3);

        observationActivity = (ObservationActivity) getActivity();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (observationActivity.getObservationPresenter().getLastSessionEntry() != null) {
            int arm = observationActivity.getObservationPresenter().getLastSessionEntry().getArm();
            buttonList.get(arm - 1).setSelected(true);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButtonArmCode1) {
            observationActivity.setArmCode(1);
        } else if (v.getId() == R.id.imageButtonArmCode2) {
            observationActivity.setArmCode(2);

        } else if (v.getId() == R.id.imageButtonArmCode3) {
            observationActivity.setArmCode(3);

        }
        observationActivity.getButtonSubmit().setVisibility(View.VISIBLE);
        getActivity().getSupportFragmentManager().popBackStack();

    }
}