package com.example.nutrites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView name, mail, texterH;
    Button logout;
    private FirebaseUser user;
    private DatabaseReference reference, reference2;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logout = findViewById(R.id.logout);
        texterH = findViewById(R.id.textH);
        name = findViewById(R.id.namem);
        mail = findViewById(R.id.mailm);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference2 = FirebaseDatabase.getInstance().getReference("userscon");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass UserProfile = snapshot.getValue(UserHelperClass.class);

                if(UserProfile != null){
                    name.setText(UserProfile.username);
                    mail.setText(UserProfile.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference2.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserConClass userCon = snapshot.getValue(UserConClass.class);

                if (userCon != null){
                    if (userCon.mode.length()== 3) {
                        texterH.setText("vegan");
                    }else{
                        texterH.setText("meat");
                    }
                }else {
                    texterH.setText("failed");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}