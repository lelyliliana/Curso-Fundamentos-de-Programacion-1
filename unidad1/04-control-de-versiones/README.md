# U1_04 · Control de versiones con Git

[Anterior](../../unidad1/03-ambiente-y-primer-programa/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/05-estructura-tipos-y-variables/README.md)

## Objetivo

Registrar cambios pequeños y recuperar el contexto de una solución mediante un historial.

Tema de la unidad: **Control de versiones**.

## Situación que resolveremos

Un programa puede funcionar hoy y dejar de hacerlo después de un cambio. Git conserva versiones del proyecto; GitHub aloja repositorios y facilita compartirlos. Un commit registra una instantánea y un mensaje que explica la intención.

## Conceptos y decisiones

Trabaja en una carpeta de práctica independiente para no alterar accidentalmente el historial del curso.

1. Crea `practica-git`, entra en ella y guarda el código como `Main.java`.
2. Inicia el repositorio con `git init`.
3. Configura tu identidad local con `git config user.name "Tu nombre"` y `git config user.email "tu-correo"`. El correo de privacidad de GitHub es una opción.
4. Crea `.gitignore` con las líneas `out/` y `*.class`.
5. Revisa con `git status`; prepara `Main.java` y `.gitignore` con `git add Main.java .gitignore`.
6. Registra con `git commit -m "Agrega saludo inicial"`.
7. Cambia el mensaje del programa. Observa `git diff` antes de prepararlo y crea otro commit.
8. Consulta `git log --oneline` y compara lo registrado con lo que ves en tu editor.

El directorio de trabajo contiene tus archivos actuales. El área de preparación define qué entrará al próximo commit. El repositorio conserva los commits. `git diff` muestra cambios sin preparar; `git diff --staged` muestra los preparados. Guardar en el editor no crea un commit y un commit local no publica automáticamente en GitHub. Para compartir, conecta un remoto y usa `git push` según las instrucciones que muestra tu repositorio. La autenticación debe realizarse con los mecanismos admitidos por GitHub, sin guardar tokens en el código.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Versión inicial del saludo");
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad1/04-control-de-versiones
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Versión inicial del saludo
```

## Seguir la ejecución

1. Ejecuta la versión inicial para comprobar su salida.
2. Registra el archivo junto con las reglas de exclusión.
3. Cambia el texto a `Saludo actualizado`.
4. Revisa el diff: debería mostrar una sola línea modificada.
5. Registra el cambio con un mensaje que explique su propósito.

## Errores frecuentes y cómo interpretarlos

Preparar todo con `git add .` sin revisar puede incluir archivos personales. Los `.class` y carpetas de salida se regeneran y no necesitan versionarse. Los tokens y contraseñas no pertenecen al repositorio, aunque esté configurado como privado.

## Práctica guiada

Crea una rama con `git switch -c mejora-saludo`, agrega una segunda impresión y registra el cambio. Compara `git log --oneline --all --graph` antes y después.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

La rama nueva señala inicialmente el mismo commit que la rama anterior. Después del commit, contiene un cambio adicional. Cambiar de rama cambia los archivos al estado registrado; un cambio pendiente puede impedir la operación si sería sobrescrito. Guarda y registra conscientemente antes de continuar.

</details>

## Preguntas para explicar con tus palabras

¿Cuál es la diferencia entre guardar, preparar, registrar y publicar? ¿Qué aporta un mensaje de commit además del código?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad1/03-ambiente-y-primer-programa/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad1/05-estructura-tipos-y-variables/README.md)
