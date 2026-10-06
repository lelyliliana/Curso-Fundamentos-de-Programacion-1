# Mapa de temas y resultados

[Inicio](../README.md)

La secuencia es fundamentos, POO y estructuras de datos. Dentro de cada unidad se conserva el orden temático. Una lección puede desarrollar parte de un tema amplio para evitar introducir varios conceptos nuevos simultáneamente.

| Unidad | Tema en orden | Recursos desarrollados |
|---|---|---|
| 1 | Comprensión del problema | [Comprender el problema antes de programar](../unidad1/01-comprender-el-problema/README.md) |
| 1 | Paradigmas de programación | [Paradigmas de programación](../unidad1/02-paradigmas/README.md) |
| 1 | Configuración del ambiente de trabajo | [Ambiente de trabajo y primer programa](../unidad1/03-ambiente-y-primer-programa/README.md) |
| 1 | Control de versiones | [Control de versiones con Git](../unidad1/04-control-de-versiones/README.md) |
| 1 | Estructura del código | [Estructura del código, tipos y variables](../unidad1/05-estructura-tipos-y-variables/README.md); [Operadores, conversiones y precisión](../unidad1/06-operadores-y-conversiones/README.md); [Entrada por consola y salida legible](../unidad1/07-entrada-y-salida/README.md); [Decisiones con if y switch](../unidad1/08-condicionales/README.md); [Repeticiones, contadores y acumuladores](../unidad1/09-ciclos/README.md); [Métodos, parámetros y retorno](../unidad1/10-metodos/README.md); [Recursión y condición de terminación](../unidad1/10b-recursion/README.md); [Arreglos y cadenas de texto](../unidad1/11-arreglos-y-cadenas/README.md); [Pruebas de escritorio y depuración](../unidad1/12-pruebas-y-depuracion/README.md) |
| 2 | Elementos de la programación orientada a objetos | [Clases, objetos, atributos y métodos](../unidad2/01-clases-y-objetos/README.md) |
| 2 | Características de la POO | [Encapsulación y contratos del objeto](../unidad2/02-encapsulacion/README.md); [Abstracción y composición de objetos](../unidad2/03-composicion/README.md); [Herencia, sobrescritura y polimorfismo](../unidad2/04-herencia-y-polimorfismo/README.md) |
| 2 | Clases abstractas e interfaces | [Clases abstractas y especialización](../unidad2/05-clases-abstractas/README.md); [Interfaces y dependencia de contratos](../unidad2/06-interfaces/README.md) |
| 2 | Excepciones | [Excepciones y validación de entrada](../unidad2/07-excepciones/README.md); [Modelo integrado con reglas y verificación](../unidad2/08-modelo-integrado/README.md) |
| 3 | Estructuras de datos abstractas (TAD) | [Tipos abstractos de datos y arreglos](../unidad3/01-tipos-abstractos-de-datos/README.md); [Matrices y recorridos por filas](../unidad3/01b-matrices/README.md); [Pilas y colas con ArrayDeque](../unidad3/02-pilas-y-colas/README.md) |
| 3 | Genéricos | [Genéricos y seguridad de tipos](../unidad3/03-genericos/README.md) |
| 3 | Colecciones | [Listas y ArrayList](../unidad3/04-listas/README.md); [Conjuntos, identidad y records](../unidad3/05-conjuntos-e-igualdad/README.md); [Mapas, búsqueda y conteos](../unidad3/06-mapas/README.md); [Recorrido, filtrado y ordenamiento](../unidad3/07-recorrido-filtrado-y-orden/README.md) |
| 3 | Manipulación de archivos | [Lectura y escritura de texto con NIO](../unidad3/08-archivos-de-texto/README.md); [Archivos estructurados y validación por línea](../unidad3/09-formato-y-validacion-de-archivos/README.md); [Archivos binarios y cierre de recursos](../unidad3/10-archivos-binarios/README.md) |
| 3 | Integración de las tres unidades | [Proyecto integrador: registro de un taller](../unidad3/11-proyecto-integrador/README.md) |

## Actualización aplicada

| Aspecto | Decisión del curso |
|---|---|
| Ambiente | JDK 21, terminal reproducible y editor a elección |
| Código | Ejemplos originales, compilables y con salidas verificables |
| Modelo | Encapsulación con invariantes; composición antes de herencia injustificada |
| Colecciones | Interfaces, genéricos, igualdad explícita y orden documentado |
| Persistencia | NIO, UTF-8, cierre de recursos y validación de archivos |
| Binarios | Formato explícito y versionado con DataInput/OutputStream |
| Calidad | Casos normales, fronteras e inválidos; comprobaciones del proyecto |
| Interfaz | Consola en la ruta principal para observar algoritmo y estado |

La organización temática guía la cobertura; el curso no reproduce las explicaciones, capturas ni actividades del material de referencia. Los ejemplos muestran decisiones y límites para que puedas elaborar después diapositivas y guiones propios.

## Resultado de cada unidad

Al cerrar la primera, resuelves una regla con entradas, validaciones, métodos y pruebas. Al cerrar la segunda, construyes un modelo que protege su estado y depende de contratos. Al cerrar la tercera, eliges estructuras, procesas objetos y recuperas un estado validado desde archivos.
