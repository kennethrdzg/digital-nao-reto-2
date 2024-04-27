# digital-nao-reto-2
Repositorio para el Reto 2 del curso Full Stack Developer Core de Digital Nao, Comandos para Servidores y Bases de Datos

## Google Search Engine Results API
Link: https://serpapi.com/search-api
El endpoint `/search` de la API permite obtener resultados de búsqueda en GOogle a través de la API. Las consultas se realizan a `https://serpapi.com/search`a través de una petición `GET`.
A continuación los parámetros más frecuentes: 

## Parámetros

### Búsqueda
- `q`: parámetro que define la consulta

### Ubicación geográfica
- `location`: define desde dónde quieres que origine la búsqueda

### Localización
- `gl`: define el país que será usado para la búsqueda. Es un código de 2 letras para cada país
- `hl`: define el idioma para la búsqueda

### Paginación
- `start`: omite los primeros "n" resultados
- `num`: define el número máximo de resultados. El valor default es 10
