package com.example.iwimhub2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.*;
import com.google.firebase.firestore.*;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText fullName,email,password,phone;
    Button registerBtn,goToLogin;
    boolean valid = true;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    CheckBox isTeacherBox , isStudentBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);
        //loginBtn = findViewById(R.id.loginBtn);
        goToLogin = findViewById(R.id.gotoLogin);
        //gotoRegister = findViewById(R.id.gotoRegister);


        isTeacherBox =findViewById(R.id.isTeacher);
        isStudentBox = findViewById(R.id.isStudent);

        //check boxes logic
        isStudentBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    isTeacherBox.setChecked(false);
                }
            }
        });
        isTeacherBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    isStudentBox.setChecked(false);
                }
            }
        });



        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(fullName);
                checkField(email);
                checkField(password);
                checkField(phone);

                // checkbox validation
                if(!(isTeacherBox.isChecked() || isStudentBox.isChecked()) ){
                    Toast.makeText(com.example.iwimhub2.Register.this,"Select the account type",Toast.LENGTH_SHORT).show();
                    return;
                }



               /* registerBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Register.class));
                    }
                });*/

                if(valid){
                    //start the user registration process
                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user =fAuth.getCurrentUser();
                            Toast.makeText(com.example.iwimhub2.Register.this,"account created",Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("FullName",fullName.getText().toString());
                            userInfo.put("UserName",email.getText().toString());
                            userInfo.put("PhoneNumber",phone.getText().toString());

                            //Specify if the user is admin
                            if(isTeacherBox.isChecked()){
                                userInfo.put("isTeacher","1");
                            }
                            if(isStudentBox.isChecked()){
                                userInfo.put("isStudent","1");
                            }
                            //userInfo.put("isUser","1");

                            df.set(userInfo);
                            if(isTeacherBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(),Admin.class));
                                finish();
                            }
                            if(isStudentBox.isChecked()){
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(com.example.iwimhub2.Register.this,"Failed to create account",Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}