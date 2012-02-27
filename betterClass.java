/*
java betterClass <idNumber> <name...(s)>
*/

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
    public betterClass(int idnumber,String name)//tested
    {
	this.idnumber=idnumber;
	this.name=name;
    }
    public betterClass(betterClass bc)//TESTED
    {
	idnumber=bc.getID();
	name=bc.getName();
    }
    public betterClass copy()//need to Test
    {
	return new betterClass(idnumber,name);
    }
    public void setID(int idnumber){this.idnumber=idnumber;}//TESTED
    public int getID(){return(idnumber);}//TESTED
    public String toString()//TESTED
    {
	return "IDnumber="+idnumber+" Name:"+name;
    }
    public String getName(){return name;}//TESTED
    public void setName(String name){this.name=name;}//TESTED
    public static void main(String args[])
    {
	System.out.println("betterClassSTART");
	betterClass bc=new betterClass();
	Integer i=new Integer(args[0]);
	String name="";
	int argc=args.length;
	System.out.println("[0] "+bc.toString());
	bc.setID((new Integer(args[0])).intValue());//what is java version of atoi()?
	System.out.println("[1] "+bc.toString());
	System.out.println("[2] getID="+bc.getID());
	//form all remaining arguments into the name string
	for(int j=1;j<argc;j++)
	    name=name.concat(args[j]);
	System.out.println("name="+name);
	bc.setName(name);
	System.out.println("[3] getName="+bc.getName());
	System.out.println("[4] "+bc.toString());
	betterClass newClass=new betterClass(bc);
	System.out.println(newClass.toString());

	System.out.println("betterClassEND");
    }
}