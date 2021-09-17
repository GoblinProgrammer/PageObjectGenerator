package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class RightPanel {


    private final Text displayAttributeIdText = new Text();
    private final Text displayAttributeClassText = new Text();
    private final GridPane grid = new GridPane();

    public RightPanel() {
        grid.setMinSize(400, 200);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);

        grid.add(displayAttributeIdText, 0, 0);
        grid.add(displayAttributeClassText, 1, 0);
    }

    public Node asNode() {
        return grid;
    }

    public void updateAttributeIdText(String attributeIdText) {
        this.displayAttributeIdText.setText(attributeIdText);
    }
    public void updateAttributeClassText(String attributeClassText) { this.displayAttributeClassText.setText(attributeClassText); }

}
