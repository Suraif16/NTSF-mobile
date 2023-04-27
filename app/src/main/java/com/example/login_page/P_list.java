package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class P_list extends AppCompatActivity {

    private Button Back;
    JSONArray list;

    String name;

    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.p_list);



        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(P_list.this, Home.class);
                startActivity(intent);
            }
        });

        c = this;

        // Call AsyncTask to get offence list
        GetOffenceListTask task = new GetOffenceListTask();
        task.execute();
    }

    protected JSONArray getOffenceList() throws JSONException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJsonString = null;
        System.out.println("Do in Background");

        final String BASE_URL = "http://10.0.2.2:8080/ntsf-backend/offenceList";

        StringBuilder builder;
        try {
            URL url = new URL(BASE_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader((new InputStreamReader(inputStream)));

            builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            bookJsonString = builder.toString();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
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

        System.out.println(bookJsonString);
        JSONObject jsonObject = new JSONObject(bookJsonString);
        JSONArray offencesArray = jsonObject.getJSONArray("offences");



        return offencesArray;

    }

    private class GetOffenceListTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String bookJsonString = null;
            System.out.println("Do in Background");

            final String BASE_URL = "http://10.0.2.2:8080/ntsf-backend/offenceList";

            StringBuilder builder;
            try {
                URL url = new URL(BASE_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader((new InputStreamReader(inputStream)));

                builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                bookJsonString = builder.toString();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
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


            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(bookJsonString);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            JSONArray offencesArray = null;
            try {
                offencesArray = jsonObject.getJSONArray("offences");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return offencesArray;
        }

        @Override
        protected void onPostExecute(JSONArray offencesArray) {
            super.onPostExecute(offencesArray);
            list = offencesArray;


//            LinearLayout layoutDriver = findViewById(R.id.driver_fines);
//            LinearLayout layoutVehicle = findViewById(R.id.vehicle_fines);
            LinearLayout layoutPedestrian = findViewById(R.id.pedestrian_fines);

            JSONArray jsonArrayD = new JSONArray();
            JSONArray jsonArrayV = new JSONArray();
            JSONArray jsonArrayP = new JSONArray();

            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = (JSONObject) list.get(i);
                    System.out.println(i+1+" Json: "+jsonObject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                String offenceType = null;
                try {
                    offenceType = jsonObject.getString("offenceType");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                if (offenceType.equals("d")) {
                    jsonArrayD.put(jsonObject);
                } else if (offenceType.equals("v")) {
                    jsonArrayV.put(jsonObject);
                } else if (offenceType.equals("p")) {
                    jsonArrayP.put(jsonObject);
                }
            }

            int amount;
            String offenceType;
            String description;
            int pointWeight;
            int offenceNo;
            JSONObject jsonObject;


//            TextView nameMessage = findViewById(R.id.name1);
//            nameMessage.setText(name);


            JSONArray jsonArray = null;
            jsonArray = list;

//            for (int i = 0; i < jsonArrayD.length(); i++) {
//
//                try {
//                    jsonObject = jsonArrayD.getJSONObject(i);
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//                try {
//                    amount = jsonObject.getInt("amount");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    offenceType = jsonObject.getString("offenceType");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    description = jsonObject.getString("description");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    pointWeight = jsonObject.getInt("pointWeight");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    offenceNo = jsonObject.getInt("offenceNo");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//                TextView textView = new TextView(c);
//                textView.setText(i+1+". "+"\n"+"Amount: " + amount + "\n" +
//                        "Offence Type: " + offenceType + "\n" +
//                        "Description: " + description + "\n" +
//                        "Point Weight: " + pointWeight + "\n" +
//                        "Offence No: " + offenceNo + "\n");
//                textView.setTextColor(Color.BLACK);
//                layoutDriver.addView(textView);
//            }
//
//            for (int i = 0; i < jsonArrayV.length(); i++) {
//
//                try {
//                    jsonObject = jsonArrayV.getJSONObject(i);
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//                try {
//                    amount = jsonObject.getInt("amount");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    offenceType = jsonObject.getString("offenceType");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    description = jsonObject.getString("description");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    pointWeight = jsonObject.getInt("pointWeight");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//                try {
//                    offenceNo = jsonObject.getInt("offenceNo");
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//                TextView textView = new TextView(c);
//                textView.setText(i+1+". "+"\n"+"Amount: " + amount + "\n" +
//                        "Offence Type: " + offenceType + "\n" +
//                        "Description: " + description + "\n" +
//                        "Point Weight: " + pointWeight + "\n" +
//                        "Offence No: " + offenceNo + "\n");
//                textView.setTextColor(Color.BLACK);
//                layoutVehicle.addView(textView);
//            }

            for (int i = 0; i < jsonArrayP.length(); i++) {

                try {
                    jsonObject = jsonArrayP.getJSONObject(i);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                try {
                    amount = jsonObject.getInt("amount");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                try {
                    offenceType = jsonObject.getString("offenceType");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                try {
                    description = jsonObject.getString("description");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                try {
                    pointWeight = jsonObject.getInt("pointWeight");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                try {
                    offenceNo = jsonObject.getInt("offenceNo");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                TextView textView = new TextView(c);
                textView.setText(i+1+". "+"\n"+"Amount: " + amount + "\n" +
                        "Offence Type: " + offenceType + "\n" +
                        "Description: " + description + "\n" +
                        "Point Weight: " + pointWeight + "\n" +
                        "Offence No: " + offenceNo + "\n");
                textView.setTextColor(Color.BLACK);
                layoutPedestrian.addView(textView);
            }
            // Update UI here
        }
    }

}