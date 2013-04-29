import java.lang.String;

public class Value{

    private int value,maxValueBelowThisNode,minValueBelowThisNode;
    private Value left,right;

    public Value(int value){
	if(value<0){
	    System.out.println("Invalid Negative Value Input");
	    System.exit(-2);
	}
	this.value=value;
	maxValueBelowThisNode=-1;//numbers are not allows to be negative
	minValueBelowThisNode=-1;
    }

    public void setLeft(Value left){
	this.left=left;
    }

    public void setRight(Value right){
	this.right=right;
    }

    public int getValue(){return value;}
    public int getLeftValue(){return left.getValue();}
    public int getRightValue(){return right.getValue();}

    public Value getMaxObject(){
	return left.getMaxValue()>right.getMaxValue() ? left : right;
    }
    
    public boolean findMaxValue(){

	if(left==null && right==null){
	    maxValueBelowThisNode=value;
	    return true;//this is bottom row Value
	}
	else if(left==null || right==null)
	    return false;//this is a not permitted condition
	else{
	    maxValueBelowThisNode=value;

	    maxValueBelowThisNode += left.getMaxValue()>right.getMaxValue() ? left.getMaxValue() : right.getMaxValue();
	    return true;
	}
	
    }
    public int getMaxValue(){
	return maxValueBelowThisNode;
    }
    
    public int getMinValue(){return minValueBelowThisNode;}

    public String toString(){
	if(left != null && right != null)
	    return "Left="+getLeftValue()+" Right="+getRightValue();
	return null;	    
    }

}
