# Billy
API para la facturacion de usuarios

## Ambiente demo
https://pure-wave-27310.herokuapp.com (SWAGGER)

Dicho ambiente esta ejecutando en un servidor Heroku con una base de Mongo y todo lo necesario para funcionar

## Arquitectura
Este proyecto esta realizado en JAVA bajo el concepto de Event Sourcing y CQRS para maximizar los RPS

### Tecnologías utilizadas
- JAX-RS (Jersey): para la API REST e inyector de dependencias
- Tomcat 8/9: como app server
- MongoDB: como base de datos para los Eventos y los Modelos
- Guava (Google): Event Bus para la sincronizacion de los eventos y los modelos
- Maven: para gestionar las dependencias

## Instalación
En caso de querer instalar en un ambiente local, se debe:
- Instalar Tomcat 8+ y configurarlo en su respectivo IDE
- Instalar MongoDB con los parametros default (localhost:27017)
- Instalar las dependencias Maven

## Overview
Los cambios al dominio llegan a traves de la API como Commands, estos son capturados y evaluados para aplicarse en su dominio.

Una vez evaluados y ejecutados disparan eventos al resto de la aplicación.

Estos eventos actualizan al dominio en sí y generan un nuevo registro en el Event Store, que servirá para recuperar el estado actual y de historial

## Consideraciones
En caso que 2+ eventos que aplican al mismo dominio se den de forma simultanea (ejemplo 2 cargos al mismo usuario) se reintentara hasta 3 veces bajo el concepto de Optimistic Locking. (En caso de tener varias API distribuidas se deberían configurar para tener afinidad por userId)

### Pendientes
Hacer un snapshot cada X cantidad de eventos y asi evitar el procesamiento excesivo con el paso del tiempo
