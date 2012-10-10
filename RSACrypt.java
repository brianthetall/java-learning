import java.io.*;
import java.lang.String;
import javax.crypto.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

public class RSACrypt{
    private PublicKey pk;
    private PrivateKey privkey;

    public RSACrypt(String mode,String filename,String inputFile){
	try{
	    pk = readKeyFromFile(filename);
	    bs = getByteStreamFromFile(inputFile);
	    System.out.println(pk.toString());
	    if(mode.compareToIgnoreCase("-e")==0){
		//encrypt
		byte[] cipherText = encrypt(bs);
	    }else if(mode.compareToIgnoreCase("-d")==0){
		//decrypt
		byte[] plainText = decrypt(bs);
	    }
	}catch(Exception e){System.out.println(e.getMessage());}
    }

    private byte[] getByteStreamFromFile(String inputFile){
	BufferedReader br = new BufferedReader(new FileInputStream(inputFile));
	byte[] ret=new byte[br.available()];
	br.read((byte[])ret,0,ret.length);
	return ret;
    }

    private byte[] encrypt(byte[] data){
	Cipher cipher = Cipher.getInstance("RSA");
	cipher.init(Cipher.ENCRYPT_MODE,pk);
	byte[] ciphertext = cipher.doFinal(data);
	return ciphertext;
    }

    private byte[] decrypt(byte[] data){
	Cipher cipher = Cipher.getInstance("RSA");
	cipher.init(Cipher.DECRYPT_MODE,privkey);
	byte[] ciphertext = cipher.doFinal(data);
	return ciphertext;
    }

    private PrivateKey readPrivKeyFromFile(String keyfile) throws IOException{
	ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(keyfile))));
	try{
	    BigInteger m = (BigInteger)ois.readObject();
	    BigInteger e = (BigInteger)ois.readObject();
	    RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m,e);
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    PrivateKey privkey = fact.generatePrivate(keySpec);
	    ois.close();
	    return privkey;
	}catch(ClassNotFoundException e){System.out.println(e.getMessage());}
	catch(NoSuchAlgorithmException e){System.out.println(e.getMessage());}
	catch(InvalidKeySpecException e){System.out.println(e.getMessage());}
	return null;
    }

    private PublicKey readKeyFromFile(String keyfile) throws IOException{
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
	if(args.length!=3){
	    System.out.println("RSACrypt [-e|-d] <keyfile> <plain-data-file>\r\n");
	    System.exit(-1);
	}
	else{
	    if(args[0].compareToIgnoreCase("-e")==0 || args[0].compareToIgnoreCase("-d")==0)
		{
		    new RSACrypt(args[0],args[1],args[2]);
		}
	}

    }
}
