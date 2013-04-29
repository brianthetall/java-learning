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
    private int numberOfRows;

    public Tree(ArrayList<Integer> values, int rowCount){
	tree = new Value[values.size()];

	System.out.println("Rows:"+rowCount);

	for(int i=0;i<tree.length;i++){
	    tree[i]=new Value(values.get(i).intValue());
	}

	int cursor=tree.length;
	
	/*
	for(int j=1 ; j<rowCount ; j++){
	    for(int i=tree.length-(rowCount-j+1)*j ; i>tree.length-rowCount-rowCount-1 ; i--){
	*/	
	/*
	    tree[i].setLeft(tree[i+1]);
	    tree[i].setRight(tree[i+2]);
	    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	    tree[i+1].setLeft(tree[i+3]);
	    tree[i+1].setRight(tree[i+4]);

	    tree[i+2].setLeft(tree[i+4]);
	    tree[i+2].setRight(tree[i+5]);
	    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	    tree[i+3].setLeft(tree[i+6]);
	    tree[i+3].setRight(tree[i+7]);
	    
	    4:7;
	    4:8;
	    
	    5:8;
	    5:9;
	    ;;;;;;;;;;;;;;;;;;;;;;;;
	    6:10;
	    6:11;

	    7:11;
	    7:12;

	    8:12;
	    8:13;
	    
	    9:13;
	    9:14;
	   
	    tree[i].setLeft(tree[i+

	}
	*/

	/**
	 * Start at first row above bottom row
	 * Set the Min/Max instance variables
	 * Keep doing this for every row going up
	 * The Root node is the solution
	 */

    }

    private int resolveRow(int index){
	return -1;//for now...
    }

    public static void main(String args[]){
		/**
		 * Count the # of rows during the file READin
		 */
	if(args.length!=1){
	    System.out.println("Tree <input file of ints>");
	    System.exit(-1);
	}

	ArrayList<Integer> buffer=null;
	int rows=0;

	try{
	    int rowCount=0;
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0]))));
	    buffer=new ArrayList<Integer>(1);
	    String temp;

	    while( (temp = br.readLine()) != null){
		rows++;
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

	new Tree(buffer,rows);
    }
}
