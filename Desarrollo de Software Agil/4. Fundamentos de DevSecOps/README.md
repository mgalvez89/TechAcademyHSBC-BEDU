## Fundamentos Docker

1. Utiliza el repo de Apache

https://hub.docker.com/_/httpd

2. Ejecuta el comando para descargar la Imagen a tu local:

> docker pull httpd

3. Ejecuta el siguiente comando:

> docker images

![My Remote Image](https://user-images.githubusercontent.com/74322391/205516310-00d66a60-04f8-408b-92aa-33ca131de69d.PNG)

4. Ahora, levantarás la imagen con lo siguiente:

> docker run -d --name apache-server2 -p 80:80 httpd

![My Remote Image](https://user-images.githubusercontent.com/74322391/205516627-91056f2b-8ca0-4883-b6b7-3d0011871d62.PNG)

5. Ubica el Container ID y ejecútalo:

> docker exec -it 5ec19c1e3408 bash

6. Una vez dentro de tu contenedor, actualiza:

> apt-get update & apt-get upgrade -y

7. Instala un par de tools (wget, zip)

> apt install wget apt install zip

7. Ubícate en el path:

> cd /usr/local/apache2/htdocs

8. Descarga un zip, con el site a desplegar:

> wget
https://github.com/beduExpert/DevOps-Fundamentals-2021/raw/main/Sesion-01/coming-soon-evening-html.zip

9. Descomprime el zip:

> unzip coming-soon-evening-html.zip

10. Actualiza el navegador, donde podrás ver el sitio actualizado como se muestra en la imagen.

![My Remote Image](https://user-images.githubusercontent.com/74322391/205516881-8a3cd269-1aff-4c01-93e5-e62134a99240.PNG)
