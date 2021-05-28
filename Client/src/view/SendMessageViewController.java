package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import viewmodel.ViewState;

public class SendMessageViewController extends ViewController{
    @FXML private TableView<User> userTableView;
    @FXML private TableColumn<User,String> username,role;
    @FXML
    private TextField receiver;
    @FXML private TextArea body;
    @FXML private Label error;

    @Override
    protected void init() {
        this.userTableView.setItems(getViewModelFactory().getSendMessageViewModel().getOnlineUsers());
        this.username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        this.role.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        reset();
    }

    public void reset(){
        this.receiver.setText("");
        this.body.setText("");
    }

    @FXML
    public void sendMessage(){
        getViewModelFactory().getSendMessageViewModel().sendMessage();
    }

    @FXML public void selectUser(){
            User user=this.userTableView.getSelectionModel().getSelectedItem();
            if(user!=null) {
                username.setText(user.getUsername());
            }
            else
                this.error.setText("Select an user first!");
    }
}
