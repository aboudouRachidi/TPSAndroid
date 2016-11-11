package com.example.rachidi.tpsandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by RACHIDI on 09/11/2016.
 */
public class AppelGoogle extends AppCompatActivity {

    private Button mImageButton;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.google);

        mImageButton = (Button) findViewById(R.id.btnSearch);

        mEditText = (EditText)findViewById(R.id.editTextSearch);

        mImageButton.setOnClickListener(monEcouteur);

    }


    public View.OnClickListener monEcouteur = new View.OnClickListener() {
    public void onClick(View view) {

        if (view == mImageButton) {

            final String requete = "http://www.google.fr/search?q=" + mEditText.getText();

//ACTION_VIEW : action définie par le framework qui consiste à démarrer un navigateur web sur l’Uri donnée) :

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(requete));

            startActivity(intent);

        }
    }
    };


}
