import java.security.SecureRandom;
import java.lang.Thread;
import java.lang.String;

public class RandomDirection{

    private SecureRandom r;
    private static final String[] directions={"LEFT","RIGHT","UP","DOWN"};
    private static int MIN_DELAY;
    private static int MAX_DELAY;

    public RandomDirection(int minMil,int maxMil){
	r=new SecureRandom();
	MAX_DELAY=maxMil;
	MIN_DELAY=minMil;
    }

    /**
     * Return a direction
     * @return a randomly selectly direction
     */
    public String getDirection(){
	r.nextInt();
	r.nextInt();
	r.nextInt();
	int i=r.nextInt();
	i=i<0?i*=-1:i;
	return directions[i%4];
    }

    /**
     * Delay roughly between min & max ms
     */
    public int delay()throws Exception{
	int delayM=r.nextInt()%MAX_DELAY;
	while(delayM<MIN_DELAY)
	    delayM=r.nextInt()%MAX_DELAY;
	Thread.sleep(delayM);
	return delayM;
    }

    public static void main(String argv[])throws Exception{

	RandomDirection rand=null;
	if(argv.length!=2){
	    System.err.println("java RandomDirection <Min Delay (ms)> <Max Delay (ms)>");
	    System.exit(1);
	}
	else if(argv[0].equals(argv[1]))
	    rand = new RandomDirection(new Integer(argv[0]).intValue(),new Integer(argv[1]).intValue()+1);
	else
	    rand = new RandomDirection(new Integer(argv[0]).intValue(),new Integer(argv[1]).intValue());
	    	
	while(true){
	    System.out.println(rand.getDirection());
	    rand.delay();
	}
    }
}
