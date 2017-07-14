package com.doctor.systemtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    public static final String JSONFILENAME = "prescriptions.json";

    RecyclerPrescriptionAdapter prescriptionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

         String resString = getJsonFileFromAssets();
        if (resString != null) {

            try {
                JSONObject jsonRes = new JSONObject(resString);
                JSONObject presObject = jsonRes.getJSONObject("prescriptions");

                List<JSONArray> listJson = new ArrayList<JSONArray>();
                listJson.add(presObject.getJSONArray("PR093"));
                listJson.add(presObject.getJSONArray("PR102"));
                listJson.add(presObject.getJSONArray("PR113"));
                listJson.add(presObject.getJSONArray("PR114"));
                listJson.add(presObject.getJSONArray("PR115"));
                listJson.add(presObject.getJSONArray("PR116"));

                String dateFormat = "yyyy-MM-dd";

                List<JSONObject> finalDetails = new ArrayList<JSONObject>();

                for (JSONArray innerJson : listJson) {
                    for (int i = 0; i < innerJson.length(); i++) {
                        if (innerJson.get(i) != null && !"null".equalsIgnoreCase(innerJson.get(i).toString())) {
                            JSONObject details = new JSONObject(innerJson.get(i).toString());
                            String endDate = details.getString("end_date_time");
                            if (endDate != null) {
                                Date dt = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                                Date resDate = sdf.parse(endDate);
                                Calendar currCal = Calendar.getInstance();
                                Calendar resCal = Calendar.getInstance();
                                resCal.setTime(resDate);
                                currCal.setTime(dt);

                                int currMonth = currCal.get(Calendar.MONTH);
                                int resMonth = resCal.get(Calendar.MONTH);
                                currCal.get(Calendar.MONTH);
                                if ((currMonth + 1) == (resMonth + 1)) {
                                    finalDetails.add(details);
                                    Log.v("End Date", endDate);
                                    Log.v("Current Month", "" + (currMonth + 1));
                                    Log.v("Our Month", "" + (resMonth + 1));
                                }
                            }
                        }
                    }
                }

                ArrayList<Prescriptions> list = new ArrayList<Prescriptions>();
                list.clear();

                for (JSONObject jRes : finalDetails) {

                    Prescriptions prescriptions = new Prescriptions();

                    prescriptions.setPatientName(jRes.getString("patient_name"));
                    prescriptions.setPatientId(jRes.getString("patient_id_c"));
                    prescriptions.setDoctorName(jRes.getString("doctor_name"));
                    prescriptions.setDrugname(jRes.getString("name"));
                    prescriptions.setDrugForm(jRes.getString("drug_form"));

                    list.add(prescriptions);

                }

                prescriptionAdapter = new RecyclerPrescriptionAdapter(getApplicationContext(), list);
                mRecyclerView.setAdapter(prescriptionAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public String getJsonFileFromAssets() {
        String text;
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream;
        try {
            inputStream = this.getAssets().open(JSONFILENAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
