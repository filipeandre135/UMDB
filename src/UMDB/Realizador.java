/**
 * Created by filipeandre135 on 16-01-2014.
 */
package UMDB;
public class Realizador
{
    private Integer id;
    private String nome;

    public Realizador(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Realizador(Realizador r) {
        this.id = r.getId();
        this.nome = r.getNome();
    }

    public Integer getId() {
        return id;
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

}
