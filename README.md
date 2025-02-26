# DWF-Investigaci-n-Aplicada
Sistema de Gestión de Torneos de Videojuegos

# Descripcion del Proyecto
Este sistema permite administrar torneos de videojuegos con diferentes tipos de competencias (Eliminación Directa, Liga y Doble Eliminación). Los torneos pueden incluir entre 4 y 16 equipos, y los enfrentamientos se generan automáticamente.

### Formatos de Torneo
- **Eliminación Directa**: Los equipos juegan rondas eliminatorias hasta que quede un ganador.
- **Liga**: Cada equipo juega contra todos los demás y acumulan puntos. El equipo con más puntos al final gana.
- **Doble Eliminación**: Un equipo debe perder dos veces para quedar eliminado.

# Requisitos del Proyecto 
-Java 17
-Maven
-Git y Github

## Instalacion 
```markdown ```

## Uso
1. Ejecuta el proyecto:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.Main"

# Estructura del proyecto 
Describe la estructura de carpetas y archivos principales del proyecto.
```markdown```
src/ ├── main/ │ ├── java/ │ │ └── com/example/ │ │ ├── Torneo.java│ │ ├── TorneoEliminacionDirecta.java│ │ ├── TorneoLiga.java│ │ ├── TorneoDobleEliminacion.java│ │ ├── Jugador.java│ │ └── Equipo.java│ └── resources/ └── test/ ├── java/ │ └── com/example/ │ └── 

## Pruebas Unitarias
1. Ejecuta las pruebas unitarias:
   ```bash ```
   mvn test

   ### Paso 8: Contribuciones
Instrucciones para que otros desarrolladores puedan contribuir al proyecto.
```markdown```
## Contribuciones
Para contribuir a este proyecto:
1. Haz un fork del repositorio.
2. Crea una rama para tu feature:
   ```bash
   git checkout -b feature/nueva-feature
TorneoTest.java└── resources/

# 
