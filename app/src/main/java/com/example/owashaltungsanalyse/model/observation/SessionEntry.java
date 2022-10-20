package com.example.owashaltungsanalyse.model.observation;

import java.time.LocalDateTime;

public class SessionEntry implements ISessionEntry {

    private int leg;
    private int arm;
    private int back;
    private int useOfForce;
    private LocalDateTime timestamp;

    public SessionEntry(int leg, int arm, int back, int useOfForce) {
        this.leg = leg;
        this.arm = arm;
        this.back = back;
        this.useOfForce = useOfForce;
        timestamp = LocalDateTime.now();
    }


    public SessionEntry(String owasCode) {
        this.leg = Integer.parseInt(String.valueOf(owasCode.charAt(0)));
        this.arm = Integer.parseInt(String.valueOf(owasCode.charAt(1)));
        this.back = Integer.parseInt(String.valueOf(owasCode.charAt(2)));
        this.useOfForce = Integer.parseInt(String.valueOf(owasCode.charAt(3)));
    }

    public String toOWASCode() {
        return "" + leg + arm + back + useOfForce;
    }

    public int getArm() {
        return this.arm;
    }

    public void setArm(int arm) {
        this.arm = arm;
    }

    public int getBack() {
        return this.back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public int getLeg() {
        return this.leg;
    }

    public void setLeg(int leg) {
        this.leg = leg;
    }

    public int getUseOfForce() {
        return this.useOfForce;
    }

    public void setUseOfForce(int useOfForce) {
        this.useOfForce = useOfForce;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}