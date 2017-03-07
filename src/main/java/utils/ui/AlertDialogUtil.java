package utils.ui;

import javafx.scene.control.Alert;

/**
 * Created by Imant on 04.03.17.
 */
public class AlertDialogUtil {

    public interface AlertDialogCallback{
        void onConfirm();
        void onCanceled();
    }

    public static void showErrorDialog(String message, AlertDialogCallback callback){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showCancelDialog(String message, AlertDialogCallback callback){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Dialog");
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();

//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//            callback.onConfirm();
//        } else {
//            callback.onCanceled();
//        }
    }
}
