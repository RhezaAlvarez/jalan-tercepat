/* Test case
####################
#                 *# 
########## #########
#      #    #      #
# ###### ###########
#                  #
# ##################
#                ^ #
####################
OK   
*/

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
        String[] shortestPath = findShortestRoute(anneCoordinate, januariCoordinate, layout);
        
        if (shortestPath != null) {
            for (String step : shortestPath) {
                System.out.println(step);
            }
        } else {
            System.out.println("Tidak ada jalan.");
        }
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

    static int[] rowMoves = {-1, 1, 0, 0};
    static int[] colMoves = {0, 0, -1, 1};
    static String[] directions = {"Atas", "Bawah", "Kiri", "Kanan"};

    static String[] findShortestRoute(Integer[] start, Integer[] end, char[][] layout) {
        int n = layout.length;
        int m = layout[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] directionGrid = new int[n][m];
        Point[] queue = new Point[n * m];
        int front = 0, rear = 0;
        
        queue[rear++] = new Point(start[0], start[1], 0);  // Masukkan titik awal ke antrian
        visited[start[0]][start[1]] = true;
    
        while (front < rear) {  // Loop berjalan selama ada elemen dalam antrian
            Point point = queue[front++];  // Ambil titik dari depan antrian
            if (point.x == end[0] && point.y == end[1]) {
                return constructPath(start, end, directionGrid, directions);  // Jika titik tujuan ditemukan, konstruksi jalur
            }
    
            for (int i = 0; i < 4; i++) {  // Periksa semua kemungkinan gerakan (atas, bawah, kiri, kanan)
                int newRow = point.x + rowMoves[i];
                int newCol = point.y + colMoves[i];
    
                if (isValidMove(newRow, newCol, n, m, layout, visited)) {
                    queue[rear++] = new Point(newRow, newCol, point.dist + 1);  // Masukkan titik baru ke antrian
                    visited[newRow][newCol] = true;
                    directionGrid[newRow][newCol] = i;
                }
            }
        }
        return null; // Tidak ada jalur yang ditemukan
    }

    static boolean isValidMove(int x, int y, int n, int m, char[][] layout, boolean[][] visited) {
        return x >= 0 && x < n && y >= 0 && y < m && layout[x][y] != '#' && !visited[x][y];
    }

    static String[] constructPath(Integer[] start, Integer[] end, int[][] directionGrid, String[] directions) {
        String[] path = new String[100];
        int pathLength = 0;
        int x = end[0];
        int y = end[1];
        int direction = directionGrid[x][y];
        int stepCount = 0;

        while (x != start[0] || y != start[1]) {
            int newDirection = directionGrid[x][y];
            if (newDirection == direction) {
                stepCount++;
            } else {
                path[pathLength++] = directions[direction] + " " + stepCount + " langkah";
                stepCount = 1;
                direction = newDirection;
            }

            if (direction == 0) {
                x++;
            } else if (direction == 1) {
                x--;
            } else if (direction == 2) {
                y++;
            } else if (direction == 3) {
                y--;
            }
        }
        path[pathLength++] = directions[direction] + " " + stepCount + " langkah";
        
        String[] result = new String[pathLength];
        for (int i = 0; i < pathLength; i++) {
            result[i] = path[pathLength - i - 1];
        }
        return result;
    }

    static class Point {
        int x, y, dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}