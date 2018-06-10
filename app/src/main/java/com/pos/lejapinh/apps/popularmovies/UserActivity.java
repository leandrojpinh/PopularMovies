package com.pos.lejapinh.apps.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pos.lejapinh.apps.popularmovies.data.UserClient;
import com.pos.lejapinh.apps.popularmovies.data.data_entities.User;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class UserActivity extends AppCompatActivity {

    private EditText edt_name, edt_email, edt_password;
    private Button btn_add;

    private UserClient userClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        InitializeComponentes();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(edt_name.getText().toString());
                user.setEmail(edt_email.getText().toString());
                user.setPassword(edt_password.getText().toString());
                user.setDate_register(Calendar.getInstance().getTime().toString());

                userClient.insert(user);

                Toast.makeText(getApplicationContext(), "User has registered", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });


    }

    private void InitializeComponentes() {
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);

        btn_add = (Button) findViewById(R.id.btn_add);

        userClient = UserClient.getInstance(this);
    }
}
