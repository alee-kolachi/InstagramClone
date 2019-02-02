package developer.android.com.instagramclone;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileFavoriteSports, edtProfileHobbies;
    private Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileFavoriteSports = view.findViewById(R.id.edtProfileFavoriteSports);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser.get("profileName") == null)
            edtProfileName.setText("");
        else
            edtProfileName.setText(parseUser.get("profileName").toString());
        if (parseUser.get("profileProfession") == null)
            edtProfileName.setText("");
        else
            edtProfileName.setText(parseUser.get("profileProfession").toString());
        if (parseUser.get("profileHobbies") == null)
            edtProfileName.setText("");
        else
            edtProfileName.setText(parseUser.get("profileHobbies").toString());
        if (parseUser.get("profileFavSports") == null)
            edtProfileName.setText("");
        else
            edtProfileName.setText(parseUser.get("profileFavSports").toString());
        if (parseUser.get("profileBio") == null)
            edtProfileName.setText("");
        else
            edtProfileName.setText(parseUser.get("profileBio").toString());


        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                parseUser.put("profileName", edtProfileName.getText() + "");
                parseUser.put("profileBio", edtProfileBio.getText() + "");
                parseUser.put("profileFavSport", edtProfileFavoriteSports.getText() + "");
                parseUser.put("profileHobbies", edtProfileHobbies.getText() + "");
                parseUser.put("profileProfession", edtProfileProfession.getText() + "");
                ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Updating Info");
                progressDialog.show();
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toasty.success(getContext(), "Info Updated", Toasty.LENGTH_SHORT).show();
                        } else {
                            Toasty.error(getContext(), e.getMessage(), Toasty.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        return view;
    }

}
