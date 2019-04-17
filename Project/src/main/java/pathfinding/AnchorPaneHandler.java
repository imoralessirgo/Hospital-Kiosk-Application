package pathfinding;

import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import entities.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utilities.Tooltip;


public class AnchorPaneHandler {
    public static double nodeSizeIdle = 16;
    public static double getNodeSizeHighlited = 19;
    List<AnchorPane> anchorPanes;
    private List<Group> groupsForNodes;
    private HashMap<String, Circle> circleFromName;
    private CurrentObjects currentObjects;

    /**
     * Setup anchor panes such that they are in a list and have groups for the node circles
     */
    AnchorPaneHandler(AnchorPane p1, AnchorPane p2, AnchorPane p3, AnchorPane p4, AnchorPane p5, AnchorPane p6) {
        this.anchorPanes = new LinkedList<AnchorPane>();
        anchorPanes.add(p1);
        anchorPanes.add(p2);
        anchorPanes.add(p3);
        anchorPanes.add(p4);
        anchorPanes.add(p5);
        anchorPanes.add(p6);

        this.groupsForNodes = new LinkedList<>(); // add groups for circles
        for (AnchorPane anchorPane : this.anchorPanes) {
            Group group = new Group();
            this.groupsForNodes.add(group);
            anchorPane.getChildren().add(group);
        }
    }

    void setCurrentObjects(CurrentObjects currentObjects) {
        this.currentObjects = currentObjects;
    }

    AnchorPane getAnchorPaneAtFloor(int floor) {
        return this.anchorPanes.get(floor);
    }

    Circle getCircleFromName(String string) {
        return this.circleFromName.get(string);
    }

    void initCircles(LinkedList<LinkedList<Node>> roomsAtEachFloor, ComboBox initialLocationSelect,
                     ComboBox destinationSelect) {
        // ~~~~~~ init circles

        this.circleFromName = new HashMap<>(); // map to get corresponding circles from longnames

        // setup circles for nodes
        for (int i = 0; i < this.groupsForNodes.size(); i++) {
            Group group = this.groupsForNodes.get(i);

            for (Node node : roomsAtEachFloor.get(i)) {
                float x = (float) node.getXcoord();
                float y = (float) node.getYcoord();

                Circle circle = new Circle(x, y, nodeSizeIdle);
                circle.setId(node.getNodeID());

                this.circleFromName.put(node.getLongName(), circle); // setup hashmap

                circle.setOnMouseClicked(e -> {
                    if ((initialLocationSelect.getValue() == null)) {
                        circle.setFill(Color.GREEN);
                        circle.setRadius(getNodeSizeHighlited);
                        this.currentObjects.setInitCircle(circle);
                        initialLocationSelect.getSelectionModel().select(node.getLongName());
                    }
                    else if ((destinationSelect.getValue() == null)) {
                        circle.setFill(Color.RED);
                        circle.setRadius(getNodeSizeHighlited);
                        this.currentObjects.setDestCircle(circle);
                        destinationSelect.getSelectionModel().select(node.getLongName());
                    }
                });
                new Tooltip(circle, node.getLongName());
                group.getChildren().add(circle);
            }
            group.setVisible(true);
        }
    }
}
