import javax.crypto.spec.IvParameterSpec;
import java.security.Security;
import java.security.SecureRandom;
import java.security.Key;
import java.security.AlgorithmParameters;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class JavaAES{
    private static final int DEBUG=0;
    private byte[] output, IV;
    private KeyGenerator keyGen;
    private transient Key key;
    private Cipher cipher;

    public JavaAES() throws Exception{
	keyGen = KeyGenerator.getInstance("AES");
	keyGen.init(128);
	key = keyGen.generateKey();
	if(DEBUG==1){System.out.println("Generated AES Key = "+new String(key.getEncoded(),"UTF8") + "\r\nKey Algorithm="+key.getAlgorithm());}
	cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    public byte[] encrypt(String plainText) throws Exception{
	cipher.init(Cipher.ENCRYPT_MODE,key);
	output = cipher.doFinal(plainText.getBytes());
	if(DEBUG==1){System.out.println("Cipher Text: " + new String(output,"UTF8"));}
	if(DEBUG==1){System.out.println("Cipher IV: "+new String(cipher.getIV(),"UTF8"));}
	IV = cipher.getIV();
	return output;
    }

    public byte[] decrypt(byte[] cipherText) throws Exception{
	cipher.init(Cipher.DECRYPT_MODE,key,new IvParameterSpec(IV));
	output = cipher.doFinal(cipherText);
	if(DEBUG==1){System.out.println("Plain Text: " + new String(output));}
	return output;
	//zero key[]
    }
    
    public static void main(String args[]) throws Exception{
	if(args.length!=1)
	    System.exit(-1);
	JavaAES crypt = new JavaAES();
	if(args[0].equals(new String(crypt.decrypt(crypt.encrypt(args[0])))))
	    System.out.println("Class: JavaAES is working!");
    }
}
