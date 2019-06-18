package DAO;

import UMDB.Filme;
import UMDB.Realizador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by filipeandre135 on 17-01-2014.
 */
public class RealizadorDAO {

    private ConnectionData conn;

    public RealizadorDAO() {
        this.conn = new ConnectionData();
    }


    public HashMap<Integer,Realizador> getRealizadores(){
        HashMap<Integer,Realizador> realizadores = new HashMap<Integer,Realizador>();
        String str = "Select * from directors";
        conn.openConnection();
        ResultSet r = conn.executeQuery(str);
        try{
            while(r.next()){
                realizadores.put(r.getInt("id"),new Realizador(r.getInt("id"),r.getString("name")));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn.closeConnection();
        }
        return realizadores;
    }

    public HashMap<Integer,Realizador> getRealizadores(Filme f){
        HashMap<Integer,Realizador> realizadores = new HashMap<Integer,Realizador>();
        String str = "Select * from directors,directors_movies where directors_movies.movie_id ="+f.getId()+" and directors_movies.director_id=directors.id";
        conn.openConnection();
        ResultSet r = conn.executeQuery(str);
        if(r==null)return realizadores;
        try{
            while(r.next()){
                realizadores.put(r.getInt("id"),new Realizador(r.getInt("id"),r.getString("name")));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn.closeConnection();
        }
        return realizadores;
    }

    public Realizador addRealizador(Realizador value) {
        conn.openConnection();
        String str = "INSERT INTO directors (name) VALUES ('" + value.getNome() + "')";
        System.out.println(str);
        if (conn.executeUpdate(str) > 0) {

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

    public void associaRealizadorFilme(Integer realizadorid,Integer filmeid){
        conn.openConnection();
        String str = "INSERT INTO directors_movies (movie_id,director_id) values ("+filmeid+","+realizadorid+")";
        conn.executeUpdate(str);
        conn.closeConnection();
    }



}
