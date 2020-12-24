package it.polimi.db2.utils;

import java.io.*;

public class ImageUtils {

	public static byte[] readImage(InputStream imageInputStream) throws IOException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];// image can be maximum of 4MB
		int bytesRead = -1;

		try {
			while ((bytesRead = imageInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			return imageBytes;
		} catch (IOException e) {
			throw e;
		}

	}
	
    // Recognize image type by first byte (this numbers can be found with a search on google)
    public static String getImageExtension(byte[] img) {
    	
		switch(img[0]) {
		case (byte) 0x89:
			return "png";
		case (byte) 0x47:
			return "gif";
		case (byte) 0xFF:
			return "jpg";
		}  
		
		return null;
    }

}
