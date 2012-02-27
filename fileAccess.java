import java.io.*;
public class fileAccess
{
    public static void main(String args[])
    {
	int temp;
	File f;
	FileReader fr;
	System.out.println("args.length="+args.length);
	for(int i=0;i<args.length;i++){
	    System.out.println("Opening File["+i+"]="+args[i]);
	    //open the file
	    try{
		f=new File(args[i]);
		fr=new FileReader(f);
	    //do something with the files
		while((temp=fr.read())!=-1)
		    System.out.print(temp);//["123"=49 50 51
	    }
	    catch(FileNotFoundException e){
		System.out.println(e.getMessage());
	    }catch(IOException e){
		System.out.println(e.getMessage());
	    }
	}
    }
}
