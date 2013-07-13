import java.lang.String;
import java.lang.Integer;

public class HashCodeTests{

    public static class ThingWithHash{
	
	private int number;
	private int hash=0;//This class is immutable, so it can save time after hashCode has been called once
	
	public ThingWithHash(){
	    this(1234);
	}
	
	public ThingWithHash(int number){
	    this.number=number;
	}

	@Override	
	public String toString(){
	    return new String(new Integer(number).toString());
	}
	
	@Override
	public boolean equals(Object o){
	    if(o==null||!(o instanceof ThingWithHash))
		return false;
	    ThingWithHash q=(ThingWithHash)o;
	    if(q.getNumber()==this.getNumber())
		return true;
	    return false;
	}

	@Override
	public int hashCode(){

	    if(hash!=0)
		return hash;
	    else{
		int retval=0;
		retval *= 67;//some odd number; not used unless there are more than one instance variables.
		retval += number;
		hash=retval;
	    }
	    
	    return hash;

	}

	public int getNumber(){return number;}
    }

    public static class Thing{
	private int number;
	public Thing(){
	    this(69);
	}
	public Thing(int number){
	    this.number=number;
	}
	public String toString(){
	    return new String(new Integer(number).toString());
	}
    }

    public static void main(String[] argv){

	Thing[] aa=new Thing[5];;
	aa[0]=new Thing(120);
	aa[1]=new Thing();
	aa[2]=new Thing(120);
	aa[3]=new Thing();
	aa[4]=new Thing(1234);

	for(int i=0;i<aa.length;i++)
	    System.out.println(aa[i]+" aa["+i+"].hashCode="+aa[i].hashCode());
	System.out.println();

	ThingWithHash[] bb=new ThingWithHash[5];;
	bb[0]=new ThingWithHash(120);
	bb[1]=new ThingWithHash();
	bb[2]=new ThingWithHash(120);
	bb[3]=new ThingWithHash();
	bb[4]=new ThingWithHash(1234);

	for(int i=0;i<bb.length;i++)
	    System.out.println(bb[i]+" bb["+i+"].hashCode="+bb[i].hashCode());
	System.out.println();

	String a=null,b=null,c=null;

	/*null objects do not have hashcodes!
	System.out.println("a.hashCode()="+a.hashCode());
	System.out.println("b.hashCode()="+b.hashCode());
	System.out.println("c.hashCode()="+c.hashCode());
	*/

	a="redhat";
	b="redhat";
	c="blueHat";

	System.out.println(a+" a.hashCode()="+a.hashCode());
	System.out.println(b+" b.hashCode()="+b.hashCode());
	System.out.println(c+" c.hashCode()="+c.hashCode());
	System.out.println();
    }
}
