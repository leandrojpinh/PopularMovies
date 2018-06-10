package com.pos.lejapinh.apps.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.pos.lejapinh.apps.popularmovies.data.UserClient;
import com.pos.lejapinh.apps.popularmovies.data.data_entities.User;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_email, edt_password;
    private Button btn_login;
    private UserClient userClient;
    private FirebaseAuth firebaseAuth;
    private User user;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitializeComponentes();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userLogin = new User();
                userLogin.setEmail(edt_email.getText().toString());

                if(userClient.findByEmail(userLogin.getEmail()) == null) {
                    //Cadastrar
                    Toast.makeText(getApplicationContext(), "Ainda não cadastrado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(i);

                    //faltou implemenntar isso
                    /*loginWithFirebase();

                    if(key.equals("")) {
                    } else {
                        //Login ok
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }*/
                } else {
                    //Login ok
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void InitializeComponentes() {
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);

        btn_login = (Button) findViewById(R.id.btn_login);

        userClient = UserClient.getInstance(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void loginWithFirebase(){
        try{
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(edt_email.getText().toString(), edt_password.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            final String uID = authResult.getUser().getUid();
                            final FirebaseDatabase firebaseDatabase =
                                    FirebaseDatabase.getInstance();

                            firebaseDatabase
                                    .getReference()
                                    .child("users")
                                    .child(uID)
                                    .addChildEventListener(favoritesListener);

                            user = new User();
                            user.setName(authResult.getUser().getDisplayName());
                            user.setEmail(authResult.getUser().getEmail());
                            user.setEmail(Calendar.getInstance().getTime().toString());

                            key = uID;
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
            });
        }catch (IllegalArgumentException e){
            int a = 1;
        }

    }

    private ChildEventListener favoritesListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
