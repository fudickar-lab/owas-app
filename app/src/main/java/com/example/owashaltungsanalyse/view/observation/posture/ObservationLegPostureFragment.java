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


public class ObservationLegPostureFragment extends Fragment implements View.OnClickListener {

    private List<ImageButton> buttonList;
    private ImageButton buttonLegCode1;
    private ImageButton buttonLegCode2;
    private ImageButton buttonLegCode3;
    private ImageButton buttonLegCode4;
    private ImageButton buttonLegCode5;
    private ImageButton buttonLegCode6;
    private ImageButton buttonLegCode7;
    private ObservationActivity observationActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_observation_leg_posture, container, false);

        this.buttonLegCode1 = root.findViewById(R.id.buttonLegCode1);
        this.buttonLegCode2 = root.findViewById(R.id.buttonLegCode2);
        this.buttonLegCode3 = root.findViewById(R.id.buttonLegCode3);
        this.buttonLegCode4 = root.findViewById(R.id.buttonLegCode4);
        this.buttonLegCode5 = root.findViewById(R.id.buttonLegCode5);
        this.buttonLegCode6 = root.findViewById(R.id.buttonLegCode6);
        this.buttonLegCode7 = root.findViewById(R.id.buttonLegCode7);

        this.buttonLegCode7.setOnClickListener(this);
        this.buttonLegCode6.setOnClickListener(this);
        this.buttonLegCode5.setOnClickListener(this);
        this.buttonLegCode4.setOnClickListener(this);
        this.buttonLegCode3.setOnClickListener(this);
        this.buttonLegCode2.setOnClickListener(this);
        this.buttonLegCode1.setOnClickListener(this);


        buttonList = Arrays.asList(this.buttonLegCode1, this.buttonLegCode2, this.buttonLegCode3, this.buttonLegCode4,
                this.buttonLegCode5, this.buttonLegCode6, this.buttonLegCode7);

        observationActivity = (ObservationActivity) getActivity();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (observationActivity.getObservationPresenter().getLastSessionEntry() != null) {
            int leg = observationActivity.getObservationPresenter().getLastSessionEntry().getLeg();
            buttonList.get(leg - 1).setSelected(true);
        }
    }

    @Override
    public void onClick(View v) {
        int legCode = 1;
        for (ImageButton buttonLegCode : buttonList) {
            if (buttonLegCode.getId() == v.getId()) {
                observationActivity.setLegCode(legCode);
                observationActivity.getButtonSubmit().setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                legCode = legCode + 1;
            }
        }

    }
}