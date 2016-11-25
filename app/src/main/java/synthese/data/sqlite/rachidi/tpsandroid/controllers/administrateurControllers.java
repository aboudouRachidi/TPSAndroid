package synthese.data.sqlite.rachidi.tpsandroid.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import synthese.data.sqlite.rachidi.tpsandroid.childBddCreate;
import synthese.data.sqlite.rachidi.tpsandroid.models.administrateurModels;

/**
 * Created by RACHIDI on 25/11/2016.
 */

public class administrateurControllers extends childBddCreate {


    public administrateurControllers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private SQLiteDatabase db;

    public void open(){
        //on ouvre la BDD en écriture
        db = this.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        db.close();
    }

    public SQLiteDatabase getBDD(){
        return db;
    }
    /*
     * Creating a admin
     */
    public long createAdmin(administrateurModels Admin) {
        //SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_LOGIN, Admin.getLogin());
        values.put(COL_MDP, Admin.getMdp());

        // insert row
        long admin_id = db.insert(TABLE_ADMINISTRATEUR, null, values);

        return admin_id;

    }
    /*
     * get single admin
     */
    public administrateurModels getAdmin(String Login) {
       // SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ADMINISTRATEUR +
        " WHERE " + COL_LOGIN + " = '" + Login +"'" ;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        administrateurModels admin = new administrateurModels();
        admin.setId(c.getInt(c.getColumnIndex(COL_ID_ADMINISTRATEUR)));
        admin.setLogin((c.getString(c.getColumnIndex(COL_LOGIN))));
        admin.setMdp(c.getString(c.getColumnIndex(COL_MDP)));

        return admin;
    }
}
