/**
 * Created by filipeandre135 on 16-01-2014.
 */
package UMDB;
public class Actor {

    private Integer id;
    private String nome;


    public Integer getId() {
        return id;
    }

    public Actor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Actor(Actor a) {
        this.id = a.getId();
        this.nome = a.getNome();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }
}
