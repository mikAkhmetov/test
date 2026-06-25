package com.mik.tablehomework.controllers;

import com.mik.tablehomework.App;
import com.mik.tablehomework.model.User;
import com.mik.tablehomework.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;


/**
 * 4.	Произвести отображение всех пользователей в таблице по нажатию на опцию
 * File->Open. Выбрать созданный вручную файл с пользователями. Программа должна отметить
 * галочкой в первой колонке таблицы тех пользователей, кому были уже отправлены письма.
 * Саму галочку сделать неизменяемой, написав следующий код в блоке инициализации после
 * поля с CheckBox-ом:
 * {
 *     btn.setDisable(true);
 *     btn.setStyle("-fx-opacity: 1;");
 * }
 */


/**
 * 5.	Для каждой строки таблицы в крайней колонке должна быть кнопка «Отправить»,
 * по нажатию на которую происходит открытие нового окна с ожиданием закрытия и
 * передачей данных о пользователе, к которому она относится
 */
public class Controller {

    @FXML
    private TableView<User> tableViewer;

    private File file;

    @FXML
    void initialize() throws IOException {
        TableColumn<User, Boolean> sendCol = new TableColumn<>("Send");
        sendCol.setCellValueFactory(new PropertyValueFactory<>("send"));
        System.out.println();

        TableColumn<User, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameCol = new TableColumn<>("User Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> registerTimeCol = new TableColumn<>("Register Time");
        registerTimeCol.setCellValueFactory(new PropertyValueFactory<>("registerTime"));

        TableColumn<User, String> emailCol = new TableColumn<>("Email Address");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, Integer> ageCol = new TableColumn<>("Users age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<User, String> countyCol = new TableColumn<>("County");
        countyCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<User, String> buttonSendCol = new TableColumn<>("To Send");

        Callback<TableColumn<User, String>, TableCell<User, String>> buttonFactory = new Callback<>() {
            @Override
            public TableCell<User, String> call(TableColumn<User, String> userStringTableColumn) {
                final TableCell<User, String> cell = new TableCell<>() {
                    final Button btn = new Button("Send");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                try {
                                    User user = getTableView().getItems().get(getIndex());
                                    App.openWindowAndWait("infoSend.fxml", "Info", user);

                                    try {
                                        tableViewer.setItems(FXCollections.observableArrayList(new UserRepository(file).getUsers()));
                                    } catch (IOException e) {
                                        App.showAlert("Error", "Wrong file type", Alert.AlertType.ERROR);
                                    }

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Boolean> call(TableColumn<User, Boolean> userBooleanTableColumn) {
                final TableCell<User, Boolean> cell = new TableCell<>() {
                    CheckBox btn = new CheckBox("isSend");

                    {
                        btn.setDisable(true);
                        btn.setStyle("-fx-opacity: 1;");
                    }

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setSelected(tableViewer.getItems().get(getIndex()).isSend());
                            btn.setOnAction(event -> {
                                User user = getTableView().getItems().get(getIndex());
                                user.setSend(!user.isSend());
                                System.out.println(user + " " + user.isSend());
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        sendCol.setCellFactory(cellFactory);
        buttonSendCol.setCellFactory(buttonFactory);
        tableViewer.getColumns().setAll(idCol, sendCol, nameCol, registerTimeCol, emailCol, ageCol,countyCol,buttonSendCol);

    }

    @FXML
    public void menuButtonOpen(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJson = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilterJson);
        FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilterAll);
        //TODO set initial directory
        fileChooser.setInitialDirectory(new File("C:\\Users\\1msmc\\IdeaProjects\\TableHomeWork"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                this.file = file;
                tableViewer.setItems(FXCollections.observableArrayList(new UserRepository(file).getUsers()));
            } catch (IOException e) {
                App.showAlert("Error", "Wrong file type", Alert.AlertType.ERROR);
            }
        }
    }
}