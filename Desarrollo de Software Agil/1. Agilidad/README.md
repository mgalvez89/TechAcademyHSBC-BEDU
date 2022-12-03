# Aplicación Web para gestionar el almacenamiento y distribución de tarjetas bancarias

## Introducción

<sub>Existen compañias orientadas a brindar servicios de almacenamiento, protección, custodia y destrucción segura para: documentos empresariales, archivos electrónicos, información médica, información financiera, muestras geológicas, obras de arte o grabaciones originales de artistas prestigiosos. Estas compañias junto con muchas otras de diferente giro, tienen claro que las tecnologías de la información son un instrumento indispensable para brindar soluciones ágiles y seguras para su negocio y así poder competir dentro del mercado actual.</sbu>

## Objetivo
Crear una aplicación web para gestionar el almacenamiento y distribución de tarjetas bancarias.

## Alcance

* **Cargar insumos**: El usuario 'admin' realiza el alta de ubicaciones con capacidad disponible para almacenar las tarjetas. El usuario 'admin' realiza el alta de sucursales a donde serán distribuidas las tarjetas.

* **Recepción de tarjetas**: Existe la forma lógica y física de recepción de tarjetas. El usuario 'operador' realiza el almacenamiento lógico al cargar un archivo excel desde la aplicación, este archivo contiene todos los items de las tarjetas que corresponden a una bolsa con el total de tarjetas que indica el archivo excel. El usuario 'operador' realiza el almacenamiento dirigiendo las tarjetas hacia la ubicación física disponible que indico la aplicación después de procesar el archivo excel.

* **Generar distribución de tarjetas**: Existe la forma lógica y física de distribución de tarjetas. El usuario 'operador' realiza la distribución lógica al cargar un archivo excel desde la aplicación, este archivo contiene la sucursal y la cantidad hacia donde serán distribuidas las tarjetas, la aplicación actulizará el inventario de tarjetas durante el procesamiento del archivo excel y generando la orden en formato pdf correspondientes al grupo de tarjetas a distribuir. El usuario 'operador' realiza la extracción física de las tarjetas dirigiendose a la ubicación física que le indica la orden generada por la aplicación. 

* **Generar guias en formato pdf**: La aplicación generará las guías en formato pdf del grupo de tarjetas a distribuir y el cual tendran el detalle de la entrega.


