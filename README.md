# Job Tracker API

API REST desarrollada con Spring Boot para gestionar el proceso de búsqueda de empleo: registrar candidaturas, hacer seguimiento de su estado (enviada, entrevista, descartada, oferta) y centralizar la información que normalmente se lleva en una hoja de cálculo.

Proyecto personal desarrollado como parte de mi aprendizaje de Spring Boot, aplicando una arquitectura en capas (Controller - Service - Repository) sobre un caso de uso real.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Web (API REST)
- Spring Data JPA
- Base de datos H2 (en fichero local)
- Maven

## Arquitectura

El proyecto sigue una arquitectura en capas estándar de Spring Boot:

- **Controller**: expone los endpoints REST y gestiona las respuestas HTTP (`ResponseEntity`)
- **Service**: contiene la lógica de negocio, actuando como intermediario entre el Controller y el Repository
- **Repository**: gestiona el acceso a la base de datos mediante Spring Data JPA
- **Model**: define la entidad `Candidatura`, mapeada a la base de datos

## Endpoints disponibles

| Método | Endpoint                  | Descripción                          |
|--------|----------------------------|---------------------------------------|
| GET    | `/api/candidaturas`        | Lista todas las candidaturas          |
| GET    | `/api/candidaturas/{id}`   | Busca una candidatura por su id       |
| POST   | `/api/candidaturas`        | Crea una nueva candidatura            |
| PUT    | `/api/candidaturas/{id}`   | Actualiza una candidatura existente   |
| DELETE | `/api/candidaturas/{id}`   | Elimina una candidatura                |

### Ejemplo de body para crear/actualizar (POST/PUT)

```json
{
    "empresa": "Nombre Empresa",
    "puesto": "Backend Junior Java",
    "fechaEnvio": "2026-07-30",
    "estado": "ENVIADO",
    "enlace": "https://ejemplo.com/oferta",
    "comentarios": "Aplicado vía LinkedIn"
}
```

Valores recomendados para `estado` (aún no forzados por validación en esta versión): `ENVIADO`, `ENTREVISTA`, `DESCARTADO`, `OFERTA`.

## Cómo ejecutarlo localmente

1. Clona el repositorio
```bash
   git clone https://github.com/CarlosRojasCode/Job-Tracker.git
```
2. Entra en la carpeta del proyecto
```bash
   cd Job-Tracker
```
3. Ejecuta la aplicación con el wrapper de Maven
```bash
   ./mvnw spring-boot:run
```
4. La API estará disponible en `http://localhost:8080`
5. La consola de la base de datos H2 está disponible en `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:file:./data/jobtracker`, usuario `sa`, sin contraseña)

## Próximas mejoras

- Manejo de errores centralizado con `@ControllerAdvice`
- Validaciones de los datos de entrada con Bean Validation (incluyendo forzar los valores válidos de `estado`, posiblemente mediante un `enum`)
- Endpoint de estadísticas (candidaturas por estado, tasa de respuesta)
- Despliegue en la nube (Render/Railway)

## Autor

Carlos Rojas — [LinkedIn](https://www.linkedin.com/in/carlosrojasanchez/) · [GitHub](https://github.com/CarlosRojasCode)
