# U3_10 · Archivos estructurados y validación por línea

[Anterior](../../unidad3/08-archivos-de-texto/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/10-archivos-binarios/README.md)

## Objetivo

Convertir líneas en objetos y señalar errores sin perder su ubicación.

Tema de la unidad: **Manipulación de archivos**.

## Situación que resolveremos

Un intercambio sencillo contiene `codigo;nombre;cupos`. Leer texto no basta: cada fila debe respetar el formato, los tipos y las reglas del modelo. El ejemplo incluye filas inválidas deliberadas para observar el diagnóstico.

## Conceptos y decisiones

Definir un formato requiere acordar encabezado, separador, codificación y datos permitidos. Usamos texto delimitado por punto y coma, sin comillas ni campos que contengan separadores o saltos de línea. No es un lector CSV general. Para CSV con escape, comillas y saltos internos, utiliza una biblioteca especializada.

`split(";", -1)` conserva campos vacíos al final. Sin el -1, una línea como `T01;Robótica;` podría perder el último campo y dificultar el diagnóstico. Después de separar se valida cantidad de campos, conversión numérica y contrato del record.

El número de línea se conserva para ubicar la fila en el archivo. La política de este ejemplo es informar filas inválidas y continuar con las demás. Un sistema diferente podría exigir rechazar el archivo completo; el proyecto final usará esa política más estricta. La decisión debe estar documentada.

No «arregles» un dato inventando ceros sin informar. Un cupo vacío puede significar dato ausente, no cero. La validación distingue errores de estructura, de conversión y de significado aunque se comuniquen desde el mismo bloque de lectura.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Taller convertir(String linea) {
        String[] campos = linea.split(";", -1);
        if (campos.length != 3) throw new IllegalArgumentException("Se requieren 3 campos");
        return new Taller(campos[0].strip(), campos[1].strip(),
                Integer.parseInt(campos[2].strip()));
    }
    public static void main(String[] args) throws IOException {
        Path archivo = Path.of("salida", "talleres.txt");
        Files.createDirectories(archivo.getParent());
        Files.write(archivo, List.of("codigo;nombre;cupos", "T01;Robótica;2",
                "T02;Dibujo;-1", "T03;Arduino;dos"), StandardCharsets.UTF_8);
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        if (lineas.isEmpty() || !lineas.get(0).equals("codigo;nombre;cupos")) {
            throw new IllegalArgumentException("Encabezado inválido");
        }
        List<Taller> validos = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            try {
                validos.add(convertir(lineas.get(i)));
            } catch (IllegalArgumentException e) {
                System.out.println("Línea " + (i + 1) + ": datos inválidos");
            }
        }
        System.out.println("Válidos: " + validos.size());
        System.out.println(validos.get(0).nombre());
    }
}

record Taller(String codigo, String nombre, int cupos) {
    Taller {
        if (codigo.isBlank() || nombre.isBlank() || cupos < 0) {
            throw new IllegalArgumentException("Datos de taller inválidos");
        }
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/09-formato-y-validacion-de-archivos
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Línea 3: datos inválidos
Línea 4: datos inválidos
Válidos: 1
Robótica
```

## Seguir la ejecución

1. El ejemplo sobrescribe su archivo de demostración con un encabezado y tres filas.
2. Verifica el encabezado antes de interpretar los datos.
3. La línea 2 produce un Taller válido.
4. La línea 3 falla por cupos negativos y la 4 por texto no numérico.
5. La lista contiene solo el taller válido y conserva diagnósticos de las otras filas.

## Errores frecuentes y cómo interpretarlos

Tratar este split como un parser CSV completo falla con campos entre comillas. Numerar desde cero confunde al usuario al localizar la fila. El acceso get(0) solo es válido aquí porque el archivo fijo incluye una fila válida; con un archivo externo debes comprobar si la lista está vacía.

## Práctica guiada

Agrega una fila con el último campo vacío y otra con cuatro campos. Incluye una comprobación para no llamar get(0) si ninguna fila es válida. Decide si los códigos duplicados deben aceptarse.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Ambas filas nuevas se rechazan. Para una lista vacía, muestra `Sin talleres válidos`. Los códigos duplicados requieren una regla adicional con Set o Map; la lectura por formato no asegura unicidad por sí sola.

</details>

## Preguntas para explicar con tus palabras

¿Qué limitaciones tiene el formato de esta práctica? ¿Por qué conservar el número de línea?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/08-archivos-de-texto/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/10-archivos-binarios/README.md)
