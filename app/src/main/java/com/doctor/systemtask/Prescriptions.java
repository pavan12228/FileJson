package com.doctor.systemtask;

/**
 * Created by CNU on 7/13/2017.
 */

public class Prescriptions {

    String patientName;
    String patientId;
    String doctorName;
    String route;
    String drugname;
    String drugForm;
    String noOfMedicines;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public String getNoOfMedicines() {
        return noOfMedicines;
    }

    public void setNoOfMedicines(String noOfMedicines) {
        this.noOfMedicines = noOfMedicines;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
