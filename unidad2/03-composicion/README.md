# U2_03 · Abstracción y composición de objetos

[Anterior](../../unidad2/02-encapsulacion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/04-herencia-y-polimorfismo/README.md)

## Objetivo

Asignar responsabilidades a objetos relacionados sin recurrir automáticamente a herencia.

Tema de la unidad: **Características de la POO**.

## Situación que resolveremos

Una inscripción relaciona una persona y un taller. No es un tipo especial de persona ni de taller. Representaremos esa relación mediante composición: un objeto conserva referencias a otros objetos.

## Conceptos y decisiones

Abstraer significa escoger información relevante para un propósito. Para una inscripción necesitamos identificar a la persona, el taller y el valor; no necesitamos modelar toda la vida de la persona. Composición representa una relación «tiene» o «usa», mientras que herencia representa «es un».

`Inscripcion` recibe sus dependencias por constructor. No crea una persona oculta ni depende de variables globales. El resumen consulta los objetos relacionados y reúne una descripción. Cada objeto conserva su responsabilidad: Persona conoce su nombre, Taller su título e Inscripcion su relación.

Las referencias no son copias profundas. Si dos inscripciones apuntaran al mismo taller mutable, ambas observarían sus cambios. En este ejemplo los atributos son finales y no ofrecemos operaciones de modificación. Para comparar entidades reales, los nombres pueden no ser suficientes: más adelante usaremos identificadores.

Un buen modelo tiene relaciones justificadas por el problema. Crear una jerarquía `Inscripcion extends Persona` porque permite reutilizar un atributo produciría una relación falsa. La reutilización no basta como criterio para la herencia.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona("Ana");
        Taller taller = new Taller("Robótica");
        Inscripcion inscripcion = new Inscripcion(persona, taller, 15000);
        System.out.println(inscripcion.resumen());
    }
}

class Persona {
    private final String nombre;
    Persona(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        this.nombre = nombre.strip();
    }
    String nombre() { return nombre; }
}

class Taller {
    private final String titulo;
    Taller(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título vacío");
        }
        this.titulo = titulo.strip();
    }
    String titulo() { return titulo; }
}

class Inscripcion {
    private final Persona persona;
    private final Taller taller;
    private final long valorPesos;
    Inscripcion(Persona persona, Taller taller, long valorPesos) {
        this.persona = Objects.requireNonNull(persona, "Falta persona");
        this.taller = Objects.requireNonNull(taller, "Falta taller");
        if (valorPesos < 0) {
            throw new IllegalArgumentException("Valor negativo");
        }
        this.valorPesos = valorPesos;
    }
    String resumen() {
        return persona.nombre() + " / " + taller.titulo() + " / " + valorPesos;
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/03-composicion
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Ana / Robótica / 15000
```

## Seguir la ejecución

1. Se construyen primero la persona y el taller.
2. Sus referencias se entregan a la inscripción.
3. El constructor comprueba dependencias y valor.
4. `resumen()` consulta datos a través de métodos.
5. El resultado expresa la relación sin heredar de ninguna de las dos entidades.

## Errores frecuentes y cómo interpretarlos

Confundir composición con concatenar textos pierde el modelo. Guardar solo el nombre del taller impide usar su comportamiento. Incorporar cualquier atributo imaginable hace más difícil mantener el propósito del objeto.

## Práctica guiada

Crea dos inscripciones que compartan el mismo taller y correspondan a personas distintas. Añade una fecha con `java.time.LocalDate` a cada inscripción.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Cada inscripción tiene una persona y fecha propias, pero ambas pueden recibir la misma referencia de Taller. La fecha es un dato de la relación, no necesariamente de la persona. Valídala como no nula y úsala en el resumen.

</details>

## Preguntas para explicar con tus palabras

¿Qué relación expresa «tiene un taller»? ¿Dónde ubicarías la fecha de inscripción y por qué?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad2/02-encapsulacion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/04-herencia-y-polimorfismo/README.md)
