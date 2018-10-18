package com.example.demo;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.security.MessageDigest;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.user;

import sun.misc.IOUtils;

import java.nio.ByteBuffer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.amazonaws.services.lambda.model.Environment;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

@SessionAttributes("user1")
@Controller
public class profileController  {
	
	@Autowired
	private userservice userservice1;
	
	@Autowired
	private Aesservice aes1;
	
	
	
	@Autowired
	private userrepository ur1;
	
	
	@ModelAttribute("user1")
	public user setUpUserForm() {
	   return new user();
	}

	String s;
	@Value("#{environment.accesskey}")
	String accesskey;
	@Value("#{environment.secretkey}")
	String secretkey;
    @GetMapping(value="/")
     public ModelAndView renderpage()
     {
        ModelAndView indexPage= new ModelAndView();
        indexPage.setViewName("index");
    	return  indexPage;
     }
     
    @PostMapping(value="/upload")
    public ModelAndView uploads3(@RequestParam("file") MultipartFile image,@ModelAttribute("user1") user user1)
    {  
    	  
       
       ModelAndView indexPage= new ModelAndView();
       System.out.println("user name is "+ user1.getUsername());
       System.out.println("image path is "+ image.getOriginalFilename());
       user user2=ur1.findByUsername(user1.getUsername());
       
       BasicAWSCredentials cred= new BasicAWSCredentials(accesskey,secretkey);
      // AmazonS3Client client=AmazonS3ClientBuilder.standard().withCredentials(new AWSCredentialsProvider(cred)).with
       AmazonS3 client=AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_1).build();
       try {
    	   
    	   
    		 InputStream in = new ByteArrayInputStream(image.getBytes()); 
    			
    			
    			BufferedImage img =  ImageIO.read(in);
    			//byte[] imageBytes = Files.readAllBytes(image.t;oPath());
    			//System.out.println(getHash(imageBytes, "SHA-512"));
    			final int width = img.getWidth();
    		    final int height = img.getHeight();
    		    final ByteBuffer byteBuffer = ByteBuffer.allocate(4 * width * height);
    		    for (int y = 0; y < height; y++)
    		    {
    		        for (int x = 0; x < width; x++)
    		        {
    		            // grab color information
    		            int argb = img.getRGB(x, y);

    		            // a,r,g,b ordered bytes per this pixel. the values are always 0-255 so the byte cast is safe
    		            byteBuffer.put((byte) ((argb >> 24) & 0xff));
    		            byteBuffer.put((byte) ((argb >> 16) & 0xff));
    		            byteBuffer.put((byte) ((argb >> 8) & 0xff));
    		            byteBuffer.put((byte) (argb & 0xff));
    		        }
    		    }
    		    
    		    System.out.println(userservice1.getHash(byteBuffer.array(), "SHA-512"));
    			String s1=userservice1.getHash(byteBuffer.array(), "SHA-512");
    			
    			ur1.setUserInfoById(s1,user1.getUsername());
    		System.out.println(image.getName());
    		
    	//	File uploadedFile=File.createTempFile("preview","",new File(configuration.getPathPrefix() + "/linux-storage/tmp/"));
    	//	String s3=image.getOriginalFilename();	
    	File f=	aes1.convert(image);
    	System.out.println(f.getAbsolutePath().replace("\\", "\\\\"));
        //f.renameTo(new File("E:\\"+f.getName()));
    	//System.out.println(f.getAbsolutePath());	
    //	StringBuffer h=new StringBuffer(aes1.multipartToFile(image).getAbsolutePath().replace("\\", "\\\\"));
//    	String g=f.getAbsolutePath().replace("\\", "\\\\");
// 	System.out.println("file " + g);
    //	System.out.println("file " + h);
    	String a=f.getAbsolutePath().replace("\\", "\\\\");
    	
   aes1.encrypt(a);
    	aes1.decrypt("E:\\ji.jpg");
    			
    			
    			 
    			
		PutObjectRequest put= new PutObjectRequest("firstravi",image.getOriginalFilename(),image.getInputStream(), new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
		client.putObject(put);
		
	
		

		
		
		
		
		
		
		

        String imgSrc = "http://"+"firstravi"+".s3.amazonaws.com/"+image.getOriginalFilename();
        indexPage.addObject("imgSrc",imgSrc);
        indexPage.setViewName("profile");
        return indexPage;
       } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
      indexPage.setViewName("error");
      
      return indexPage;
       
      
    }
        
    
    @RequestMapping("/welcome")
    public String welcome(HttpServletRequest request)
    {
    	request.setAttribute("mode", "MODE_HOME");
    	return "welcomepage1";
    }
    
    @RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage1";
	}
    
    @PostMapping("/save-user")
	public String registerUser(@ModelAttribute user user1, BindingResult bindingResult, HttpServletRequest request) {
		userservice1.savemyuser(user1);
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage1";
	}
    
    @GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		request.setAttribute("users", userservice1.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage1";
   }
    
    @RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		userservice1.deleteMyUser(id);
		request.setAttribute("users", userservice1.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage1";
	} 
    
    @RequestMapping("/edit-user")
	public String editUser(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("user", userservice1.editUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "welcomepage1";
	}
    
    @RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage1";
	}
	
	@RequestMapping ("/login-user")
	public String loginUser(@ModelAttribute("user1") user user1, HttpServletRequest request) {
		user user2=userservice1.findByUsernameAndPassword(user1.getUsername(),user1.getPassword() );
		System.out.println(user1.getUsername());
		System.out.println("user2 "+user2.getUsername());
		System.out.println(user2.getRole());
		user1.setUsername(user2.getUsername());
		System.out.println("user1 after session"+user1.getUsername());
		if(userservice1.findByUsernameAndPassword(user1.getUsername(), user1.getPassword())!=null) {
			if("admin".equals(user2.getRole()))
			{
			 
				
				return "admin";
	
			}
			else 
			{
			
				return "index2";
			}
		}
		else {
			request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "welcomepage1";
			
		}
	}

}
