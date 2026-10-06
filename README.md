# Fundamentos de Programación 1

Curso abierto de **Leli Liliana Díaz Izquierdo** para aprender a resolver problemas con **Java**, construir modelos orientados a objetos y gestionar datos en memoria y archivos.

La ruta comienza con el análisis del problema y avanza hasta una aplicación de consola con registro de participantes, control de cupos y persistencia. Las explicaciones y los ejemplos están organizados para estudiar de manera autónoma y practicar cada concepto con programas ejecutables.

## Empieza aquí

Si estás leyendo desde el celular o no conoces GitHub, puedes estudiar directamente los README. Entra a [la primera lección](unidad1/01-comprender-el-problema/README.md) y utiliza **Siguiente recurso** al terminar. Para ejecutar el código necesitarás un computador con JDK.

[Cómo estudiar y descargar el curso](docs/como-estudiar-en-github.md) · [Preparar el ambiente](docs/ambiente-y-herramientas.md)

## Unidades

| Unidad | Orden de temas | Acceso |
|---|---|---|
| 1. Fundamentos de programación | Comprensión del problema → paradigmas → ambiente de trabajo → control de versiones → estructura del código | [Estudiar unidad 1](unidad1/README.md) |
| 2. Programación orientada a objetos | Elementos de POO → características → clases abstractas e interfaces → excepciones | [Estudiar unidad 2](unidad2/README.md) |
| 3. Estructuras de datos y colecciones | TAD → genéricos → colecciones → manipulación de archivos | [Estudiar unidad 3](unidad3/README.md) |

El orden temático se conserva; las herramientas, explicaciones y prácticas se actualizan. Los contenidos se desarrollan en **33 lecciones**, tres talleres de unidad y un proyecto integrador incluido en la última lección.

## Qué encontrarás en cada ejemplo

- Un problema concreto y el objetivo de aprendizaje.
- Conceptos explicados antes de utilizarlos.
- Código completo en `Main.java`, también visible en el README.
- Ejecución paso a paso y salida esperada.
- Seguimiento de valores, errores frecuentes y límites de la solución.
- Práctica guiada, orientación de solución y preguntas para explicar el razonamiento.
- Enlaces al recurso anterior, al índice y al siguiente recurso.

## Requisitos y decisiones técnicas

Puedes realizar el curso en **Windows, Ubuntu o macOS**. La base es **JDK 21**, con características estables y sin funciones experimentales. Java 21 es la versión elegida para mantener una ruta reproducible; no se presenta como la versión más reciente. Necesitas un editor y una terminal. Git se incorpora en la unidad 1; Python 3 solo es necesario para la verificación global opcional.

Los ejemplos principales son de consola y no requieren frameworks, Maven, bases de datos ni servicios externos. Así cada resultado puede relacionarse con las instrucciones que lo producen. Los archivos generados quedan en carpetas `salida/` excluidas del control de versiones.

## Ejecutar el primer ejemplo

Después de descargar o clonar el repositorio, abre una terminal en su raíz y entra a la carpeta:

```bash
cd unidad1/01-comprender-el-problema
```

Ejecuta:

```bash
java Main.java
```

La primera salida será atraso 3 y multa 3000. Cada README indica su carpeta, entradas y resultados. Consulta la guía de herramientas si necesitas compilar por separado o resolver un error del ambiente.

## Verificar todo el curso

Esta comprobación es opcional. Desde la raíz, con JDK y Python 3 instalados, usa el comando correspondiente:

| Sistema | Comando |
|---|---|
| Windows (PowerShell) | `py -3 scripts/verificar.py` |
| Ubuntu (Terminal) | `python3 scripts/verificar.py` |
| macOS (Terminal) | `python3 scripts/verificar.py` |

Si Windows no ofrece el lanzador `py`, usa `python scripts/verificar.py` y verifica que sea Python 3. El script ejecuta cada ejemplo en una carpeta temporal, compara su salida y verifica las reglas del proyecto y los enlaces internos. Los archivos de la demostración no se mezclan con tus datos. El flujo de GitHub Actions repite la verificación con JDK 21 en Windows, Ubuntu y macOS.

## Recursos de estudio

[Mapa de temas y resultados](docs/mapa-del-curso.md) · [Fuentes oficiales](docs/fuentes.md) · [Cierre y continuidad](docs/cierre-y-continuidad.md)

Consulta el mapa para localizar los temas, sus explicaciones y ejemplos. Completa los talleres de cada unidad para comprobar tu comprensión.

## Autora

**Leli Liliana Díaz Izquierdo**  
Ingeniera de Sistemas y docente.  
[Sitio personal](https://lelyliliana.com/) · [GitHub](https://github.com/lelyliliana)
