package com.example.nutrites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeTabFragment extends Fragment {
    TextView name, mail, texterH;
    Button logout;
    private FirebaseUser user;
    private DatabaseReference reference, reference2;

    private String userID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.home_tab_fragment, container, false);
        logout = root.findViewById(R.id.logout);
        texterH = root.findViewById(R.id.textH);
        name = root.findViewById(R.id.namem);
        mail = root.findViewById(R.id.mailm);


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
                        texterH.setText("vegetarian");
                    }else{
                        texterH.setText("omnivore");
                    }
                }else {
                    texterH.setText("");
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
                Intent intent = new Intent(getContext().getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
