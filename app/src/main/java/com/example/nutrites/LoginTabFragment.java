package com.example.nutrites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    EditText email,pass;
    TextView forgotPass;
    Button login;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgotPass = root.findViewById(R.id.forgotPass);
        login = root.findViewById(R.id.loginbutton);

        email.setTranslationY(800);
        pass.setTranslationY(800);
        forgotPass.setTranslationY(800);
        login.setTranslationY(800);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotPass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;

    }
}
