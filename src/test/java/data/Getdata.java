package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Getdata {

	public static void main(String[] args) throws IOException
	{
		List<HashMap<String, String>> dt=getJsonDataToMap();
		HashMap<String, String> m1=dt.get(0);
		HashMap<String, String> m2=dt.get(1);
		
		Set<String> m1k=m1.keySet();
		Set<String> m2k=m2.keySet();
		
		for(String s:m1k)
		{
			System.out.println(s);
		}
	}
	
//utility to read data from json file

	public static List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//data//productdata.json"), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Datbind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	
	}
}
