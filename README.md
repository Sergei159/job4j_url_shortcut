# url_shortcut

This is a learning project based on the Spring boot module.
This application is a service for safely connecting users to websites

### Technologies
1. Spring Data
2. Spring Web
3. Spring Security
4. JWT (for authentication and authorization)
5. PostgreSQL

User sends url. After successful registration of this url user receives login and password;
![ScreenShot](images/1.jpg)

After successful authentication user receives unique authorization token
![ScreenShot](images/2.jpg)

Authorized user sends url and receives shortcut for this url
![ScreenShot](images/3.jpg)

Sending shortcut to "/redirect" redirects user to mapped url
![ScreenShot](images/4.jpg)

Authorized user can get statistics for all registered urls
![ScreenShot](images/5.jpg)
