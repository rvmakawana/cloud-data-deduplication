package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.security.MessageDigest;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;







@Service
@Transactional

public class userservice {
	private final userrepository userrepository1; 
	
	  public userservice(userrepository userrepository1)
	  {
		  this.userrepository1=userrepository1;
	  }
	  
		public void savemyuser(user user1)
		{
			
			userrepository1.save(user1);
		}
		
		public List<user> showAllUsers(){
			List<user> users = new ArrayList<user>();
			for(user user1 : userrepository1.findAll()) {
				users.add(user1);
			}
			
			return users;
		}
		
		public void deleteMyUser(int id) {
			userrepository1.delete(id);
		}
		
		public user editUser(int id) {
			return userrepository1.findOne(id);
		}
		
		public user findByUsernameAndPassword(String username, String password) {
			return userrepository1.findByUsernameAndPassword(username, password);
		}
		
		public  String getHash(byte[] inputBytes, String algo) {
			String hashVal = "";
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(algo);
				messageDigest.update(inputBytes);
				byte[] digestedBytes = messageDigest.digest();
				hashVal = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return hashVal;
		}
		
		  public BufferedImage convertToImage(MultipartFile file) throws IOException { 
		        InputStream in = new ByteArrayInputStream(file.getBytes()); 
		        return ImageIO.read(in); 
		    } 
		  public static File convert(MultipartFile file) throws IOException {
			    File convFile = new File(file.getOriginalFilename());
			    convFile.createNewFile();
			    FileOutputStream fos = new FileOutputStream(convFile);
			    fos.write(file.getBytes());
			    fos.close();
			    return convFile;
			}
}
