# U1_07 · Entrada por consola y salida legible

[Anterior](../../unidad1/06-operadores-y-conversiones/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/08-condicionales/README.md)

## Objetivo

Recibir texto del usuario y convertirlo sin mezclar lecturas incompatibles.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Un formulario de consola pide un nombre y una cantidad de cupos. Usaremos una línea completa para cada dato. Así evitamos el salto de línea pendiente que suele aparecer al mezclar `nextInt()` y `nextLine()`.

## Conceptos y decisiones

`Scanner` permite leer la entrada estándar, que normalmente proviene del teclado. `nextLine()` devuelve el texto de una línea; `Integer.parseInt()` convierte una cadena con formato entero. El contrato inicial es que la cantidad se escriba como entero válido. En la unidad 2 aprenderás a manejar entradas incorrectas con excepciones y reintentos.

La conversión no valida todas las reglas del dominio. `-3` puede convertirse correctamente y aun así ser una cantidad inválida. Distingue el formato del dato (¿es entero?) de su significado (¿es una cantidad permitida?). `strip()` elimina espacios en los extremos.

Un mensaje de entrada debe explicar qué se espera y en qué unidad. Los mensajes finales deben permitir comprobar el cálculo. Utilizamos `println` para que la interacción quede en líneas separadas y su resultado sea fácil de comparar. El cierre de este `Scanner` cierra también `System.in`; aquí se hace al terminar toda la aplicación, nunca antes de que otro componente deba leer.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
            System.out.println("Nombre:");
            String nombre = teclado.nextLine().strip();
            System.out.println("Cantidad de cupos (entero positivo):");
            int cupos = Integer.parseInt(teclado.nextLine().strip());
            if (nombre.isBlank() || cupos <= 0) {
                System.out.println("Nombre y cantidad no válidos");
                return;
            }
            System.out.println("Hola, " + nombre);
            System.out.println("Total en pesos: " + cupos * 15000L);
        }
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/07-entrada-y-salida
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

Cuando el programa lo pida, escribe una línea por dato:

```text
Ana
2
```

La salida siguiente no incluye el eco del teclado, que tu terminal puede mostrar.

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Nombre:
Cantidad de cupos (entero positivo):
Hola, Ana
Total en pesos: 30000
```

## Seguir la ejecución

1. Escribe `Ana` y presiona Enter.
2. Escribe `2` y presiona Enter.
3. La segunda línea se convierte a entero.
4. La validación verifica nombre no vacío y cantidad positiva.
5. El precio se calcula únicamente después de validar.

## Errores frecuentes y cómo interpretarlos

Escribir `dos` genera `NumberFormatException` en esta etapa. No es un error del IDE. Comparar un nombre con `==` no compara su contenido: utiliza `equals` cuando necesites hacerlo. Pedir datos sin indicar formato dificulta la prueba.

## Práctica guiada

Prueba un nombre con espacios a los lados y cupos 4. Después prueba un nombre vacío y cantidad 0. Documenta el resultado de cada caso.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Un nombre con espacios queda limpio por `strip()`. Con 4 cupos, el total es 60000. Nombre vacío o cantidad 0 producen el mensaje de validación. La entrada no numérica todavía tiene un fallo conocido que se resolverá en la unidad 2.

</details>

## Preguntas para explicar con tus palabras

¿Qué valida `parseInt` y qué valida el `if`? ¿Por qué cada lectura usa una línea completa?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/06-operadores-y-conversiones/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/08-condicionales/README.md)
