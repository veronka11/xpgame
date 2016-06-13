package gui1;

import static gui1.GUI1.budova;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class BudovyController implements Initializable {

    int vybrany = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void klik(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        System.out.println(budova);
        budova = "HomeGuiController.java";
        //label.setText("Hello World!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomeGui.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void vyber1(MouseEvent event) {
        if (vybrany == -1) {
            vybrany = 1;
            budova = "obr1.png";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: red; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        } else if (vybrany == 1) {
            vybrany = -1;
            budova = "";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: white; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        }
    }

    @FXML
    private void vyber2(MouseEvent event) {
        if (vybrany == -1) {
            vybrany = 2;
            budova = "obr2.png";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: red; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        } else if (vybrany == 2) {
            vybrany = -1;
            budova = "";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: white; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        }
    }

    @FXML
    private void vyber3(MouseEvent event) {
        if (vybrany == -1) {
            vybrany = 3;
            budova = "obr3.png";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: red; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        } else if (vybrany == 3) {
            vybrany = -1;
            budova = "";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: white; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        }
    }

    @FXML
    private void vyber4(MouseEvent event) {
        if (vybrany == -1) {
            vybrany = 4;
            budova = "obr4.png";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: red; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        } else if (vybrany == 4) {
            vybrany = -1;
            budova = "";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: white; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        }
    }

    @FXML
    private void vyber5(MouseEvent event) {
        if (vybrany == -1) {
            vybrany = 5;
            budova = "obr5.png";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: red; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        } else if (vybrany == 5) {
            vybrany = -1;
            budova = "";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: white; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        }
    }

    @FXML
    private void vyber6(MouseEvent event) {
        if (vybrany == -1) {
            vybrany = 6;
            budova = "obr6.png";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: red; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        } else if (vybrany == 6) {
            vybrany = -1;
            budova = "";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: white; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        }
    }

    @FXML
    private void vyber7(MouseEvent event) {
        if (vybrany == -1) {
            vybrany = 7;
            budova = "obr7.png";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: red; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        } else if (vybrany == 7) {
            vybrany = -1;
            budova = "";
            Node target = (Node) event.getTarget(); // you find where the user click
            if (target instanceof AnchorPane) {
                ((AnchorPane) target).setStyle("-fx-border-color: white; -fx-border-style:dashed; -fx-border-radius:15;"); // apply it like you already know
            }
        }
    }

    @FXML
    private void Vyber(ActionEvent event) throws IOException {
        if (budova.length() != 0) {
            System.out.println("vybral si si budovu!");
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomeGui.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
    }

}
