# U1_01 · Comprender el problema antes de programar

[Anterior](../../README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/02-paradigmas/README.md)

## Objetivo

Transformar una solicitud cotidiana en entradas, reglas, salidas y casos verificables.

Tema de la unidad: **Comprensión del problema**.

## Situación que resolveremos

Una biblioteca presta libros durante 7 días. Por cada día adicional cobra 1000 pesos. Antes de elegir botones o escribir Java, necesitamos precisar qué significa atraso y cuáles datos son válidos. Esta primera solución usa datos fijos para concentrarnos en el razonamiento.

## Conceptos y decisiones

Un problema describe una necesidad; un algoritmo establece pasos finitos y ordenados para atenderla. Un programa expresa esos pasos en un lenguaje ejecutable. Un requisito funcional dice qué debe hacer el sistema; una restricción fija límites. La frase «calcular una multa» no determina por sí sola el plazo, la tarifa ni si los días son hábiles.

| Elemento | Decisión del ejemplo |
|---|---|
| Entrada | Días transcurridos: entero mayor o igual a cero |
| Regla | Hasta 7 días, multa cero; después, 1000 por día |
| Salida | Días de atraso y multa en pesos |
| Supuesto | Días calendario completos, sin fracciones |

Pseudocódigo: leer días; comprobar que sean válidos; calcular `máximo(0, días - 7)`; multiplicar por 1000; informar. El máximo impide que una devolución anticipada produzca una multa negativa. Todavía no estudiaremos toda la sintaxis: identifica qué línea representa cada paso.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        int diasTranscurridos = 10;
        int plazo = 7;
        long tarifaPesos = 1000;
        if (diasTranscurridos < 0) {
            System.out.println("Los días no pueden ser negativos");
            return;
        }
        int diasAtraso = Math.max(0, diasTranscurridos - plazo);
        long multaPesos = diasAtraso * tarifaPesos;
        System.out.println("Atraso: " + diasAtraso);
        System.out.println("Multa: " + multaPesos);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/01-comprender-el-problema
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Atraso: 3
Multa: 3000
```

## Seguir la ejecución

1. `10` entra como dato del problema.
2. La validación descarta un valor negativo antes del cálculo.
3. `10 - 7` produce 3 días de atraso.
4. `3 * 1000` produce 3000 pesos.
5. La salida muestra información comprobable, no solo «operación exitosa».

## Errores frecuentes y cómo interpretarlos

Confundir días transcurridos con días de atraso duplica el cálculo. Cobrar desde el día 7 contradice «hasta 7 días». Programar sin aclarar días hábiles o calendario puede producir una solución correcta para el problema equivocado.

## Práctica guiada

Cambia únicamente los días a 0, 7, 8 y -1. Antes de ejecutar, completa una tabla con el resultado que esperas. Agrega la regla de una multa máxima de 20000 pesos.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Para 0 y 7: atraso 0, multa 0. Para 8: atraso 1, multa 1000. Para -1: mensaje de validación. El máximo se aplica después del cálculo: `Math.min(multaPesos, 20000L)`. Debes aclarar que el atraso sigue siendo el real aunque la multa tenga un tope.

</details>

## Preguntas para explicar con tus palabras

¿Qué pregunta harías al solicitante antes de programar? ¿Por qué el caso 7 es más revelador que probar solamente 10?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/02-paradigmas/README.md)
