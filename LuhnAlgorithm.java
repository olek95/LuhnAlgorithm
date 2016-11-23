package luhnalgorithm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LuhnAlgorithm extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LuhnAlgorithmFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setResizable(false);
        stage.setTitle("Sprawdzanie poprawności PESEL");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
