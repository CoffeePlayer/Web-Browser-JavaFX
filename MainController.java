package pl.coffee.web;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.web.WebHistory;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField urlField;

    @FXML
    private WebView webView;

    private WebEngine engine;

    private double zoomint = 1;

    private WebHistory history;

    @FXML
    private void loadUrlFromField() {
        final String HOME_URL = getClass().getResource("/test.html").toExternalForm();
        String HOME_PAGE = urlField.getText().trim();
        if (HOME_PAGE.equals("homepage")) { 
            engine.load(HOME_URL);
         } else {

        String url = urlField.getText();
        engine.load("http://" + url);
         }
    }

    @FXML
    private void homepagebt() {
        urlField.setText("homepage");
        final String HOME_URL = getClass().getResource("/test.html").toExternalForm();
        engine.load(HOME_URL);
    }

    @FXML

    private void rebtac() {
        engine.reload();
    }

    @FXML
    private void zoomin() {
        zoomint += 0.15;
        webView.setZoom(zoomint);
    
    }

    @FXML

    private void wsbtac() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        String akt = urlField.getText();
        akt = akt.replace("http://", "");
        akt = akt.replace("https://", "");
        urlField.setText(akt);
    }


    @FXML
    private void fwbtac() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        String akt = urlField.getText();
        akt = akt.replace("http://", "");
        akt = akt.replace("https://", "");
        urlField.setText(akt);
    }

    @FXML
    private void zoomout() {
        zoomint -= 0.15;
        webView.setZoom(zoomint);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final String HOME_URL = getClass().getResource("/test.html").toExternalForm();
        this.engine = webView.getEngine();
        engine.load(HOME_URL);
        engine.locationProperty().addListener((obs, oldUrl, newUrl) -> {
        String cleanurl = newUrl;
        cleanurl = cleanurl.replace("http://","");
        cleanurl = cleanurl.replace("https://","");
        urlField.setText(cleanurl);
    });
    }
}