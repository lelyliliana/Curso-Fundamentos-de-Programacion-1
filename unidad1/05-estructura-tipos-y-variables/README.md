# U1_05 · Estructura del código, tipos y variables

[Anterior](../../unidad1/04-control-de-versiones/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/06-operadores-y-conversiones/README.md)

## Objetivo

Leer un programa de consola y escoger tipos coherentes para datos sencillos.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Un taller ofrece 3 cupos de 15000 pesos cada uno. El programa representará el nombre, la cantidad, el precio y si la inscripción sigue abierta. Los nombres de las variables deben comunicar el significado de cada dato.

## Conceptos y decisiones

Una variable tiene tipo, nombre y valor. `int` representa enteros; `long` permite enteros de mayor rango; `double` aproxima números con decimales; `boolean` representa verdadero o falso; `char` un valor de carácter UTF-16; `String` es una clase para texto. Los tipos primitivos no son objetos, mientras que `String` sí lo es.

`final` impide reasignar una variable después de inicializarla. Es útil para una tarifa que no cambia durante este cálculo. No convierte automáticamente en inmutable un objeto al que una variable hace referencia. En Java importan las mayúsculas: `String` y `string` no son lo mismo.

El programa sigue una secuencia: declara datos, calcula y muestra. Una expresión produce un valor; una instrucción realiza una acción. El punto y coma termina muchas instrucciones, pero no se pone después de cada llave. Los comentarios deben aclarar una decisión; repetir «suma» encima de una suma aporta poco. Elegimos pesos enteros para no introducir redondeos en un precio sin centavos. Para dinero fraccionario, estudiaremos `BigDecimal`.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        String taller = "Introducción a robótica";
        int cupos = 3;
        final long precioPesos = 15000L;
        boolean inscripcionAbierta = true;
        long totalPesos = cupos * precioPesos;
        System.out.println("Taller: " + taller);
        System.out.println("Total: " + totalPesos);
        System.out.println("Inscripción abierta: " + inscripcionAbierta);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/05-estructura-tipos-y-variables
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Taller: Introducción a robótica
Total: 45000
Inscripción abierta: true
```

## Seguir la ejecución

1. `taller` guarda texto entre comillas dobles.
2. `cupos` guarda un entero; la tarifa se declara como `long`.
3. La multiplicación produce un `long` porque participa la tarifa de ese tipo.
4. Al unir texto y valores con `+`, Java construye la salida textual.
5. El booleano imprime `true`; una interfaz podría traducirlo a «sí».

## Errores frecuentes y cómo interpretarlos

Usar comillas en `"3"` produce texto, no un entero. Intentar reasignar `precioPesos` genera error por `final`. Nombrar todas las variables `x`, `a` y `dato` dificulta revisar la regla.

## Práctica guiada

Representa un segundo taller con 5 cupos de 22000 pesos. Añade una variable para el nombre del responsable y muéstrala. Explica qué dato tiene sentido como constante en este ejemplo.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

El total es 110000 pesos. El nombre usa `String`. La tarifa puede ser `final` si permanece fija durante la operación; si las tarifas cambian por taller, no conviene imponer una constante global única.

</details>

## Preguntas para explicar con tus palabras

¿Por qué el precio usa `long`? ¿Qué pasa si quieres guardar 2.5 en un `int`?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/04-control-de-versiones/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/06-operadores-y-conversiones/README.md)
