# transtour-backend-monolith
Backend monolitico de transtour

###### Precondociones:
 
- ingreasar la carpeta una vez clonado el proyecto link: https://transtour.com
  correr el comando del de la carpeta clonado ./gradlew clean build -Djasypt.encryptor.password=password

##### Correr aplicacion:

- para iniciar la aplicacion con docker
-
    - crear la imagen con el comando
      docker build . -t transtour-app:1.0 .
-
    - docker run -d -p 8080:8080 -d -p 443:8443 --name transtourapp -e  "SPRING_PROFILES_ACTIVE=log-dev"  -t
      transtour-app:1.0 --env.file .env-dev
-
    - abrir el broser e ingresear http:://localhost:8080/healthcheck
      .Si la apliación inicio correctamente dira hola mundo.
- para finalizar la app ejecutar el siguiente comando docker -rf transtour-app:1.0