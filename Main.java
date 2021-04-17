import model.Artists;
import model.Datasource;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println(" can't open datasource ");
            return;
        }

        List<Artists> artists = datasource.queryArtists();
        if (artists==null){
            System.out.println(" No Artists!");
            return;
        }
        for (Artists artist:
             artists) {
            System.out.println("ID =  "+ artist.getId() +" , Name = "+artist.getName());
        }
        datasource.close();
    }
}
