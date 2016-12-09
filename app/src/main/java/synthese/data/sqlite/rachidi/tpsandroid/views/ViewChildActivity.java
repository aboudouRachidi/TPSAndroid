package synthese.data.sqlite.rachidi.tpsandroid.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rachidi.tpsandroid.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import data.sqlite.rachidi.tpsandroid.Livre;
import data.sqlite.rachidi.tpsandroid.LivresBDD;
import synthese.data.sqlite.rachidi.tpsandroid.controllers.*;
import synthese.data.sqlite.rachidi.tpsandroid.models.*;

/**
 * Created by RACHIDI on 25/11/2016.
 */

public class ViewChildActivity extends AppCompatActivity{

    // Spinner element
    Spinner spinner;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        //declaration des buttons
        final Button visiter = (Button) findViewById(R.id.btnVisiter);
        final Button rechercher = (Button) findViewById(R.id.btnSearchAdmin);
        spinner = (Spinner) findViewById(R.id.listUrl);
        //Création d'une instance de ma classe childBdd
        final childBddControllers childBdd = new childBddControllers(this);
        // Loading spinner data from database
        loadSpinnerData();

        visiter.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                childBdd.open();
                //on extrait l url de la BDD grâce au login l'on a créé précédemment
                childModels urlFromBdd = childBdd.getUrl(spinner.getSelectedItem().toString());
                //Si un admin est retourné (donc si l admin à bien été ajouté à la BDD)
                if (urlFromBdd != null && !Objects.equals(spinner.getSelectedItem().toString(), "")) {
                    //On affiche l url dans un Toast
                    Toast.makeText(getBaseContext(), urlFromBdd.toString(), Toast.LENGTH_LONG).show();
                    final String requete = "http://" + urlFromBdd.toString();
                    //ACTION_VIEW : action définie par le framework qui consiste à démarrer un navigateur web sur l’Uri donnée) :
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(requete));

                    startActivity(intent);
                }else {
                    Toast.makeText(getBaseContext(), "Veuillez choisir un site à visiter", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getBaseContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        // database handler
        childBddControllers db = new childBddControllers(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
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
