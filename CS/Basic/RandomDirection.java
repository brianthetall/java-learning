import java.security.SecureRandom;
import java.lang.Thread;
import java.lang.String;

public class RandomDirection{

    private SecureRandom r;
    private static final String[] directions={"LEFT","RIGHT","UP","DOWN"};

    public RandomDirection(){
	r=new SecureRandom();
    }

    public String getDirection(){
	r.nextInt();
	r.nextInt();
	r.nextInt();
	int i=r.nextInt();
	i=i<0?i*=-1:i;
	return directions[i%4];
    }

    public static void main(String argv[]){
	RandomDirection rand = new RandomDirection();
	while(true){
	    System.out.println(rand.getDirection());
	    try{
		Thread.sleep(1000);
	    }catch(Exception e){
		System.err.println(e.getMessage());
	    }
	}
    }
}
