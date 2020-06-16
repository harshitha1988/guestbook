# guestbook
a demo application for simple guestbook app using angular(UI) and springboot(backend)

## Running the application
### Softwares required
- Java 8, Maven 3.6.2
- Node 12.13.0, Angular 8.2.14, NG cli 8.3.27

### Running the application
- Checkout the project
- within guestapp_api from commandline 'mvn clean package' & 'mvn spring-boot:run' This starts spring boot application listening to port 8080
- within guestbook-ui from command line 'npm install' & 'ng serve' This starts angular application at http://localhost:4200/feedback
- Feedback can be added with username and comment at the top, likes and unlikes can be click in the listing below
