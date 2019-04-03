import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphJUnit extends TestCase {
    public void testShortestPath() {
        Node n0 = new Node("N0", 0, 0, "", "", "", "", "");
        Node n1 = new Node("N1", 1, 1, "", "", "", "", "");
        Node n2 = new Node("N2", 2, 2, "", "", "", "", "");
        Node n3 = new Node("N3", 4, 1, "", "", "", "", "");
        Node n4 = new Node("N4", 1, 4, "", "", "", "", "");
        Node n5 = new Node("N5", 3, 2, "", "", "", "", "");
        Node n6 = new Node("N6", 1, 0, "", "", "", "", "");
        Node n7 = new Node("N7", 2, 0, "", "", "", "", "");
        Node n8 = new Node("N8", 3, 0, "", "", "", "", "");
        Node n9 = new Node("N9", 4, 0, "", "", "", "", "");
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(n0);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);
        nodes.add(n7);
        nodes.add(n8);
        nodes.add(n9);
        Graph g = new Graph(nodes);
        g.addBiEdge("N0", "N1");
        g.addBiEdge("N1", "N2");
        g.addBiEdge("N1", "N4");
        g.addBiEdge("N2", "N4");
        g.addBiEdge("N2", "N5");
        g.addBiEdge("N3", "N5");
        g.addBiEdge("N0", "N6");
        g.addBiEdge("N6", "N7");
        g.addBiEdge("N7", "N8");
        g.addBiEdge("N8", "N9");
        g.addBiEdge("N9", "N3");
        List<String> path = g.shortestPath("N0", "N3");
        //g.checkEdges();
        List<String> expected = new LinkedList<>();
        expected.add("N0");
        expected.add("N6");
        expected.add("N7");
        expected.add("N8");
        expected.add("N9");
        expected.add("N3");
        assertEquals(expected, path);
    }

    public void testRealNodes() {
        Node n0 = new Node("N0", 1580, 2538, "", "", "", "", "");
        Node n1 = new Node("N1", 1395, 2674, "", "", "", "", "");
        Node n2 = new Node("N2", 1532, 2777, "", "", "", "", "");
        Node n3 = new Node("N3", 1591, 2560, "", "", "", "", "");
        Node n4 = new Node("N4", 1590, 2604, "", "", "", "", "");
        Node n5 = new Node("N5", 1590, 2745, "", "", "", "", "");
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(n0);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        Graph g = new Graph(nodes);
        g.addBiEdge("N0", "N1");
        g.addBiEdge("N1", "N5");
        g.addBiEdge("N3", "N4");
        g.addBiEdge("N2", "N3");
        g.addBiEdge("N4", "N1");
        g.addBiEdge("N3", "N0");
        List<String> path = g.shortestPath("N5", "N0");
//        List<String> expected = new LinkedList<>();
//        expected.add("N5");
//        expected.add("N1");
//        expected.add("N0");
        System.out.println(path);
        //assertEquals(path, expected);
    }
}
