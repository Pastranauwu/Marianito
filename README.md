# Marianito (Java Swing Platformer)

Un pequeño juego tipo Mario hecho en Java/Swing. Incluye mejoras de colisiones, transición de mundos sin bloquear, menú inicial, pausa, caché de imágenes, y sonido modernizado con `Clip`.

## Requisitos
- Java 11+ (OpenJDK o similar)

## Ejecutar desde el código fuente

1. Compilar:
```bash
# desde la carpeta del proyecto
javac -encoding UTF-8 -d out $(find src -name "*.java")
```
2. Copiar recursos:
```bash
cp -R Assets out/
```
3. Ejecutar:
```bash
java -cp out src.Ventana
```

## Crear JAR ejecutable (precompilado)

Para generar un JAR autoejecutable con todos los assets:
```bash
# limpiar y compilar
rm -rf out dist
mkdir -p out dist
javac -encoding UTF-8 -d out $(find src -name "*.java")
# copiar assets
cp -R Assets out/
# empaquetar JAR (Main-Class: src.Ventana)
jar --create --file dist/marianito.jar --main-class src.Ventana -C out .
```

Ejecutar el JAR:
```bash
java -jar dist/marianito.jar
```

Si al ejecutar no carga imágenes/sonidos, asegúrate de:
- Estructura interna del JAR contiene `/Assets/...` en la raíz (el comando anterior lo hace así).
- Tu Java puede abrir audio con `javax.sound.sampled`.

## Controles
- Enter: Iniciar juego (desde el menú)
- Flechas Izquierda/Derecha: Mover
- Flecha Arriba: Saltar
- P: Pausar/Continuar
- Escape: Salir

## Características técnicas
- Bucle de render a ~60 FPS con `Timer` de Swing.
- Lógica de juego a paso fijo (~47 ms) para mantener la velocidad original.
- Transición de niveles no bloqueante con overlay de carga.
- Caché de imágenes (`AssetCache`) para evitar I/O por frame.
- Sonido con `Clip` y caché de bytes (`SoundManager`); BGM en loop y SFX discretos.
- Menú inicial, pausa con overlay, y pantalla final de victoria.

## Estructura
```
Assets/      # imágenes y sonidos
src/         # código fuente Java (paquete src)
  Ventana.java
  Marianito.java
  Enemigos.java
  Bloques.java
  Poderes.java
  AssetCache.java
  SoundManager.java
```

## Notas
- En Linux, si el audio no se reproduce, prueba con otra versión de OpenJDK o instala codecs de sonido.
- Si el rendimiento baja, revisa GPU/driver y compositor de tu entorno gráfico; Swing es CPU-bound.

## Licencia
Proyecto educativo. Assets de ejemplo incluidos solo para fines de demostración.
