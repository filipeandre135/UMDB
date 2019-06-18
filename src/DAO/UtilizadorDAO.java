package DAO;
import java.security.MessageDigest;

import UMDB.Utilizador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UtilizadorDAO {





    public Utilizador validaAdministrador(String username,String password)
    {

        ConnectionData conexao = new ConnectionData();
        conexao.openConnection();
        System.out.println("A buscar Admin ");
        String str = "SELECT * from users where username =" +"'"+username+"'";
        ResultSet rs = conexao.executeQuery(str);
        Utilizador admin=null;
        String pw = "";
        try{
            pw = md5digest(password);
            System.out.println(pw);
        }catch(Exception ex){System.out.println();}
        try {
            if(rs.next())
            {   if(rs.getInt("level")==2)
                admin = new Utilizador(rs.getInt("id"),rs.getInt("level"),rs.getString("username"),false,rs.getString("password"));}
        }catch (SQLException e) {
            e.printStackTrace();
        }
        conexao.closeConnection();
        if(admin==null){ return null;}//utilizador não encontrado}
        if(admin.getPassword().equals(pw))
        return admin;//sucesso
        else return null;// password errada

    }

    public void addUtilizador(Utilizador user)
    {
        ConnectionData conexao = new ConnectionData();
        String str = "INSERT INTO users (username,password,level,bloqueado) VALUES ('" + user.getSid() + "',1,"+user.getBloqueado()+")";
        conexao.executeSQL(str);
        conexao.closeConnection();
    }


    public void toggleUtilizador(int id,boolean bloq)
    {
        ConnectionData conexao = new ConnectionData();
        int val = bloq? 1 : 0;
        String str = "UPDATE users set bloqueado="+val+" where id ='"+id+"'";
        conexao.executeUpdate(str);
        conexao.closeConnection();
    }

    public HashMap<Integer,Utilizador> getUtilizadores()
    {
        ConnectionData conn = new ConnectionData();
        conn.openConnection();
        HashMap<Integer,Utilizador> ret = new HashMap<Integer,Utilizador>();

        ResultSet r = conn.executeQuery("SELECT * FROM users");

        try {
            while (r.next()) {
                ret.put(r.getInt("id"), new Utilizador(r.getInt("id"),r.getInt("level"),r.getString("username"),false,""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            conn.closeConnection();
        }
        return ret;
    }

    public String md5digest(String pw) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pw.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();

    }



}
