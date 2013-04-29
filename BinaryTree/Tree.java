import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
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
	    int rowCount=0;
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
	    ArrayList<Integer> buffer=new ArrayList<Integer>(1);
	    String temp;

	    while( (temp = br.readLine()) != null){

		StringTokenizer st = new StringTokenizer(temp);
		buffer.ensureCapacity(buffer.size() + (++rowCount));
		try{
		    while(true)
			buffer.add(new Integer(st.nextToken()));
		}catch(NoSuchElementException e){
		    //there are no more tokens in this tokenizer
		    //ignore Exception and let While-loop continue
		}
	    }

	    /*Works!
	    System.out.println("Buffer.size="+buffer.size());
	    for(int i=0;i<buffer.size();i++)
		System.out.print(buffer.get(i).toString()+" ");
	    */
	    
	}catch(IOException e){
	    System.out.println("File Err"+e.getMessage());
	}

	new Tree(2);
    }
}
