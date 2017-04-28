
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
// Imports for HBox and VBox
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
// Imports for application icon
import javafx.scene.image.Image;

// For drawing the rectangular shaped box with "?"
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Stop;
import javafx.scene.paint.LinearGradient;
import javafx.geometry.Pos;
import javafx.scene.paint.CycleMethod;
import javafx.scene.layout.Priority;

// Drop-down list
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.Collections;

//
import java.util.List;
import java.util.ArrayList;


public class UIProgram extends Application {

    // Constants
    private final String TITLE = "Title of the application";
    private final String BUTTON1 = "Button1";
    private final String BUTTON2 = "Button2";
    private final String LABEL1 = "Label1";
    private final String LABEL2 = "Label2";
    private final String LABEL3 = "Label3";
    private final String OPTION1 = "Option 1";
    private final String OPTION2 = "Option 2";
    private final String OPTION3 = "Option 3";
    private final String OPTION4 = "Option 4";
    
    // Attributes
    // ListView component for the left Vertical Box.
    private ListView listview = new ListView();
    
    
    // add a Horizontal box to the BorderPane panel
    private HBox addHBox() {
        
        HBox hbox = new HBox();
        hbox.setId("hbox");
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #000000;");

        Button button1 = new Button(BUTTON1);
        button1.setId("button1");
        button1.setPrefSize(100, 20);

        Button button2 = new Button(BUTTON2);
        button2.setId("button2");
        button2.setPrefSize(100, 20);
        
        hbox.getChildren().addAll(button1, button2);

        return hbox;
    }

    private VBox addVBox() {
            
        // Store the hyperlinks in a link
        List<Hyperlink> links = new ArrayList<>();
        
        links.add(new Hyperlink("http://www.google.com"));
        links.add(new Hyperlink("http://www.nu.nl"));
        
        // Define the actions when creating on a hyperlink.
        for (Hyperlink hyperlink : links) {
            hyperlink.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent click) {
                    
                    getHostServices().showDocument(hyperlink.getText());
                }
            });
        }
        
        this.listview.getItems().addAll(links);
        
        // Vbox
        VBox vbox = new VBox();
        vbox.setId("vbox");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        vbox.getChildren().addAll(this.listview);

        return vbox;
    }

    private GridPane addGridPane() {
    
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        // Label added to the center grid in column 2, row 2.
        Label label1 = new Label(LABEL1);
        label1.setId("lbl1");
        grid.add(label1, 1, 1);
        
        // Textfield comes next to label1: column 3, row 2.
        TextField textfield1 = new TextField();
        grid.add(textfield1, 2, 1);
        
        //Label added to the center grid in column 2, row 3.
        Label label2 = new Label(LABEL2);
        label2.setId("lbl2");
        grid.add(label2, 1, 2);
        
        // Textfield comes next to label2: column 3, row 3.
        TextField textfield2 = new TextField();
        grid.add(textfield2, 2, 2);
        
        // Label added to the center grid in column 2, row 4.
        Label label3 = new Label(LABEL3);
        label3.setId("lbl3");
        grid.add(label3, 1, 3);
        
        // Textfield added to the center grid in column 3, row 4.
        TextField textfield3 = new TextField();
        grid.add(textfield3, 2, 3);
        
        // Combobox
        ObservableList<String> options =
                FXCollections.observableArrayList(
                    OPTION1,
                    OPTION2,
                    OPTION3,
                    OPTION4
                );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setId("cmb");
        grid.add(comboBox, 2, 4);
        
        // Add a submit button under the textfields and labels
        Button buttonSubmit = new Button();
        buttonSubmit.setId("btnSubmit");
        buttonSubmit.setText("Submit");
        grid.add(buttonSubmit, 1, 4);

        // Define the action of the "buttonSubmit" button
        buttonSubmit.setOnAction(new EventHandler<ActionEvent>() {
     
            @Override
            public void handle(ActionEvent event) {
                String s2 = textfield2.getText(); 
                System.out.println(s2);

                String s1 = textfield1.getText();
                // Add the link if the textfield is not empty and valid
                if (s1.length() > 0 && s1.startsWith("http://")) {
                    addLink(s1);
                }
                
                String s3 = textfield3.getText();
                // Adding the item to a combobox if not empty
                
                if (s3.length() > 0) {
                    comboBox.getItems().addAll(s3);
                    // Sort the list alphabetically
                    Collections.sort(comboBox.getItems());
                }
   
                textfield1.clear();
                textfield2.clear();
                textfield3.clear();
            }
        });

        
        return grid;
       
    }
    
    private void addStackPane(HBox hb) {
        StackPane stack = new StackPane();
        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
            new Stop[] {
            new Stop(0, Color.web("#4d4d4d")),
            new Stop(0.5, Color.web("#8f8f8f")),
            new Stop(1, Color.web("#4d4d4d"))}));
        helpIcon.setStroke(Color.web("#FFFFFF"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);
        
        Text helptext = new Text("?");
        helptext.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helptext.setFill(Color.WHITE);
        helptext.setStroke(Color.web("#FFFFFF"));
        
        stack.getChildren().addAll(helpIcon, helptext);
        stack.setAlignment(Pos.CENTER_RIGHT);
        StackPane.setMargin(helptext, new Insets(0, 10, 0, 0));
        
        hb.getChildren().add(stack);
        HBox.setHgrow(stack, Priority.ALWAYS);
    }
    
    private void addLink(String url) {
        
        Hyperlink link = new Hyperlink(url);
        link.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent click) {
                getHostServices().showDocument(link.getText());
                //openBrowser(link.getText());
            }
        });
        this.listview.getItems().add(link);
    }
        
        

    @Override
    public void start(Stage primaryStage) {
               
        // BorderPane has 5 areas (Top / Bottom / Left / Right / Center
        BorderPane root = new BorderPane();
        
        // Top horizontal box
        HBox hbox = addHBox();
        root.setTop(hbox);
        
        // Add a stack to the HBox in the top region (contains "help" rectangle)
        addStackPane(hbox);
        
        // Vertical side box
        VBox vbox = addVBox();
        root.setLeft(vbox);

        // In the center, we add a GridPane
        root.setCenter(addGridPane());
        

        Scene scene = new Scene(root, 800, 450);
        
        String cssPath = "/stylesheet.css";
        
        scene.getStylesheets().addAll(cssPath);

        //primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle(TITLE);
        //primaryStage.setFullScreen(true);
        primaryStage.getIcons().add(new Image("pokeball.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        
        UIProgram ui = new UIProgram();
        ui.launch();
    }    
    
}
