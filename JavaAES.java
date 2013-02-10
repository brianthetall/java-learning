import java.security.*;
import javax.crypto.Cipher;

public class JavaAES{
    private byte[] input,output;
    private KeyGenerator key = KeyGenerator.getInstance("AES");
    private transient Key generatedKey;
    private Cipher cipher;
    public JavaAES(){
	key.init(128);
	generatedKey = key.generateKey();
	cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	cipher.init(Cipher.ENCRYPTMODE,generatedKey);
	output = cipher.doFinal(input);
	System.out.println(new String(output));
    }
    public encrypt(String plain){
	
    }
    public decrypt(String cipher){

    }
|
