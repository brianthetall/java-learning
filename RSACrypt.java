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
	    byte[] bs = getByteStreamFromFile(inputFile);
	    if(mode.compareToIgnoreCase("-e")==0){
		//encrypt
		pk = readKeyFromFile(filename);
		byte[] cipherText = encrypt(bs);
		//write to file
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(inputFile+".enc"));
		bos.write(cipherText,0,cipherText.length);
		bos.flush();
		bos.close();
	    }else if(mode.compareToIgnoreCase("-d")==0){
		//decrypt
		privkey = readPrivKeyFromFile(filename);
		byte[] plainText = decrypt(bs);
		//writeFile
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(inputFile+".dec"));
		bos.write(plainText,0,plainText.length);
		bos.flush();
		bos.close();
	    }
	}catch(Exception e){System.out.println(e.getMessage());}
    }

    private byte[] getByteStreamFromFile(String inputFile){
	try{
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
	    byte[] ret=new byte[bis.available()];
	    bis.read((byte[])ret,0,ret.length);
	    bis.close();
	    return ret;
	}catch(Exception e){System.out.println(e.getMessage());}
	return null;
    }

    private byte[] encrypt(byte[] data){
	try{
	    Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.ENCRYPT_MODE,pk);
	    byte[] ciphertext = cipher.doFinal(data);
	    return ciphertext;
	}catch(Exception e){System.out.println(e.getMessage());}
	return null;
    }

    private byte[] decrypt(byte[] data){
	try{
	    Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.DECRYPT_MODE,privkey);
	    byte[] ciphertext = cipher.doFinal(data);
	    return ciphertext;
	}catch(Exception e){System.out.println(e.getMessage());}
	return null;
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
