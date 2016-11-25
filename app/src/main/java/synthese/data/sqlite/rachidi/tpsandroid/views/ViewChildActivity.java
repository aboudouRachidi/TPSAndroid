package synthese.data.sqlite.rachidi.tpsandroid.views;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rachidi.tpsandroid.R;

import synthese.data.sqlite.rachidi.tpsandroid.controllers.*;
import synthese.data.sqlite.rachidi.tpsandroid.models.*;

/**
 * Created by RACHIDI on 25/11/2016.
 */

public class ViewChildActivity extends AppCompatActivity{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        //declaration des buttons et un textView message
        final Button ajouter = (Button) findViewById(R.id.btnAddAdmin);
        final Button rechercher = (Button) findViewById(R.id.btnSearchAdmin);
        final EditText login = (EditText) findViewById(R.id.editTextLogin);
        final EditText mdp = (EditText) findViewById(R.id.editTextMdp);

        //Création d'une instance de ma classe LivresBDD
        administrateurControllers adminBdd = new administrateurControllers(this);

        //action du button afficher
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(login.getText())) {
                    Toast.makeText(getBaseContext(), "Veuillez saisir le Login", Toast.LENGTH_SHORT).show();
                } else {
                    //affichage du message de type Toast
                    Toast.makeText(getBaseContext(), "Votre nom " + login.getText(), Toast.LENGTH_SHORT).show();

                    //Création d'une instance de ma classe LivresBDD
                    administrateurControllers adminBdd = new administrateurControllers(ajouter.getContext());

                    //Création d'un livre
                    administrateurModels admin = new administrateurModels(login.getText().toString(), mdp.getText().toString());

                    //On ouvre la base de données pour écrire dedans
                    adminBdd.open();
                    //On insère le livre que l'on vient de créer
                    adminBdd.createAdmin(admin);

                }

            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {
                administrateurControllers adminBdd = new administrateurControllers(rechercher.getContext());
                adminBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                administrateurModels AdminFromBdd = adminBdd.getAdmin(login.getText().toString());
                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if (AdminFromBdd != null) {
                    //On affiche les infos du livre dans un Toast
                    Toast.makeText(getBaseContext(), AdminFromBdd.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
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
}
