package com.mik.tablehomework.controllers;

import com.mik.tablehomework.App;
import com.mik.tablehomework.mail.MailSender;
import com.mik.tablehomework.model.User;
import com.mik.tablehomework.repository.SendUserIdRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.mik.tablehomework.util.Constants.FIRST_MAIl;
import static com.mik.tablehomework.util.Constants.MY_PASSWORD;


/**
 * 6.	В новом окне сделать поля для ввода темы письма(textField) и текста письма(textArea).
 * По нажатию на кнопку «Отправить» в этом окне произвести отправку письма пользователю
 * и вывода сообщения об успехе, сохранив id этого пользователя в файл send.json через
 * репозиторий. Для отправки сообщения использовать код из приложения к проекту
 */

public class InfoSendController implements ControllerData<User> {

    @FXML
    public Label labelForMail;
    @FXML
    public TextField textTopic;
    @FXML
    public TextArea textLetter;

    private User user;
    private MailSender sender;
    public void initData(User data) {
        this.user = data;
        labelForMail.setText(user.getEmail());
//        textOne.setText(user.getName() + " " + user.getId());

        sender = new MailSender(FIRST_MAIl, MY_PASSWORD, user.getEmail());

    }

    public void buttonCancel(ActionEvent actionEvent) {
        App.closeWindow(actionEvent, false);
    }

    /**
     * 6.	В новом окне сделать поля для ввода темы письма(textField) и текста письма(textArea).
     * По нажатию на кнопку «Отправить» в этом окне произвести отправку письма пользователю и
     * вывода сообщения об успехе, сохранив id этого пользователя в файл send.json через репозиторий.
     * Для отправки сообщения использовать код из приложения к проекту
     */
    public void buttonSend(ActionEvent actionEvent) {
        int id = user.getId();
        try{
            sender.send(textTopic.getText(), textLetter.getText());
        } catch (Exception e) {
            App.showAlert("Error", "Invalid mail", Alert.AlertType.ERROR);
        }

        SendUserIdRepository repository = new SendUserIdRepository();
        repository.addSender(user.getId());

        App.closeWindow(actionEvent, true);
    }
}
