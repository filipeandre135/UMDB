package Controladores;

import DAO.*;
import UMDB.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Iterator;

public class ControladorAdmin implements Initializable {

    private Main application;
    public static Utilizador loggeduser;
    public static Stage addfilmeStage;

    @FXML Label labelData;
    @FXML Label labelUser;
    @FXML protected TableView<Filme> tabelaFilmes;
    @FXML protected Button btVerDetalhes;

    @FXML protected TextField txtNomeActor, txtAnoActor,txtImagemActor,txtIdFilmeActor,txtIdActor;
    @FXML protected TableView<Actor> tabelaActores;
    @FXML protected ComboBox<Filme> cbSeleccionaFilmeActor;


    @FXML protected TextField txtNomeRealizador, txtImagemRealizador,txtIdFilmeRealizador,txtIdRealizador;
    @FXML protected TableView<Realizador> tabelaRealizadores;
    @FXML protected ComboBox<Filme> cbSeleccionaFilmeRealizador;

    @FXML protected TextField txtIdUtilizador;
    @FXML protected TableView<Utilizador> tabelaUtilizadores;

    @FXML protected Label nutil, nfilme, nrating, nactor, nrealizador;
    @FXML protected TableView<Media> tabelaMedia;




    private static HashMap<Integer,Filme> hashfilmes = new HashMap<Integer, Filme>();
    private HashMap<Integer,Actor> hashactores = new HashMap<Integer, Actor>();
    private HashMap<Integer,Realizador> hashrealizadores = new HashMap<Integer, Realizador>();
    private HashMap<Integer,Utilizador> hashutilizadores = new HashMap<Integer, Utilizador>();
    private HashMap<Integer,Media> hashmedias = new HashMap<Integer,Media>();

    private static ObservableList<Filme> listaFilmes = FXCollections.observableArrayList();
    private ObservableList<Actor> listaActores = FXCollections.observableArrayList();
    private ObservableList<Realizador> listaRealizadores = FXCollections.observableArrayList();
    private ObservableList<Utilizador> listaUtilizadores = FXCollections.observableArrayList();
    private ObservableList<Media> listaMedia = FXCollections.observableArrayList();

    static FilmeDAO filmes = new FilmeDAO();
    ActorDAO actores = new ActorDAO();
    RealizadorDAO realizadores = new RealizadorDAO();
    UtilizadorDAO utilizadores = new UtilizadorDAO();
    MediaDAO medias = new MediaDAO();


    @Override
    public void initialize(URL url, ResourceBundle rb){
        //apenas necessário para o botão funcionar
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        labelData.setText(dateFormat.format(date));
        labelUser.setText("Olá,"+this.loggeduser.getSid());


        tabelaFilmes.setItems(listaFilmes);
        tabelaFilmes.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>()
        {
            @Override
            public void onChanged(Change<? extends Integer> change)
            {
                if (change.getList().size() >= 1)
                {
                    btVerDetalhes.setDisable(false);
                }
                else
                {
                    btVerDetalhes.setDisable(true);
                }
            }

        });
        tabelaActores.setItems(listaActores);
        tabelaRealizadores.setItems(listaRealizadores);
        tabelaUtilizadores.setItems(listaUtilizadores);
        cbSeleccionaFilmeActor.setItems(listaFilmes);
        cbSeleccionaFilmeRealizador.setItems(listaFilmes);

        hashfilmes= filmes.getFilmes();
        hashactores = actores.getActores();
        hashrealizadores = realizadores.getRealizadores();
        hashutilizadores = utilizadores.getUtilizadores();

        listaFilmes.addAll(hashfilmes.values());
        listaActores.addAll(hashactores.values());
        listaRealizadores.addAll(hashrealizadores.values());
        listaUtilizadores.addAll(hashutilizadores.values());


        tabelaMedia.setItems(listaMedia);
        nutil.setText("" + utilizadores.getUtilizadores().size());
        nfilme.setText("" + filmes.getFilmes().size());
        nactor.setText("" + actores.getActores().size());
        nrealizador.setText("" + realizadores.getRealizadores().size());
        hashmedias = medias.getMedias();
        listaMedia.addAll(hashmedias.values());
        int rating = 0;
        Iterator itr = hashmedias.values().iterator();
        while(itr.hasNext()) {
            Object med = itr.next();
            Media med1 = (Media) med;
            rating += med1.getRating();
        }
        nrating.setText("" + rating/filmes.getFilmes().size());
    }


    @FXML
    protected void adicionarFilme(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Vistas/VistaAddFilme.fxml"));
            addfilmeStage = new Stage();
            addfilmeStage.setTitle("Adicionar Filme");
            addfilmeStage.setScene(new Scene(root, 450, 450));
            addfilmeStage.show();


        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void verDetalhes(ActionEvent e) {
        Parent root;
        try {
            ControladorDetalhesFilme.f = tabelaFilmes.getSelectionModel().getSelectedItem();
            root = FXMLLoader.load(getClass().getResource("/Vistas/VistaDetalhesFilme.fxml"));

            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root, 700, 450));
            stage.show();


        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    protected void adicionaActor(ActionEvent e){

        String nome = txtNomeActor.getText();
        Integer ano =0;
        try{
            ano = Integer.parseInt(txtAnoActor.getText());
        }catch(NumberFormatException ex){System.out.println("ano not a number");}
        String imagem = txtImagemActor.getText();

        if(!nome.isEmpty() && ano!=0 && !imagem.isEmpty())
        {
            Actor a = new Actor(0,nome);

            actores.addActor(a);
            listaActores.add(a);
        }

        txtNomeActor.clear();
        txtAnoActor.clear();
        txtImagemActor.clear();


    }

    @FXML
    protected void adicionaRealizador(ActionEvent e){

        String nome = txtNomeRealizador.getText();
        String imagem = txtImagemRealizador.getText();

        if(!nome.isEmpty() && !imagem.isEmpty())
        {
            Realizador r = new Realizador(0,nome);

            realizadores.addRealizador(r);
            listaRealizadores.add(r);
        }

        txtNomeRealizador.clear();
        txtImagemRealizador.clear();




    }

    @FXML
    protected void listarFilmesActor(ActionEvent e){
            Filme f = cbSeleccionaFilmeActor.getValue();
            Filme fil = this.hashfilmes.get(f.getId());
            listaActores.clear();
        if(f.getActores().isEmpty())
            return;
        else
        listaActores.addAll(f.getActores().values());
    }

    @FXML
    protected void listarFilmesRealizador(ActionEvent e){
        Filme f = cbSeleccionaFilmeRealizador.getValue();
        Filme fil = this.hashfilmes.get(f.getId());
        listaRealizadores.clear();
        listaRealizadores.addAll(f.getRealizadores().values());
    }

    @FXML
    protected void toggleUser(ActionEvent e){
        int id=-1;
        try
        {id = Integer.parseInt(txtIdUtilizador.getText());}
        catch(NumberFormatException ex){System.out.println("id user not a number");return;}
        boolean bloq=false;
        for(Utilizador u : listaUtilizadores)
        {
            if(u.getId()==id){bloq = u.getBloqueado();u.setBloqueado(!bloq);}
        }
        utilizadores.toggleUtilizador(id,!bloq);
    }

    @FXML
    protected void associaActorFilme(ActionEvent e){
        Integer idfilme = 0;
        Integer idactor = 0;

        try{
            idfilme = Integer.parseInt(txtIdFilmeActor.getText());
            idactor = Integer.parseInt(txtIdActor.getText());
        }catch(NumberFormatException ex){System.out.println("id not a number");}
        if(idfilme>0 && idactor>0)
        {
    actores.associaFilmeActor(idactor,idfilme);
    Actor actor=null;
            for(Actor a : listaActores)
            {
                if(a.getId()==idactor)
                    actor = a;
            }
     if(actor!=null)
     {
    for(Filme f : listaFilmes)
    {
        if(f.getId()==idfilme){
            f.addActor(actor);
        }
    }
        }else System.out.println("actor not found");

     }
    txtIdFilmeActor.clear();
    txtIdActor.clear();

    }

    @FXML
    protected void associaRealizadorFilme(ActionEvent e)
    {

        Integer idfilme = 0;
        Integer idrealizador = 0;

        try{
            idfilme = Integer.parseInt(txtIdFilmeRealizador.getText());
            idrealizador = Integer.parseInt(txtIdRealizador.getText());
        }catch(NumberFormatException ex){System.out.println("id not a number");}
        if(idfilme>0 && idrealizador>0)
        {
            realizadores.associaRealizadorFilme(idrealizador,idfilme);
            Realizador r= hashrealizadores.get(idrealizador);
            if(r!=null)
            {
                Filme f = hashfilmes.get(idfilme);
                f.addRealizador(r);
            }else System.out.println("actor not found");

        }
        txtIdFilmeRealizador.clear();
        txtIdRealizador.clear();
    }

    public static void addFilme(Filme f){
        filmes.addFilme(f);
        hashfilmes.put(f.getId(),f);
        listaFilmes.add(f);
    }



}











