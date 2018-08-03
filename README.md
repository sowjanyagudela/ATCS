

Unstall the ATCS Zip folder, were below  modules can be found.

atcs.core : This module comprises of DATA Layer .
			Repositories  and Service Implementation
			
atcs.env :	Environment Specific assets like properties,profiles 	

atcs.model : database tables and interfaces, DATA Layer
			Repositories

atcs.parent : all dependencies will be added here.

atcs.web  : main application and controllers to be run.

atcs.exceptions : exception handling 

atcs.api.spec  : collections of json 

Goto atcs.parent

mvn clean install package -DskipTests

successfull build

Run the ATCSApplication.java from IDE


ATCS.postman_collection.json is included for the postman scripts.

Install Postman Plugin from chrome

After installing import > add atcs.api.spec>ATCS.postman_collection.json from Postman Window

where all the Request  collections will be downloaded into ATCS 
