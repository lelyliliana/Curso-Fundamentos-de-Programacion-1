# U3_06 · Conjuntos, identidad y records

[Anterior](../../unidad3/04-listas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/06-mapas/README.md)

## Objetivo

Evitar duplicados según una regla de igualdad explícita.

Tema de la unidad: **Colecciones**.

## Situación que resolveremos

Una persona no debe inscribirse dos veces con el mismo código. Dos nombres iguales pueden corresponder a personas distintas, de modo que elegiremos el código como clave de unicidad. Compararemos esa regla con la igualdad automática de un record.

## Conceptos y decisiones

Set representa elementos únicos según equals. LinkedHashSet conserva orden de inserción; HashSet no garantiza ese orden. La unicidad depende de cómo los objetos definen igualdad, no de su apariencia al imprimir.

Un `record` es una forma compacta de representar un conjunto fijo de componentes. Genera constructor, accesores, equals, hashCode y toString. Su igualdad compara todos los componentes. Dos participantes con el mismo código pero nombres diferentes no son iguales como records; si la regla de negocio exige unicidad por código, guarda esos códigos en un Set o usa un Map por código.

Un record es superficialmente inmutable: no puedes reasignar sus componentes, pero un componente que referencia una lista mutable puede cambiar internamente. No es sinónimo de «cualquier objeto totalmente inmutable». Aquí los componentes son String, que sí son inmutables.

Si implementas equals manualmente en otra clase, debes mantener un hashCode coherente para estructuras basadas en hash. Evita modificar los atributos que determinan igualdad mientras el objeto está en un conjunto. Elegir primero la identidad del dominio evita eliminar por error personas distintas.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Participante ana = new Participante("P01", "Ana");
        Participante anaRepetida = new Participante("P01", "Ana");
        System.out.println("Records iguales: " + ana.equals(anaRepetida));
        Set<String> codigos = new LinkedHashSet<>();
        System.out.println("Primera alta: " + codigos.add(ana.codigo()));
        System.out.println("Duplicado: " + codigos.add(anaRepetida.codigo()));
        codigos.add("P02");
        System.out.println("Códigos: " + codigos);
    }
}

record Participante(String codigo, String nombre) {
    Participante {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos incompletos");
        }
        codigo = codigo.strip();
        nombre = nombre.strip();
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/05-conjuntos-e-igualdad
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Records iguales: true
Primera alta: true
Duplicado: false
Códigos: [P01, P02]
```

## Seguir la ejecución

1. Los dos records reciben los mismos componentes y son iguales por contenido.
2. El primer código se incorpora al conjunto.
3. La segunda alta encuentra el código y devuelve false.
4. P02 agrega un elemento nuevo.
5. LinkedHashSet imprime según el orden de inserción conservado.

## Errores frecuentes y cómo interpretarlos

Un HashSet no debe usarse para prometer un orden de salida. Creer que record compara únicamente el primer componente puede violar una regla por identificador. Personalizar equals sin hashCode puede impedir detectar duplicados.

## Práctica guiada

Crea un participante con P01 y nombre `Ana María`. Compara los records y luego intenta agregar su código al Set. Explica por qué los resultados son distintos.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Los records no son iguales porque un componente cambió. El código sigue siendo P01, por lo que el Set rechaza la nueva alta. La identidad por código y la igualdad de todos los datos son relaciones diferentes.

</details>

## Preguntas para explicar con tus palabras

¿Qué define la unicidad del sistema? ¿Por qué un nombre no es una buena clave universal?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/04-listas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/06-mapas/README.md)
