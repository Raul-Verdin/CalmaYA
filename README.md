# 🧘‍♀️ CalmaYA - Monitoreo de Estrés con Google Pixel Watch 3

Este proyecto consiste en una aplicación dual (Wear OS + Android) que permite al usuario monitorear su nivel de estrés mediante el sensor de ritmo cardíaco del Google Pixel Watch 3.

## 📲 ¿Cómo funciona?

- **Wear OS App (`calmaya_watch`)**
  - Lee los latidos por minuto (BPM) desde el sensor cardíaco.
  - Envía los datos cada 5 segundos al dispositivo móvil vía `DataClient`.

- **App Móvil (`calmaya_mobile`)**
  - Recibe los BPM enviados por el reloj.
  - Muestra los datos en pantalla para el usuario.
  - Cuenta con interfaz de sesión (por implementar).

## 🚀 Estado Actual

✅ El reloj ya puede medir BPM y enviarlos.  
✅ El móvil puede mostrar los datos recibidos.  
❌ Pendiente iniciar sesión de Google para probar emparejamiento completo.  
❌ Falta mejorar las interfaces de usuario.  

---

## 🛠️ ¿Cómo correr el proyecto?

### 🔗 Requisitos
- Android Studio Hedgehog o más reciente.
- Un dispositivo Wear OS (ej. Google Pixel Watch 3 real o emulado).
- Dispositivo Android real o emulado (API 30+).
- Cuenta de Google para pruebas de emparejamiento (opcional de momento).

### 📁 Estructura
CalmaYA/
├── calmaya_watch/ --> Proyecto Wear OS
└── calmaya_mobile/ --> Proyecto móvil Android


### 🧪 Pasos para probar

1. Abre el proyecto completo en Android Studio.
2. Corre el emulador del reloj o conecta el dispositivo Pixel Watch 3.
3. Corre el emulador móvil o conecta un teléfono real.
4. Asegúrate de que ambos estén en la misma red Wi-Fi (importante).
5. Inicia ambas apps. Deberías ver en el móvil los BPM simulados o reales desde el reloj.

---

## 📌 Mejoras futuras

- [ ] Autenticación con Google para alumnos.
- [ ] Guardar BPM en Firebase o localmente.
- [ ] Visualización de gráficas y tendencias.
- [ ] Alerta si el BPM excede cierto umbral (estrés alto).
- [ ] Interfaz profesional para el móvil (pantallas limpias y modernas).
- [ ] Interfaz visual atractiva en el reloj (iconos, color, animaciones).

---

## 🎨 Mejoras de interfaz pendientes

### Wear OS
- Diseño más elegante con íconos y colores relajantes.
- Agregar ícono de estrés o animación sutil de respiración.

### Móvil
- Pantalla de inicio con login.
- Pantalla principal con nombre del alumno y BPM en grande.
- Posible integración con gráficos (MPAndroidChart).

---

## 🤝 Créditos

Proyecto desarrollado como parte del proyecto final de **Wearables**  
por [Tu Nombre] – Universidad Tecnológica Fidel Velázquez.

---

## 📷 Screenshots

_Puedes agregar imágenes aquí como evidencia visual del funcionamiento._

---

## 📄 Licencia

MIT License
