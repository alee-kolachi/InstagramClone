package developer.android.com.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import es.dmoral.toasty.Toasty;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);


        btnSave.setOnClickListener(SignUp.this);
    }

    @Override
    public void onClick(View v) {
        try {
            final ParseObject boxer = new ParseObject("Boxer");
            boxer.put("name", edtName.getText().toString());
            boxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            boxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            boxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            boxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));

            boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null)
                        Toasty.success(SignUp.this, boxer.get("name") + " is saved to server", Toast.LENGTH_LONG).show();
                    else {
                        Toasty.error(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }catch(Exception e) {
            Toasty.warning(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
