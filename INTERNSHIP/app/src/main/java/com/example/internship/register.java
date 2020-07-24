package com.example.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {
    public static boolean isAlpha(String s) {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText etmail,etpass,etpass2,etname;
    Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etmail = findViewById(R.id.etmail);
        final EditText etpass = findViewById(R.id.etpass);
        final EditText etname = findViewById(R.id.etname);
        final EditText etpass2 = findViewById(R.id.etpass2);
        final Button btnregister = findViewById(R.id.btnregister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etmail.getText().toString().trim();
                String password = etpass.getText().toString().trim();
                if(password.equals(etpass2.getText().toString().trim())){
                        if(isAlpha(etname.getText().toString().trim()) == true){
                            mAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information

                                                FirebaseUser user = mAuth.getCurrentUser();
                                                startActivity(new Intent(register.this, tabactivity.class));
                                            } else {
                                                Toast.makeText(register.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                                            }

                                            // ...
                                        }
                                    });

                    }
                    else{
        Toast.makeText(register.this, "Name should be alphabets", Toast.LENGTH_SHORT).show();
                                     }

                }
                else{
                    Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}