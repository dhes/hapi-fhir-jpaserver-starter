A record of implementing spring security on the virtual server. Spring security is already installed on the local machine server. The key file is SecurityConfig.java located at src/main/java/ca/uhn/fhir/jpa/starter. 

As I write this the local implementation is working as expected. This type of security depencs upson loca env variables HAPI_FHIR_USERNAME= HAPI_FHIR_PASSWORD= that are visible in the local .env file and with a terminal command line echo $HAPI_FHIR_USERNAME etc. 

chat describes the process like so: 
```
Steps to Implement Spring Security on the Virtual Server:

	1.	Set Environment Variables:
	•	Add HAPI_FHIR_USERNAME and HAPI_FHIR_PASSWORD as environment variables on the virtual server. You can do this temporarily in the shell or permanently in a profile file (e.g., .bashrc or .bash_profile for Linux).
	2.	Shut Down the FHIR Server:
	•	Stop any running instance of the FHIR server on the virtual machine to avoid conflicts.
	3.	Copy SecurityConfig.java to the Virtual Server:
	•	Ensure that SecurityConfig.java is in the correct directory on the virtual server. Also, verify that any other dependencies, such as Spring Security libraries, are included in the project.
	4.	Run mvn clean spring-boot:run:
	•	This will clean, compile, and start the FHIR server with the security configuration active.
	5.	Test the Configuration:
	•	Access the FHIR server’s endpoint from a client or browser.
	•	Verify that it prompts for authentication and accepts the credentials specified in the environment variables.

Additional Notes

	•	Network Configuration: Ensure that any firewall or security group settings on the virtual server allow access to the server’s port (typically 8080 for Spring Boot).
	•	Logging: Check the logs on the virtual server if any issues arise, as they can provide insights into authentication errors or environment variable issues.
```

The full terminal command to copy the file is 
```
scp hapi-fhir-jpaserver-starter/src/main/java/ca/uhn/fhir/jpa/starter/SecurityConfig.java {username}@cds.hopena.info:hapi-fhir-jpaserver-starter/src/main/java/ca/uhn/fhir/jpa/starter/
```
assuming you are in the home directory on the local machine. 

Running mvn clean install at this point fails because the testing framework no longer has access to the server. For that you need to upload TestSecurityConfig.java

```
scp hapi-fhir-jpaserver-starter/src/test/java/ca/uhn/fhir/jpa/starter/TestSecurityConfig.java {username}@cds.hopena.info:hapi-fhir-jpaserver-starter/src/test/java/ca/uhn/fhir/jpa/starter/
```

UitstekendRoach3@

TrickyRosie9@

