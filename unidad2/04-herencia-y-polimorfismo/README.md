# U2_04 · Herencia, sobrescritura y polimorfismo

[Anterior](../../unidad2/03-composicion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/05-clases-abstractas/README.md)

## Objetivo

Invocar un contrato común conservando comportamientos distintos según el objeto.

Tema de la unidad: **Características de la POO**.

## Situación que resolveremos

Dos tipos de recurso tienen reglas de préstamo diferentes. Un libro permite 7 días y una revista 3. El programa consultará un recurso sin preguntar manualmente de qué subtipo se trata.

## Conceptos y decisiones

La herencia establece una relación «es un». Una Revista es un Recurso dentro de este modelo. `extends` define la relación; `super(...)` invoca el constructor de la clase base. La sobrescritura reemplaza el comportamiento heredado para una misma firma. `@Override` permite al compilador verificar la intención.

Polimorfismo significa que una referencia de tipo base puede apuntar a objetos de subtipos y la llamada utiliza el comportamiento del objeto real. En `Recurso recurso = new Revista(...)`, el tipo de la variable es Recurso y el objeto es Revista. No necesitas cadenas de `if` para seleccionar el método.

La sobrecarga es otra cosa: varios métodos del mismo nombre con parámetros distintos. No equivale a sobrescribir. La herencia debe respetar el contrato del tipo base; un subtipo que rechaza arbitrariamente operaciones válidas del base rompe la posibilidad de sustituirlo.

Aquí la clase base tiene una regla predeterminada y el subtipo la especializa. En la próxima lección veremos una clase abstracta cuando la base no deba instanciarse directamente. Evita heredar solo para ahorrar unas líneas: primero comprueba que la relación tenga sentido.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    static void informar(Recurso recurso) {
        System.out.println(recurso.titulo() + ": " + recurso.diasPrestamo() + " días");
    }
    public static void main(String[] args) {
        informar(new Recurso("Libro de algoritmos"));
        informar(new Revista("Ciencia hoy"));
    }
}

class Recurso {
    private final String titulo;
    Recurso(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título vacío");
        }
        this.titulo = titulo;
    }
    String titulo() { return titulo; }
    int diasPrestamo() { return 7; }
}

class Revista extends Recurso {
    Revista(String titulo) { super(titulo); }
    @Override
    int diasPrestamo() { return 3; }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/04-herencia-y-polimorfismo
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Libro de algoritmos: 7 días
Ciencia hoy: 3 días
```

## Seguir la ejecución

1. `informar` recibe un parámetro Recurso.
2. La primera llamada usa el comportamiento de Recurso y devuelve 7.
3. La segunda recibe una Revista, permitida por la relación de tipos.
4. La llamada a `diasPrestamo()` selecciona la implementación de Revista.
5. La función no cambió para atender el nuevo comportamiento.

## Errores frecuentes y cómo interpretarlos

Escribir un método de nombre distinto no sobrescribe; `@Override` ayuda a detectarlo. Guardar una etiqueta `tipo` y preguntar por ella en cada función suele duplicar las reglas. Hacer públicos todos los atributos para que el subtipo los modifique debilita el contrato.

## Práctica guiada

Agrega `MaterialConsulta` con préstamo de 1 día y úsalo con la misma función `informar`. Explica cuándo un recurso solo consultable en sala podría necesitar un contrato diferente.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

El subtipo sobrescribe `diasPrestamo()` y devuelve 1. `informar` funciona sin cambios. Si un material no puede prestarse, devolver un número ficticio puede resultar engañoso; conviene separar el contrato de préstamo de otros recursos.

</details>

## Preguntas para explicar con tus palabras

¿Qué determina el método ejecutado: la variable o el objeto real? ¿Qué distingue sobrecarga de sobrescritura?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad2/03-composicion/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/05-clases-abstractas/README.md)
