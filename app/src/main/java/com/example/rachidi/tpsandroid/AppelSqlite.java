package com.example.rachidi.tpsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import data.sqlite.rachidi.tpsandroid.Livre;
import data.sqlite.rachidi.tpsandroid.LivresBDD;
import tp.synthese.rachidi.tpsandroid.Convertisseur;

/**
 * Created by RACHIDI on 09/11/2016.
 */

public class AppelSqlite extends AppCompatActivity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
        //declaration des buttons et un textView message
        final Button ajouter = (Button) findViewById(R.id.btnAddLivre);
        final Button rechercher = (Button) findViewById(R.id.btnSearchLivre);
        final Button modifier = (Button) findViewById(R.id.btnUpdateLivre);
        final Button afficher = (Button) findViewById(R.id.btnAfficherLivre);
        final EditText titreLivre = (EditText) findViewById(R.id.editTextTitreLivre);
        final EditText nouveauTitreLivre = (EditText) findViewById(R.id.editTextNouveauTitre);
        final EditText nIsbn = (EditText) findViewById(R.id.editTextNumeroIsbn);
        //Création d'une instance de ma classe LivresBDD
        LivresBDD livreBdd = new LivresBDD(this);
        //action du button afficher
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(titreLivre.getText())){
                    Toast.makeText(getBaseContext(), "Veuillez saisir le nom du livre", Toast.LENGTH_SHORT).show();
                }else {
                    //affichage du message de type Toast
                    Toast.makeText(getBaseContext(), "Votre nom "+titreLivre.getText(), Toast.LENGTH_SHORT).show();

                    //Création d'une instance de ma classe LivresBDD
                    LivresBDD livreBdd = new LivresBDD(ajouter.getContext());

                    //Création d'un livre
                    Livre livre = new Livre(nIsbn.getText().toString(), titreLivre.getText().toString());

                    //On ouvre la base de données pour écrire dedans
                    livreBdd.open();
                    //On insère le livre que l'on vient de créer
                    livreBdd.insertLivre(livre);

                }

            }
        });

        rechercher.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                LivresBDD livreBdd = new LivresBDD(rechercher.getContext());
                livreBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                Livre livreFromBdd = livreBdd.getLivreWithTitre(titreLivre.getText().toString());
                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if(livreFromBdd != null) {
                    //On affiche les infos du livre dans un Toast
                    Toast.makeText(getBaseContext(), livreFromBdd.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        modifier.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                LivresBDD livreBdd = new LivresBDD(modifier.getContext());
                livreBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                Livre livreFromBdd = livreBdd.getLivreWithTitre(titreLivre.getText().toString());
                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if(livreFromBdd != null) {
                    //On affiche les infos du livre dans un Toast
                    Toast.makeText(getBaseContext(), livreFromBdd.toString(), Toast.LENGTH_LONG).show();
                    if(nouveauTitreLivre.getText() != null) {
                        //On modifie le titre du livre
                        livreFromBdd.setTitre(nouveauTitreLivre.getText().toString());
                        //Puis on met à jour la BDD
                        livreBdd.updateLivre(livreFromBdd.getId(), livreFromBdd);
                    }

                }
            }
        });

        afficher.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                LivresBDD livreBdd = new LivresBDD(afficher.getContext());
                livreBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                ArrayList<Livre> livreFromBdd = livreBdd.getAllLivres();
                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if(livreFromBdd != null) {
                    //On affiche les infos du livre dans un Toast
                    Toast.makeText(getBaseContext(), livreFromBdd.toString(), Toast.LENGTH_LONG).show();
                    showMessage("Livres",livreFromBdd.toString().toString());
                }
            }
        });

/*
        //Pour vérifier que l'on a bien créé notre livre dans la BDD
        //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
        Livre livreFromBdd = livreBdd.getLivreWithTitre(titreLivre.getText().toString());
        //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
        if(livreFromBdd != null){
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, livreFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du livre
            livreFromBdd.setTitre("J'ai modifié le titre du livre");
            //Puis on met à jour la BDD
            livreBdd.updateLivre(livreFromBdd.getId(), livreFromBdd);
        }

        //On extrait le livre de la BDD grâce au nouveau titre
        livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifié le titre du livre");
        //S'il existe un livre possédant ce titre dans la BDD
        if(livreFromBdd != null){
            //On affiche les nouvelles informations du livre pour vérifier que le titre du livre a bien été mis à jour
            Toast.makeText(this, livreFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le livre de la BDD grâce à son ID
            livreBdd.removeLivreWithID(livreFromBdd.getId());
        }

        //On essaye d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
        livreFromBdd = livreBdd.getLivreWithTitre("J'ai modifié le titre du livre");
        //Si aucun livre n'est retourné
        if(livreFromBdd == null){
            //On affiche un message indiquant que le livre n'existe pas dans la BDD
            Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le livre existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le livre existe dans la BDD
            Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        livreBdd.close();*/
    }

    /*********************************************************************
     *********************************************************************
     ********************CREATION D'UN ALERTDIALOG************************
     *********************************************************************/
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
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

                Toast.makeText(getBaseContext(),"Cet interface vous permet de découvrir SQLITE !", Toast.LENGTH_LONG).show();

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
