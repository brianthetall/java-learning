import java.io.*;
public class simpleClass
{
    private int idnumber;
    public simpleClass()
    {
	idnumber=-1;
    }
    public void setID(int idnumber){this.idnumber=idnumber;}
    public int getID(){return(idnumber);}
    public String toString()
    {
	return "IDnumber="+idnumber;
    }
    public static void main(String args[])
    {
	System.out.println("simpleClassSTART");
	simpleClass sc=new simpleClass();
	System.out.println("[0] "+sc.toString());
	sc.setID(69);
	System.out.println("[1] "+sc.toString());
	System.out.println("[2] getID="+sc.getID());
	System.out.println("simpleClassEND");
    }
}