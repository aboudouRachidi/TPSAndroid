package synthese.data.sqlite.rachidi.tpsandroid.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import synthese.data.sqlite.rachidi.tpsandroid.Database.childBddCreate;
import synthese.data.sqlite.rachidi.tpsandroid.models.administrateurModels;
import synthese.data.sqlite.rachidi.tpsandroid.models.childModels;

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
        //long admin_id = db.insert(TABLE_ADMINISTRATEUR, null, values);

        return db.insert(TABLE_ADMINISTRATEUR, null, values);//admin_id;

    }
    /*
     * get single admin
     */
    public administrateurModels getAdmin(String Login) {
       // SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ADMINISTRATEUR +
        " WHERE " + COL_LOGIN + " = '" + Login +"'" ;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        //if (c != null)
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
            c.moveToFirst();

        administrateurModels admin = new administrateurModels();
        admin.setId(c.getInt(c.getColumnIndex(COL_ID_ADMINISTRATEUR)));
        admin.setLogin((c.getString(c.getColumnIndex(COL_LOGIN))));
        //admin.setMdp(c.getString(c.getColumnIndex(COL_MDP)));
        c.close();
        return admin;
    }

    /*
 * Deleting a Admin
 */
    public administrateurModels deleteAdmin(String admin_id) {
        //SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADMINISTRATEUR, COL_LOGIN + " = ?",
                new String[] { String.valueOf(admin_id) });
        return null;
    }
    /*
     * getting all Admin
     * */
    public List<administrateurModels> getAllAdmin() {
        List<administrateurModels> Admins = new ArrayList<administrateurModels>();
        String selectQuery = "SELECT  * FROM " + TABLE_ADMINISTRATEUR;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                administrateurModels td = new administrateurModels();
                td.setId(c.getInt((c.getColumnIndex(COL_ID_ADMINISTRATEUR))));
                td.setLogin((c.getString(c.getColumnIndex(COL_LOGIN))));
                //td.setMdp((c.getString(c.getColumnIndex(COL_MDP))));

                // adding to admin_list
                Admins.add(td);
            } while (c.moveToNext());
        }
        c.close();
        return Admins;
    }
    /*
 * Connect admin
 */
    public administrateurModels connectAdmin(String Login/*, String Mdp*/) {
        // SQLiteDatabase db = this.getReadableDatabase();

        /*String selectQuery = "SELECT" + COL_LOGIN +","+ COL_MDP + "FROM " + TABLE_ADMINISTRATEUR +
                " WHERE " + COL_LOGIN + " = '" + Login +"'" +
                " AND " + COL_MDP + " = '" + Mdp +"'"
                 ;*/
        String selectQuery = "SELECT  * FROM " + TABLE_ADMINISTRATEUR +
                " WHERE " + COL_LOGIN + " = '" + Login +"'" ;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        //if (c != null)
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();

        administrateurModels admin = new administrateurModels();
        admin.setId(c.getInt(c.getColumnIndex(COL_ID_ADMINISTRATEUR)));
        admin.setLogin((c.getString(c.getColumnIndex(COL_LOGIN))));
        //admin.setMdp(c.getString(c.getColumnIndex(COL_MDP)));
        c.close();
        return admin;
    }

    public long createUrl(childModels Child) {
        //SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_LIBELLE, Child.getLibelle());
        values.put(COL_URL, Child.getUrl());

        // insert row

        return db.insert(TABLE_CHILD, null, values);

    }

    /*
    * get single admin
    */
    public childModels getUrl(String Libelle) {

        String selectQuery = "SELECT  * FROM " + TABLE_CHILD +
                " WHERE " + COL_LIBELLE + " = '" + Libelle +"'" ;


        Cursor c = db.rawQuery(selectQuery, null);

        //if (c != null)
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();

        childModels url = new childModels();
        url.setId(c.getInt(c.getColumnIndex(COL_ID_CHILD)));
        url.setLibelle((c.getString(c.getColumnIndex(COL_LIBELLE))));
        url.setUrl(c.getString(c.getColumnIndex(COL_URL)));
        c.close();
        return url;
    }
}
