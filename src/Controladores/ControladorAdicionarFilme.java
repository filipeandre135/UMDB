package Controladores;

import DAO.FilmeDAO;
import UMDB.Filme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.sound.midi.SysexMessage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by filipeandre135 on 25-01-2014.
 */
public class ControladorAdicionarFilme implements Initializable {

    @FXML protected TextField txtTitulo,txtAno,txtDuracao,txtImagem;
    @FXML protected TextArea txtSinopse;
    @FXML protected ComboBox<String> cbAudiencia;
    @FXML protected ListView<String> listaGeneros;

    private ObservableList<String> listageneros = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle rb){
        listaGeneros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listageneros.addAll(new FilmeDAO().getGeneros());
        this.listaGeneros.setItems(listageneros);
        ObservableList<String> ratings = FXCollections.observableArrayList();
        ratings.addAll("Unrated","G","PG","PG-13","R","NC-17");
        this.cbAudiencia.setItems(ratings);


    }

    @FXML
    protected void adicionaFilme(ActionEvent e) throws NumberFormatException{
        String titulo = txtTitulo.getText();
        String imagem = txtImagem.getText();
        String sinopse = txtSinopse.getText();
        String audiencia = cbAudiencia.getValue();
        Filme f = null;
        int ano = 0;
        int duracao =0;
        ano = Integer.parseInt(txtAno.getText());
        duracao = Integer.parseInt(txtDuracao.getText());
        ArrayList<String> generos = new ArrayList<String>(listaGeneros.getSelectionModel().getSelectedItems());

        if(generos.size()==0)
        {
            System.out.println("no genre selected");
        }
        if(!titulo.isEmpty() && !imagem.isEmpty() && !sinopse.isEmpty() && ano!=0 && duracao !=0)
        {f = new Filme(0,titulo,ano,audiencia,imagem,sinopse,duracao);
            f.getGeneros().addAll(generos);

        }
        ControladorAdmin.addFilme(f);
        ControladorAdmin.addfilmeStage.close();
    }
}
