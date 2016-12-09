package synthese.data.sqlite.rachidi.tpsandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rachidi.tpsandroid.MainActivity;
import com.example.rachidi.tpsandroid.R;

import java.util.ArrayList;
import java.util.Objects;

import synthese.data.sqlite.rachidi.tpsandroid.controllers.administrateurControllers;
import synthese.data.sqlite.rachidi.tpsandroid.models.administrateurModels;

/**
 * Created by RACHIDI on 09/12/2016.
 */

public class ViewLoginActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //declaration des buttons et  textView

        final EditText login = (EditText) findViewById(R.id.editTextLogin);
        final EditText mdp = (EditText) findViewById(R.id.editTextMdp);
        final Button connexion = (Button) findViewById(R.id.btnConnexion);
        final Button connexionEnfant = (Button) findViewById(R.id.btnConnexionEnfant);
        //Création d'une instance de ma classe LivresBDD
        administrateurControllers adminBdd = new administrateurControllers(this);



        connexion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {
                administrateurControllers adminBdd = new administrateurControllers(connexion.getContext());
                adminBdd.open();


                //on extrait l admin de la BDD grâce au login l'on a créé précédemment
                administrateurModels AdminFromBdd = adminBdd.connectAdmin(login.getText().toString());
                //Si un admin est retourné (donc si l admin à bien été ajouté à la BDD)
                if (AdminFromBdd != null && Objects.equals(mdp.getText().toString(), "Admin") ) {
                    //On affiche les infos de l admin dans un Toast
                    Toast.makeText(getBaseContext(), AdminFromBdd.toString(), Toast.LENGTH_LONG).show();
                    Intent AdminActivity = new Intent(ViewLoginActivity.this, ViewAdminActivity.class);

                    startActivity(AdminActivity);
                }else{
                    Toast.makeText(getBaseContext(), "mot de passe incorrect", Toast.LENGTH_LONG).show();
                }
            }
        });


        connexionEnfant.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {

                    //On affiche les infos de l admin dans un Toast
                    Toast.makeText(getBaseContext(), "Bienvenue", Toast.LENGTH_LONG).show();
                    Intent ChildActivity = new Intent(ViewLoginActivity.this, ViewChildActivity.class);

                    startActivity(ChildActivity);

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
