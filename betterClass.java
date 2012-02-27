import java.io.*;
public class betterClass
{
    private int idnumber;
    private String name;
    public betterClass()
    {
	idnumber=-1;
	name="";
    }
    public betterClass(betterClass bc)//clone a given object
    {
	idnumber=bc.getID();
	name=bc.getName();
    }
    public void setID(int idnumber){this.idnumber=idnumber;}
    public int getID(){return(idnumber);}
    public String toString()
    {
	return "IDnumber="+idnumber+" Name:"+name;
    }
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public static void main(String args[])
    {
	/*
add circuitry to handle the command line input.
be able to pass the ID name & ID Number when calling the program
*/


	System.out.println("betterClassSTART");
	betterClass bc=new betterClass();
	System.out.println("[0] "+bc.toString());
	bc.setID(69);
	System.out.println("[1] "+bc.toString());
	System.out.println("[2] getID="+bc.getID());
	bc.setName("A Name");
	System.out.println("[3] getName="+bc.getName());
	System.out.println("[4] "+bc.toString());
	System.out.println("betterClassEND");

	betterClass newClass=new betterClass(bc);
	System.out.println(newClass.toString());
    }
}