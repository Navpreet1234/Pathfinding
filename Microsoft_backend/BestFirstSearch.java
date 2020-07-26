
// package front;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author hp
 */
public class BestFirstSearch {
    private int[][] graph;
    private int[] src;//// src[0]=row val of matrix.....
                      //// src[1]=col val of matrix.....src[2]=weight........
    private int[] det; /// same as src...
    private String typeheuristic;
    private String output;
    private boolean isdiagonal;

    public BestFirstSearch(int[][] graph, int[] src, int[] det, String typeheuristic, boolean isdiagonal) {
        this.src = src;
        this.det = det;
        this.graph = graph;
        this.typeheuristic = typeheuristic;
        this.isdiagonal = isdiagonal;
    }

    public class pair implements Comparable<pair> {
        int i;
        int j;
        int g;
        String st;
        double h;
        double f;

        pair(int src, int par, int wsf, String st, double heuristic) {
            this.i = src;
            this.j = par;
            this.st = st;
            this.g = wsf;
            h = heuristic;
        }

        public int compareTo(pair other) {
            if (h < other.h)
                return (-1);
            else
                return 1;
        }
    }

    public double heuristic(int x1, int y1, int x2, int y2) {
        if (typeheuristic == "Euclidean") {
            return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        } else if (typeheuristic == "Diagonal") {
            return Math.max((double) Math.abs(x1 - x2), (double) Math.abs(y1 - y2));
        } else {
            return (double) Math.abs(x1 - x2) + (double) Math.abs(y1 - y2);
        }
    }

    public void bestfirstAlgo() {
        int r = graph.length, c = graph[0].length;
        PriorityQueue<pair> que = new PriorityQueue<>();

        boolean[][] vis = new boolean[r][c];
        double h1 = heuristic(1, 1, 2, 2);
        que.add(new pair(src[0], src[1], 0, "", h1));

        while (que.size() > 0) {
            pair fro = que.remove();
            int x = fro.i;
            int y = fro.j;
            int wsf = fro.g;
            String st = fro.st;
            // if(vis[x][y])continue;
            vis[x][y] = true;
            if (det[0] == x && det[1] == y) {
                output = st;
                break;
            }
            wsf++;
            if (x > 0 && !vis[x - 1][y] && graph[x - 1][y] != -1) {
                vis[x - 1][y] = true;
                double h = heuristic(x - 1, y, det[0], det[1]);
                que.add(new pair(x - 1, y, wsf, st + "U", h));
            }
            if (y > 0 && !vis[x][y - 1] && graph[x][y - 1] != -1) {
                vis[x][y - 1] = true;
                double h = heuristic(x, y - 1, det[0], det[1]);
                que.add(new pair(x, y - 1, wsf, st + "L", h));
            }
            if (x < r - 1 && !vis[x + 1][y] && graph[x + 1][y] != -1) {
                vis[x + 1][y] = true;
                double h = heuristic(x + 1, y, det[0], det[1]);
                que.add(new pair(x + 1, y, wsf, st + "D", h));
            }
            if (y < c - 1 && !vis[x][y + 1] && graph[x][y + 1] != -1) {
                vis[x][y + 1] = true;
                double h = heuristic(x, y + 1, det[0], det[1]);
                que.add(new pair(x, y + 1, wsf, st + "R", h));
            }
            if (isdiagonal) {
                if (x > 0 && y < c - 1 && !vis[x - 1][y + 1] && graph[x - 1][y + 1] != -1) {
                    vis[x - 1][y + 1] = true;
                    double h = heuristic(x - 1, y + 1, det[0], det[1]);
                    que.add(new pair(x - 1, y + 1, wsf, st + "E", h));
                }
                if (y > 0 && x > 0 && !vis[x - 1][y - 1] && graph[x - 1][y - 1] != -1) {
                    vis[x - 1][y - 1] = true;
                    double h = heuristic(x - 1, y - 1, det[0], det[1]);
                    que.add(new pair(x - 1, y - 1, wsf, st + "N", h));
                }
                if (x < r - 1 && y > 0 && !vis[x + 1][y - 1] && graph[x + 1][y - 1] != -1) {
                    vis[x + 1][y - 1] = true;
                    double h = heuristic(x - 1, y, det[0], det[1]);
                    que.add(new pair(x + 1, y - 1, wsf, st + "W", h));
                }
                if (y < c - 1 && x < r - 1 && !vis[x + 1][y + 1] && graph[x + 1][y + 1] != -1) {
                    vis[x + 1][y + 1] = true;
                    double h = heuristic(x + 1, y + 1, det[0], det[1]);
                    que.add(new pair(x + 1, y + 1, wsf, st + "S", h));
                }
            }
            // flag -> diagonal calls;
        }

        int[][] ans = conversion();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                System.out.print(ans[i][j]+",");
            }
            System.out.print(" ");
        }
    }

    public int[][] conversion() {
        int i = src[0], j = src[1];
        int ret[][] = new int[output.length()][2];
        for (int x = 0; x < output.length(); x++) {
            char ch = output.charAt(x);
            if (ch == 'U') {
                i--;
            } else if (ch == 'D') {
                i++;
            } else if (ch == 'L') {
                j--;
            } else if (ch == 'R') {
                j++;
            } else if (ch == 'N') {
                i--;
                j--;
            } else if (ch == 'S') {
                i++;
                j++;
            } else if (ch == 'E') {
                i--;
                j++;
            } else if (ch == 'W') {
                i++;
                j--;
            }
            ret[x][0] = i;
            ret[x][1] = j;
        }
        return ret;
    }

}