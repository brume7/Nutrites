package com.example.nutrites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginTabFragment extends Fragment {

    EditText lusername,lpass;
    TextView forgotPass;
    Button login;
    float v=0;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        lusername = root.findViewById(R.id.username);
        lpass = root.findViewById(R.id.pass);
        forgotPass = root.findViewById(R.id.forgotPass);
        login = root.findViewById(R.id.loginbutton);

        lusername.setTranslationY(800);
        lpass.setTranslationY(800);
        forgotPass.setTranslationY(800);
        login.setTranslationY(800);

        lusername.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        lpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotPass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = LoginTabFragment.this.lusername.getText().toString().trim();
                String pass = LoginTabFragment.this.lpass.getText().toString().trim();
                String noWhitespace = "\\A\\w{4,20}\\z";

                if (username.isEmpty()) {
                    lusername.setError("Field cannot be empty");

                } else {
                    if (!username.matches(noWhitespace)) {
                        lusername.setError("White Spaces are not allowed");

                    } else {
                        if (pass.isEmpty()) {
                            lpass.setError("Field cannot be empty");

                        } else {

                            Query checkUser = reference.orderByChild("username").equalTo(username);

                            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        String passFromDB = snapshot.child(username).child("password").getValue(String.class);

                                        if(passFromDB.equals(pass))
                                        {
                                            String emailFromDB = snapshot.child(username).child("email").getValue(String.class);
                                            String usernameFromDB = snapshot.child(username).child("username").getValue(String.class);
                                            Intent intent = new Intent(getContext().getApplicationContext(),MainActivity.class);
                                            intent.putExtra("email", emailFromDB);
                                            intent.putExtra("username", usernameFromDB);

                                            startActivity(intent);
                                        }else {
                                            lusername.setError("wrong username or password");
                                            lpass.setError("wrong username or password");
                                        }


                                    }else{
                                        lusername.setError("wrong username or password");
                                        lpass.setError("wrong username or password");
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
        });









        return root;

    }
}
