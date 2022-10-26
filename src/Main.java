import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner keyb = new Scanner(System.in);
        System.out.println("Which graph do you want to choose?");
        String filename = keyb.nextLine();

        Map<String, List<String>> graph2 = readFileIntoMap(filename);
        System.out.print("Here is the graph " + graph2);
        System.out.println();

        //System.out.println(graph2);
        ArrayList<String> pathfinder = searchForPath(graph2);
        System.out.println("The path is " + pathfinder);
//        while(){
//            System.out.println("New starting point? \n " +
//                "Or Quit");
//            Scanner use = new Scanner(System.in);
//            String chosen = use.nextLine();
//            String go = "New starting point?";
//            if(chosen.equals(go)){
//                pathfinder = searchForPath(graph2);
//            }
//        }








    }


    private static ArrayList<String> searchForPath(Map<String, List<String>> graph2) {
        ArrayList<String> path = new ArrayList<>();
        Scanner keyb = new Scanner(System.in);
        System.out.println("What is the starting point?");
        String start = keyb.nextLine().toUpperCase();
        System.out.println("What is the endpoint?");
        String finish = keyb.nextLine().toUpperCase();
        path = bfSearch(graph2, start, finish);
        //System.out.println(path + "\n" + start + finish + "\n");
        System.out.println("The system searches through the queue" + path);
        System.out.println("The path starts at " + start + " it finishes at " + finish);



        return path;
    }


    private static ArrayList<String> bfSearch(Map<String, List<String>> graph2, String start, String finish) {
        ArrayDeque<ArrayList<String>> queue = new ArrayDeque<>();
        ArrayList<String> currentPath = new ArrayList<>();
        currentPath.add(start);
        queue.add(currentPath);
        while (queue.size() != 0) {
            currentPath = queue.poll();
            String lastNode = currentPath.get(currentPath.size() - 1);
            List<String> nabors = graph2.get(lastNode);
            for (String nabor : nabors) {
                if (!currentPath.contains(nabor)) {
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(nabor);
                    //System.out.println("The system connects to " + newPath);
                    if (nabor.equals(finish)) {
                        return (ArrayList<String>) newPath;
                    } else {
                        queue.add((ArrayList<String>) newPath);
                    }

                }

            }
        }
        return null;
    }

    private static Map<String, List<String>> readFileIntoMap(String filename) {
        Map<String, List<String>> graph = new HashMap<>();

        try (Scanner filScanner = new Scanner(new File(filename))) {
            while (filScanner.hasNext()) {
                String lineFromFile = filScanner.nextLine();
                String[] parts = lineFromFile.split(",");
                String key = parts[0];
                List<String> connections = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    connections.add(parts[i]);
                }
                graph.put(key, connections);
            }
        } catch (Exception e) {
            System.out.println("Couldn't open file");
        }
        return graph;
    }
}
