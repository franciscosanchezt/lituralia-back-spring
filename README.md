[![CircleCI](https://circleci.com/gh/franciscosanchezt/lituralia-open/tree/develop.svg?style=shield)](https://circleci.com/gh/franciscosanchezt/lituralia-back-spring)
[![codecov](https://codecov.io/gh/franciscosanchezt/lituralia-open/branch/master/graph/badge.svg)](https://codecov.io/gh/franciscosanchezt/lituralia-back-spring)

# Lituralia Spring Angular

_Lituralia desarrollado con Spring Boot y Angular_

#### Proyecto en desarrollo

## Comenzando 🚀

En la rama [master](https://github.com/franciscosanchezt/lituralia-open/tree/master) conseguiras la ultima version "estable".

En la rama [develop](https://github.com/franciscosanchezt/lituralia-open/tree/develop) conseguiras los ultimos cambios realizados.

## Pre-requisitos 📋

_Que cosas necesitas para ejecutar el software_

```
Java 8
```

## Descarga ⏬

Descarga el ejecutable (jar) en: [Releases](https://github.com/franciscosanchezt/lituralia-open/releases)

Ultima Version: [0.0.2-SNAPSHOT](https://github.com/franciscosanchezt/lituralia-open/releases/tag/0.0.2)

## Despliegue 📦
   
   _Por seguridad primero debemos establecer variables de entorno:_
   
   ```
   PG_JDBC= jdbc:postgresql://ip:puerto/db 
   PG_USER= usuario de postgres 
   PG_PASS= contraseña postgres 
   ```
      
   _Luego ejcutamos el siguiente comando en la ruta del archivo: lituralia-open-0.0.2-SNAPSHOT.jar_
   
   ```
   java -jar lituralia-open-0.0.2-SNAPSHOT.jar
   ```


## Build 🔧

_Requisitos:_

```
Java 8
Maven 3.6+
```
_Descarga el proyecto_

```
git clone https://github.com/franciscosanchezt/lituralia-open.git
```

_Entra en la carpeta del proyecto_

```
cd lituralia-open
```

_Instala dependencias con maven_

```
mvn clean install
```

El empaquetado del proyecto estara en la ruta: 

```
lituralia-open/target/lituralia-open-[version].jar
```

Este paquete despliega el backend y el frontend en el puerto 33333

```
http://localhost:8080
```

El build del frontend lo encontraremos en:

```
lituralia-open/src/main/resources/static
```

Los datos de inicio de sesión son:

```
Usuario    : 
Contraseña : 
```

## Versionado 📌

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](tags/).

## Desarrollado con 🛠️

#### Back-end: 🖧

* [OpenJDK 8](https://adoptopenjdk.net/) - Open Java
* [Maven](https://maven.apache.org/) - Dependencies Management
* [Spring](https://spring.io/) - Framework

#### Front-end: 🖥

* [Node.js](http://nodejs.org/) - Runtime Environment
* [Npm](https://www.npmjs.com/) - Package Management
* [Angular](https://angular.io/) - Framework
* [MDBootstrap](https://mdbootstrap.com/) - Components


## Autores ✒️

* **Lara Vazquez** - [LaraVazquezDorna](https://github.com/LaraVazquezDorna)
* **Nico Fernandes** - [NicoFernandes](https://github.com/NicoFernandes)
* **Francisco Sanchez** - [franciscosanchezt](https://github.com/franciscosanchezt)


## Licencia 📄

Este proyecto está bajo la Licencia (MIT) - mira el archivo [LICENSE.md](LICENSE.md) para detalles
