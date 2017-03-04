package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Imant on 04.03.17.
 */
public class AlertDialogUtil {

    public interface AlertDialogCallback{
        void onConfirm();
        void onCanceled();
    }

    public static void showErrorDialog(String message){

    }

    public static void showSuccessDialog(String message, AlertDialogCallback callback){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            callback.onConfirm();
        } else {
            callback.onCanceled();
        }
    }

}
