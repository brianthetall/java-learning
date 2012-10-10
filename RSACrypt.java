import java.io.*;
import javax.crypto.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

public class RSACrypt{

    public RSACrypt(){
	try{
	    PublicKey pk = readKeyFromFile("public.key");
	    System.out.println(pk.toString());
	}catch(Exception e){System.out.println(e.getMessage());}
    }

    public PublicKey readKeyFromFile(String keyfile) throws IOException{
	ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(keyfile))));
	try{
	    BigInteger m = (BigInteger)ois.readObject();
	    BigInteger e = (BigInteger)ois.readObject();
	    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m,e);
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    PublicKey pubkey = fact.generatePublic(keySpec);
	    ois.close();
	    return pubkey;
	}catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	catch(NoSuchAlgorithmException e){System.out.println(e.getMessage());}
	catch(InvalidKeySpecException e){System.out.println(e.getMessage());}
	return null;
    }

    public static void main(String args[]){
	new RSACrypt();
    }
}
