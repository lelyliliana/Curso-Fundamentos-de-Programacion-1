# U3_02 · Matrices y recorridos por filas

[Anterior](../../unidad3/01-tipos-abstractos-de-datos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/02-pilas-y-colas/README.md)

## Objetivo

Procesar arreglos de arreglos sin suponer que todas las filas tienen igual longitud.

Tema de la unidad: **Estructuras de datos abstractas (TAD)**.

## Situación que resolveremos

Cada fila contiene consumos de un aula en varios días. Algunas aulas tienen dos mediciones y otra tiene tres. Sumaremos por fila y calcularemos un total respetando esa diferencia.

## Conceptos y decisiones

En Java, `int[][]` representa un arreglo cuyos elementos son otros arreglos. Puede ser rectangular, pero no está obligado a serlo. La longitud exterior informa cuántas filas hay; cada fila tiene su propia longitud. Para recorrer columnas utiliza `consumos[fila].length`.

Los índices identifican posiciones, no días o códigos por sí mismos. Si una posición representa un día ausente, debes documentarlo; no uses cero automáticamente para un dato faltante. En este ejemplo las filas solo contienen mediciones presentes.

Dos ciclos anidados visitan cada celda. El acumulador de fila se inicializa al comenzar cada fila y el total general fuera de ambos ciclos. Ubicar el acumulador en el lugar equivocado mezcla subtotales o borra resultados anteriores.

El costo de recorrer una matriz rectangular de f filas y c columnas es O(f × c). Para filas de longitudes diferentes, el trabajo corresponde al número total de elementos. El ejemplo fija todas las filas como no nulas; con datos externos también necesitarías validar cada fila antes de consultar length.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        int[][] consumos = {{12, 8}, {10, 5, 7}};
        int total = 0;
        for (int fila = 0; fila < consumos.length; fila++) {
            int subtotal = 0;
            for (int columna = 0; columna < consumos[fila].length; columna++) {
                subtotal += consumos[fila][columna];
            }
            total += subtotal;
            System.out.println("Aula " + (fila + 1) + ": " + subtotal);
        }
        System.out.println("Total: " + total);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/01b-matrices
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Aula 1: 20
Aula 2: 22
Total: 42
```

## Seguir la ejecución

1. El arreglo exterior tiene dos filas.
2. La primera recorre 12 y 8, y produce 20.
3. El subtotal se reinicia antes de visitar la segunda fila.
4. La segunda recorre tres valores y produce 22.
5. El total suma 20 y 22, conservando ambos subtotales.

## Errores frecuentes y cómo interpretarlos

Usar la longitud de la primera fila para todas omite datos o excede límites. Inicializar total dentro del ciclo de filas deja solo el último subtotal. Mostrar fila sin sumar 1 confunde un índice técnico con la numeración para el usuario.

## Práctica guiada

Agrega una tercera fila vacía y una cuarta con 6, 9 y 5. Predice las cuatro salidas y el total. Explica qué debería mostrar una media para la fila vacía.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Los subtotales son 20, 22, 0 y 20; el total es 62. Para la media vacía necesitas indicar ausencia de mediciones, no dividir entre cero ni presentar 0 como promedio observado.

</details>

## Preguntas para explicar con tus palabras

¿Qué mide la longitud exterior? ¿Cómo verificas el tamaño real de cada fila?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/01-tipos-abstractos-de-datos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/02-pilas-y-colas/README.md)
