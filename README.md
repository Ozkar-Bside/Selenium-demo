
# 🚀 Selenium Demo CI

Este proyecto automatiza la ejecución de pruebas Selenium con GitHub Actions y publica el reporte generado con **ExtentReports** en **GitHub Pages**.

---

## 🧪 Flujo de trabajo (`selenium-demo.yml`)

### 1. Ejecución del test

- Se ejecuta el test `LoginBusquedaTest.java`
- Se genera el reporte con ExtentReports en:

```
reports/ExtentReport.html
```

> Este archivo **se mantiene en la rama `main`** como registro histórico.

---

### 2. Preparación para despliegue

- Se copia todo el contenido de la carpeta `reports/` a una carpeta temporal `report/`
- El archivo `ExtentReport.html` se renombra a `index.html` **solo para publicación**
- Se agrega un comentario con timestamp para forzar recarga del navegador

---

### 3. Despliegue automático

- Se despliega el contenido de `report/` a la rama `gh-pages` con [peaceiris/actions-gh-pages](https://github.com/peaceiris/actions-gh-pages)
- Esto actualiza el sitio de GitHub Pages en:

```
https://ozkar-bside.github.io/Selenium-demo/
```

> El archivo `index.html` permite que el reporte se cargue directamente sin necesidad de escribir la ruta completa.

---

### 4. Actualización del repositorio

- Se vuelve a copiar el archivo `index.html` a `reports/ExtentReport.html`
- Se hace commit y push a la rama `main` para mantener siempre actualizado el último resultado

---

### 5. Notificación a Microsoft Teams

- Se envía un mensaje automático al canal de Teams configurado
- El mensaje incluye:
  - Nombre del conjunto de pruebas
  - Total de pruebas ejecutadas
  - Cuántas pasaron o fallaron
  - Link directo al reporte en GitHub Pages con query `?v={{timestamp}}` para evitar caché

---

## 📁 Estructura de carpetas

```
├── reports/
│   ├── ExtentReport.html   # Se genera localmente por ExtentReports
│   ├── login.png
│   ├── productos.png
│   ├── detalle-producto.png
│   └── spark/              # Estilos y JS para el reporte
├── report/
│   └── index.html          # Copia de ExtentReport.html con timestamp (solo para gh-pages)
```

---

## 🔗 Accesos rápidos

- 🔍 [Ver último reporte](https://ozkar-bside.github.io/Selenium-demo/)
- 📂 [Repositorio en GitHub](https://github.com/Ozkar-Bside/Selenium-demo)

---

## ⚠️ Notas

- El archivo `index.html` **no se mantiene en la rama `main`**
- Solo `ExtentReport.html` se guarda en `main` para versionamiento
- Todo el contenido de GitHub Pages se borra y reemplaza en cada despliegue

---

## 🤖 Tecnologías

- Selenium + TestNG
- Maven
- ExtentReports v5 (Spark)
- GitHub Actions
- GitHub Pages
- Microsoft Teams Webhook

---

## ✍️ Autor

Oscar Eduardo Rodríguez Romero – [ozkar-bside](https://github.com/Ozkar-Bside)
