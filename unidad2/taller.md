# Taller de unidad 2 · Préstamo de recursos

[Índice de unidad](README.md) · [Unidad siguiente](../unidad3/README.md)

## Problema

Modela recursos de una biblioteca comunitaria. Un libro permite préstamo por 7 días y una revista por 3. Cada recurso tiene un código y título no vacíos, y puede estar disponible o prestado. Prestar un recurso ya prestado debe rechazarse sin alterar su estado. Devolver uno disponible tampoco representa una devolución válida.

Un servicio debe coordinar préstamo y notificación. La notificación se recibe como una dependencia de tipo interfaz. No necesitas colecciones todavía: prueba con dos objetos concretos. La duración de cada recurso se obtiene de su implementación, no con una cadena de comparaciones de etiquetas.

## Trabajo por etapas

1. Define invariantes y contratos para prestar y devolver. Decide si rechazos normales usan booleano o una excepción de dominio; conserva la misma decisión.
2. Crea una base abstracta Recurso con código, título y estado privado. Añade Libro y Revista con la duración correspondiente.
3. Implementa las operaciones que cambian el estado. El servicio no debe asignarlo directamente.
4. Declara una interfaz Notificador y una implementación de consola.
5. Sustituye el canal por uno de memoria para comprobar llamadas.
6. Prueba cada contrato y explica la selección polimórfica de la duración.

## Casos para comprobar

| Escenario | Resultado requerido |
|---|---|
| Libro disponible, primer préstamo | Aceptación; queda prestado; duración 7 |
| Mismo libro, segundo préstamo | Rechazo; sigue prestado; sin confirmación extra |
| Libro prestado, devolución | Aceptación; queda disponible |
| Libro disponible, segunda devolución | Rechazo; sigue disponible |
| Revista disponible | Duración 3 a través del mismo contrato |
| Código o título vacío | Rechazo al construir |

## Entrega y criterios

Presenta fuentes, README, tabla de casos y explicación de responsabilidades. El diagrama o la tabla de relaciones debe justificar «es un» y «tiene un». Un video opcional de 3 a 5 minutos puede mostrar la sustitución del canal sin cambiar el servicio. En equipos de hasta tres personas, identifica contribuciones en el historial. Declara apoyo de IA y verificación.

| Criterio | Peso | Evidencia de logro |
|---|---|---|
| Modelo | 25 % | Responsabilidades y relaciones justificadas |
| Contratos | 30 % | Estado privado y operaciones que conservan invariantes |
| Polimorfismo y dependencia | 20 % | Duración especializada y canal sustituible |
| Pruebas y documentación | 25 % | Rechazos no alteran estado ni producen confirmaciones falsas |

## Orientación de solución

Recurso puede ofrecer `boolean prestar()` y `boolean devolver()`. Cada operación comprueba estado antes de cambiarlo. `abstract int diasPrestamo()` obliga a Libro y Revista a definir la duración. El servicio recibe Notificador por constructor y solo notifica cuando prestar devuelve true. Un canal de memoria guarda contador y último mensaje para verificar ambos. La composición relaciona el servicio con su canal; no convierte al servicio en subtipo de Notificador.

Para comprobar una excepción del constructor, usa try/catch específico y falla la prueba si el constructor acepta datos inválidos. No captures errores de programación como si fueran rechazos esperados.

## Autoevaluación

¿Puede otro objeto dejar un recurso en estado inconsistente? ¿Existe alguna herencia solo para ahorrar líneas? ¿Puedes cambiar el canal desde main? ¿Tus pruebas observan estado además de mensajes? ¿Distingues una condición normal de negocio de un contrato violado?
