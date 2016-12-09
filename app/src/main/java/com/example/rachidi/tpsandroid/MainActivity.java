package com.example.rachidi.tpsandroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import synthese.data.sqlite.rachidi.tpsandroid.views.ViewChildActivity;
import synthese.data.sqlite.rachidi.tpsandroid.views.ViewLoginActivity;
import tp.synthese.rachidi.tpsandroid.Convertisseur;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //declaration des buttons et un textView message
        Button valider = (Button) findViewById(R.id.btnValider);
        Button annuler = (Button) findViewById(R.id.btnAnnuler);
        Button quitter = (Button) findViewById(R.id.btnQuitter);
        final EditText nom = (EditText) findViewById(R.id.editTextNom);
        final Button lireMusique = (Button) findViewById(R.id.btnLire);
        final Button pauseMusique = (Button) findViewById(R.id.btnPause);
        final Button stopMusique = (Button) findViewById(R.id.btnStop);
        final MediaPlayer musique = MediaPlayer.create(this,R.raw.lettreaelisebeethoven);
        final TextView message = (TextView) findViewById(R.id.textViewMessage);
        final ImageView photoBeethoven = (ImageView) findViewById(R.id.imageViewBeethoven);


        //action du button afficher
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nom.getText())){
                    Toast.makeText(getBaseContext(), "Veuillez saisir votre nom", Toast.LENGTH_SHORT).show();
                }else {
                    //affichage du message de type Toast
                    Toast.makeText(getBaseContext(), "Votre nom "+nom.getText(), Toast.LENGTH_SHORT).show();
                    lireMusique.setVisibility(lireMusique.VISIBLE);

                }

            }
        });

        lireMusique.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                try {
                    musique.start();
                    message.setText(nom.getText()+", Vous ecoutez : Beethoven - Lettre à Elise !");
                    pauseMusique.setVisibility(pauseMusique.VISIBLE);
                    stopMusique.setVisibility(stopMusique.VISIBLE);
                    photoBeethoven.setVisibility(photoBeethoven.VISIBLE);
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }
        });
        pauseMusique.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                musique.pause();
                message.setText(nom.getText()+", Vous avez mis la musique sur pause !");
            }
        });
        stopMusique.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                musique.stop();
                message.setText(nom.getText()+", Vous avez arrêter la musique !");
                pauseMusique.setVisibility(pauseMusique.INVISIBLE);
                stopMusique.setVisibility(stopMusique.INVISIBLE);
                photoBeethoven.setVisibility(photoBeethoven.INVISIBLE);
            }
        });
        //action du button quitter (quitter l'application)
        quitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                System.exit(0);
            }
        });
    }


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

                Intent google = new Intent(MainActivity.this, AppelGoogle.class);

                startActivity(google);

                return true;

            case R.id.action_tp4:

                Toast.makeText(getBaseContext(),"Vous avez choisi le "+item.getTitle(), Toast.LENGTH_LONG).show();

                Intent sqlite = new Intent(MainActivity.this, AppelSqlite.class);

                startActivity(sqlite);
                return true;

            case R.id.action_tpSynthese:

                Toast.makeText(getBaseContext(),"Vous avez choisi le "+item.getTitle(), Toast.LENGTH_LONG).show();

                Intent tpSyntheseConvertisseur = new Intent(MainActivity.this, Convertisseur.class);

                startActivity(tpSyntheseConvertisseur);
                return true;

            case R.id.action_tpSynthese_sqlite:

                Toast.makeText(getBaseContext(),"Vous avez choisi le "+item.getTitle(), Toast.LENGTH_LONG).show();

                Intent tpSyntheseSqlite = new Intent(MainActivity.this, ViewLoginActivity.class);

                startActivity(tpSyntheseSqlite);
                return true;

            case R.id.action_help:

                message.setText("Vous devez saisir votre nom pour ensuite ecouter une musique !");

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
