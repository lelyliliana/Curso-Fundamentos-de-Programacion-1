# Orientaciones para preparar tutorías

[Inicio](../README.md) · [Mapa de recursos](mapa-del-curso.md)

Este documento localiza materiales y propone focos de demostración. No impone número de sesiones ni reemplaza las diapositivas y los guiones que se preparen para un grupo concreto.

## Secuencia didáctica para cada tema

Empieza con el problema y una pregunta de predicción. Presenta la regla con un caso pequeño; relaciona después cada parte del código con esa regla. Ejecuta la versión correcta, introduce un error deliberado y explica qué evidencia permite localizarlo. Termina con una variación que el estudiante deba justificar.

Para una tutoría de 60 minutos puedes reservar 8 para recuperación de ideas, 12 para explicación, 20 para demostración, 15 para práctica y 5 para cierre. Ajusta esa distribución a los conocimientos del grupo; no conviertas la lectura del código en una exposición sin participación.

| Bloque | Material base | Pregunta de apertura | Demostración útil |
|---|---|---|---|
| Problema y paradigmas | U1, primeras dos lecciones | ¿Qué necesitamos aclarar antes de calcular una multa? | Misma entrada, tres formas de expresar una suma |
| Ambiente y Git | U1, configuración y versiones | ¿Guardar equivale a crear una versión? | Compilar, editar y comparar un diff pequeño |
| Tipos y entrada | U1, variables a consola | ¿Por qué una media puede perder su fracción? | Convertir antes y después de dividir; leer una línea |
| Control y métodos | U1, decisiones a recursión | ¿Qué garantiza que este proceso termine? | Caso de frontera y estado de un acumulador |
| Pruebas | Cierre de U1 | ¿Compilar prueba que la regla está bien? | Cambiar el plazo y observar la prueba que detecta el defecto |
| Objetos y encapsulación | U2, primeras dos lecciones | ¿Quién debe impedir los cupos negativos? | Intentar una segunda inscripción sin cupos |
| Relaciones y polimorfismo | U2, composición a abstracción | ¿Esta relación es «es un» o «tiene un»? | Misma referencia base, resultados de subtipos distintos |
| Contratos y excepciones | U2, interfaces y excepciones | ¿Un entero válido siempre es un dato válido? | Sustituir notificador y comparar error de formato/rango |
| TAD y genéricos | U3, TAD a genéricos | ¿Qué operaciones necesitamos y en qué orden? | FIFO/LIFO; error de tipo detectado antes de ejecutar |
| Colecciones | U3, listas a ordenamiento | ¿Qué define un duplicado aquí? | Código igual con nombre diferente; lista frente a Set y Map |
| Archivos | U3, texto a binarios | ¿Dónde queda el archivo y qué pasa al ejecutar dos veces? | UTF-8, sobrescritura, fila inválida y formato versionado |
| Integración | Proyecto de U3 | ¿Qué debe quedar igual después de guardar y cargar? | Alta, duplicado, falta de cupos, carga válida y carga rechazada |

## Preparar diapositivas a partir de una lección

Usa su objetivo como resultado de aprendizaje. Toma el problema como apertura, el concepto central como explicación y la tabla o recorrido como demostración. Selecciona pocas líneas de código para cada diapositiva y conserva el enlace al fuente completo. Utiliza el resultado esperado para contrastar una predicción, no como respuesta que se muestre antes de preguntar.

## Preparar el guion

Especifica qué pregunta harás, qué cambio ejecutarás y qué resultado debe observarse. Incluye una frase que conecte la salida con la regla. Prepara el ejemplo original y una copia de práctica para no perder tiempo reconstruyendo un error. Antes de la sesión comprueba JDK, carpeta de trabajo y entradas necesarias.

## Evidencias y retroalimentación

Pide explicación del razonamiento, no solo captura de que «funciona». Una evidencia breve puede incluir el enunciado, dos casos relevantes, ejecución y justificación de una modificación. Si se usa video, 3 a 5 minutos permiten explicar una decisión con claridad. Para trabajo colaborativo, equipos de hasta tres personas deben indicar contribuciones verificables. Conserva enlaces al repositorio y al README de la solución.

La declaración de asistencia de IA debe describir su uso y la verificación realizada. Las preguntas de explicación y las variaciones de entrada permiten comprobar comprensión sin asumir que el código fue escrito sin ayuda.
