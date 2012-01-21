# About #
 This is a simple spark java framework based web application demo "Spark Bookshelf".
 
 This demo use spark to dispatch http request to different route handler.
 
 Two GET request handlers are integrated with FreeMarker template engine to show simple web page in client-side browser.
 
 As to POST/PUT/DELETE request, this web application demo handle as RESTful api way.
 
## How to Run ##
 * Eclipse: Run As-> Maven Build ->Goals exec:java
 * CLI: mvn exec:java
 
 In a browser go to <http://localhost:4567/books>

## TODO ##
 1. Implement alternative to start web application via external web server (Tomcat, Jetty)
 2. Add session management
 3. Try different integration way for template engine