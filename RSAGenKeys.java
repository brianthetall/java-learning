import java.io.*;
import javax.crypto.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

public class RSAGenKeys{
    private static int KEYSIZE=2048;//5mins
    //    private static int KEYSIZE=4096;
    private KeyPairGenerator kpg;

    public RSAGenKeys(){
	try{
	    kpg = KeyPairGenerator.getInstance("RSA");
	}catch(NoSuchAlgorithmException e){
	    System.out.println(e.getMessage());
	}
	kpg.initialize(KEYSIZE);
	KeyPair kp = kpg.genKeyPair();
	Key publicKey = kp.getPublic();
	Key privateKey = kp.getPrivate();
	try{
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    RSAPublicKeySpec pub=fact.getKeySpec(kp.getPublic(),RSAPublicKeySpec.class);
	    RSAPrivateKeySpec priv=fact.getKeySpec(kp.getPrivate(),RSAPrivateKeySpec.class);
	    try{
		saveToFile("public.key",pub.getModulus(),pub.getPublicExponent());
		saveToFile("private.key",priv.getModulus(),priv.getPrivateExponent());
	    }catch(Exception e){System.out.println("Error Saving Keys To Disk.\r\n"+e.getMessage()+"\r\n");}
	}catch(NoSuchAlgorithmException e){
	    System.out.println(e.getMessage());
	}catch(InvalidKeySpecException e){
	    System.out.println(e.getMessage());
	}
    }
    
    private void saveToFile(String filename,BigInteger mod,BigInteger exp)throws IOException
    {
	ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
	try{
	    oos.writeObject(mod);
	    oos.writeObject(exp);
	}catch(Exception e){
	    throw new IOException("Error writing to ObjectOutputStream");
	}finally{
	    oos.close();
	}
    }

    public static void main(String args[]){
	//	if(args.length == 0){
	    RSAGenKeys rsa = new RSAGenKeys();
    }
}
