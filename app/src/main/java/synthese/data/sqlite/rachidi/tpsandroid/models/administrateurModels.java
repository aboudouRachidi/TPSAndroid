package synthese.data.sqlite.rachidi.tpsandroid.models;

/**
 * Created by RACHIDI on 25/11/2016.
 */

public class administrateurModels {
    int Id;
    String Login;
    String Mdp;

    // constructors
    public administrateurModels() {
    }

    public administrateurModels(String Login, String Mdp) {
        this.Login = Login;
        this.Mdp = Mdp;
    }

    public administrateurModels(int Id, String Login, String Mdp) {
        this.Id = Id;
        this.Login = Login;
        this.Mdp = Mdp;
    }

    // setters
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }


    // getters
    public long getId() {
        return this.Id;
    }

    public String getLogin() {
        return this.Login;
    }

    public String getMdp() {
        return this.Mdp;
    }

    public String toString(){
        return "Admin  : "+Login+"mdp  : "+Mdp;
    }
}
