# Taller de unidad 3 · Inventario de kits y persistencia

[Índice de unidad](README.md) · [Proyecto integrador](11-proyecto-integrador/README.md)

## Problema

Un centro comunitario administra kits identificados por código. Cada kit tiene nombre y unidades disponibles (entero no negativo). Debe registrar kits sin duplicar códigos, consultar por código, prestar una unidad si hay existencias, devolver una unidad y guardar/cargar el inventario.

Para esta práctica, el préstamo registra una reducción del inventario pero no conserva identidad de quien recibió el kit. Debes declarar esa limitación: devolver sin historial no demuestra que existiera un préstamo previo. Si amplías el modelo con préstamos identificados, define sus reglas y verifícalas.

## Trabajo por etapas

1. Escoge una estructura según las operaciones. Justifica búsqueda por código y orden de listados.
2. Encapsula el estado de cada kit. Impide unidades negativas y rechaza altas duplicadas.
3. Agrega listado ordenado por nombre sin alterar accidentalmente el inventario.
4. Define un archivo UTF-8 con encabezado y campos permitidos. Documenta si sobrescribes o anexas.
5. Carga en una estructura candidata; publica el resultado solo si todo el archivo es válido.
6. Prueba guardar/cargar con tildes y verifica igualdad de código, nombre y existencias.

## Casos para comprobar

| Escenario | Resultado requerido |
|---|---|
| Alta K01, Robótica, 2 | Un único registro con dos unidades |
| Repetir K01 | Rechazo; mantiene el registro previo |
| Prestar dos veces | Dos aceptaciones; unidades 0 |
| Tercer préstamo | Rechazo; no queda en -1 |
| Guardar y cargar | Recupera los datos y cantidades reales |
| Archivo con una fila no numérica | Rechazo completo; conserva estado anterior |
| Archivo con códigos repetidos | Rechazo completo; conserva estado anterior |
| Archivo de solo encabezado | Inventario vacío válido, si ese es tu contrato |

## Entrega y criterios

Entrega un repositorio con fuentes, README, muestra válida del formato y tabla de pruebas. No incluyas datos personales reales. Explica elección de estructuras, identidad y política ante archivo inválido. Un video opcional de 3 a 5 minutos debe mostrar una recuperación después de reiniciar el programa. Declara cualquier asistencia de IA y cómo la comprobaste.

| Criterio | Peso | Evidencia de logro |
|---|---|---|
| Estructuras e identidad | 25 % | Elección razonada, códigos únicos y búsqueda correcta |
| Reglas del modelo | 25 % | Existencias no negativas y rechazos sin alteración |
| Persistencia | 30 % | Formato explícito, UTF-8 y carga validada como conjunto |
| Verificación y documentación | 20 % | Ida y vuelta, corrupción y pasos reproducibles |

## Orientación de solución

Un `LinkedHashMap<String, Kit>` permite localizar por código y conservar orden de inserción. Para mostrar por nombre, copia sus valores a una lista y ordena con un Comparator. `prestar()` comprueba unidades antes de reducirlas. Define un límite para devoluciones si necesitas impedir desbordamiento o cantidades imposibles.

El archivo puede usar `codigo;nombre;unidades` con campos que prohíban `;` y saltos de línea. Lee encabezado, número de campos, entero, rango y unicidad. Construye un mapa candidato sin reemplazar el actual. Solo después de validar todas las filas actualiza el inventario. Para la ida y vuelta, compara contenido por campos o implementa igualdad pertinente; que dos objetos sean referencias diferentes no implica pérdida de datos.

El [proyecto integrador](11-proyecto-integrador/README.md) muestra este patrón con registro de personas y cupos. Adapta las responsabilidades a inventario: no reemplaces solo los textos del menú.

## Autoevaluación

¿La estructura aplica la identidad real? ¿Tu ordenamiento modifica el original? ¿Sabes dónde se guarda el archivo? ¿Una carga corrupta destruye datos que estaban bien? ¿Tu prueba de recuperación verifica información y no solo que el archivo exista?
