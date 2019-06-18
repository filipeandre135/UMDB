/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import java.io.*;
import java.sql.*;
//import oracle.jdbc.*;

public class ConnectionData{

    private Connection conn;
    private Statement st;
    private ResultSet rs;

    private String url;
    private String user;
    private String password;


    public ConnectionData(){
        this.conn=null;
        this.st=null;
        this.rs=null;

        this.url = "jdbc:oracle:thin:@umdb.no-ip.org:1522:umdb";
        this.user = "hugomarisco";
        this.password = "hugooracle";
    }

    private void createDBConnection()
    {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        }
        catch(ClassNotFoundException e) {
            System.out.println("Oops! Can't find class oracle.jdbc.OracleDriver");
        }
        try{
            conn = DriverManager.getConnection(url, user, password);

        }
        catch(Exception e){
            System.out.println("ERRO " + e.getMessage());
        }
    }

    public Statement createStatement()
    {
        st=null;
        if(this.conn==null)
            this.createDBConnection();
        try{

            st = this.conn.createStatement();
        }
        catch(Exception e){
            System.out.println("ERRO " + e.getMessage());
        }
        return st;
    }

    public Connection openConnection()
    {
        if(this.conn==null)
            this.createDBConnection();
        else{
            try{
                if (this.conn.isClosed())
                    this.createDBConnection();
            }
            catch(Exception e){
                System.out.println("ERRO " + e.getMessage());
            }
        }
        return this.conn;
    }

    public void executeSQL(String SqlComm)
    {
        try{
            st.execute(SqlComm);
        }
        catch(Exception e){
            System.out.println("ERRO " + e.getMessage());
        }

    }

    public int executeUpdate(String SqlComm){
        int r = 0;

        st = this.createStatement();
        try{
            int[ ] colIndexes = new int[1];
            colIndexes[0] = 1;
            r = st.executeUpdate(SqlComm, colIndexes);
        }
        catch(Exception e){
            System.out.println("ERRO " + e.getMessage());
        }
        return r;

    }

    public Integer getLastKey() throws SQLException {

        int key = 0;

        rs = st.getGeneratedKeys();

        if (rs.next()) {
            System.out.println(rs.toString());
            key = rs.getInt(1);
            System.out.println(key);
        }

        return key;
    }

    public ResultSet executeQuery(String SqlComm)
    {
        rs=null;
        st=createStatement();
        try{
            rs = st.executeQuery(SqlComm);
        }
        catch(Exception e){
            System.out.println("ERRO " + e.getMessage());
        }
        return rs;
    }

    public ResultSet executeQueryNavigate(String SqlComm)
    {
        try{
            st = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(SqlComm);
        }
        catch(Exception e){
            System.out.println("ERRO " + e.getMessage());
            //javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }

    public void closeConnection()
    {
        if(this.conn!=null)
        {
            try{
                this.conn.close();
            }
            catch(Exception e){
                System.out.println("ERRO " + e.getMessage());
                //javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(),"ERRO", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}