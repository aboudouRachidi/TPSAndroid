package synthese.data.sqlite.rachidi.tpsandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RACHIDI on 25/11/2016.
 */

public class childBddCreate extends SQLiteOpenHelper {

    protected static final String LOG = "childBddCreate";

    // Database Version
    protected static final int DATABASE_VERSION = 1;

    // Database Name
    protected static final String DATABASE_NAME = "contactsManager";

    //TABLE_CHILD
    protected static final String TABLE_CHILD = "table_child";
    protected static final String COL_ID_CHILD = "Id";
    protected static final String COL_LIBELLE = "Libelle";
    protected static final String COL_URL = "Url";

    //TABLE ADMINISTRATEUR
    protected static final String TABLE_ADMINISTRATEUR = "table_administrateur";
    protected static final String COL_ID_ADMINISTRATEUR = "Id";
    protected static final String COL_LOGIN = "Login";
    protected static final String COL_MDP = "Mdp";

    //create table child
    private static final String CREATE_TABLE_CHILD = "CREATE TABLE " + TABLE_CHILD + " ("
            + COL_ID_CHILD + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LIBELLE + " TEXT NOT NULL, "
            + COL_URL + " TEXT NOT NULL);";

    //create table administrateur
    private static final String CREATE_TABLE_ADMINISTRATEUR = "CREATE TABLE " + TABLE_ADMINISTRATEUR + " ("
            + COL_ID_ADMINISTRATEUR + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LOGIN + " TEXT NOT NULL, "
            + COL_MDP + " TEXT NOT NULL);";

    public childBddCreate(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLE_CHILD);
        db.execSQL(CREATE_TABLE_ADMINISTRATEUR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_CHILD + ";");
        onCreate(db);
    }
}
