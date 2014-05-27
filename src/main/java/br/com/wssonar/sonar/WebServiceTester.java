package br.com.wssonar.sonar;

public class WebServiceTester {
	
	/**
	* Here is called the methods that test each web-service 
	* 
	* Each web service test can have it's own logic for uptime, depending on it's return;
	* i.g., it takes account not only if the web service is online, but if it's working correctly
	*/
	public static void testWebService(int webServiceId) throws Exception {
		switch(webServiceId) {

		case 1:
			// Call to the first web service test method
			break;

		case 2:
			// Call to the second web service test method
			break;
			
		case 3:
			// Call to the third web service test method
			break;
		}

	}

}
