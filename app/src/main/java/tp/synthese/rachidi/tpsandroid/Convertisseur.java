package tp.synthese.rachidi.tpsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rachidi.tpsandroid.AppelGoogle;
import com.example.rachidi.tpsandroid.AppelSqlite;
import com.example.rachidi.tpsandroid.MainActivity;
import com.example.rachidi.tpsandroid.R;

/**
 * Created by RACHIDI on 21/11/2016.
 */

public class Convertisseur extends AppCompatActivity {

    // Bouton de conversion
    Button btnConvertir;
    // EditText de saisi de montant
    EditText editTextMontant;
    //radio button pour choisir la devise de la conversion
    RadioButton radioDollar;
    RadioButton radioEuro;
    // Texte du résultat
    TextView textViewResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertisseur);


        // On instancie nos éléments avec leur id de la vue
        editTextMontant = (EditText) findViewById(R.id.editTextMontant);
        radioEuro = (RadioButton) findViewById(R.id.radioEuro);
        radioDollar = (RadioButton) findViewById(R.id.radioDollar);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        btnConvertir = (Button) findViewById(R.id.btnConvertir);



        radioDollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioEuro.setChecked(false);
            }
        });
        radioEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioDollar.setChecked(false);
            }
        });
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(editTextMontant.getText().toString().isEmpty())
                {
					/* Si le contenu est vide alors on affiche un toast qui
					 * nous demande de remplir le champ
					 */
                    Toast.makeText(getBaseContext(), "Veuillez remplir un montant", Toast.LENGTH_SHORT).show();
                }
                else
                    if(radioDollar.isChecked() == true || radioEuro.isChecked() == true) {
                        // On éxécute l'algorithme de conversion
                        conversion();
                    }else {
                        Toast.makeText(getBaseContext(), "Veuillez cocher la devise de la conversion", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
    /*********************************************************************
     *********************************************************************
     ********************Algorithme de conversion**************************
     *********************************************************************/

    public void conversion()
    {
        // Multiplicateur(Taux de change) pour conversion en euro
        double dollar = 1.06240;
        // Multiplicateur(Taux de change) pour conversion en dollar
        double euro = 0.94126;
        // Montant de la l'EditText avec conversion en type double
        double montant = Double.parseDouble(editTextMontant.getText().toString());
        // Résultat de la conversion
        double result;

        // Si le bouton radio euro est activé alors ...
        if(radioEuro.isChecked())
        {
            // Résultat égale montant * par la valeur en euro
            result = montant * euro;
            // Changement du text de la textView pour affiche le résultat
            textViewResult.setText(result + "" + " €");
        }
        // Si c'est le bouton dollar qui est activé
        else
        {
            // Résultat égale montant * par la valeur en dollar
            result = montant * dollar;
            // Changement du text de la textView pour affiche le résultat
            textViewResult.setText(result + "" + " $");
        }
    }
    /*********************************************************************
     *********************************************************************
     **********************CREATION D'UN ALERTDIALOG*****************************
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

                Toast.makeText(getBaseContext(),"Cet interface vous permet de convertir en Euro <-> Dollar !", Toast.LENGTH_LONG).show();

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
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
    }
}
