package ma.fstt.model;


// java bean
public class Commande {
    private Long Date_De ;

    private String Etat ;
    private String Nom_Client;

    public Commande() {
    }

    public Commande(Long date_De, String etat, String nom_Client) {
        Date_De = date_De;
        Etat = etat;
        Nom_Client = nom_Client;
    }

    public Long getDate_De() {
        return Date_De;
    }

    public void setDate_De(Long date_De) {
        Date_De = date_De;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

    public String getNom_Client() {
        return Nom_Client;
    }

    public void setNom_Client(String nom_Client) {
        Nom_Client = nom_Client;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "Date_De=" + Date_De +
                ", Etat='" + Etat + '\'' +
                ", Nom_Client='" + Nom_Client + '\'' +
                '}';
    }
}
