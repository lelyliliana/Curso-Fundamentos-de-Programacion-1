# U3_05 · Listas y ArrayList

[Anterior](../../unidad3/03-genericos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/05-conjuntos-e-igualdad/README.md)

## Objetivo

Administrar una secuencia de tamaño variable y reconocer mutabilidad.

Tema de la unidad: **Colecciones**.

## Situación que resolveremos

Una lista de participantes crece durante la inscripción. El orden importa y pueden existir nombres repetidos. Usaremos List como contrato y ArrayList como implementación modificable.

## Conceptos y decisiones

List mantiene un orden de posiciones y admite duplicados. ArrayList utiliza un arreglo redimensionable internamente. Su acceso por índice es O(1); agregar al final tiene costo amortizado O(1); insertar o eliminar en medio puede desplazar elementos y cuesta O(n).

Las operaciones principales son add, get, set, remove y size. El índice válido más alto es size - 1. `remove(1)` en una lista de enteros elimina una posición, mientras que `remove(Integer.valueOf(1))` elimina el valor 1 si existe. La sobrecarga exige atención.

`List.of(...)` crea una lista no modificable y no permite elementos nulos. Si necesitas editarla, crea `new ArrayList<>(List.of(...))`. `List.copyOf` genera una copia no modificable de los elementos, pero no una copia profunda de cada objeto.

Para eliminar según una regla, `removeIf` evita modificar directamente una lista dentro de un for-each. Una modificación estructural durante ese recorrido puede causar ConcurrentModificationException. El ejemplo utiliza cadenas inmutables, por lo que una copia de referencias no expone atributos mutables de participantes.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> participantes = new ArrayList<>(List.of("Ana", "Luis"));
        participantes.add("Marta");
        participantes.set(1, "Luisa");
        participantes.removeIf(nombre -> nombre.equals("Ana"));
        System.out.println("Participantes: " + participantes);
        System.out.println("Primero: " + participantes.get(0));
        System.out.println("Cantidad: " + participantes.size());
        List<String> copia = List.copyOf(participantes);
        participantes.add("Pedro");
        System.out.println("Copia: " + copia);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/04-listas
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Participantes: [Luisa, Marta]
Primero: Luisa
Cantidad: 2
Copia: [Luisa, Marta]
```

## Seguir la ejecución

1. El constructor copia dos nombres a una lista modificable.
2. add amplía la secuencia y set reemplaza una posición.
3. removeIf evalúa una regla para cada nombre y retira Ana.
4. El índice 0 pasa a contener Luisa.
5. La copia mantiene dos nombres aunque el original reciba Pedro después.

## Errores frecuentes y cómo interpretarlos

Intentar add sobre List.of lanza UnsupportedOperationException. Usar `get(size())` excede el límite. Asignar otra variable a la misma lista no crea una copia; ambas referencias ven las modificaciones.

## Práctica guiada

Agrega dos veces `Ana` y observa el tamaño. Elimina todos los nombres que comiencen con `A`. Después explica si List sería adecuada para impedir duplicados por identidad.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

List conserva ambos nombres repetidos. `removeIf(nombre -> nombre.startsWith("A"))` elimina ambos. Si necesitas unicidad, estudia Set o un Map por identificador; nombres iguales no significan necesariamente la misma persona.

</details>

## Preguntas para explicar con tus palabras

¿Por qué List.of no admite add? ¿Cuándo cuesta O(n) una modificación de ArrayList?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad3/03-genericos/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/05-conjuntos-e-igualdad/README.md)
