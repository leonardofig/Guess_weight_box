import java.util.List;
import java.util.Formatter;

public class Listener {
    Formatter fmt = new Formatter();
    private int id;
    private String name;
    private String locality;
    private int phone;
    private int plays;
    private int victories;
    private int ranking;

    private List<Listener> listenerList;

    public Listener(int id, String name, String locality, int phone, int plays, int victories, int ranking) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.phone = phone;
        this.plays = plays;
        this.victories = victories;
        this.ranking = ranking;
    }

    // No Arg_constructor
    public Listener(int id, String name, String locality, int phone) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.phone = phone;
        plays = 0;
        victories = 0;
        ranking = 0;
    }

    public Listener() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Listener) obj).getId();
    }

    @Override
    public String toString() {
        return writeHeaderData() + writeTableData();
    }

    public String writeHeaderData() {
        fmt = new Formatter();
        return String.valueOf(fmt.format("| %-5s | %-15s | %-15s | %15s | %10s | %10s | %10s | %95s", "Id", "Name", "Locality", "Phone", "Plays", "Victories", "Ranking",
                "\n======================================================================================================"));
    }

    public String writeTableData() {
        fmt = new Formatter();
        return String.valueOf(fmt.format("| %-5s | %-15s | %-15s | %,15d | %10s | %10s | %10s |", id, name, locality, phone, plays, victories, ranking));
    }

    public String writeHeaderDataRanking() {
        fmt = new Formatter();
        return String.valueOf(fmt.format("| %-5s | %-5s | %-15s | %-15s | %10s | %10s | %95s", "Rank", "Id", "Name", "Locality", "Plays", "Victories",
                "\n==============================================================================="));
    }

    public String writeTableDataRanking() {
        fmt = new Formatter();
        return String.valueOf(fmt.format("| %-5s | %-5s | %-15s | %-15s | %10s | %10s | ", ranking, id, name, locality, plays, victories));
    }
}
