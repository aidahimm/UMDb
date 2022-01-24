package unipi.it.umdb.controllers;
import javafx.event.ActionEvent;

import com.jfoenix.controls.JFXButton;

import unipi.it.umdb.UmdbUserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unipi.it.umdb.data.RegisteredUser;

public class RegisteredUserProfile {

    @FXML
    private Text profileTitle;

    @FXML
    private Text userIDLabel;

    @FXML
    private Text userID;

    @FXML
    private Text emailLabel;

    @FXML
    private Text userEmail;

    @FXML
    private Text NameLabel;

    @FXML
    private Text userName;

    @FXML
    private Text surnameLabel;

    @FXML
    private Text userSurname;

    @FXML
    private Text countryLabel;

    @FXML
    private Text userCountry;

    @FXML
    private Text dobLabel;

    @FXML
    private Text userDob;

    @FXML
    private JFXButton deleteProfile;

    public void initialize() {
        RegisteredUser user;

        UmdbUserSession session = UmdbUserSession.getInstance();
        user = session.getLoggedUser();

        userID.setText(user.getUserID());
        userEmail.setText(user.getEmail());
        userName.setText(user.getName());
        userSurname.setText(user.getSurname());
        userCountry.setText(user.getCountry());
//        userDob.setText(user.getDob());

    }

//    @FXML
//    void deleteUserProfile(ActionEvent event) {
//        try {
//            Parent root;
//            Stage stage;
//
//            RegisteredUser.delete(userNickname.getText());
//
//            root = FXMLLoader.load(getClass().getResource("/Javafx/Homepage.fxml"));
//
//            stage = (Stage)profileTitle.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}


