# Test_2_Programacion_Avanzada
Modulo centrado en manejo de inasistencias y atrasos
Sistema de manejo de inasistencias y atrasos

Este módulo para la inspectoría se centra en el editar, añadir, eliminar inasistencias y atrasos mientras también permite observar las susodichas para revisión.
Características Principales:
- Añadir atraso
- Funcion para pasar la lista de asistencia
- Editar atraso o inasistencia
-Eliminar atraso o inasistencia
- Revisar historial de atrasos e inasistencias
- Navegación por curso, alumno y tipo de registro
- Inicio de sesión con credenciales
Elementos utilizados:
-java.
-apache tomcat.
-MySQL.
-NetBeans.
instalación:
-Preparar ambiente NetBeans con tomcat y script SQL
-ejecutar botón "Run".

Uso:
1. Abrir el proyecto en NetBeans.
2. Configurar Apache Tomcat como servidor.
3. Importar el script SQL incluido en /db para crear la base de datos.
4. Ajustar credenciales de conexión en el archivo correspondiente del DAO.
5. Ejecutar el proyecto con el botón "Run".
6. Ingresar credenciales en login 

Estructura:
/src/java
    /vistas
	/asistencia
		/atraso
		/curso
		/estudiante
		/inasistencia
    /controlador
    /modelo
    /dao
    /db
    /dto
    /vista
    /servicios

Ejemplo:
-Inspector inicia sesión
-Selecciona Curso 2B 
-Alumno Juan Pérez 
-Menú inasistencias
-“Agregar inasistencia” 
-Ingresa fecha y motivo 
-Guardar.
 
Autores:
-Pedro Maldonado 
-Lester Sáez
-Benjamin Aedo
-Benjamin Campos
-Benjamin Manriquez
