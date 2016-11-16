package luhnalgorithm;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Klasa <code>LuhnAlgorithmFXMLController</code> reprezentuje klasę zarządzającą
 * programem sprawdzającym poprawność numeru PESEL. Algorytm sprawdzający jest 
 * algorytmem Luhna z wagami 1, 3, 7, 9. Klasa jest zabezpieczona przed podawaniem 
 * złego formatu nr PESEL, a także posiada funkcję automatycznego zapisu poprawnych 
 * numerów do pliku "Wynik.txt" znajdującycm się w katalogu z programem. 
 * @author AleksanderSklorz
 */
public class LuhnAlgorithmFXMLController implements Initializable {
    @FXML
    private Button checkButton, exitButton;
    @FXML
    private TextField personalIdentityNumber;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private void checkButtonAction(ActionEvent event) {
        String text = personalIdentityNumber.getText();
        if(text.matches("\\d{11}")){
            resultTextArea.setText("");
            byte[] bPersonalIdentityNumber = (text + "\r\n").getBytes(); // skok do nowego wiersza dodany aby zmieniało wiersz w przypadku zapisu do pliku
            int[] number = new int[11], multipliers = {1, 3, 7, 9};
            for(int i = 0; i < 11; i++)
                number[i] = bPersonalIdentityNumber[i] - 48;
            int sum = 0, k = 0; 
            boolean parity = false;
            for(int i = 0; i < 10; i++){
                int temp = number[i];
                sum += temp * multipliers[k];
                resultTextArea.appendText(temp + " * " + multipliers[k] + " = " + (temp * multipliers[k]) + "\n" + "Aktualna suma: " + sum + "\n");
                k++;
                if(k == 4) k = 0;
            }
            sum += number[10];
            resultTextArea.appendText("Całkowita suma, po dodaniu cyfry kontrolnej: " + sum + "\n");
            if(sum % 10 == 0){
                resultTextArea.appendText("PESEL poprawny");
                writeToFile(bPersonalIdentityNumber);
            }
            else resultTextArea.appendText("PESEL niepoprawny, bo modulo wynosi: " + (sum % 10) + "!");
        }else resultTextArea.setText("Niewłaściwy format nr PESEL. Musi składac się z 11 cyfr.");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exitButton.setOnAction(event -> {
            System.exit(0);
        });
    }    
    private void writeToFile(byte[] bPersonalIdentityNumber){
        try{
            Files.write(Paths.get("Wynik.txt"), bPersonalIdentityNumber, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Alert createdFileAlert = new Alert(AlertType.INFORMATION);
            createdFileAlert.setHeaderText("Zapisano do pliku w katalogu z programem.");
            createdFileAlert.showAndWait();
        }catch(IOException e){
            Logger.getLogger(LuhnAlgorithmFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

