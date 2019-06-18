package DAO;

import UMDB.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Lucas on 26-01-2014.
 */
public class MediaDAO {


        private ConnectionData conn;

        public MediaDAO() {
            this.conn = new ConnectionData();
        }


        public HashMap<Integer,Media> getMedias(){
            HashMap<Integer,Media> medias = new HashMap<Integer,Media>();
            String str = "select id, title, rating from movies";
            String str2 = "select count(movie_id) as ncomentarios from comments where movie_id=";
            conn.openConnection();
            ResultSet r = conn.executeQuery(str);
            try{
                while(r.next()){
                    int idaux = r.getInt("id");
                    ResultSet aux = conn.executeQuery(str2 + idaux);
                    aux.next();
                    medias.put(idaux,new Media(idaux,r.getString("title"),r.getFloat("rating"),aux.getInt("ncomentarios")));
                    }

            }catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conn.closeConnection();
            }
            return medias;
        }

    }
