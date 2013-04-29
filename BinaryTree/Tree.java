import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.lang.Integer;

public class Tree{

    private Value[] tree;

    public Tree(int size){
	tree = new Value[size];
    }

    public static void main(String args[]){

	if(args.length!=1){
	    System.out.println("Tree <input file of ints>");
	    System.exit(-1);
	}

	try{
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
	    ArrayList<Integer> buffer=new ArrayList<Integer>();
	    for(int i=0; i<3 ;i++)
		System.out.println(br.readLine());
	    
	}catch(IOException e){
	    System.out.println("File Err"+e.getMessage());
	}

	new Tree(2);
    }
}
