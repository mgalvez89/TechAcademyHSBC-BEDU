# Desarrollo: libgdx-maven-archetype 

* Cómo crear un archivo de configuración de Maven
* Cómo agregar dependencias a un proyecto
* Cómo utilizar bibliotecas externas en Java

## Instalación de arquetipo

El arquetipo aún no está disponible a través de Maven Central, por lo que deberá construirlo e instalarlo antes puedes usarlo para generar proyectos esqueletos. Haz eso así:

```
% git clone git://github.com/libgdx/libgdx-maven-archetype.git
% cd libgdx-maven-archetype
% mvn install
```
>![arquetipo](https://user-images.githubusercontent.com/74322391/206133304-25ef043f-1c75-40cf-8a81-29631c732b33.PNG)

## Crear un proyecto usando el nuevo arquetipo de libgdx

```
% mvn archetype:generate \
    -DarchetypeRepository=local \
    -DarchetypeRepository=$HOME/.m2/repository \
    -DarchetypeGroupId=com.badlogic.gdx \
    -DarchetypeArtifactId=gdx-archetype \
    -DarchetypeVersion=1.2.0
```
Esto le hará algunas preguntas:

* Definir valor para la propiedad 'groupId': : **com.mytest**
* Definir valor para la propiedad 'artifactId': : **mygame**
* Definir valor para la 'versión' de la propiedad: 1.2.0: : **<default>**
* Definir valor para el 'paquete' de la propiedad: com.mytest: : **<default>**
* Definir valor para la propiedad 'JavaGameClassName': : **MyGame**

>![creandoArquetipo](https://user-images.githubusercontent.com/74322391/206135547-4e96f578-7561-4f83-95fa-65ac7472ac45.PNG)

## Construir jar del el proyecto desktop

Comando:

```
% cd mygame
% mvn integration-test -Pdesktop
```
>![secreajar](https://user-images.githubusercontent.com/74322391/206140034-f5a742b4-1c96-4769-9d3f-922febedbc57.PNG)


#  Ejecutar jar del proyecto desktop

Comando:

```
% cd mygame
% mvn package -Pdesktop
% java -jar desktop/target/mygame-desktop-1.0-SNAPSHOT-jar-with-dependencies.jar
```

>![Test](https://user-images.githubusercontent.com/74322391/206140152-ede930b9-eee5-4eb0-a2f9-28d95461e1ea.PNG)



