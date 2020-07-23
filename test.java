import java.util.*; 
import java.io.File; 
import java.io.IOException;
import java.io.FileNotFoundException; 
public class test 
{
    public static void main(String[] args) throws IOException{
File file_ = 
      new File("E:\\workshops\\Doubts\\Microsoft\\Visualizer\\Visualizer\\Microsoft_backend\\file.txt"); 
      Scanner sc = new Scanner(file_); 
      String[] arr=sc.nextLine().split(" ");
      ArrayList<ArrayList<String>> data=new ArrayList<>(); 

      for(String str: arr){
          String[] al=str.split(",");
          ArrayList<String> s=new ArrayList<>();
          for(String d: al)s.add(d);
          data.add(s);
      }

      System.out.println(data);
      


    }
}