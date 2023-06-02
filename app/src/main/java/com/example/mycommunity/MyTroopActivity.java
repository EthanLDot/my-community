package com.example.mycommunity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MyTroopActivity extends AppCompatActivity {
    Button closeButton;
    AlertDialog.Builder builder;

    EditText name, email, address, description;

    ImageButton image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_troop);
        View view = getLayoutInflater().inflate(R.layout.edit, null);

        image = view.findViewById(R.id.tImage);

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });

        closeButton = (Button) findViewById(R.id.edit);
        builder = new AlertDialog.Builder(this);

        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                builder.setTitle("Update your troop!");

                View view = getLayoutInflater().inflate(R.layout.edit, null);
                builder.setView(view);

                name = view.findViewById(R.id.tName);
                email = view.findViewById(R.id.tEmail);
                address = view.findViewById(R.id.tAddress);
                description = view.findViewById(R.id.tDescription);

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Map<String, Object> map = new HashMap<>();

                        map.put("Troop Name", name.getText().toString());
                        map.put("Troop Email", email.getText().toString());
                        map.put("Troop Address", address.getText().toString());
                        map.put("Troop Description", description.getText().toString());

                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Troop Details");
                        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MyTroopActivity.this);

                        if(acct!=null){
                            String email = acct.getEmail();
                            assert email != null;
                            db.child(email.replaceAll("[.#$]" , ",")).setValue(map);
                        }
                        else{
                            Toast.makeText(MyTroopActivity.this, "Your email is not valid", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel(); // closes dialog
                    }
                });

                builder.show();
            }
        });


    }
}