/**
 * Created by filipeandre135 on 16-01-2014.
 */
package UMDB;
public class Comentario {

    private Integer id;
    private String comentario;


    public Comentario(Integer id, String comentario) {
        this.id = id;
        this.comentario = comentario;
    }

    public Comentario(Comentario c) {
        this.id = c.getId();
        this.comentario = c.getComentario();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
