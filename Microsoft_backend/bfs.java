// package front;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;
/**
 *
 * @author hp
 */
public class bfs {
    private int [][]graph;
  private int[] src;
  private int[] det;
  private String output;
  private boolean isdiagonal;
  
    public bfs(int[][] graph,int[] src,int[] det,boolean isdiagonal){
       this.graph=graph;
       this.src=src;
       this.det=det;
       this.isdiagonal=isdiagonal;
    }
    class pair {
        int i, j;
        String st = "";
        pair(int i, int j, String str) {
            this.i = i;
            this.j = j;
            this.st = str;
        }
    }

    public void bfsalgo() {
        int r = graph.length, c = graph[0].length;
        boolean[][] vis = new boolean[r][c];
        LinkedList<pair> que = new LinkedList();
        que.add(new pair(src[0], src[1],""));
        while(que.size() > 0) {
            pair fro = que.remove();
            int x = fro.i;
            int y = fro.j;
            String st = fro.st;
            if(det[0] == x && det[1] == y) {
                output = st;
                break;
            }
            if(x > 0 && !vis[x - 1][y] && graph[x - 1][y] != -1) {
                que.add(new pair(x - 1, y, st + "U"));
                vis[x - 1][y] = true;
            }
             if(y > 0 && !vis[x][y - 1] && graph[x][y - 1] != -1) {
                que.add(new pair(x, y - 1, st + "L"));
                vis[x][y - 1] = true;
            }
             if(x < r - 1 && !vis[x + 1][y] && graph[x + 1][y] != -1) {
                que.add(new pair(x + 1, y, st + "D"));
                vis[x + 1][y] = true;
            }
             if(y < c - 1 && !vis[x][y + 1] && graph[x][y + 1] != -1) {
                que.add(new pair(x, y + 1, st + "R"));
                vis[x][y + 1] = true;
            }
            if(isdiagonal){
			if(x > 0 && y < c-1 && !vis[x - 1][y+1] && graph[x - 1][y+1] != -1) {
                que.add(new pair(x - 1, y+1, st + "E"));
                vis[x - 1][y +1] = true;
            }
             if(y > 0 && x > 0 &&!vis[x -1][y - 1] && graph[x - 1][y - 1] != -1) {
                que.add(new pair(x-1, y - 1,  st + "N"));
                vis[x - 1][y -1] = true;
            }
             if(x < r - 1 && y>0 && !vis[x + 1][y-1] && graph[x + 1][y -1] != -1) {
                que.add(new pair(x + 1, y-1, st + "W"));
                vis[x + 1][y -1] = true;
            }
             if(y < c - 1 && x < r-1 && !vis[x + 1][y + 1] && graph[x + 1][y + 1] != -1) {
                que.add(new pair(x+1, y + 1, st + "S"));
                vis[x +1][y+1] = true;
            }
	}
			
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