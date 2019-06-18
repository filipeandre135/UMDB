package DAO;

import UMDB.Comentario;
import UMDB.Filme;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by filipeandre135 on 17-01-2014.
 */
public class ComentarioDAO {

    private ConnectionData conn;

    public ComentarioDAO() {
        this.conn = new ConnectionData();
    }


    public HashMap<Integer,Comentario> getComentarios(Filme f){
        HashMap<Integer,Comentario> comentarios = new HashMap<Integer,Comentario>();
        String str = "Select * from comments where movie_id="+f.getId();
        conn.openConnection();
        ResultSet r = conn.executeQuery(str);
        try{
            while(r.next()){
                comentarios.put(r.getInt("id"),new Comentario(r.getInt("id"),r.getString("comentario")));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn.closeConnection();
        }
        return comentarios;
    }




    public int deleteComentario(Comentario f) {
        conn.openConnection();
        conn.executeUpdate("DELETE FROM Comments WHERE id = " + f.getId());
        conn.closeConnection();
        return 1;
    }
}
