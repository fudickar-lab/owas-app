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


public class ObservationUseOfForceFragment extends Fragment implements View.OnClickListener {

    private List<ImageButton> buttonList;
    private ImageButton buttonUseOfForceCode1;
    private ImageButton buttonUseOfForceCode2;
    private ImageButton buttonUseOfForceCode3;
    private ObservationActivity observationActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_observation_use_of_force, container, false);

        this.buttonUseOfForceCode1 = root.findViewById(R.id.buttonUseOfForceCode1);
        this.buttonUseOfForceCode2 = root.findViewById(R.id.buttonUseOfForceCode2);
        this.buttonUseOfForceCode3 = root.findViewById(R.id.buttonUseOfForceCode3);

        this.buttonUseOfForceCode3.setOnClickListener(this);
        this.buttonUseOfForceCode2.setOnClickListener(this);
        this.buttonUseOfForceCode1.setOnClickListener(this);

        buttonList = Arrays.asList(this.buttonUseOfForceCode1, this.buttonUseOfForceCode2, this.buttonUseOfForceCode3);

        observationActivity = (ObservationActivity) getActivity();

        return root;
    }

    @Override
    public void onClick(View v) {
        int useOfForceCode = 1;
        for (ImageButton button : buttonList) {
            if (button.getId() == v.getId()) {
                observationActivity.setUseOfForceCode(useOfForceCode);
                observationActivity.getButtonSubmit().setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().popBackStack();
            } else useOfForceCode = useOfForceCode + 1;

        }

    }
}