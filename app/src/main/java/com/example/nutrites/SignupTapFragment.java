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

    EditText email, pass, number, username;
    Button signup;
    float v=0;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tap_fragment, container, false);

        email = root.findViewById(R.id.semail);
        number = root.findViewById(R.id.snumber);
        pass = root.findViewById(R.id.spass);
        username = root.findViewById(R.id.susername);
        signup = root.findViewById(R.id.signupbutton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String email = SignupTapFragment.this.email.getText().toString();
                String number = SignupTapFragment.this.number.getText().toString();
                String username = SignupTapFragment.this.username.getText().toString();
                String pass = SignupTapFragment.this.pass.getText().toString();

                UserHelperClass userHelperClass = new UserHelperClass(email,number,username,pass);



                reference.child(username).setValue(userHelperClass);
            }
        });

        return root;

    }
}
