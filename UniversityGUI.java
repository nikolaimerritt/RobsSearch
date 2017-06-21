import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class UniversityGUI {
    private static TextField txtFieldRanking;
    private static TextField txtFieldName;
    private static TextField txtFieldCountry;
    private static TextField txtFieldSearch;
    private static ListView<University> uniListView;
    private static ArrayList<University> uniArrList = new ArrayList<University>();

    public static void main(String args[]) {
        launchFX();
    }

    private static void launchFX() {
        // Initialises JavaFX
        new JFXPanel();
        // Runs initialisation on the JavaFX thread
        Platform.runLater(() -> initialiseGUI());
    }

    private static void initialiseGUI() {
        Stage stage = new Stage();
        stage.setTitle("Top Universities");
        stage.setResizable(false);
        Pane rootPane = new Pane();
        stage.setScene(new Scene(rootPane));
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setOnCloseRequest((WindowEvent we) -> terminate());
        stage.show();

        uniListView =  new ListView<University>();
        uniListView.setLayoutX(700);
        uniListView.setLayoutY(50);
        rootPane.getChildren().add(uniListView);

        Label label = new Label("Enter details of top university");
        label.setLayoutX(50);
        label.setLayoutY(50);
        rootPane.getChildren().add(label);

        txtFieldRanking = new TextField();
        txtFieldRanking.setLayoutX(50);
        txtFieldRanking.setLayoutY(100);
        txtFieldRanking.setPrefWidth(400);
        txtFieldRanking.setPromptText("Enter ranking");        //Click off the GUI and you can see this
        rootPane.getChildren().add(txtFieldRanking);

        txtFieldName = new TextField();
        txtFieldName.setLayoutX(50);
        txtFieldName.setLayoutY(150);
        txtFieldName.setPrefWidth(400);
        txtFieldName.setPromptText("Enter name");        //Click off the GUI and you can see this
        rootPane.getChildren().add(txtFieldName);

        txtFieldCountry = new TextField();
        txtFieldCountry.setLayoutX(50);
        txtFieldCountry.setLayoutY(200);
        txtFieldCountry.setPrefWidth(400);
        txtFieldCountry.setPromptText("Enter country");        //Click off the GUI and you can see this
        rootPane.getChildren().add(txtFieldCountry);


        txtFieldSearch = new TextField();
        txtFieldSearch.setLayoutX(50);
        txtFieldSearch.setLayoutY(250);
        txtFieldSearch.setPrefWidth(400);
        txtFieldSearch.setPromptText("Enter search text");
        rootPane.getChildren().add(txtFieldSearch);

        // add an event listener that is called whenever the text inside the textfield changess
        txtFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase(); // or txtFieldSearch.getText();

            uniListView.getItems().clear();

            // loop over each university in the array list
            uniArrList.forEach(university -> {
                // if it matches our search criteria, add it to the filtered list
                if(university.toString().toLowerCase().contains(searchText)){
                    uniListView.getItems().add(university);
                }
            });
        });

        Button btn = new Button();
        btn.setText("Add");
        btn.setLayoutX(50);
        btn.setLayoutY(500);
        btn.setOnAction((ActionEvent ae) ->addNewItem());
        rootPane.getChildren().add(btn);
    }

    private static void addNewItem() {
        int ranking = Integer.parseInt(txtFieldRanking.getText());
        String name = txtFieldName.getText();
        String country = txtFieldCountry.getText();
        uniArrList.add(new University(ranking, name, country));

        uniListView.getItems().clear();             //deletes everything in the ListView

        for(University uni : uniArrList) {         //for every University object in uniArrayList
            uniListView.getItems().add(uni);       //put each University object into the ListView
        }
    }

    private static void terminate()
    {
        System.out.println("bye bye!");
        System.exit(0);
    }
}
