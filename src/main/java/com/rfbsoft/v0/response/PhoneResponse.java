package com.rfbsoft.v0.response;

import java.util.List;

public class PhoneResponse {
    private List majorPhones;
    private List publicPhones;
    private List globalPhones;

    public PhoneResponse(List majorPhone, List publicPhones, List globalPhones) {
        this.majorPhones = majorPhone;
        this.publicPhones = publicPhones;
        this.globalPhones = globalPhones;
    }

    public List getMajorPhones() {
        return majorPhones;
    }

    public void setMajorPhones(List majorPhones) {
        this.majorPhones = majorPhones;
    }

    public List getPublicPhones() {
        return publicPhones;
    }

    public void setPublicPhones(List publicPhones) {
        this.publicPhones = publicPhones;
    }

    public List getGlobalPhones() {
        return globalPhones;
    }

    public void setGlobalPhones(List globalPhones) {
        this.globalPhones = globalPhones;
    }
}
