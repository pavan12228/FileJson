package com.doctor.systemtask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RAVI on 7/13/2017.
 */

public  class RecyclerPrescriptionAdapter extends RecyclerView.Adapter<RecyclerPrescriptionAdapter.PatientViewHolder>{


    Context context;
    ArrayList<Prescriptions> list_patient;


    public RecyclerPrescriptionAdapter(Context context, ArrayList<Prescriptions> list_patient) {

        this.context = context;
        this.list_patient = list_patient;
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        TextView patient_name;
        TextView patient_id;
        TextView doctor_name;
        TextView drug_name;
        TextView drug_form;


        public PatientViewHolder(View itemView) {
            super(itemView);

            patient_name = (TextView) itemView.findViewById(R.id.cpi_patient_name);
            patient_id = (TextView) itemView.findViewById(R.id.cpi_patient_id);
            doctor_name = (TextView) itemView.findViewById(R.id.cpi_doctor_name);
            drug_name = (TextView) itemView.findViewById(R.id.cpi_drug_name);
            drug_form = (TextView) itemView.findViewById(R.id.cpi_drugForm);

        }
    }

    @Override
    public RecyclerPrescriptionAdapter.PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_prescription_item, parent, false);

        RecyclerPrescriptionAdapter.PatientViewHolder patientViewHolder = new RecyclerPrescriptionAdapter.PatientViewHolder(view);
        return patientViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerPrescriptionAdapter.PatientViewHolder holder, int position) {

        holder.patient_name.setText(list_patient.get(position).getPatientName());
        holder.patient_id.setText(list_patient.get(position).getPatientId());
        holder.doctor_name.setText(list_patient.get(position).getDoctorName());
        holder.drug_name.setText(list_patient.get(position).getDrugname());
        holder.drug_form.setText(list_patient.get(position).getDrugForm());
    }

    @Override
    public int getItemCount() {
        return list_patient.size();
    }
}
