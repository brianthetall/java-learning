import java.security.*;
import javax.crypto.*;

public class JavaAES{

    private byte[] output;
    private KeyGenerator key;
    private /*transient*/ Key generatedKey;
    private Cipher cipher;

    public JavaAES() throws Exception{
	key = KeyGenerator.getInstance("AES");
	key.init(128);
	generatedKey = key.generateKey();
	System.out.println("Generated AES128 Key = "+generatedKey.getEncoded());
	cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//AES/CBC/PKCS5Padding
    }

    public byte[] encrypt(String plainText) throws Exception{
	cipher.init(Cipher.ENCRYPT_MODE,generatedKey);
	output = cipher.doFinal(plainText.getBytes());
	System.out.println("Cipher Text: " + new String(output));
	return output;
    }

    public byte[] decrypt(String cipherText) throws Exception{
	cipher.init(Cipher.DECRYPT_MODE,generatedKey);
	output = cipher.doFinal(cipherText.getBytes());
	System.out.println("Plain Text: " + new String(output));
	return output;
	//zero key[]
    }
    
    public static void main(String args[]) throws Exception{
	System.out.println("# ARGS = "+args.length);
	JavaAES crypt = new JavaAES();
	crypt.encrypt(args[0]);
	crypt.decrypt(args[0]);
    }
}
