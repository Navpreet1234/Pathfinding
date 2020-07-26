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
public class dijistra {

    private int [][]graph;
    private int []src;////src[0]=row val of matrix.....
                     ////src[1]=col val of matrix.....src[2]=weight........
    private int []det; ///same as src... 
    private boolean isdiagonal;
    private String output;

	public dijistra(int[][] graph,int[] src,int[] det,boolean isdiagonal){
		this.src=src;
		this.det=det;
		this.graph=graph; 
		 this.isdiagonal=isdiagonal;
	}
  
	public class pair{ 
		int i;
		int j;
		int w;
		int wsf;
			String st;
		pair(int src,int par,int wsf, String st){
			this.i=src;
			this.j=par;
			this.st = st;
			this.wsf=wsf;
		}
	}
	public void dijikstraAlgo(){
	int r = graph.length, c = graph[0].length;
        //System.out.println(r+" "+c);
		PriorityQueue<pair> que=new PriorityQueue<>((pair a,pair b)->{
			return a.wsf - b.wsf;
		});

		boolean[][] vis=new boolean[r][c];
		que.add(new pair(src[0], src[1], 0, ""));
                   //System.out.println(det[0]+" "+det[1]);
		while(que.size() > 0) {
            pair fro = que.remove();
            int x = fro.i;
            int y = fro.j;
            //System.out.print(x+" "+y+" "+fro.st+ "->");
			int wsf = fro.wsf;
            String st = fro.st;
			//if(vis[x][y]) continue;
			vis[x][y] = true;
            if(det[0] == x && det[1] == y) {
                //System.out.println(st);
                output = st;
                break;
            }
			wsf++;
            if(x > 0 && !vis[x - 1][y] && graph[x - 1][y] != -1) {
		vis[x - 1][y]=true;
                que.add(new pair(x - 1, y, wsf, st + "U"));
            }
             if(y > 0 && !vis[x][y - 1] && graph[x][y - 1] != -1) {
				vis[x][y - 1]=true;
                que.add(new pair(x, y - 1, wsf, st + "L"));
            }

              if(x < r - 1  && !vis[x + 1][y] && graph[x + 1][y] != -1) {
				vis[x + 1][y] =true;
                que.add(new pair(x + 1, y, wsf, st + "D"));
            }
             if(y < c - 1  && !vis[x][y +1] && graph[x][y + 1] != -1) {
				vis[x][y + 1]=true;
                que.add(new pair(x, y + 1, wsf, st + "R"));
            }
			if(isdiagonal){
			if(x > 0 && y < c-1 && !vis[x - 1][y+1] && graph[x - 1][y+1] != -1) {
				vis[x - 1][y+1]=true;
                que.add(new pair(x - 1, y+1, wsf, st + "E"));
            }
             if(y > 0 && x > 0 &&!vis[x -1][y - 1] && graph[x - 1][y - 1] != -1) {
				vis[x -1][y - 1] =true;
                que.add(new pair(x-1, y - 1, wsf, st + "N"));
            }
             if(x < r - 1 && y>0 && !vis[x + 1][y-1] && graph[x + 1][y -1] != -1) {
				vis[x + 1][y-1] =true;
                que.add(new pair(x + 1, y-1, wsf, st + "W"));
            }
             if(y < c - 1 && x < r-1 && !vis[x + 1][y + 1] && graph[x + 1][y + 1] != -1) {
				vis[x + 1][y + 1] =true;
                              que.add(new pair(x+1, y + 1, wsf, st + "S"));
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