# U2_05 · Clases abstractas y especialización

[Anterior](../../unidad2/04-herencia-y-polimorfismo/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/06-interfaces/README.md)

## Objetivo

Compartir estado y exigir una operación que cada subtipo debe implementar.

Tema de la unidad: **Clases abstractas e interfaces**.

## Situación que resolveremos

Una actividad tiene nombre, pero su duración depende del tipo. No existe una duración válida para una actividad genérica de este modelo. Una clase abstracta expresa esa decisión.

## Conceptos y decisiones

Una clase `abstract` no se puede instanciar directamente. Puede tener constructor, atributos, métodos concretos y métodos abstractos. Un método abstracto declara una firma sin cuerpo; una subclase concreta debe implementarlo.

El constructor abstracto sí se ejecuta cuando construyes una subclase. En TallerPractico, `super(nombre)` inicializa el estado común y `minutos()` aporta el comportamiento específico. `descripcion()` ya está implementado en la base y usa polimorfismo al llamar a `minutos()`.

Compartir código no requiere que todos los métodos sean abstractos. El criterio es qué conoce realmente la base y qué debe dejar a los subtipos. La descripción sabe combinar nombre y duración, pero no inventa un tiempo universal.

Java permite extender una sola clase. Si varias responsabilidades independientes requieren contratos, una jerarquía de clases puede volverse rígida. Las interfaces de la siguiente lección permiten expresar capacidades sin compartir necesariamente estado. No llames métodos sobrescribibles desde un constructor para calcular propiedades: el subtipo puede no estar inicializado todavía.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        Actividad actividad = new TallerPractico("Sensores");
        System.out.println(actividad.descripcion());
    }
}

abstract class Actividad {
    private final String nombre;
    Actividad(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        this.nombre = nombre;
    }
    abstract int minutos();
    String descripcion() {
        return nombre + ": " + minutos() + " minutos";
    }
}

class TallerPractico extends Actividad {
    TallerPractico(String nombre) { super(nombre); }
    @Override
    int minutos() { return 90; }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/05-clases-abstractas
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Sensores: 90 minutos
```

## Seguir la ejecución

1. Se crea TallerPractico y se ejecuta el constructor de Actividad.
2. La referencia se conserva con el tipo abstracto.
3. `descripcion()` usa el nombre común de la base.
4. Su llamada a `minutos()` devuelve 90 desde el subtipo.
5. El texto une el estado compartido con el comportamiento especializado.

## Errores frecuentes y cómo interpretarlos

Intentar `new Actividad(...)` falla al compilar. Declarar un método abstracto con cuerpo no respeta su sintaxis. Olvidar implementar un método deja la subclase también abstracta o genera error si se declara concreta.

## Práctica guiada

Crea una subclase `Charla` con duración de 45 minutos. Prueba dos objetos a través de variables Actividad y compara sus descripciones.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Charla extiende Actividad, llama a `super(nombre)` y sobrescribe `minutos()` con 45. No necesita copiar `descripcion()`. Ambas referencias admiten la misma consulta, pero seleccionan duraciones distintas.

</details>

## Preguntas para explicar con tus palabras

¿Por qué la base puede tener constructor aunque no sea instanciable? ¿Qué operación de este modelo no debería tener una regla predeterminada?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad2/04-herencia-y-polimorfismo/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/06-interfaces/README.md)
