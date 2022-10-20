package com.example.owashaltungsanalyse.model.observation;

import java.time.LocalDateTime;

public interface ISessionEntry {

    String toOWASCode();

    int getArm();

    int getBack();

    int getLeg();

    int getUseOfForce();

    LocalDateTime getTimestamp();

    void setTimestamp(LocalDateTime timestamp);

}