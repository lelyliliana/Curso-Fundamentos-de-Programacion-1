# U2_02 · Encapsulación y contratos del objeto

[Anterior](../../unidad2/01-clases-y-objetos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/03-composicion/README.md)

## Objetivo

Proteger un estado válido mediante constructor y operaciones controladas.

Tema de la unidad: **Características de la POO**.

## Situación que resolveremos

El taller no debería tener cupos negativos ni permitir que cualquier parte del programa cambie la cantidad directamente. El objeto será responsable de conservar sus reglas durante toda su existencia.

## Conceptos y decisiones

Encapsular consiste en reunir una responsabilidad y controlar su acceso. `private` impide el acceso directo al atributo desde otras clases. Pero agregar getters y setters para todos los campos sin validar no garantiza encapsulación: un setter que acepta -10 sigue rompiendo el dominio.

Una invariante es una propiedad que el objeto debe conservar. Aquí el nombre no está vacío y los cupos nunca son negativos. El constructor rechaza un estado inicial inválido. `inscribir()` solo resta si hay disponibilidad. Una consulta devuelve los cupos sin permitir reemplazarlos.

`final` en el nombre impide cambiar la referencia después del constructor. No usamos setter porque el ejemplo no necesita renombrar talleres. Diseñar una API de objeto significa ofrecer operaciones que tengan sentido, no exponer todo su almacenamiento.

La validación debe ubicarse donde no se pueda eludir. Validar solo en `main` dejaría otros llamadores libres de crear talleres incorrectos. Las excepciones indican un contrato violado; la falta normal de cupos se representa como `false`, porque es una situación prevista del negocio.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        Taller taller = new Taller("Robótica", 1);
        System.out.println("Primera: " + taller.inscribir());
        System.out.println("Segunda: " + taller.inscribir());
        System.out.println("Disponibles: " + taller.cuposDisponibles());
    }
}

class Taller {
    private final String nombre;
    private int cupos;

    Taller(String nombre, int cupos) {
        if (nombre == null || nombre.isBlank() || cupos < 0) {
            throw new IllegalArgumentException("Nombre o cupos inválidos");
        }
        this.nombre = nombre.strip();
        this.cupos = cupos;
    }

    boolean inscribir() {
        if (cupos == 0) {
            return false;
        }
        cupos--;
        return true;
    }

    int cuposDisponibles() {
        return cupos;
    }

    String nombre() {
        return nombre;
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad2/02-encapsulacion
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Primera: true
Segunda: false
Disponibles: 0
```

## Seguir la ejecución

1. El constructor comprueba el nombre antes de llamar a `strip()`.
2. La condición usa cortocircuito para no operar sobre `null`.
3. La primera inscripción reduce 1 a 0.
4. La segunda detecta cupos agotados y no cambia el estado.
5. La consulta devuelve 0 sin exponer una asignación directa.

## Errores frecuentes y cómo interpretarlos

Llamar a `isBlank()` antes de comprobar null produce un fallo diferente de la validación deseada. Un setter público sin restricciones permite saltarse la regla. Decrementar antes de comprobar puede dejar el objeto en -1 aunque después devuelvas false.

## Práctica guiada

Añade `ampliarCupos(int cantidad)` que acepte únicamente cantidades positivas. Prueba la ampliación después de agotar el taller. Define también un máximo de cupos para evitar un rango sin control.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Comprueba `cantidad > 0` y que la suma no supere el máximo definido, por ejemplo 100. Después de agotar 1 cupo, ampliar en 2 permite dos nuevas inscripciones. La operación debe rechazar el incremento inválido sin cambiar el estado previo.

</details>

## Preguntas para explicar con tus palabras

¿Qué invariante conserva esta clase? ¿Por qué no existe un setter de cupos?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad2/01-clases-y-objetos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad2/03-composicion/README.md)
