package com.codeelit.ts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private ImageView logo, ivSignIn, btnTwitter;
    private AutoCompleteTextView login_mail, login_password;
    private TextView forgotPass, txt_signup;
    private Button loginBtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;
    private Intent MainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        login_mail = findViewById(R.id.login_mail);
        login_password = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.loginBtn);
        initializeGUI();

        user = firebaseAuth.getCurrentUser();

        if(user != null) {
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inEmail = login_mail.getText().toString();
                String inPassword = login_password.getText().toString();

                if(validateInput(inEmail, inPassword)){
                    signUser(inEmail, inPassword);
                }

            }
        });


        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,PWresetActivity.class));
            }
        });
    }


    public void signUser(String email, String password){
        progressDialog.setMessage("Verificating...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    goToMainActivity();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Invalid email or password",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            //if user is already connected we should redirect him to home page
            updateUI();
        }
    }

    private void updateUI() {
        startActivity(MainActivity);
        finish();
    }


    private void initializeGUI(){

        logo = findViewById(R.id.ivLogLogo);
        ivSignIn = findViewById(R.id.ivSignIn);
        login_mail = findViewById(R.id.login_mail);
        login_password = findViewById(R.id.login_password);
        forgotPass = findViewById(R.id.tvForgotPass);
        txt_signup = findViewById(R.id.txt_signup);
        loginBtn = findViewById(R.id.loginBtn);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }


    public boolean validateInput(String inemail, String inpassword){

        if(inemail.isEmpty()){
            login_mail.setError("Email field is empty.");
            return false;
        }
        if(inpassword.isEmpty()){
            login_password.setError("Password is empty.");
            return false;
        }

        return true;
    }

}
