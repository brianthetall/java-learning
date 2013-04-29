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

	tree = new Value[values.size()];//setup memory to hold Values

	for(int i=0;i<tree.length;i++){//populate tree with Value objects
	    tree[i]=new Value(values.get(i).intValue());//only Value.value is set
	}

	{//build the tree by linking the list
	    int cursor=tree.length-(rowCount+1);//start on 2nd row from bottom
	    int currentRow=rowCount-1;
	    for(int j=rowCount-1 ; j>0 ; j--){
		//	    System.out.println("Row:"+currentRow+" Cursor="+cursor);
		for(int i=cursor ; i > cursor-currentRow ; i--){
		    tree[i].setLeft(tree[i+currentRow]);
		    tree[i].setRight(tree[i+currentRow+1]);
		}
		
		cursor -= currentRow;
		currentRow--;
	    }
	}

	for(int i=tree.length-1;i>=0;i--){
	    if(false == tree[i].findMaxValue())//run from bottom up
		System.out.println("Invalid Entry Exception");
	}

	/*
	for(int i=0;i<tree.length;i++)
	    System.out.println("["+i+"] Value="+tree[i].getValue()+" Max Under This:"+tree[i].getMaxValue() );
	*/
	System.out.println("Max Sum of This Triangle-File="+tree[0].getMaxValue());//print ANSWER

    }

    public static void main(String args[]){

	if(args.length!=1){
	    System.out.println("Tree <triangle input file of ints>");
	    System.exit(-1);
	}

	ArrayList<Integer> buffer=null;
	int rows=0;

	try{//Load file into ArrayList, record number of rows & create a Tree-object
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
			buffer.add(new Integer(st.nextToken()));//add value into List
		}catch(NoSuchElementException e){
		    //there are no more tokens in this tokenizer
		    //ignore Exception and let While-loop continue
		}
	    }
	    
	}catch(IOException e){
	    System.out.println("File Err"+e.getMessage());
	}

	new Tree(buffer,rows);//create Tree with List & number of rows
    }
}
