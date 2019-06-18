package UMDB;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by filipeandre135 on 16-01-2014.
 */
public class Filme {
    private Integer id;
    private String nome;
    private Integer ano;
    private String audiencia;
    private String imagem;
    private String sinopse;
    private Integer duracao;
    private HashMap<Integer,Actor> actores;
    private HashMap<Integer,Realizador> realizadores;
    private ArrayList<String> generos;


    public Filme(Integer id, String nome, Integer ano, String audiencia, String imagem, String sinopse,Integer duracao) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.audiencia = audiencia;
        this.imagem = imagem;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.actores = new HashMap<Integer, Actor>();
        this.realizadores = new HashMap<Integer, Realizador>();
        this.generos = new ArrayList<String>();
    }

    public Filme(Filme f) {
        this.id = f.getId();
        this.nome = f.getNome();
        this.ano = f.getAno();
        this.audiencia = f.getAudiencia();
        this.imagem = f.getImagem();
        this.sinopse = f.getSinopse();
        this.duracao = f.getDuracao();
    }

    public void addActor(Actor a){
        this.actores.put(a.getId(),a);
    }

    public void addRealizador(Realizador r){ this.realizadores.put(r.getId(),r);}
    public void addGenero(String s){ this.generos.add(s);}

    public String getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(String audiencia) {
        this.audiencia = audiencia;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }


    public Integer getId() {
        return id;
    }

    public HashMap<Integer,Actor> getActores(){return this.actores;}
    public HashMap<Integer,Realizador> getRealizadores(){ return this.realizadores;}
    public ArrayList<String> getGeneros(){ return this.generos;}


    public void setId(Integer id) {
        this.id = id;

    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        String res = this.getId()+"-"+this.getNome();
        return res;
    }
}
