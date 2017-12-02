package com.example.deepanshu.rjshs_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepanshu.rjshs_app.models.CarJourney;
import com.example.deepanshu.rjshs_app.models.StatusModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView statusTextView, numberTextView, latTextView, lonTextView, tempTextView, fuelTextView;
    private JourneyAdapter journeyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //setSupportActionBar(toolbar);
        //Stetting textView
        statusTextView = (TextView)findViewById(R.id.car_status_status);
        numberTextView = (TextView)findViewById(R.id.car_status_number);
        latTextView = (TextView)findViewById(R.id.car_status_lat);
        lonTextView = (TextView)findViewById(R.id.car_status_lon);
        tempTextView = (TextView)findViewById(R.id.car_status_temp);
        fuelTextView = (TextView)findViewById(R.id.car_status_fuel);



        recyclerView = (RecyclerView) findViewById(R.id.car_journey_recyclerView);
        LinearLayoutManager layoutManagerCast = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagerCast);
        StatusFetchTask statusFetchTask = new StatusFetchTask();
        statusFetchTask.execute("http://f82cef4d.ngrok.io/api/v1/cars/AUBT9863/status/");
        JourneysFetchTask journeysFetchTask = new JourneysFetchTask();
        journeysFetchTask.execute("http://f82cef4d.ngrok.io/api/v1/cars/AUBT9863/journeys/");
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
////        switch (item.getItemId()) {
////            case R.id.main_refresh:
////                CommonFetchTask commonFetchTask = new CommonFetchTask();
////                commonFetchTask.execute("http://192.168.43.125:3000/api/patients/all");
////                return true;
////            case R.id.main_register:
////                Intent intent = new Intent(this, RegisterActivity.class);
////                startActivity(intent);
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
////        }
//    }

    public class CommonFetchTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonData = null;
            try {
                //setting the urlConnection
                Log.v("hello","from back ground thread");
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream stream = urlConnection.getInputStream();
                if (stream == null){
                    jsonData = null;
                }
                StringBuffer stringBuffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(stream));
                String inputLine;
                while ((inputLine = reader.readLine()) != null){
                    stringBuffer.append(inputLine + "\n");
                }
                if (stringBuffer.length() == 0){
                    jsonData = null;
                }
                jsonData = stringBuffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return jsonData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String jsonData) {
//            ArrayList<PatientModel> patientModelArrayList = new ArrayList<>();
//            super.onPostExecute(jsonData);
//            try {
//                JSONObject jsonObject = new JSONObject(jsonData);
//                JSONArray resultArray = jsonObject.getJSONArray("results");
//                for (int i=0; i < resultArray.length(); i++){
//                    JSONObject patient = (JSONObject) resultArray.get(i);
//                    Log.i("reults",patient.toString());
//                    PatientModel patientModel = new PatientModel();
//                    patientModel.setPatientId(patient.get("patient_id").toString());
//                    patientModel.setPatientName(patient.get("patient_name").toString());
//                    patientModel.setPatientGender(patient.get("patient_gender").toString());
//                    patientModel.setPatientLocation(patient.get("patient_location").toString());
//                    patientModel.setPatientPhoneNumber(patient.get("patient_phone_no").toString());
//                    patientModel.setPatientPulseRate(patient.get("patient_heartbeat").toString());
//                    patientModel.setPatientRoomtemp(patient.get("patient_room_temp").toString());
//                    patientModel.setPatientStatus(patient.get("patient_status").toString());
//                    patientModel.setPatientTemp(patient.get("patient_temp").toString());
//                    patientModelArrayList.add(patientModel);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            patientAdapter = new PatientAdapter(getApplicationContext(),patientModelArrayList);
//            recyclerView.setAdapter(patientAdapter);
        }

        @Override
        protected void onCancelled(String arrayList) {
            super.onCancelled(arrayList);
        }
    }
    public class StatusFetchTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonData = null;
            try {
                //setting the urlConnection
                Log.v("hello","from back ground thread");
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream stream = urlConnection.getInputStream();
                if (stream == null){
                    jsonData = null;
                }
                StringBuffer stringBuffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(stream));
                String inputLine;
                while ((inputLine = reader.readLine()) != null){
                    stringBuffer.append(inputLine + "\n");
                }
                if (stringBuffer.length() == 0){
                    jsonData = null;
                }
                jsonData = stringBuffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return jsonData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String jsonData) {
            StatusModel statusModel = new StatusModel();
            super.onPostExecute(jsonData);
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                statusModel.setCarStatus(jsonObject.get("car_status").toString());
                statusModel.setCarNumber(jsonObject.get("car_number").toString());
                statusModel.setCarLat(jsonObject.get("car_lat").toString());
                statusModel.setCarLon(jsonObject.get("car_lon").toString());
                statusModel.setCarFuel(jsonObject.get("car_fuel").toString());
                statusModel.setCarTemp(jsonObject.get("car_temp").toString());
                statusTextView.setText(statusModel.getCarStatus());
                numberTextView.setText(statusModel.getCarNumber());
                latTextView.setText(statusModel.getCarLat());
                lonTextView.setText(statusModel.getCarLon());
                tempTextView.setText(statusModel.getCarTemp());
                fuelTextView.setText(statusModel.getCarFuel());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled(String arrayList) {
            super.onCancelled(arrayList);
        }
    }

    public class JourneysFetchTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonData = null;
            try {
                //setting the urlConnection
                Log.v("hello","from back ground thread");
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream stream = urlConnection.getInputStream();
                if (stream == null){
                    jsonData = null;
                }
                StringBuffer stringBuffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(stream));
                String inputLine;
                while ((inputLine = reader.readLine()) != null){
                    stringBuffer.append(inputLine + "\n");
                }
                if (stringBuffer.length() == 0){
                    jsonData = null;
                }
                jsonData = stringBuffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return jsonData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String jsonData) {
            ArrayList<CarJourney> carJourneysArrayList = new ArrayList<>();
            super.onPostExecute(jsonData);
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray resultArray = jsonObject.getJSONArray("results");
                for (int i=0; i < resultArray.length(); i++){
                    JSONObject patient = (JSONObject) resultArray.get(i);
                    Log.i("reults",patient.toString());
                    CarJourney carJourney = new CarJourney();
                    carJourney.setJourneyDriverId(patient.get("jdriver_id").toString());
                    carJourney.setJourneyDate(patient.get("jdate").toString());
                    carJourney.setJourneyStartLat(patient.get("jstart_lat").toString());
                    carJourney.setJourneyStartLon(patient.get("jstart_lon").toString());
                    carJourney.setJourneyEndLat(patient.get("jend_lat").toString());
                    carJourney.setJourneyEndLon(patient.get("jend_lon").toString());
                    carJourney.setJourneyStartTime(patient.get("jstart_time").toString());
                    carJourney.setJourneyEndTime(patient.get("jend_time").toString());
                    carJourneysArrayList.add(carJourney);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            journeyAdapter = new JourneyAdapter(carJourneysArrayList);
            recyclerView.setAdapter(journeyAdapter);
        }

        @Override
        protected void onCancelled(String arrayList) {
            super.onCancelled(arrayList);
        }
    }
    class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.journeyViewHolder> {
        public ArrayList<CarJourney> carJourneys;

        class journeyViewHolder extends RecyclerView.ViewHolder {
            TextView driverTextView, dateTextView, startCordTextView, endCordTextView, startTimeTextView, endTimeTextView;

            public journeyViewHolder(View itemView) {
                super(itemView);
                driverTextView = (TextView) itemView.findViewById(R.id.journey_driver);
                dateTextView = (TextView) itemView.findViewById(R.id.journey_date);
                startCordTextView = (TextView) itemView.findViewById(R.id.journey_start_cord);
                endCordTextView = (TextView) itemView.findViewById(R.id.journey_end_cord);
                startTimeTextView = (TextView) itemView.findViewById(R.id.journey_start_time);
                endTimeTextView = (TextView) itemView.findViewById(R.id.journey_end_time);
            }
        }

        public JourneyAdapter(ArrayList<CarJourney> arrayList) {
            this.carJourneys = arrayList;
        }

        @Override
        public journeyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.journey_recycler_view, parent, false);
            return new journeyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final journeyViewHolder holder, int position) {
            final CarJourney journey = carJourneys.get(position);

            holder.driverTextView.setText(journey.getJourneyDriverId());
            holder.dateTextView.setText(journey.getJourneyDate());
            holder.startCordTextView.setText(journey.getJourneyStartLat().substring(0,6) +"," + journey.getJourneyStartLon().substring(0,6));
            holder.endCordTextView.setText(journey.getJourneyEndLat().substring(0,6) +"," + journey.getJourneyEndLon().substring(0,6));
            holder.startTimeTextView.setText(journey.getJourneyStartTime().substring(0,8));
            holder.endTimeTextView.setText(journey.getJourneyEndTime());
        }

        @Override
        public int getItemCount() {
            return carJourneys.size();
        }
    }

}