package com.example.nutrites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeUserConTabFragment extends Fragment {

    TextView Welcometext, Username, started;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_welcome_user_con_tab_fragment, container, false);

        Welcometext = root.findViewById(R.id.welcomeUC);
        Username = root.findViewById(R.id.wucusername);
        started = root.findViewById(R.id.wucstarted);

        Welcometext.setTranslationY(800);
        Username.setTranslationY(800);
        started.setTranslationY(800);

        Welcometext.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        Username.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        started.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelperClass UserProfile = snapshot.getValue(UserHelperClass.class);

                if(UserProfile != null){
                    Username.setText(UserProfile.username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return root;
    }
}