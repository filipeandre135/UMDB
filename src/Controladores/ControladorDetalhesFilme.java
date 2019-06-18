package Controladores;

import UMDB.Filme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by filipeandre135 on 25-01-2014.
 */
public class ControladorDetalhesFilme implements Initializable {

    public static Filme f;
    @FXML protected Label labelTitulo,labelGeneros,labelAudiencia,labelAno,labelDuracao;
    @FXML protected TextArea labelSinopse;
    @FXML protected ImageView imagemFilme;

    public void initialize(URL url, ResourceBundle rb) throws IllegalArgumentException{

        String generos= "";
        for(String s :f.getGeneros())
        {
            generos = generos + s + "/";
        }
        labelTitulo.setText(f.getNome());
        labelGeneros.setText(generos);
        labelAudiencia.setText(f.getAudiencia());
        labelAno.setText(f.getAno()+"");
        labelDuracao.setText(f.getDuracao()+" mins");
        labelSinopse.setText(f.getSinopse());
        Image imagem = new Image(f.getImagem());
        imagemFilme.setImage(imagem);


    }
}