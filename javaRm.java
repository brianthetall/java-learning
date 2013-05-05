import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class javaRm{
    public static void main(String args[])throws Exception{
	if(args.length!=1){
	    System.out.println("javaRm <List of Files>");
	    System.exit(-1);
	}
	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
	String line=null;
	while( (line=br.readLine()) != null){
	    File deleteThis = new File(line);
	    if( deleteThis.delete() == false)
		System.out.println(line+" failed to delete");
	}
    }
}