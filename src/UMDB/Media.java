package UMDB;

public class Media {

        private Integer id;
        private String nome;
        private float rating;
        private Integer comentarios;




        public Media(Integer id, String nome, float rating, Integer comentarios) {
            this.id = id;
            this.nome = nome;
            this.rating = rating;
            this.comentarios = comentarios;
        }

        public Media(Media a) {
            this.id = a.getId();
            this.nome = a.getNome();
            this.rating = a.getRating();
            this.comentarios = a.getComentarios();
        }

        public Integer getId() { return id; }
        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }

        public float getRating(){ return this.rating;}
        public void setRating(float rating){this.rating = rating;}

        public Integer getComentarios(){ return this.comentarios;}
        public void setComentarios(Integer comentarios){this.comentarios = comentarios;}
    }
