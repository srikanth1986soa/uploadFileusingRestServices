package com.fileupload.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/file")
public class UploadFileService {

	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(@MultipartForm FileUploadForm form) {

		String fileName = "C:\\tools\\workingdirectory\\hllo.csv";
		
		try {
			writeFile(form.getData(), fileName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		System.out.println("Done");

		return Response.status(200)
			.entity("uploadFile is called, Uploaded file name : " + fileName).build();

	}
	
/*
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(@MultipartForm FileUploadForm form) throws IOException {
		

		System.out.println(form);
	    byte[] content = form.getData();
	    InputStream  inputStream = new ByteArrayInputStream(content);
		System.out.println(inputStream);
	   
		File targetFile = new File("C:\\tools\\workingdirectory\\DocusignFailed.doc");
		FileOutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(content);
		outStream.close();
		System.exit(0);

		
		return Response.status(200)
			.entity("uploadFile is called, Uploaded file name : " ).build();

	}*/
	

	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
}