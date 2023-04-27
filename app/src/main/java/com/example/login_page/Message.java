package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.login_page.R;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String fineNumber = intent.getStringExtra("fine_number");
        System.out.println(fineNumber);

        setContentView(R.layout.message);


        TextView fineNum = findViewById(R.id.FineNo);
        fineNum.setText(fineNumber);

        System.out.println(fineNumber);

        Button okbtn = findViewById(R.id.Okaybtn);
        okbtn.setOnClickListener(view -> {
            Intent intent2 = new Intent(Message.this, Home.class);
            startActivity(intent2);
        });

    }

}