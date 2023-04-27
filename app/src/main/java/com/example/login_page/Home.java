package com.example.login_page;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_page.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button logout = findViewById(R.id.logoutbtn);
        logout.setOnClickListener(view -> {
            Intent intent=new Intent(Home.this, Login.class);
            startActivity(intent);
        });

        Button view = findViewById(R.id.viewbtn);
        view.setOnClickListener(view1 -> {
            Intent intent;
            intent = new Intent(Home.this,allfines.class);
            startActivity(intent);
        });

        Button driver = findViewById(R.id.Driverbtn);
        driver.setOnClickListener(view12 -> {
            Intent intent;
            intent = new Intent(Home.this,D_fine.class);
            startActivity(intent);
        });

        Button vehicle = findViewById(R.id.Vehiclebtn);
        vehicle.setOnClickListener(view12 -> {
            Intent intent;
            intent = new Intent(Home.this,V_fine.class);
            startActivity(intent);
        });

        Button pedestrian = findViewById(R.id.Pedestrianbtn);
        pedestrian.setOnClickListener(view12 -> {
            Intent intent;
            intent = new Intent(Home.this,P_fine.class);
            startActivity(intent);
        });

    }
}