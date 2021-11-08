package com.example.nutrites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ForgotPassword extends AppCompatActivity {

    EditText resetEmail;
    Button resetButton;
    ProgressBar resetProgressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        resetEmail = findViewById(R.id.resetemail);
        resetButton = findViewById(R.id.resetbutton);
        resetProgressbar = findViewById(R.id.progressBarreset);
        resetProgressbar.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();

            }
        });

    }

    private void resetPassword() {
        String email = resetEmail.getText().toString().trim();

        if (email.isEmpty()) {
            resetEmail.setError("Field cannot be empty");
            resetEmail.requestFocus();
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                resetEmail.setError("Input Valid Email");
                resetEmail.requestFocus();

            } else {
                resetProgressbar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            resetProgressbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Try again! something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }
    }
}