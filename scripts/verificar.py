"""Comprueba ejemplos independientes, contratos del proyecto y enlaces locales."""
from pathlib import Path
import json
import re
import shutil
import subprocess
import tempfile

ROOT = Path(__file__).resolve().parents[1]

def ejecutar(carpeta, args=(), entrada=''):
    resultado = subprocess.run(['java', 'Main.java', *args], cwd=carpeta,
                               input=entrada, text=True, encoding='utf-8',
                               capture_output=True, timeout=45)
    if resultado.returncode:
        raise AssertionError(f'{carpeta.name}: salida {resultado.returncode}\n{resultado.stderr}\n{resultado.stdout}')
    return resultado.stdout.replace('\r\n', '\n')

def main():
    ejemplos = json.loads((ROOT / 'scripts/ejemplos.json').read_text(encoding='utf-8'))
    for ejemplo in ejemplos:
        origen = ROOT / ejemplo['folder']
        esperado = (origen / 'esperado.txt').read_text(encoding='utf-8')
        with tempfile.TemporaryDirectory(prefix='curso-java-') as temporal:
            carpeta = Path(temporal)
            shutil.copy(origen / 'Main.java', carpeta / 'Main.java')
            obtenido = ejecutar(carpeta, entrada=ejemplo['stdin'])
            if obtenido != esperado:
                raise AssertionError(f"{ejemplo['folder']}\nEsperado: {esperado!r}\nObtenido: {obtenido!r}")
            if origen.name == '11-proyecto-integrador':
                salida = ejecutar(carpeta, ('--verificar',))
                if salida != 'Verificación de dominio y persistencia aprobada\n':
                    raise AssertionError('La verificación del proyecto no produjo su confirmación')
                # Menú: alta válida, duplicado, guardado, carga y consulta.
                menu = ejecutar(carpeta, ('--interactivo',), '1\nP09\nMaría\n1\nP09\nOtra\n3\n4\n2\n0\n')
                for texto in ('Inscripción registrada', 'Código duplicado: P09',
                              'Guardado completo', 'Carga completa', 'P09: María', 'Disponibles: 2'):
                    if texto not in menu:
                        raise AssertionError(f'Falta comportamiento del menú: {texto}')
        print(f"OK {ejemplo['folder']}")
    # Validación de la entrada sencilla y de los rechazos de dominio.
    consola = ROOT / 'unidad1/07-entrada-y-salida'
    with tempfile.TemporaryDirectory(prefix='curso-entrada-') as temporal:
        carpeta = Path(temporal)
        shutil.copy(consola / 'Main.java', carpeta / 'Main.java')
        if 'Nombre y cantidad no válidos' not in ejecutar(carpeta, entrada='\n0\n'):
            raise AssertionError('La entrada vacía no se rechazó')
    enlaces = 0
    for archivo in ROOT.rglob('*.md'):
        contenido = archivo.read_text(encoding='utf-8')
        # En este curso todos los destinos locales son rutas sin espacios.
        for destino in re.findall(r'\]\(([^)]+)\)', contenido):
            if '://' in destino or destino.startswith(('mailto:', '#')):
                continue
            ruta = destino.split('#', 1)[0]
            if ruta and not (archivo.parent / ruta).resolve().exists():
                raise AssertionError(f'Enlace roto en {archivo.relative_to(ROOT)}: {destino}')
            enlaces += 1
        if 'uniremington' in contenido.lower():
            raise AssertionError(f'Referencia institucional en {archivo}')
    print(f'{len(ejemplos)} ejemplos comprobados; proyecto y entrada verificados; {enlaces} enlaces locales válidos.')

if __name__ == '__main__':
    main()
