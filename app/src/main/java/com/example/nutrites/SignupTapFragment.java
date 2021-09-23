package com.example.nutrites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignupTapFragment extends Fragment {

    EditText remail, rpass, rnumber, rusername;
    Button signup;
    float v=0;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tap_fragment, container, false);

        remail = root.findViewById(R.id.semail);
        rnumber = root.findViewById(R.id.snumber);
        rpass = root.findViewById(R.id.spass);
        rusername = root.findViewById(R.id.susername);
        signup = root.findViewById(R.id.signupbutton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String email = SignupTapFragment.this.remail.getText().toString().trim();
                String number = SignupTapFragment.this.rnumber.getText().toString().trim();
                String username = SignupTapFragment.this.rusername.getText().toString().trim();
                String pass = SignupTapFragment.this.rpass.getText().toString();
                String noWhitespace = "\\A\\w{4,20}\\z";

                if (email.isEmpty()) {
                    remail.setError("Field cannot be empty");
                    remail.requestFocus();
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        remail.setError("Input Valid Email");
                        remail.requestFocus();

                    }else {
                        if (number.isEmpty()) {
                            rnumber.setError("Field cannot be empty");
                            rnumber.requestFocus();

                        } else {
                            if (!Patterns.PHONE.matcher(number).matches()){
                                rnumber.setError("Input Valid Number");
                                rnumber.requestFocus();

                            }else {

                            if (username.isEmpty()) {
                                rusername.setError("Field cannot be empty");
                                rusername.requestFocus();

                            } else {

                                if (!username.matches(noWhitespace)) {
                                    rusername.setError("White Spaces are not allowed");
                                    rusername.requestFocus();

                                } else {

                                    Query checkUser = reference.orderByChild("username").equalTo(username);

                                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {

                                                rusername.setError("User name already in use");
                                                rusername.requestFocus();

                                            }else if (pass.isEmpty()) {
                                                rpass.setError("Field cannot be empty");
                                                rpass.requestFocus();

                                            } else {

                                                UserHelperClass userHelperClass = new UserHelperClass(email, number, username, pass);


                                                reference.child(username).setValue(userHelperClass);
                                                Intent intent = new Intent(getContext().getApplicationContext(),ConfigureUser.class);
                                                startActivity(intent);

                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                            }


                            }
                        }
                        }
                    }
            }
            }
        });

        return root;

    }
}
