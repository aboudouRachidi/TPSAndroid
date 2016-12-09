package synthese.data.sqlite.rachidi.tpsandroid.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import synthese.data.sqlite.rachidi.tpsandroid.Database.childBddCreate;
import synthese.data.sqlite.rachidi.tpsandroid.models.childModels;

/**
 * Created by RACHIDI on 25/11/2016.
 */

public class childBddControllers extends childBddCreate {

    public childBddControllers(Context context) {
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

        String selectQuery = "SELECT " + COL_URL + " FROM " + TABLE_CHILD +
                " WHERE " + COL_LIBELLE + " = '" + Libelle +"'" ;


        Cursor c = db.rawQuery(selectQuery, null);

        //if (c != null)
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();

        childModels url = new childModels();
        url.setUrl(c.getString(c.getColumnIndex(COL_URL)));
        c.close();
        return url;
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHILD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
}
