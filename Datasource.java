package model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME="music.db";
   public static final String CONNECTION_STRING="jdbc:sqlite:D:\\Music\\"+ DB_NAME;

   public static final String TABLE_ALBUMS="albums";
   public static final String COLUMN_ALBUM_ID="id";
   public static final String COLUMN_ALBUM_NAME="name";
   public static final String COLUMN_ALBUM_ARTIST="artist";

    public static final String TABLE_ARTIST="artist";
    public static final String COLUMN_ARTIST_ID="id";
    public static final String COLUMN_ARTIST_NAME="name";

    public static final String TABLE_SONGS="songs";
    public static final String COLUMN_SONGS_ID="id";
    public static final String COLUMN_SONGS_TRACK="track";
    public static final String COLUMN_SONG_TITLE="title";
    public static final String COLUMN_SONG_ALBUM="album";

    private Connection conn;

    public boolean open() throws SQLException {
        try {
            conn= DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch (SQLException e){
            System.out.println("Couldn't connect to database "+e.getMessage());
            return false;
        }

    }

    public void close(){
        try {
            if(conn != null)
                conn.close();
        }catch (SQLException e){
            System.out.println(" couldn't close connection : " + e.getMessage());
        }
    }

    public List<Artists> queryArtists(){
        
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(" SELECT * FROM " + TABLE_ARTIST)){
            List<Artists> artists= new ArrayList<>();
            while (results.next()){
                Artists artist = new Artists();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        }catch (SQLException e ){
            System.out.println(" Query failed :" + e.getMessage());
            e.printStackTrace();

            return null;
        }
    }

    public void queryAlbumDElete (){

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(" DELETE FROM "+TABLE_ALBUMS+" WHERE name ="+COLUMN_ALBUM_NAME)){
        }catch (SQLException e ){
            System.out.println(" Query failed :" + e.getMessage());
            e.printStackTrace();

        }
    }

    public void queryArtistAlert (){

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(" ALTER TABLE "+ TABLE_ALBUMS +"ADD COLUMN Year VARCHAR(200);")){
        }catch (SQLException e ){
            System.out.println(" Query failed :" + e.getMessage());
            e.printStackTrace();

        }
    }


}
