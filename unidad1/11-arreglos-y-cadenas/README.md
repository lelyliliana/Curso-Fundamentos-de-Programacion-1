# U1_12 · Arreglos y cadenas de texto

[Anterior](../../unidad1/10b-recursion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/12-pruebas-y-depuracion/README.md)

## Objetivo

Recorrer datos de tamaño fijo y diferenciar igualdad de texto e identidad.

Tema de la unidad: **Estructura del código**.

## Situación que resolveremos

Tenemos tres temperaturas y una opción escrita con espacios. Calcularemos una media y normalizaremos la opción. Este primer contacto con arreglos prepara el estudio de estructuras de datos de la unidad 3.

## Conceptos y decisiones

Un arreglo almacena elementos del mismo tipo y tiene longitud fija. Sus índices comienzan en 0: un arreglo de longitud 3 tiene posiciones 0, 1 y 2. La propiedad `length` indica su cantidad de elementos; acceder al índice 3 excede sus límites.

`for-each` recorre elementos cuando no necesitas el índice. Un `for` con índice sirve cuando la posición importa. La media de un arreglo vacío necesita una decisión, porque dividir por su longitud sería dividir por cero. Aquí mostramos «Sin mediciones» y terminamos.

`String` es inmutable: sus operaciones devuelven resultados sin modificar el texto original. `strip()` limpia extremos y `toLowerCase(Locale.ROOT)` normaliza para una comparación independiente de la configuración regional. No significa que todas las reglas de texto sean universales: nombres propios y textos para mostrar no deberían normalizarse destructivamente.

`equals()` compara contenido; `==` compara identidad de referencias cuando trabaja con objetos. Algunas cadenas pueden compartir una instancia, de modo que una prueba ocasional con `==` no demuestra que sea la comparación correcta.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        double[] temperaturas = {28.0, 30.0, 29.0};
        if (temperaturas.length == 0) {
            System.out.println("Sin mediciones");
            return;
        }
        double suma = 0;
        for (double temperatura : temperaturas) {
            suma += temperatura;
        }
        System.out.println("Primera: " + temperaturas[0]);
        System.out.println("Media: " + suma / temperaturas.length);
        String entrada = "  SI  ";
        String opcion = entrada.strip().toLowerCase(Locale.ROOT);
        System.out.println("Confirmado: " + opcion.equals("si"));
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/11-arreglos-y-cadenas
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Primera: 28.0
Media: 29.0
Confirmado: true
```

## Seguir la ejecución

1. El arreglo guarda tres valores `double`.
2. Se verifica que exista al menos uno.
3. El ciclo acumula 87 y la división produce 29.
4. La lectura del índice 0 obtiene 28.
5. La normalización produce `si`, que coincide por contenido.

## Errores frecuentes y cómo interpretarlos

Usar `length()` con arreglos confunde la propiedad del arreglo con el método de String. Un acceso a `temperaturas[temperaturas.length]` está fuera del arreglo. Llamar a `strip()` sin guardar el resultado deja la variable original igual.

## Práctica guiada

Agrega 33.0 al arreglo y calcula también la máxima temperatura. Prueba después un arreglo vacío. Explica por qué la máxima no debe inicializarse siempre en cero.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La media es 30.0 y la máxima 33.0. Para calcular máxima, tras comprobar que no está vacío, inicia con el primer elemento y compara los demás. Si todos los valores fueran negativos, iniciar en cero inventaría una medición inexistente.

</details>

## Preguntas para explicar con tus palabras

¿Cuál es el último índice válido? ¿Por qué `String` y arreglo consultan su tamaño de maneras diferentes?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad1/10b-recursion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/12-pruebas-y-depuracion/README.md)
