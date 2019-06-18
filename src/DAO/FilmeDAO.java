package DAO;


import UMDB.Filme;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class FilmeDAO {

    private ConnectionData conn;

    public FilmeDAO() {
        this.conn = new ConnectionData();
    }


    public HashMap<Integer,Filme> getFilmes(){
        HashMap<Integer,Filme> filmes = new HashMap<Integer,Filme>();
        String str = "Select * from movies";
        conn.openConnection();
        ResultSet r = conn.executeQuery(str);
        try{
        while(r.next()){
            Filme f = new Filme(r.getInt("id"),r.getString("title"),r.getInt("year"),r.getString("audience"),r.getString("poster_url"),r.getString("synopsis"),r.getInt("runtime"));
            f.getGeneros().addAll(new FilmeDAO().getGeneros(f.getId()));
            f.getActores().putAll(new ActorDAO().getActores(f));
            f.getRealizadores().putAll(new RealizadorDAO().getRealizadores(f));
            filmes.put(f.getId(),f);
        }

        }catch (SQLException e) {
                e.printStackTrace();
            }finally{
                conn.closeConnection();
            }
     return filmes;
    }

    public Filme addFilme(Filme value) {
        conn.openConnection();
        String str = "INSERT INTO movies (title, year, audience,poster_url,synopsis,runtime) VALUES ('" + value.getNome() + "'," + value.getAno() + ", '" + value.getAudiencia() + "', '" + value.getImagem() + "', '" + value.getSinopse() + "',"  + value.getDuracao() + ")";

        System.out.println(str);
        if (conn.executeUpdate(str) > 0) {

            try {
                value.setId(conn.getLastKey());

                for(String s : value.getGeneros())
                {
                    String str2 = "INSERT INTO genres_movies (movie_id,genre_id) values ("+value.getId()+","+getIdGenero(s)+")";
                    conn.executeUpdate(str2);
                    System.out.println(str2);
                }
                conn.closeConnection();
                return value;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn.closeConnection();
        return null;
    }

    public ArrayList<String> getGeneros(){

        ArrayList<String> generos = new ArrayList<String>();
        conn.openConnection();
        String str = "SELECT name from genres";
        ResultSet r = conn.executeQuery(str);
        try{
            while(r.next()){
                generos.add(r.getString("name"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn.closeConnection();
        }
        return generos;
    }

    public ArrayList<String> getGeneros(int id){
        conn.openConnection();
        ArrayList<String> generos = new ArrayList<String>();
        String str = "SELECT name from genres,genres_movies where movie_id="+id+" and genres.id = genres_movies.genre_id";
        ResultSet r = conn.executeQuery(str);
        try{
            while(r.next()){
                generos.add(r.getString("name"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        conn.closeConnection();
        return generos;
    }

    public int getIdGenero(String g) throws SQLException{
        String str = "SELECT id from genres where name ='"+g+"'";
        ResultSet r = conn.executeQuery(str);
        if(r.next())
            return r.getInt("id");
        else
            return -1;
    }






}

