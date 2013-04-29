public class Value{

    private int value,maxValueBelowThisNode,minValueBelowThisNode;
    private Value left,right;

    public Value(int value){
	this.value=value;
    }

    public void setLeft(Value left){
	this.left=left;
    }

    public void setRight(Value right){
	this.right=right;
    }

    public int getValue(){return value;}
    public int getMaxValue(){return maxValueBelowThisNode;}
    public int getMinValue(){return minValueBelowThisNode;}

}
