# U3_04 · Genéricos y seguridad de tipos

[Anterior](../../unidad3/02-pilas-y-colas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/04-listas/README.md)

## Objetivo

Construir una clase reutilizable que mantenga el tipo de sus datos.

Tema de la unidad: **Genéricos**.

## Situación que resolveremos

Una caja puede contener un nombre o una cantidad. Con Object tendríamos que convertir al leer y podríamos descubrir tarde una mezcla equivocada. Un parámetro de tipo permite expresar el contenido desde la creación de la caja.

## Conceptos y decisiones

Un genérico define una clase o método con parámetros de tipo. `Caja<T>` utiliza T como marcador; `Caja<String>` reemplaza ese marcador por String para esa referencia. El compilador puede impedir que se inserte un valor incompatible antes de ejecutar.

Los genéricos reciben tipos de referencia: usa Integer en lugar de int. Java realiza autoboxing cuando pasa de int a Integer y unboxing en sentido contrario. Un Integer nulo no puede convertirse a int sin provocar NullPointerException, así que este ejemplo no permite contenido nulo.

`<>` en la creación es el operador diamante; permite inferir el parámetro a partir del contexto. No uses el tipo crudo `Caja` sin parámetro, porque pierde comprobaciones y produce advertencias. Tampoco confundas `List<Integer>` con un subtipo de `List<Number>`: los genéricos son invariantes. Los comodines permiten expresar relaciones más flexibles, pero requieren definir si necesitas leer, escribir o ambas cosas.

El parámetro de tipo no cambia la regla de la caja: siempre guarda un valor no nulo y permite consultarlo. La reutilización conserva el contrato, sin repetir una CajaTexto y una CajaEntero con implementaciones iguales.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Caja<String> nombre = new Caja<>("Ana");
        Caja<Integer> cupos = new Caja<>(3);
        String texto = nombre.obtener();
        int cantidad = cupos.obtener();
        System.out.println("Nombre: " + texto);
        System.out.println("Cupos: " + cantidad);
    }
}

class Caja<T> {
    private final T contenido;
    Caja(T contenido) {
        this.contenido = Objects.requireNonNull(contenido, "Contenido nulo");
    }
    T obtener() { return contenido; }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/03-genericos
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Nombre: Ana
Cupos: 3
```

## Seguir la ejecución

1. Caja<String> fija el tipo de la primera referencia.
2. Caja<Integer> recibe 3 convertido a Integer.
3. El método obtener devuelve el tipo correspondiente sin cast manual.
4. La asignación a int desenvuelve el Integer.
5. La salida conserva nombres y cantidades con sus tipos correctos.

## Errores frecuentes y cómo interpretarlos

`Caja<int>` no es válido. Una conversión manual no reemplaza la seguridad del contrato. Intentar guardar texto en Caja<Integer> debe fallar al compilar, no tratarse como un error que el usuario final deba descubrir.

## Práctica guiada

Crea Caja<Double> con 28.5. Luego intenta `Caja<Integer> incorrecta = new Caja<>("tres")` en una copia de práctica y registra el error de compilación. Retira la línea inválida.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La caja de Double devuelve 28.5. La segunda declaración exige Integer pero recibe String; el compilador rechaza la inferencia incompatible. Ese fallo temprano es precisamente el beneficio de usar genéricos.

</details>

## Preguntas para explicar con tus palabras

¿Qué representa T? ¿Qué diferencia hay entre usar Object y usar un parámetro de tipo?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/02-pilas-y-colas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/04-listas/README.md)
