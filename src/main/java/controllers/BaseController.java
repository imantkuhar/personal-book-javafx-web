package controllers;

import javafx.scene.control.ProgressIndicator;

/**
 * Created by Imant on 05.03.17.
 */
public abstract class BaseController {

    protected ProgressIndicator progressIndicator;

    public void setProgressIndicator(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    public void showProgress(){
        progressIndicator.setVisible(true);
    }

    public void hideProgress(){
        progressIndicator.setVisible(false);
    }
}
