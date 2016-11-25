package synthese.data.sqlite.rachidi.tpsandroid.models;

/**
 * Created by RACHIDI on 25/11/2016.
 */

public class childModels {
    int Id;
    String Libelle;
    String Url;

    // constructors
    public childModels() {
    }

    public childModels(String Libelle, String Url) {
        this.Libelle = Libelle;
        this.Url = Url;
    }

    public childModels(int Id, String Libelle, String Url) {
        this.Id = Id;
        this.Libelle = Libelle;
        this.Url = Url;
    }

    // setters
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }


    // getters
    public long getId() {
        return this.Id;
    }

    public String getLibelle() {
        return this.Libelle;
    }

    public String getUrl() {
        return this.Url;
    }

    public String toString(){
        return "Libelle : "+Libelle+"\nUrl : "+Url;
    }
}
