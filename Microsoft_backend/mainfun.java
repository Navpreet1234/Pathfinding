import java.util.*; 
import java.io.File; 
import java.io.IOException;
import java.io.FileNotFoundException; 

public class mainfun {
    public static int maxsz = 1000;
    public static void main(String[] args)  throws IOException{

        ArrayList<ArrayList<String>> data=parseInput();

        int src=-1;
        int desti=-1;
        int[][] matrix=new int[23][48];
        int []source=new int[3];       ////src[0]=row val of matrix.....src[1]=col val of matrix
                                     ////.....src[2]=weight
        int []destination=new int[3];///same as source
        String typeheuristic=data.get(5).get(0);
        String algotype = data.get(3).get(0);
        boolean isdiagonal=data.get(4).contains("Allow_Diagonal");
 
        source[0]=Integer.parseInt(data.get(0).get(0));
        source[1]=Integer.parseInt(data.get(0).get(1));;
        source[2]=80;///weight
        destination[0]=Integer.parseInt(data.get(1).get(0));;
        destination[1]=Integer.parseInt(data.get(1).get(1));;
        destination[2]=100;//weight
        
        ArrayList<ArrayList<Integer>> obs=new ArrayList<>();///// nX2 matrix...
        for(int i=0;i<data.get(2).size();i+=2){
            ArrayList<Integer> d=new ArrayList<>();
            d.add(Integer.parseInt(data.get(2).get(i)));
            d.add(Integer.parseInt(data.get(2).get(i+1)));
            obs.add(d);
        }
        
        int[][] obstacle =new int[obs.size()][2];
        for(int i=0;i<obs.size();i++){
            obstacle[i][0]=obs.get(i).get(0);
            obstacle[i][1]=obs.get(i).get(1);
        }
        // System.out.println(data);

        inputData(matrix,obstacle,source,destination,algotype,isdiagonal);
        // dijistra d=new dijistra(matrix,source,destination,isdiagonal);

         callalgo(matrix,algotype,obstacle,source,destination,isdiagonal,typeheuristic);
    }
 
    public static void inputData(int[][] matrix,int[][] obstacle,int[] source,int []destination,String algotype,boolean isdiagonal){
        int src=-1;
        int desti=-1;
        int n=matrix.length, m= matrix[0].length;   
        Scanner sc= new Scanner(System.in);
     
      for(int i=0;i<obstacle.length;i++){
               int x=obstacle[i][0];
               int y=obstacle[i][1];
               if(x>=0&&y>=0&&x<matrix.length&&y<matrix[0].length){
                    matrix[x][y]=-1;
               }
            }
        int a=source[0];
        int b=source[1];

        int c=destination[0];
        int d=destination[1];
     
                 matrix[a][b]=1;
                 matrix[c][d]=2;
    }

     public static void callalgo(int[][] matrix,String algotype ,int[][] obstacle,int[] source,int []destination,boolean isdiagonal, String typeheuristic){
            //  System.out.print("sd");
         if(algotype.equals("astaralgo")){
             astaralgo a=new astaralgo(matrix,source,destination,typeheuristic,isdiagonal);
             a.astarAlgo();
         }
         else if(algotype.equals("BestFirstSearch")){
         BestFirstSearch b=new BestFirstSearch(matrix,source,destination,typeheuristic,isdiagonal);
         b.bestfirstAlgo();
         }
         else if(algotype.equals("bfs")){
         bfs s=new bfs(matrix,source,destination,isdiagonal);
         s.bfsalgo();
         }
         else{
           dijistra d=new dijistra(matrix,source,destination,isdiagonal);
         d.dijikstraAlgo();
         }
     }

     public static  ArrayList<ArrayList<String>> parseInput()  throws IOException{
         File file_ = 
      new File("file.txt"); 
      Scanner sc = new Scanner(file_); 
      String[] arr=sc.nextLine().split(" ");
      ArrayList<ArrayList<String>> data=new ArrayList<>(); 

      for(String str: arr){
          String[] al=str.split(",");
          ArrayList<String> s=new ArrayList<>();
          for(String d: al)s.add(d);
          data.add(s);
      }

        return data;
     } 
}