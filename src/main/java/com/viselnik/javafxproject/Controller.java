package com.viselnik.javafxproject;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

import java.awt.Desktop;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller  implements Initializable {
    @FXML
    private Label output;
    @FXML
    private TextField Amount;


    @FXML
    private JFXComboBox<String> fromBox;
    @FXML
    private JFXComboBox<String> toCode;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();

    }



    private void LoadData() {
        fromBox.setItems(FXCollections.observableArrayList("USD", "EUR", "JPY", "GBP", "AUD", "CAD", "TRY", "CHF", "CNY", "SEC", "MXN", "NZD", "SGD"));
        toCode.setItems(FXCollections.observableArrayList("USD", "EUR", "JPY", "GBP", "AUD", "CAD", "TRY", "CHF", "CNY", "SEC", "MXN", "NZD", "SGD"));
        fromBox.getEditor().setStyle("-fx-text-fill: #ffffff;");
        toCode.getEditor().setStyle("-fx-text-fill: #ffffff;");
    }

    @FXML

    public void clickSend() throws IOException, InterruptedException {
        String API_KEY = "9e84cfbba49c7d2328df79f2";
        String url_str = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + fromBox.getValue() + "/" + toCode.getValue() + "/" + Amount.getText() + "";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        // Accessing object
        String req_result = jsonobj.get("conversion_result").getAsString();

        output.setText(req_result + "  " + toCode.getValue());
        Thread.sleep(1200);

    }

    String github = "https://github.com/ViselnikAscet";
    String linkedin = "https://www.linkedin.com/in/muhammed-r%C4%B1za-kaynak-a575a0238/";

    @FXML
    public void clickGithub() {
        try {
            // Desktop sınıfını kontrol etme
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(github));
            } else {
                System.out.println("Tarayıcı açma işlemi desteklenmiyor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void clickLinkedin() {
        try {
            // Desktop sınıfını kontrol etme
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(linkedin));
            } else {
                System.out.println("Tarayıcı açma işlemi desteklenmiyor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}