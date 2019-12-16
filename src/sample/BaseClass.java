package sample;

import javafx.scene.control.Alert;

public class BaseClass {
    public String userName;

    public void setUserName(String uName) {
        this.userName = uName;
    }
    /**
     * Окошко с message
     * @param message
     */
    protected void drawAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
