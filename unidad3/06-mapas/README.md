# U3_07 · Mapas, búsqueda y conteos

[Anterior](../../unidad3/05-conjuntos-e-igualdad/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/07-recorrido-filtrado-y-orden/README.md)

## Objetivo

Relacionar claves con valores y recuperar información por identificador.

Tema de la unidad: **Colecciones**.

## Situación que resolveremos

Buscaremos a una persona por código y contaremos inscripciones por taller. Un Map representa asociaciones de clave a valor; no es una lista de posiciones.

## Conceptos y decisiones

Un Map asocia cada clave con un valor. Las claves son únicas; distintos códigos pueden tener el mismo nombre. `put` agrega o reemplaza, `get` consulta y `containsKey` comprueba existencia. Map no extiende Collection, aunque ofrece vistas de claves, valores y entradas.

LinkedHashMap conserva el orden de inserción. HashMap no garantiza orden y sus búsquedas tienen costo esperado O(1) bajo condiciones normales de dispersión. TreeMap mantiene claves ordenadas y operaciones principales O(log n). Escoge según la necesidad, no por el nombre más conocido.

`get` puede devolver null cuando no encuentra una clave. Si el mapa admite valores null, ese resultado también puede significar una asociación existente con null; `containsKey` permite distinguir. El modelo de este ejemplo no inserta nombres nulos.

`merge(clave, 1, Integer::sum)` agrega 1 si la clave no tiene valor y suma si ya existe. `Integer::sum` es una referencia a método usada como regla de combinación. Para prohibir códigos repetidos, `putIfAbsent` permite conservar el primero y detectar la colisión. Un put sin verificación puede reemplazar información silenciosamente.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> personas = new LinkedHashMap<>();
        personas.put("P01", "Ana");
        personas.put("P02", "Luis");
        String anterior = personas.putIfAbsent("P01", "Otra persona");
        System.out.println("Duplicado detectado: " + (anterior != null));
        System.out.println("P01: " + personas.get("P01"));
        System.out.println("P99 existe: " + personas.containsKey("P99"));
        Map<String, Integer> conteo = new LinkedHashMap<>();
        for (String taller : new String[]{"Robótica", "Dibujo", "Robótica"}) {
            conteo.merge(taller, 1, Integer::sum);
        }
        System.out.println("Conteo: " + conteo);
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/06-mapas
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Duplicado detectado: true
P01: Ana
P99 existe: false
Conteo: {Robótica=2, Dibujo=1}
```

## Seguir la ejecución

1. El mapa relaciona dos códigos con sus nombres.
2. putIfAbsent encuentra P01 y devuelve su valor previo sin sustituirlo.
3. get confirma que Ana sigue asociada a P01.
4. containsKey informa que P99 está ausente.
5. merge cuenta dos ocurrencias de Robótica y una de Dibujo.

## Errores frecuentes y cómo interpretarlos

Usar put para un alta que debe ser única puede reemplazar otra persona. Suponer que el Map se consulta por índice confunde su contrato. Desenvolver un Integer nulo al hacer un conteo manual provoca un fallo si la clave aún no existe.

## Práctica guiada

Agrega un taller más y muestra cada par con `entrySet()`. Busca P99 y produce un mensaje explícito de persona no encontrada. Compara qué pasaría si cambiaras putIfAbsent por put.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Cada entrada ofrece getKey y getValue. Para P99 puedes comprobar containsKey o un get nulo bajo el contrato de no admitir valores null. put sustituiría Ana por Otra persona, comportamiento incorrecto para un alta única.

</details>

## Preguntas para explicar con tus palabras

¿Qué ocurre al insertar una clave existente? ¿Cuándo preferirías TreeMap?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/05-conjuntos-e-igualdad/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/07-recorrido-filtrado-y-orden/README.md)
