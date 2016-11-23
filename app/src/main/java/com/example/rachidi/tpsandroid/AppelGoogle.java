package com.example.rachidi.tpsandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import tp.synthese.rachidi.tpsandroid.Convertisseur;

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

    /*********************************************************************
     *********************************************************************
     **********************CREATION D'UN MENU*****************************
     *********************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView message = (TextView) findViewById(R.id.textViewMessage);
        switch (item.getItemId()) {

            case R.id.action_accueil:

                Toast.makeText(getBaseContext(),"Vous avez choisi l' "+item.getTitle(), Toast.LENGTH_LONG).show();

                Intent accueil = new Intent(this, MainActivity.class);

                startActivity(accueil);

                return true;

            case R.id.action_google:

                Toast.makeText(getBaseContext(),"Vous avez choisi le "+item.getTitle(), Toast.LENGTH_LONG).show();

                Intent google = new Intent(this, AppelGoogle.class);

                startActivity(google);

                return true;

            case R.id.action_tp4:

                Toast.makeText(getBaseContext(),"Vous avez choisi le "+item.getTitle(), Toast.LENGTH_LONG).show();

                Intent sqlite = new Intent(this, AppelSqlite.class);

                startActivity(sqlite);
                return true;

            case R.id.action_tpSynthese:

                Toast.makeText(getBaseContext(),"Vous avez choisi le "+item.getTitle(), Toast.LENGTH_LONG).show();

                Intent tpSyntheseConvertisseur = new Intent(this, Convertisseur.class);

                startActivity(tpSyntheseConvertisseur);
                return true;

            case R.id.action_help:

                Toast.makeText(getBaseContext(),"Cet interface vous permet de faire des recherche sur google !", Toast.LENGTH_LONG).show();

                return true;

            case R.id.action_quitter:

                Toast.makeText(getBaseContext(),"A bientôt !", Toast.LENGTH_LONG).show();
                System.exit(0);
                return true;

            case R.id.action_settings:

                Toast.makeText(getBaseContext(),"Vous avez choisi l'item "+item.getTitle(), Toast.LENGTH_LONG).show();

                return true;

        }
        return false;
    }
}
