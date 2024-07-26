import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JalanTercepat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> layouList = new ArrayList<>();
        Integer[] anneCoordinate = {0,0};
        Integer[] januariCoordinate = {0,0};

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equals("OK")){
                break;
            }
            layouList.add(line);
        }
        scanner.close();

        char[][] layout = new char[layouList.size()][];
        for (int i = 0; i < layouList.size(); i++) {
            layout[i] = layouList.get(i).toCharArray();
        }

        anneCoordinate = findCoordinate(layout, '^');
        januariCoordinate = findCoordinate(layout, '*');
        findShortestRoute(anneCoordinate, januariCoordinate, layout);
    }

    static Integer[] findCoordinate(char[][] layout, char object){
        Integer[] output = new Integer[2];
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if(layout[i][j] == object){
                    output[0] = i;
                    output[1] = j;
                }
            }
        }
        return output;
    }

    static void findShortestRoute(Integer[] anneCoordinate, Integer[] januariCoordinate, char[][] layout){
        Integer[] anneCoordinateNow = anneCoordinate;
        Integer[] januariCoordinateNow = januariCoordinate;
        Integer[] previousCoordinate = anneCoordinateNow;
        char[][] layoutNow = layout;
        Integer stepCount = 0;

        while (stepCount != 40) {
            if(layoutNow[anneCoordinateNow[0]][anneCoordinateNow[1]-1] == ' ' && anneCoordinateNow[1]-1 != previousCoordinate[1]){
                Integer i = anneCoordinateNow[0];
                Integer j = anneCoordinateNow[1];
                // layoutNow[i][j+1] = ' ';
                layoutNow[i][j] = '^';
                previousCoordinate[1] = anneCoordinateNow[1]-1;
                stepCount++;
                printLayout(layoutNow, stepCount);
            }
            if(layoutNow[anneCoordinateNow[0]][anneCoordinateNow[1]+1] == ' ' && anneCoordinateNow[1]+1 != previousCoordinate[1]){
                Integer i = anneCoordinateNow[0];
                Integer j = anneCoordinateNow[1];
                // layoutNow[i][j-1] = ' ';
                layoutNow[i][j] = '^';
                previousCoordinate[1] = anneCoordinateNow[1]+1;
                stepCount++;
                printLayout(layoutNow, stepCount);
            }
            if(layoutNow[anneCoordinateNow[0]-1][anneCoordinateNow[1]] == ' ' && anneCoordinateNow[0]-1 != previousCoordinate[0]){
                Integer i = anneCoordinateNow[0];
                Integer j = anneCoordinateNow[1];
                // layoutNow[i+1][j] = ' ';
                layoutNow[i][j] = '^';
                previousCoordinate[0] = anneCoordinateNow[0]-1;
                stepCount++;
                printLayout(layoutNow, stepCount);
            }
            if(layoutNow[anneCoordinateNow[0]+1][anneCoordinateNow[1]] == ' ' && anneCoordinateNow[0]+1 != previousCoordinate[0]){
                Integer i = anneCoordinateNow[0];
                Integer j = anneCoordinateNow[1];
                // layoutNow[i-1][j] = ' ';
                layoutNow[i][j] = '^';
                previousCoordinate[0] = anneCoordinateNow[0]+1;
                stepCount++;
                printLayout(layoutNow, stepCount);
            }
        }
    }

    static void printLayout(char[][] layout, Integer stepCount){
        System.out.println();
        System.out.println("Step: " + stepCount);
        for (char[] output : layout) {
            System.out.println(output);
        }
    }
}