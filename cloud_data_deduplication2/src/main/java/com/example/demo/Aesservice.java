package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import sun.misc.BASE64Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Service
@Transactional
public class Aesservice {
	private static final String encryptionKey           = "ABCDEFGHIJKLMNOP";
	private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
	
	
    public  void decrypt(String s1)
    {
    	try
    	{
    	Cipher cipher=Cipher.getInstance(cipherTransformation);


    	byte[] key1      = encryptionKey.getBytes(characterEncoding);
    	SecretKeySpec secretKey = new SecretKeySpec(key1, "AES");
    	IvParameterSpec ivparameterspec = new IvParameterSpec(key1);
    	cipher.init(Cipher.DECRYPT_MODE, secretKey,ivparameterspec);
    	CipherInputStream ciptt=new CipherInputStream(new FileInputStream(new File(s1)), cipher);

    	FileOutputStream fop=new FileOutputStream(new File("E:\\kj1.jpg"));

    	int j;
    	while((j=ciptt.read())!=-1)
    	{
    	fop.write(j);
    	}

    	}
    	catch(Exception e)
    	{
    	e.printStackTrace();
    	}

    }



    public  void encrypt(String s2)
    {
    	try
    	{
    		Cipher cipher=Cipher.getInstance(cipherTransformation);


    		byte[] key1      = encryptionKey.getBytes(characterEncoding);
    		SecretKeySpec secretKey = new SecretKeySpec(key1, "AES");
    		IvParameterSpec ivparameterspec = new IvParameterSpec(key1);
    		cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivparameterspec);

    		CipherInputStream cipt=new CipherInputStream(new FileInputStream(new File(s2)), cipher);

    		FileOutputStream fip=new FileOutputStream(new File("E:\\ji.jpg"));

    		int i; 
    		while((i=cipt.read())!=-1)
    		{
    		fip.write(i);

    		}
    	}
    	catch (Exception e)
    	{
    		
    	}
    	
    	
    	}


    
    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
    {
        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
       
       // File f=new File("E://"+convFile.getName());
        return convFile;
    }
    
    public File convert(MultipartFile file) throws IOException
    {    
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile(); 
        FileOutputStream fos = new FileOutputStream(new File("G://checking//"+convFile.getName())); 
        System.out.println("conv "+convFile.getName());
        fos.write(file.getBytes());
        fos.close(); 
        return convFile;
    }
    
   

}
