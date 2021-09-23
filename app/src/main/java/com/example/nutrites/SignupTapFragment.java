package com.example.nutrites;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                String email = SignupTapFragment.this.remail.getText().toString();
                String number = SignupTapFragment.this.rnumber.getText().toString();
                String username = SignupTapFragment.this.rusername.getText().toString();
                String pass = SignupTapFragment.this.rpass.getText().toString();
                String noWhitespace = "\\A\\w{4,20}\\z";

                if (email.isEmpty()) {
                    remail.setError("Field cannot be empty");

                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        remail.setError("Input Valid Email");

                    }else {
                        if (number.isEmpty()) {
                            rnumber.setError("Field cannot be empty");

                        } else {
                            if (!Patterns.PHONE.matcher(number).matches()){
                                rnumber.setError("Input Valid Number");

                            }else {

                            if (username.isEmpty()) {
                                rusername.setError("Field cannot be empty");

                            } else {

                                if (!username.matches(noWhitespace)) {
                                    rusername.setError("White Spaces are not allowed");

                                } else {

                                if (pass.isEmpty()) {
                                    rpass.setError("Field cannot be empty");

                                } else {

                                    UserHelperClass userHelperClass = new UserHelperClass(email, number, username, pass);


                                    reference.child(username).setValue(userHelperClass);
                                }
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
