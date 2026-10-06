# U1_02 · Paradigmas de programación

[Anterior](../../unidad1/01-comprender-el-problema/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/03-ambiente-y-primer-programa/README.md)

## Objetivo

Reconocer enfoques imperativo, orientado a objetos y funcional en una misma tarea.

Tema de la unidad: **Paradigmas de programación**.

## Situación que resolveremos

Tenemos tres consumos de energía: 12, 8 y 10 kWh. Calcularemos el total de tres formas. El objetivo es comparar cómo se expresa la solución; no es necesario dominar todavía clases, arreglos o streams.

## Conceptos y decisiones

Un paradigma orienta la organización de la solución. En el enfoque imperativo se indica cómo cambia el estado paso a paso. La programación orientada a objetos reúne datos y comportamiento en objetos con responsabilidades. El enfoque funcional favorece transformaciones y funciones; Java permite usar operaciones funcionales junto a clases e instrucciones imperativas.

| Enfoque | Pregunta principal | Elemento del código |
|---|---|---|
| Imperativo | ¿Qué pasos y cambios hago? | Acumulador en un ciclo |
| Orientado a objetos | ¿Quién conserva y procesa los datos? | Objeto `Medicion` |
| Funcional | ¿Qué transformación o reducción necesito? | `sum()` sobre una secuencia |

Un lenguaje puede soportar varios paradigmas. Declarar una clase que contiene solamente `main` no demuestra por sí solo buen diseño orientado a objetos. Tampoco usar un stream convierte automáticamente todo el programa en funcional. La comparación no establece un ganador universal: elegimos según claridad, responsabilidades y contexto.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] consumos = {12, 8, 10};
        int total = 0;
        for (int consumo : consumos) {
            total += consumo;
        }
        System.out.println("Imperativo: " + total);
        Medicion medicion = new Medicion(12, 8, 10);
        System.out.println("Objetos: " + medicion.total());
        System.out.println("Funcional: " + Arrays.stream(consumos).sum());
    }
}

class Medicion {
    private final int dia1;
    private final int dia2;
    private final int dia3;

    Medicion(int dia1, int dia2, int dia3) {
        this.dia1 = dia1;
        this.dia2 = dia2;
        this.dia3 = dia3;
    }

    int total() {
        return dia1 + dia2 + dia3;
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/02-paradigmas
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Imperativo: 30
Objetos: 30
Funcional: 30
```

## Seguir la ejecución

1. El arreglo reúne los mismos datos para la comparación.
2. El ciclo cambia `total` de 0 a 12, luego a 20 y finalmente a 30.
3. El objeto almacena tres valores y ofrece el comportamiento `total()`.
4. El stream reduce los valores a una suma sin que escribamos un acumulador.
5. Las tres salidas deben coincidir porque resolvemos el mismo problema.

## Errores frecuentes y cómo interpretarlos

Copiar datos diferentes para cada versión invalida la comparación. Usar streams sin entender el cálculo oculta errores. Guardar tres días en tres atributos es deliberadamente pequeño: no escala a un mes, y se mejorará con colecciones.

## Práctica guiada

Cambia el consumo del segundo día a 18 en las dos representaciones y predice las tres salidas. Describe qué versión modificarías si necesitases guardar fecha y consumo de cada medición.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Las tres salidas serán 40. Un objeto por medición permitiría asociar fecha y consumo; una colección conservaría varios objetos. El cálculo de suma puede seguir siendo imperativo o funcional dentro de ese diseño.

</details>

## Preguntas para explicar con tus palabras

¿Qué cambia entre las tres versiones y qué permanece igual? ¿Por qué una aplicación puede combinar paradigmas?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/01-comprender-el-problema/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/03-ambiente-y-primer-programa/README.md)
