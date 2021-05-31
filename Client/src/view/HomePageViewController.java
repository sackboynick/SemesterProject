package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Message;
import model.Offer;
import model.User;
import viewmodel.ViewState;

public class HomePageViewController extends ViewController{
    @FXML
    private ListView<User> onlineUsers;
    @FXML private ListView<Offer> offerListView;
    @FXML private ListView<Message> messageListView;
    @FXML private Label username;


    public HomePageViewController(){

    }

    public void reset(){
        getViewModelFactory().getHomePageViewModel().updateLists();
        this.onlineUsers.setItems(getViewModelFactory().getHomePageViewModel().getOnlineUsers());
        this.offerListView.setItems(getViewModelFactory().getHomePageViewModel().getOffers());
        this.messageListView.setItems(getViewModelFactory().getHomePageViewModel().getMessages());
        this.username.setText(ViewState.getInstance().getUser().getUsername());
    }

    @Override
    protected void init() {
        reset();
    }

    @FXML
    public void openOffersList(){
        reset();
        getViewHandler().openView("offersList");
    }

    @FXML
    public void openUserInterface(){
        User user=this.onlineUsers.getSelectionModel().getSelectedItem();
        if(user!=null) {
            ViewState.getInstance().setDisplayedUser(user);
            getViewModelFactory().getUserViewViewModel().setUserInfo();
            getViewHandler().openView("userInterface");
        }
    }

    @FXML public void openOfferInterface(){
        Offer offer=this.offerListView.getSelectionModel().getSelectedItem();
        if(offer!=null){
            ViewState.getInstance().setOffer(offer);
            getViewHandler().openView("offerView");
        }
    }

    @FXML void openYourProfileInterface(){
        ViewState.getInstance().setDisplayedUser(ViewState.getInstance().getUser());
        getViewModelFactory().getUserViewViewModel().setUserInfo();
        getViewHandler().openView("userInterface");
    }

    @FXML public void openMessage(){
        Message message=this.messageListView.getSelectionModel().getSelectedItem();
        if(message!=null){
            ViewState.getInstance().setDisplayedMessage(message);
            getViewHandler().openView("messageView");
        }
    }

    @FXML public void openRentingList(){
        getViewHandler().openView("rentingListView");
    }

    @FXML void openSendMessageInterface(){
        getViewHandler().openView("sendMessageView");
    }

    @FXML public void exit(){
        getViewHandler().close();
    }
}
