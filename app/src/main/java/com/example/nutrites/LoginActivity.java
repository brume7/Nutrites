package com.example.nutrites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TabLayout logtabLayout;
    ViewPager logviewPager;
    FloatingActionButton fb,google,twitter;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    float v=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        createRequest();

        logtabLayout = findViewById(R.id.logtabLayout);
        logviewPager = findViewById(R.id.logviewPager);
        fb = findViewById(R.id.fab_facebook);
        google = findViewById(R.id.fab_google);
        twitter = findViewById(R.id.fab_twitter);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        logtabLayout.addTab(logtabLayout.newTab().setText("Login"));
        logtabLayout.addTab(logtabLayout.newTab().setText("Signup"));
        logtabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this,logtabLayout.getTabCount());
        logviewPager.setAdapter(adapter);

        logviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(logtabLayout));
        logtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                logviewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fb.setTranslationY(300);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        logtabLayout.setTranslationY(300);

        fb.setVisibility(View.INVISIBLE);
        twitter.setVisibility(View.INVISIBLE);

        fb.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);
        logtabLayout.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        logtabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("408557606602-qnm7pvqm6djt387g8cop792oa3h55fsn.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = (GoogleSignInAccount) task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserHelperClass userHelperClass = new UserHelperClass(GoogleSignIn.getLastSignedInAccount(LoginActivity.this).getEmail(),"", GoogleSignIn.getLastSignedInAccount(LoginActivity.this).getDisplayName(),"1234" );
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userHelperClass);
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(LoginActivity.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();


                        }


                        // ...
                    }
                });
    }
}