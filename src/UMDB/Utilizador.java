/**
 * Created by filipeandre135 on 16-01-2014.
 */
package UMDB;
public class Utilizador {

    private Integer id;
    private Integer estatuto;
    private String sid;
    private boolean bloqueado;
    private String password;

    public Utilizador(Integer id, Integer estatuto, String sid,boolean bloqueado,String password) {
        this.id = id;
        this.estatuto = estatuto;
        this.sid = sid;
        this.bloqueado = bloqueado;
        this.password = password;
    }

    public Utilizador(Utilizador u) {
        this.id = u.getId();
        this.estatuto = u.getEstatuto();
        this.sid = u.getSid();
        this.password = u.getPassword();
    }

    public boolean getBloqueado(){return this.bloqueado;}
    public void setBloqueado(boolean bloqueado){this.bloqueado = bloqueado;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {return this.password;}
    public void setPassword(String pw){this.password = pw;}

    public Integer getEstatuto() {
        return estatuto;
    }

    public void setEstatuto(Integer estatuto) {
        this.estatuto = estatuto;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
