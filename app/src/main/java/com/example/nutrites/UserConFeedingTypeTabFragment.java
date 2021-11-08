package com.example.nutrites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserConFeedingTypeTabFragment extends Fragment {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressBar progressBars;

    FirebaseUser user;
    String userID;

    String sHText,sEText;
    Spinner spinnerEthnicity, spinnerHabits;
    Button finishb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_user_con_feeding_type_tab_fragment, container, false);


        spinnerEthnicity = root.findViewById(R.id.spinnerEthnicity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext().getApplicationContext(), R.array.spinner_Ethnicity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEthnicity.setAdapter(adapter);
        spinnerEthnicity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 sEText = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerHabits = root.findViewById(R.id.spinnerHabits);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext().getApplicationContext(), R.array.spinner_Decisions, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHabits.setAdapter(adapter2);
        spinnerHabits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 sHText = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        progressBars = root.findViewById(R.id.progressBarwuc);
        progressBars.setVisibility(View.INVISIBLE);

        finishb = root.findViewById(R.id.wucfinish);

        finishb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sHText == null || sEText == null){

                }else{
                    progressBars.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext().getApplicationContext(), "Configuring please wait", Toast.LENGTH_LONG).show();
                    configure();
                }
            }
        });

        return root;
    }

    private void configure() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("userscon");
        userID = user.getUid();
        UserConClass userConClass = new UserConClass(sEText,sHText);
        reference.child(userID).setValue(userConClass);
        Intent intent = new Intent(getContext().getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void finish() {
        finish();
    }

}