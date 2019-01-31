package developer.android.com.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import es.dmoral.toasty.Toasty;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUp;
    private EditText edtEmail, edtUsername, edtPassword;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        btnSignUp = findViewById(R.id.btnSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });
        edtUsername = findViewById(R.id.edtUsername);
        tvLogin = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        if (ParseUser.getCurrentUser() != null)
            //ParseUser.getCurrentUser().logOut();
            goToSocialMediaActivity();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                if(edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("") || edtEmail.getText().toString().equals("")){
                    Toasty.warning(SignUp.this,"Email, Username, Password is required!!");
                }
                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up" + edtUsername.getText().toString());
                progressDialog.show();
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toasty.success(SignUp.this, appUser.getUsername() + " is signed up successfully", Toasty.LENGTH_LONG).show();
                            goToSocialMediaActivity();
                        } else {
                            Toasty.error(SignUp.this, e.getMessage(), Toasty.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
                break;
            case R.id.textView:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);

        }
    }
    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void goToSocialMediaActivity(){
        Intent intent = new Intent(SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
    }


}

