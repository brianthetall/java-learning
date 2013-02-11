import java.security.SecureRandom;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class JavaAES{

    private byte[] output;
    private KeyGenerator keyGen;
    private /*transient*/ Key key;
    private Cipher cipher;

    public JavaAES() throws Exception{
	keyGen = KeyGenerator.getInstance("AES");
	keyGen.init(128);
	key = keyGen.generateKey();
	System.out.println("Generated AES Key = "+new String(key.getEncoded(),"UTF8") + "\r\nKey Algorithm="+key.getAlgorithm());
	//	cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public byte[] encrypt(String plainText) throws Exception{
	cipher.init(Cipher.ENCRYPT_MODE,key);
	output = cipher.doFinal(plainText.getBytes());
	System.out.println("Cipher Text: " + new String(output,"UTF8"));
	return output;
    }

    public byte[] decrypt(byte[] cipherText) throws Exception{
	cipher.init(Cipher.DECRYPT_MODE,key);
	output = cipher.doFinal(cipherText);
  	System.out.println("Plain Text: " + new String(output));
	return output;
	//zero key[]
    }
    
    public static void main(String args[]) throws Exception{
	System.out.println("# ARGS = "+args.length);
	JavaAES crypt = new JavaAES();
	;
	crypt.decrypt(crypt.encrypt(args[0]));
    }
}
