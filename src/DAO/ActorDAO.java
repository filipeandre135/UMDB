package DAO;

import UMDB.Actor;
import UMDB.Filme;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by filipeandre135 on 17-01-2014.
 */
public class ActorDAO {

    private ConnectionData conn;

    public ActorDAO() {
        this.conn = new ConnectionData();
    }


    public HashMap<Integer,Actor> getActores(){
        HashMap<Integer,Actor> Actores = new HashMap<Integer,Actor>();
        String str = "Select * from Actors";
        conn.openConnection();
        ResultSet r = conn.executeQuery(str);
        try{
            while(r.next()){
                Actores.put(r.getInt("id"),new Actor(r.getInt("id"),r.getString("name")));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn.closeConnection();
        }
        return Actores;
    }

    public HashMap<Integer,Actor> getActores(Filme f){
        HashMap<Integer,Actor> Actores = new HashMap<Integer,Actor>();
        String str = "Select * from Actors,actors_movies where actors_movies.movie_id ="+f.getId()+" and actors_movies.actor_id=Actors.id";
        conn.openConnection();
        ResultSet r = conn.executeQuery(str);
        try{
            while(r.next()){
                Actores.put(r.getInt("id"),new Actor(r.getInt("id"),r.getString("name")));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn.closeConnection();
        }
        return Actores;
    }

    public Actor addActor(Actor value) {
        conn.openConnection();
        String str = "INSERT INTO Actors (name) VALUES ('" + value.getNome() +"')";

        System.out.println(str);
        if (conn.executeUpdate(str) > 0 ) {

            try {
                value.setId(conn.getLastKey());
                conn.closeConnection();
                return value;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        conn.closeConnection();
        return null;
    }

    public void associaFilmeActor(Integer actorid,Integer filmeid)
    {
        conn.openConnection();
        String str = "INSERT INTO actors_movies (movie_id,actor_id) values ("+filmeid+","+actorid+")";
        conn.executeUpdate(str);
        conn.closeConnection();

    }


}
