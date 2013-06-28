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


    public RSACrypt(String mode,String filename,String inputFile,String sign,String signkey,String signatureForVerification){
	this(mode,filename,inputFile);
	if(sign.compareToIgnoreCase("-s")==0){//sign the plaintext with the privateKey found in signkey
	    if(mode.compareToIgnoreCase("-e")==0){
		    byte[] plain = getByteStreamFromFile(inputFile);//reopen the plain
		    try{
		    privkey = readPrivKeyFromFile(signkey);//read privatekey used for signatures
		    byte[] signature = sign(privkey,plain);//generate signature using private-key & plaintext
		    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(inputFile+".sig"));
		    bos.write(signature,0,signature.length);//write the signature to file
		    bos.flush();
		    bos.close();
		    }catch(Exception e){System.out.println(e.getMessage());}
	    }else{
		System.out.println("Cannot sign when not encrypting\r\n");
	    }
	}else if(sign.compareToIgnoreCase("-v")==0){//verify using the public key contained in file 'signkey'
	    if(mode.compareToIgnoreCase("-d")==0){
		byte[] plain = getByteStreamFromFile(inputFile+".dec");
		byte[] sigVerify = getByteStreamFromFile(signatureForVerification);
		try{
		    pk = readKeyFromFile(signkey);
		    boolean verified = verify(pk,plain,sigVerify);
		    if(verified){System.out.println("MessageVerified");}
		    else{System.out.println("MessageNOTVerified\r\n");}
		}catch(Exception e){System.out.println(e.getMessage());}
	    }else{
		System.out.println("Cannot verify when not decrypting\r\n");
	    }
	}
	
    }
    
    private byte[] sign(PrivateKey priv,byte[] data){
	try{
	    Signature sig = Signature.getInstance("SHA256withRSA");
	    sig.initSign(priv);
	    sig.update(data);
	    return sig.sign();
	}catch(Exception e){System.out.println(e.getMessage());}
	return null;
    }

    private boolean verify(PublicKey pub,byte[] data,byte[] signature){
	try{
	    Signature sig = Signature.getInstance("SHA256withRSA");
	    sig.initVerify(pub);
	    sig.update(data);
	    return sig.verify(signature);
	}catch(Exception e){System.out.println(e.getMessage());}
	return false;
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
	if(args.length!=3 && args.length!=5 && args.length!=6){
	    System.out.println("RSACrypt [-e|-d] ([-s|-v] <signkey/verifykey> <signature-input-file(Verification)>) <keyfile> <data-file>\r\n-eNcrypt -dEcrypt -sIgn -vErify\r\n");
	    System.exit(-1);
	}
	else{
	    if(args.length==3 && (args[0].compareToIgnoreCase("-e")==0 || args[0].compareToIgnoreCase("-d")==0) ){
		new RSACrypt(args[0],args[1],args[2]);
	    }else if(args.length==5){//signing
		if(args[0].compareToIgnoreCase("-e")==0){
		    new RSACrypt(args[0],args[3],args[4],args[1],args[2],null);
		}
	    }else if(args.length==6){//verification
		if(args[0].compareToIgnoreCase("-d")==0){
		    new RSACrypt(args[0],args[4],args[5],args[1],args[2],args[3]);
		    //RSACrypt(mode,key-crypt,data,sign/verify-mode,sign-key,signature-file)
		}
	    }
	}
    }//end main
}//end class
