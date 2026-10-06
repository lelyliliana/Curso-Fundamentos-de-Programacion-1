# U1_10 · Métodos, parámetros y retorno

[Anterior](../../unidad1/09-ciclos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/10b-recursion/README.md)

## Objetivo

Dividir una solución en responsabilidades y reutilizar una regla sin copiarla.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

La tarifa de préstamo debe calcularse en varios momentos. Separaremos el cálculo de la impresión: un método devolverá la multa y `main` decidirá cómo mostrarla. Así podremos comprobar la regla sin depender de un mensaje específico.

## Conceptos y decisiones

Un método agrupa instrucciones bajo un nombre. Los parámetros reciben datos; `return` entrega un resultado y termina el método. Un método `void` no devuelve valor. Una variable declarada dentro de un método tiene alcance local y no existe en otros métodos.

El contrato de `calcularMulta` recibe días enteros no negativos y devuelve pesos enteros. La validación forma parte del método para que todos sus usos respeten el mismo requisito. Si se viola el contrato, lanza `IllegalArgumentException`; estudiaremos su manejo en la unidad 2.

Java pasa argumentos por valor. En este ejemplo se copia el valor entero de `dias`, no se crea una conexión que permita cambiar la variable del llamador. Con objetos se copia el valor de la referencia, una distinción importante para comprender las colecciones posteriores.

`static` permite llamar estos métodos desde `main` sin crear un objeto. Es una simplificación útil para reglas pequeñas; cuando datos y comportamiento formen una responsabilidad conjunta, construiremos objetos. No confundas separar métodos con crear una arquitectura grande para cualquier operación.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    static long calcularMulta(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Días negativos");
        }
        return Math.max(0, dias - 7) * 1000L;
    }

    static void mostrarMulta(int dias) {
        System.out.println("Días " + dias + ": " + calcularMulta(dias));
    }

    public static void main(String[] args) {
        mostrarMulta(7);
        mostrarMulta(10);
        long total = calcularMulta(8) + calcularMulta(9);
        System.out.println("Total: " + total);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/10-metodos
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Días 7: 0
Días 10: 3000
Total: 3000
```

## Seguir la ejecución

1. `main` llama a `mostrarMulta(7)`.
2. Ese método llama a `calcularMulta` y usa el valor devuelto.
3. El cálculo termina con `return`; la impresión ocurre fuera de él.
4. Las llamadas con 8 y 9 devuelven 1000 y 2000.
5. `main` suma los resultados y muestra 3000.

## Errores frecuentes y cómo interpretarlos

Un método que imprime el cálculo pero devuelve `void` no permite sumarlo directamente. Copiar la misma fórmula en varios sitios produce versiones divergentes de la regla. Usar variables globales para todo oculta las dependencias.

## Práctica guiada

Crea `calcularDescuento(long precio, int porcentaje)` con precio no negativo y porcentaje entre 0 y 100. Para esta práctica usa pesos enteros y explica si descartas fracciones.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Una solución devuelve `precio * porcentaje / 100` dentro de un rango de precios que no desborde `long`. Para 15000 y 10 devuelve 1500. La división entera descarta fracciones; para reglas monetarias más generales conviene `BigDecimal` y una política explícita de redondeo.

</details>

## Preguntas para explicar con tus palabras

¿Qué gana el cálculo al devolver un valor? ¿Dónde verificarías una regla que deben cumplir todos los llamadores?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/09-ciclos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/10b-recursion/README.md)
