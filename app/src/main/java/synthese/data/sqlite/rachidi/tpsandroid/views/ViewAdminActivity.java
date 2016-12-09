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

import java.util.ArrayList;

import synthese.data.sqlite.rachidi.tpsandroid.controllers.administrateurControllers;
import synthese.data.sqlite.rachidi.tpsandroid.models.administrateurModels;
import synthese.data.sqlite.rachidi.tpsandroid.controllers.childBddControllers;
import synthese.data.sqlite.rachidi.tpsandroid.models.childModels;

/**
 * Created by RACHIDI on 09/12/2016.
 */

public class ViewAdminActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //declaration des buttons et editText de gestion admin
        final Button ajouterAdmin = (Button) findViewById(R.id.btnAddAdmin);
        final Button rechercherAdmin = (Button) findViewById(R.id.btnSearchAdmin);
        final Button afficherAdmins = (Button) findViewById(R.id.btnAfficherAdmin);
        final Button supprimerAdmin = (Button) findViewById(R.id.btnSupprimerAdmin);
        final EditText login = (EditText) findViewById(R.id.editTextLogin);
        final EditText mdp = (EditText) findViewById(R.id.editTextMdp);

        //declaration des buttons et editText de gestion url
        final Button ajouterUrl = (Button) findViewById(R.id.btnAddUrl);
        final Button rechercherUrl = (Button) findViewById(R.id.btnSearchUrl);
        final Button afficherUrls = (Button) findViewById(R.id.btnAfficherUrl);
        final Button supprimerUrl = (Button) findViewById(R.id.btnSupprimerUrl);
        final EditText titre = (EditText) findViewById(R.id.editTextTitreUrl);
        final EditText url = (EditText) findViewById(R.id.editTextUrl);

        //Création d'une instance de ma classe adminBDD
        administrateurControllers adminBdd = new administrateurControllers(this);

        //Création d'une instance de ma classe childBdd
        childBddControllers childBdd = new childBddControllers(this);

        //action du button afficher
        ajouterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(login.getText())) {
                    Toast.makeText(getBaseContext(), "Veuillez saisir le Login", Toast.LENGTH_SHORT).show();
                } else {
                    //affichage du message de type Toast
                    Toast.makeText(getBaseContext(), "Votre nom " + login.getText(), Toast.LENGTH_SHORT).show();

                    //Création d'une instance de ma classe adminBDD
                    administrateurControllers adminBdd = new administrateurControllers(ajouterAdmin.getContext());

                    //Création d'un livre
                    administrateurModels admin = new administrateurModels(login.getText().toString(), mdp.getText().toString());

                    //On ouvre la base de données pour écrire dedans
                    adminBdd.open();
                    //On insère l admin que l'on vient de créer
                    adminBdd.createAdmin(admin);

                }

            }
        });

        rechercherAdmin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {
                administrateurControllers adminBdd = new administrateurControllers(rechercherAdmin.getContext());
                adminBdd.open();


                //on extrait l admin de la BDD grâce au login l'on a créé précédemment
                administrateurModels AdminFromBdd = adminBdd.getAdmin(login.getText().toString());
                //Si un admin est retourné (donc si l admin à bien été ajouté à la BDD)
                if (AdminFromBdd != null) {
                    //On affiche les infos de l admin dans un Toast
                    Toast.makeText(getBaseContext(), AdminFromBdd.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        afficherAdmins.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                administrateurControllers adminBdd = new administrateurControllers(afficherAdmins.getContext());
                adminBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                ArrayList<administrateurModels> AdminFromBdd = (ArrayList<administrateurModels>) adminBdd.getAllAdmin();
                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if(AdminFromBdd != null) {
                    //On affiche les infos de l admin dans un Toast
                    Toast.makeText(getBaseContext(), AdminFromBdd.toString(), Toast.LENGTH_LONG).show();
                    showMessage("Admin",AdminFromBdd.toString().toString());
                }
            }
        });

        supprimerAdmin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                administrateurControllers adminBdd = new administrateurControllers(supprimerAdmin.getContext());
                adminBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                administrateurModels AdminFromBdd = adminBdd.deleteAdmin(login.getText().toString());

                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if (AdminFromBdd != null) {
                    //On affiche les infos du livre dans un Toast
                    Toast.makeText(getBaseContext(), AdminFromBdd.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


        /************************************************************************
         ************************************************************************
        *****************************Gestion url*********************************
         * ***********************************************************************
        **************************************************************************/

        //action du button ajouter
        ajouterUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(titre.getText())) {
                    Toast.makeText(getBaseContext(), "Veuillez saisir le Titre", Toast.LENGTH_SHORT).show();
                } else {
                    //affichage du message de type Toast
                    Toast.makeText(getBaseContext(), "Le titre de l'url " + titre.getText(), Toast.LENGTH_SHORT).show();

                    //Création d'une instance de ma classe adminBDD
                    childBddControllers childBdd = new childBddControllers(ajouterUrl.getContext());

                    //Création d'un url
                    childModels child = new childModels(titre.getText().toString(), url.getText().toString());

                    //On ouvre la base de données pour écrire dedans
                    childBdd.open();
                    //On insère l admin que l'on vient de créer
                    childBdd.createUrl(child);

                }

            }
        });

        rechercherUrl.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {
                childBddControllers childBdd = new childBddControllers(rechercherUrl.getContext());
                childBdd.open();


                //on extrait l url de la BDD grâce au titre l'on a créé précédemment
                childModels urlFromBdd = childBdd.getUrl(titre.getText().toString());
                //Si un admin est retourné (donc si l admin à bien été ajouté à la BDD)
                if (urlFromBdd != null) {
                    //On affiche les infos de l url dans un Toast
                    Toast.makeText(getBaseContext(), urlFromBdd.toString(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), "aucun url", Toast.LENGTH_LONG).show();
                }
            }
        });

        afficherUrls.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                administrateurControllers adminBdd = new administrateurControllers(afficherUrls.getContext());
                adminBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                ArrayList<administrateurModels> AdminFromBdd = (ArrayList<administrateurModels>) adminBdd.getAllAdmin();
                //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
                if(AdminFromBdd != null) {
                    //On affiche les infos de l admin dans un Toast
                    Toast.makeText(getBaseContext(), AdminFromBdd.toString(), Toast.LENGTH_LONG).show();
                    showMessage("Admin",AdminFromBdd.toString().toString());
                }
            }
        });

        supprimerUrl.setOnClickListener(new View.OnClickListener(){

            public void onClick(View V) {
                administrateurControllers adminBdd = new administrateurControllers(supprimerUrl.getContext());
                adminBdd.open();

                //Pour vérifier que l'on a bien créé notre livre dans la BDD
                //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
                administrateurModels AdminFromBdd = adminBdd.deleteAdmin(login.getText().toString());

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
