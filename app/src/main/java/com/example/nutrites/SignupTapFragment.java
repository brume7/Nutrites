package com.example.nutrites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTapFragment extends Fragment {

    EditText semail,spass,snumber,srepass;
    Button signup;
    float v=0;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tap_fragment, container, false);

        semail = root.findViewById(R.id.email);
        snumber = root.findViewById(R.id.snumber);
        spass = root.findViewById(R.id.spass);
        srepass = root.findViewById(R.id.srepass);
        signup = root.findViewById(R.id.signupbutton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                reference.setValue("i did it");
            }
        });

        return root;

    }
}
