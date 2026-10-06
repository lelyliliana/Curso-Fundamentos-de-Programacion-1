# U2_01 · Clases, objetos, atributos y métodos

[Anterior](../../unidad1/12-pruebas-y-depuracion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/02-encapsulacion/README.md)

## Objetivo

Modelar una entidad sencilla y distinguir la clase de sus instancias.

Tema de la unidad: **Elementos de la programación orientada a objetos**.

## Situación que resolveremos

Un taller tiene nombre y cupos disponibles. Dos talleres comparten las mismas reglas, pero cada uno conserva su propio estado. La clase describirá la estructura y cada objeto representará un taller concreto.

## Conceptos y decisiones

Una clase define atributos y comportamiento. Un objeto es una instancia creada a partir de ella. Los atributos almacenan estado; los métodos expresan acciones o consultas. `new Taller(...)` crea una instancia y ejecuta su constructor. El constructor tiene el mismo nombre de la clase y no declara tipo de retorno.

`this` identifica el objeto actual. En `this.nombre = nombre`, el lado izquierdo es el atributo y el derecho es el parámetro. Esta distinción permite usar nombres naturales sin inventar abreviaciones. Dos objetos de la misma clase pueden tener valores diferentes y evolucionar independientemente.

En este primer modelo los datos son visibles dentro de la carpeta de ejemplo para estudiar su relación; en la siguiente lección los protegeremos. `inscribir()` reduce un cupo y devuelve si pudo hacerlo. Que una acción devuelva `boolean` permite al llamador decidir cómo informar el resultado. Mostrar mensajes dentro de cada entidad la acoplaría a la consola.

Cada ejemplo incluye `Main` como primera clase y otras clases de apoyo en el mismo archivo para ejecutarlo sin configuración. En un proyecto mayor, estas clases se separan en archivos y paquetes. El diseño no depende de que todo esté en un solo archivo.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        Taller robotica = new Taller("Robótica", 2);
        Taller dibujo = new Taller("Dibujo", 3);
        System.out.println("Inscripción: " + robotica.inscribir());
        System.out.println(robotica.nombre + ": " + robotica.cupos);
        System.out.println(dibujo.nombre + ": " + dibujo.cupos);
    }
}

class Taller {
    String nombre;
    int cupos;

    Taller(String nombre, int cupos) {
        this.nombre = nombre;
        this.cupos = cupos;
    }

    boolean inscribir() {
        if (cupos <= 0) {
            return false;
        }
        cupos--;
        return true;
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/01-clases-y-objetos
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Inscripción: true
Robótica: 1
Dibujo: 3
```

## Seguir la ejecución

1. Se crean dos objetos diferentes.
2. El constructor inicializa el estado de cada uno.
3. `robotica.inscribir()` opera sobre los cupos de Robótica.
4. La operación reduce 2 a 1 y devuelve `true`.
5. Dibujo conserva sus 3 cupos porque no recibió esa operación.

## Errores frecuentes y cómo interpretarlos

Usar `static` para los cupos los compartiría entre todas las instancias. Confundir la variable `robotica` con la clase `Taller` dificulta entender el modelo. Un constructor `void Taller(...)` sería un método, no un constructor.

## Práctica guiada

Inscribe tres veces en Robótica y registra los resultados. Crea un tercer taller y explica si modificar el primero cambia los demás.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Con dos cupos iniciales, las llamadas devuelven true, true y false. Los cupos quedan en 0. Los otros objetos no cambian porque sus atributos de instancia son independientes. El modelo aún debe validar cupos negativos al construir; esa mejora viene a continuación.

</details>

## Preguntas para explicar con tus palabras

¿Qué diferencia hay entre clase y objeto? ¿Por qué `inscribir()` devuelve información?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad1/12-pruebas-y-depuracion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/02-encapsulacion/README.md)
