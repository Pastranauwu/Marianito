import pandas as pd
import matplotlib.pyplot as plt

# Datos
data = pd.DataFrame({
    'Fecha': ["Día 1", "Día 2", "Día 3", "Día 4", "Día 5", "Día 6", "Día 7"],
    'Trabajo.restante': [8, 8, 8, 5, 5, 0, 0],
    'Trabajo.ideal': [16, 12.8, 9.6, 6.4, 3.2, 0, 0]
})

# Crear el gráfico burndown chart
plt.figure(figsize=(10, 6))
plt.plot(data['Fecha'], data['Trabajo.restante'], marker='o', label='Trabajo Restante', color='blue')
plt.plot(data['Fecha'], data['Trabajo.ideal'], marker='o', label='Trabajo Ideal', color='red', linestyle='dashed')
plt.title('Gráfico burndown chart')
plt.xlabel('Tiempo (días)')
plt.ylabel('Esfuerzo (puntos de historia)')
plt.legend()
plt.grid(True)
plt.show()