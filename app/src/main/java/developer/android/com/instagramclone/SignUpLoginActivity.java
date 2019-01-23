package developer.android.com.instagramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import es.dmoral.toasty.Toasty;

public class SignUpLoginActivity extends AppCompatActivity {

    private Button btnLogin, btnSignUp;
    private EditText edtNameSignUp, edtPasswordSignUp, edtNameLogin, edtpasswordLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtNameSignUp = findViewById(R.id.edtNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtNameLogin = findViewById(R.id.edtNameLogin);
        edtpasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                            Toasty.success(SignUpLoginActivity.this, appUser.get("username") + " is signed up successfully", Toasty.LENGTH_LONG).show();
                        else
                            Toasty.error(SignUpLoginActivity.this, e.getMessage(), Toasty.LENGTH_LONG).show();
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtNameLogin.getText().toString(), edtpasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null)
                            Toasty.success(SignUpLoginActivity.this, user.get("username") + " is logged in successfully", Toasty.LENGTH_LONG).show();
                        else
                            Toasty.error(SignUpLoginActivity.this, e.getMessage(), Toasty.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
}
