package com.example.login_page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@SuppressWarnings("ALL")
public class Login extends Activity implements OnClickListener {
    EditText Id, Password;
    Button submit;
    //Login activity;
    String s1, s2;
    Context c;
    //private ProgressBar pb;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        findViewsById();

        submit.setOnClickListener(this);
    }

    private void findViewsById() {

        Id = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        submit = findViewById(R.id.loginbtn);
//        pb=(ProgressBar)findViewById(R.id.progressBar1);
//        pb.setVisibility(View.GONE);
        c = this;
    }

    public void onClick(View view) {
        System.out.println("Test");
        s1 = Id.getText().toString();
        s2 = Password.getText().toString();

//        pb.setVisibility(View.VISIBLE);
        if (s1.matches("") || s2.matches("")) {
            Toast.makeText(c, "Credentials are Blank", Toast.LENGTH_LONG).show();
//            pb.setVisibility(View.INVISIBLE);
        } else {
            new MyLoginAsynTask().execute();

        }

// ASync task starting
    }

    private void showHomePage() {
        Intent intent;
        intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }

    class MyLoginAsynTask extends AsyncTask<Void, Void, String> {
        Boolean loggedIn;



        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String bookJSONString = null;
            System.out.println("do in background");
            // Base URL for Books API.
            final String BOOK_BASE_URL = "http://10.0.2.2:8080/ntsf-backend/policelogin";
//            final String USER_TYPE_PARAM = "user_type";
            final String LOGIN_ID_PARAM = "police_id";
            final String PASSWORD_PARAM = "password";

            String loginId = Id.getText().toString();
            String password = Password.getText().toString();

            try {
                Uri uri = Uri.parse(BOOK_BASE_URL)
                        .buildUpon()
                        .appendQueryParameter(LOGIN_ID_PARAM, loginId)
                        .appendQueryParameter(PASSWORD_PARAM, password)
                        .build();


                URL url = new URL(uri.toString());
                System.out.println(url);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                try {
                    urlConnection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader((new InputStreamReader(inputStream)));

                StringBuilder builder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    builder.append(line).append("\n");
                }
                JSONObject responseJson = new JSONObject(builder.toString());
                if(responseJson.getString("loggedIn") == "true"){
                    loggedIn = true;
                    String policeId = responseJson.getString("police_id");
                    PoliceIdSession storenew = new PoliceIdSession();
                    storenew.storePoliceId(Login.this, policeId);

                } else {
                    loggedIn = false;
                }
                System.out.println("Logged in: "+loggedIn);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (loggedIn) {
                showHomePage();
            } else {
                Toast.makeText(c, "Username or password is incorrect", Toast.LENGTH_LONG).show();
            }


        }
    }
}