package com.example.owashaltungsanalyse.view.observation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.owashaltungsanalyse.R;

import java.util.Arrays;
import java.util.List;

public class ObservationChooseFragment extends Fragment implements View.OnClickListener {

    private ObservationActivity observationActivity;
    private ImageButton imageButtonLeg;
    private ImageButton imageButtonArm;
    private ImageButton imageButtonBack;
    private ImageButton imageButtonUseOfForce;
    private List<Integer> legImageIds;
    private List<Integer> armImageIds;
    private List<Integer> backImageIds;
    private List<Integer> useOfForceImageIds;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_observation_choose, container, false);


        this.legImageIds = Arrays.asList(R.drawable.ic_lower_1, R.drawable.ic_lower_2, R.drawable.ic_lower_3,
                R.drawable.ic_lower_4, R.drawable.ic_lower_5, R.drawable.ic_lower_6, R.drawable.ic_lower_7);

        this.armImageIds = Arrays.asList(R.drawable.ic_upper_1, R.drawable.ic_upper_2, R.drawable.ic_upper_3);

        this.backImageIds = Arrays.asList(R.drawable.ic_back_1, R.drawable.ic_back_2, R.drawable.ic_back_3,
                R.drawable.ic_back_4);

        this.useOfForceImageIds = Arrays.asList(R.drawable.ic_weight_1, R.drawable.ic_weight_2, R.drawable.ic_weight_3);

        this.imageButtonLeg = root.findViewById(R.id.imageButtonLeg);
        this.imageButtonArm = root.findViewById(R.id.imageButtonArm);
        this.imageButtonBack = root.findViewById(R.id.imageButtonBack);
        this.imageButtonUseOfForce = root.findViewById(R.id.imageButtonUseOfForce);

        this.imageButtonUseOfForce.setOnClickListener(this);
        this.imageButtonBack.setOnClickListener(this);
        this.imageButtonArm.setOnClickListener(this);
        this.imageButtonLeg.setOnClickListener(this);


        observationActivity = (ObservationActivity) getActivity();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        observationActivity.showSubmitButton();
    }

    @Override
    public void onStart() {
        super.onStart();
        setLegImage();
        setArmImage();
        setBackImage();
        setUseOfForceImage();

    }

    public void setLegImage() {
        int leg = observationActivity.getLegCode();
        this.imageButtonLeg.setImageResource(legImageIds.get(leg - 1));
    }

    public void setArmImage() {
        int arm = observationActivity.getArmCode();
        this.imageButtonArm.setImageResource(armImageIds.get(arm - 1));
    }

    public void setBackImage() {
        int back = observationActivity.getBackCode();
        this.imageButtonBack.setImageResource(backImageIds.get(back - 1));
    }

    public void setUseOfForceImage() {
        int useOfForce = observationActivity.getUseOfForceCode();
        this.imageButtonUseOfForce.setImageResource(useOfForceImageIds.get(useOfForce - 1));
    }


    @Override
    public void onClick(View v) {
        ObservationActivity observationActivity = (ObservationActivity) getActivity();
        if (v.getId() == R.id.imageButtonArm) {
            observationActivity.switchToArmFragment();
        }
        if (v.getId() == R.id.imageButtonLeg) {
            observationActivity.switchToLegFragment();
        }
        if (v.getId() == R.id.imageButtonBack) {
            observationActivity.switchToBackFragment();
        }
        if (v.getId() == R.id.imageButtonUseOfForce) {
            observationActivity.switchToUseOfForceFragment();
        }

    }

}