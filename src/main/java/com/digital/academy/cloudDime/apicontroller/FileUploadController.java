package com.digital.academy.cloudDime.apicontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digital.academy.cloudDime.implementation.EventService;
import com.digital.academy.cloudDime.implementation.UserService;

@RestController

/*
 * This API uploads a PDF file from the android,it only accept PDF files.The file is stored with its description,
 * and the URL which will be used by the user to download the file
 */

public class FileUploadController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value = "CloudDime/User/uploadFile", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)	   
		   public String fileUpload(@RequestParam("file") MultipartFile file,@RequestParam String fileDescription,@RequestParam int userId) throws IOException{
		
			  if(file.getContentType().equalsIgnoreCase("application/pdf")) {
				  /*
				   * This block code verifies that a correct file format is being uploaded,which is the
				   * PDF file,if it is the correct format it is uploaded to the file server of the path
				   * provided. 
				   */
				  
				  File convertFile = new File("/Users/academy_intern/documents/"+file.getOriginalFilename());
				
			      FileOutputStream fout = new FileOutputStream(convertFile);
			      fout.write(file.getBytes());
			      fout.close();
			    
			    	  
			  }else {
				  return "Not supported ,PDFs only";
			  }
			  
			  //file path used to download the uploaded document,i.e a link to the uploaded document.
			  String url = "/Users/academy_intern/documents/"+file.getOriginalFilename();//get the file name
			  
	    	  userService.uploadFile(url,fileDescription, userId);
		      return "File is upload successfully";
		   }
	
	
	/*
	 * API receives the URL of the file,which is the location at the file is stored
	 *in the file server  and download the file from the server..
	 * The API returns PDF only.
	 */
	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String url) throws IOException {

		File file = new File(url);

	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(file.length())
	            .contentType(MediaType.parseMediaType("application/pdf"))
	            .body(resource);
	}
	
	
	@RequestMapping(value = "CloudDime/User/uploadEventFile", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)	   
	   public String eventsFileUpload(@RequestParam("file") MultipartFile file,@RequestParam String fileDescription,@RequestParam int eventId) throws IOException{
	
		  if(file.getContentType().equalsIgnoreCase("application/pdf")) {
			  /*
			   * This block code verifies that a correct file format is being uploaded,which is the
			   * PDF file,if it is the correct format it is uploaded to the file server of the path
			   * provided. 
			   */
			  
			  File convertFile = new File("/Users/academy_intern/documents/events"+file.getOriginalFilename());
			
		      FileOutputStream fout = new FileOutputStream(convertFile);
		      fout.write(file.getBytes());
		      fout.close();
		    
		    	  
		  }else {
			  return "Not supported ,PDFs only";
		  }
		  
		  //file path used to download the uploaded document,i.e a link to the uploaded document.
		  String url = "/Users/academy_intern/documents/events"+file.getOriginalFilename();//get the file name
		  
		  eventService.uploadFileForEvent(url, fileDescription, eventId);
	      return "File is upload successfully";
	   }

}
