package luhnalgorithm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LuhnAlgorithmFXMLController implements Initializable {
    
    @FXML
    private Button checkButton, exitButton;
    @FXML
    private TextField personalIdentityNumber;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private void checkButtonAction(ActionEvent event) {
        byte[] bPersonalIdentityNumber = personalIdentityNumber.getText().getBytes(); 
        int[] number = new int[11];
        for(int i = 0; i < 11; i++)
            number[i] = bPersonalIdentityNumber[i] - 48;
        int sum = 0; 
        boolean parity = false;
        for(int i = 10; i >= 0; i--){
            int temp = number[i];
            resultTextArea.appendText(temp + " * ");
            if(parity){
                temp *= 2;
                resultTextArea.appendText("2 = " + temp + "\n");
                if(temp > 9){
                    temp -= 9;
                    resultTextArea.appendText("Sumuję liczbę dwucyfrową: " + temp + "\n");
                }
            }else resultTextArea.appendText("1 = " + temp + "\n");
            sum += temp;
            resultTextArea.appendText("Suma wszystkich dotychczasowych wyników: " + sum + "\n");
            parity = !parity;
        }
        if(sum % 10 == 0) resultTextArea.appendText("PESEL poprawny");
        else resultTextArea.appendText("PESEL niepoprawny, bo modulo wynosi: " + (sum % 10) + "!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exitButton.setOnAction(event -> {
            System.exit(0);
        });
    }    
}
