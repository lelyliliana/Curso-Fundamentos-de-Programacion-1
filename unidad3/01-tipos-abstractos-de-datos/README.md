# U3_01 · Tipos abstractos de datos y arreglos

[Anterior](../../unidad2/08-modelo-integrado/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/01b-matrices/README.md)

## Objetivo

Separar las operaciones de una estructura de su representación en memoria.

Tema de la unidad: **Estructuras de datos abstractas (TAD)**.

## Situación que resolveremos

Un registro de mediciones necesita guardar valores y calcular su promedio. Definiremos qué operaciones ofrece y después estudiaremos cómo un arreglo puede implementarlas. El usuario del registro no debe manipular sus posiciones internas.

## Conceptos y decisiones

Un tipo abstracto de datos describe valores y operaciones con su comportamiento. Su contrato responde qué se puede hacer; la implementación responde cómo se almacena y procesa. No equivale a una clase abstracta de Java: el concepto existe con independencia del lenguaje y de esa palabra clave.

El registro permite agregar una medición, consultar cantidad y calcular promedio. Tiene capacidad fija y rechaza valores negativos o no finitos. El contrato define que el promedio sin mediciones no existe y lanza IllegalStateException. El arreglo tiene capacidad 3, pero `cantidad` informa cuántas posiciones están ocupadas.

Un arreglo permite acceso por índice en tiempo constante, pero su longitud no cambia. Agregar aquí usa la siguiente posición libre; cuando no hay espacio, el contrato rechaza la operación. No confundas capacidad con cantidad: recorrer todo el arreglo incluiría ceros de posiciones todavía libres y alteraría el promedio.

La complejidad mide cómo crece el trabajo con el tamaño: consultar cantidad es O(1); sumar n mediciones es O(n). Estas descripciones no son tiempos exactos en milisegundos. Un dato más grande puede requerir otra estructura, pero la elección debe responder a las operaciones necesarias.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        Registro registro = new Registro(3);
        registro.agregar(12);
        registro.agregar(18);
        System.out.println("Cantidad: " + registro.cantidad());
        System.out.println("Promedio: " + registro.promedio());
    }
}

class Registro {
    private final double[] datos;
    private int cantidad;
    Registro(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad inválida");
        datos = new double[capacidad];
    }
    void agregar(double valor) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException("Medición inválida");
        }
        if (cantidad == datos.length) throw new IllegalStateException("Registro lleno");
        datos[cantidad++] = valor;
    }
    int cantidad() { return cantidad; }
    double promedio() {
        if (cantidad == 0) throw new IllegalStateException("Sin mediciones");
        double suma = 0;
        for (int i = 0; i < cantidad; i++) suma += datos[i];
        return suma / cantidad;
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/01-tipos-abstractos-de-datos
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Cantidad: 2
Promedio: 15.0
```

## Seguir la ejecución

1. El constructor crea tres posiciones disponibles, inicialmente en cero.
2. La primera medición se guarda en el índice 0 y cantidad pasa a 1.
3. La segunda se guarda en 1 y cantidad pasa a 2.
4. El promedio recorre solo esas dos posiciones.
5. La tercera posición no afecta el resultado porque no contiene una medición registrada.

## Errores frecuentes y cómo interpretarlos

Dividir entre `datos.length` confunde capacidad con cantidad. Permitir que el llamador reciba el arreglo interno rompe el control del registro. `NaN` e infinito son valores posibles de double, por lo que comprobar solo `< 0` no los rechaza.

## Práctica guiada

Agrega 30 y calcula el nuevo promedio. Después intenta una cuarta medición y comprueba que el rechazo no altere las tres anteriores. Prueba promedio con un registro vacío.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

El promedio de 12, 18 y 30 es 20.0. La cuarta operación lanza IllegalStateException por capacidad llena y cantidad sigue en 3. En un registro vacío, promedio lanza la excepción definida; no debe inventar cero como si fuera una medición.

</details>

## Preguntas para explicar con tus palabras

¿Qué diferencia hay entre contrato e implementación? ¿Qué operación de esta clase recorre n elementos?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad2/08-modelo-integrado/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/01b-matrices/README.md)
