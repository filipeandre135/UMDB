package Controladores;

import DAO.UtilizadorDAO;
import UMDB.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tiago.
 */
public class ControladorLogin implements Initializable {

    @FXML private TextField textfield_username;
    @FXML private PasswordField textfield_password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }



    @FXML
    private void loginHandler(ActionEvent e) throws IOException {
        String username = this.textfield_username.getText();
        String password = this.textfield_password.getText();
        if(!username.isEmpty() && !password.isEmpty()){
        Utilizador currentUser = new UtilizadorDAO().validaAdministrador(username,password);

            if(currentUser!=null){
                System.out.println("Já sei que é um utilizador");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistas/VistaAdmin.fxml"));
                ControladorAdmin.loggeduser = currentUser;
                ControladorAdmin controller = fxmlLoader.getController();
                Parent root = (Parent) fxmlLoader.load();

                Scene scene = new Scene(root);
                Node trigger = (Node) e.getSource();
                Stage stage = (Stage) trigger.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

        }
            else{}//mensagem de erro aqui
        }
    }


}
