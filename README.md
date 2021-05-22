# task-speedhome

# Authentication
    JWT Based Authentication. Upon authentication a JWT token is being created which is valid for an hour. JWT token is signed with private key and for verification we are using public key. i.e **RSA Algorithm** . Both Private and Public keys are generated dynamically upon server startup.

# Application

    The application is divided into the following entities
      * Property
          * property_id
          * property_name
          * buildup_size
          * bedroom 
          * bathroom 
          * parking 
          * floor_level_id 
          * furnishing_id 
          * address_id 
          * status 
          * created_by 
          * creation_date
      * User
          * user_id
          * username
          * password
      * Floor Level
          * floor_level_id
          * floor_level_name
      * Furnishing
          * furnishing_id
          * furnishing_type
      * Address
          * address_id
          * full_address
          * post_code
 **Schema and Data Scripts can be found in the resource folder**
 
 All of the entities are Validated from backend.Some of the constraints which are applied are like
 
  1. Property Name must not be null and empty
  2. Floor and Furnishing must be selected between 1 and 3
  3. Address must not be null and empty
  4. Minimum value for bedroom and bathroom is 1
  5. Build Up Size Minimum is 100 sqft.

The Design I followed is 

Controller **->** Service Layer **->** DAO Layer

Controller -  Accepts the request and Forward the data to Service Layer for processing
Service Layer - DTO **->** Entity and Entity **->** Dto  conversions and Business Processing done in this layer
DAO Layer  - DB Interaction happens Here
