package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getProducts {

	
	public static List<String> getProductListFromAPI() throws JsonMappingException, JsonProcessingException {
		  String apiUrl="https://49aa9918-6291-4480-9f7c-1309025d53be.mock.pstmn.io/getExpectedProduct";
		  
		  System.out.println(apiUrl);
		  Response response = RestAssured.get(apiUrl);
		  List<String> productList= new ArrayList<>();
			if(response.getStatusCode()==200) { String jsonData=
			  response.getBody().asString();
			
			ObjectMapper objectMapper= new ObjectMapper();
			 
			JsonNode rootNode = objectMapper.readTree(jsonData);
            JsonNode dataArray = rootNode.get("data");
            
            List<String> productList1 = new ArrayList<>();
            if (dataArray.isArray()) {
                for (JsonNode node : dataArray) {
                    JsonNode productNameNode = node.get("ProductName");
                    if (productNameNode != null && productNameNode.isTextual()) {
                        String productName = productNameNode.asText();
                        productList1.add(productName);
                    }
                }
            }
			return productList1;
		  
		 
	  }
			return null;
}}
