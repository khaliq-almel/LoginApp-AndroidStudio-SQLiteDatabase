package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupForm extends AppCompatActivity {

    EditText t_fullname,t_user,t_email,t_password,t_phone;
    Button btn_register;
    RadioButton rmale,rfemale;
    String gender ="";
    DatbaseHelper db;

    /* FireBase
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;


    /* fireBAse End */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Sign Up");

        t_fullname= (EditText)findViewById(R.id.fullname);
        t_user=(EditText)findViewById(R.id.user);
        t_email=(EditText)findViewById(R.id.email);
        t_password=(EditText)findViewById(R.id.password);
        t_phone=(EditText)findViewById(R.id.phone);
        btn_register=(Button)findViewById(R.id.register);
        rmale=(RadioButton)findViewById(R.id.male);
        rfemale=(RadioButton)findViewById(R.id.female);


        /*sqlite */

        db = new DatbaseHelper(this);

        /* FireBAse
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("customer");
        firebaseAuth =FirebaseAuth.getInstance();
        /* firebase */


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fname=t_fullname.getText().toString().trim();
                final String uname=t_user.getText().toString().trim();
                final String email=t_email.getText().toString().trim();
                final String phone=t_phone.getText().toString().trim();
                String pass=t_password.getText().toString().trim();

                if(rmale.isChecked()){
                    gender="male";
                }
                if(rfemale.isChecked()){
                    gender="female";
                }

                if(TextUtils.isEmpty(fname)){
                    Toast.makeText(SignupForm.this,"Enter your name",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(uname)){
                    Toast.makeText(SignupForm.this,"Enter your Username",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupForm.this,"Enter your email",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(SignupForm.this,"Enter your phone",Toast.LENGTH_SHORT).show();

                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(SignupForm.this,"Enter your password",Toast.LENGTH_SHORT).show();

                }

                if(true){
                    long val =db.addUser(fname,uname,email,phone,pass,gender);
                    if(val>0){
                        Toast.makeText(SignupForm.this,"Succesfully Registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Login.class));

                    }else{
                        Toast.makeText(SignupForm.this,"Registration Error",Toast.LENGTH_SHORT).show();

                    }

                }


                /* firebase
                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignupForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Customer customer = new Customer(fname,uname,phone,email,gender);

                                    FirebaseDatabase.getInstance().getReference("Customer")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                        }
                                    });



                                } else {
                                    startActivity(new Intent(getApplicationContext(),Login.class));

                                }

                                // ...
                            }
                        });


                /* firebase */

            }
        });


    }
}
