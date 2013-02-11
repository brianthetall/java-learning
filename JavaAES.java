import javax.crypto.spec.IvParameterSpec;
import java.security.Security;
import java.security.SecureRandom;
import java.security.Key;
import java.security.AlgorithmParameters;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class JavaAES{

    private byte[] output, IV;
    private KeyGenerator keyGen;
    private transient Key key;
    private Cipher cipher;
    private AlgorithmParameters parameters;

    public JavaAES() throws Exception{
	parameters = AlgorithmParameters.getInstance("AES");

	keyGen = KeyGenerator.getInstance("AES");
	keyGen.init(128);
	key = keyGen.generateKey();
	System.out.println("Generated AES Key = "+new String(key.getEncoded(),"UTF8") + "\r\nKey Algorithm="+key.getAlgorithm());
	cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	//	cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public byte[] encrypt(String plainText) throws Exception{
	cipher.init(Cipher.ENCRYPT_MODE,key);
	output = cipher.doFinal(plainText.getBytes());
	System.out.println("Cipher Text: " + new String(output,"UTF8"));
	System.out.println("Cipher IV: "+new String(cipher.getIV(),"UTF8"));
	IV = cipher.getIV();
	return output;
    }

    public byte[] decrypt(byte[] cipherText) throws Exception{
	cipher.init(Cipher.DECRYPT_MODE,key,new IvParameterSpec(IV));
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
