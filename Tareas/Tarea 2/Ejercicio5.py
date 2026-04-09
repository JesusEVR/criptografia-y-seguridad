import numpy as np
import matplotlib.pyplot as plt

def probabilidad_colision(d, n):
    return 1 - np.exp(-(d * (d - 1)) / (2 * n))

def main():
    # Se estipula un tamaño de espacio n (posibles mensajes cifrados)
    n_espacio = 1_000_000 
    
    # Simulaciuón de la intercepción de mensajes
    d_max = int(4 * np.sqrt(n_espacio))
    mensajes_interceptados = np.arange(1, d_max)
    
    # La probbilidad para cada cantidad de mensajes interceptados
    probabilidades = probabilidad_colision(mensajes_interceptados, n_espacio)

    # Probabilidad del 50% (1.17741 * sqrt(n))
    d_50 = 1.17741 * np.sqrt(n_espacio)
    
    # Punto de inflexión donde el ataque es efctivo 
    # Despejando d de: 1 - exp(-d^2/2n) = 0.99 => d = sqrt(-2 * n * ln(0.01))
    d_efectivo = np.sqrt(-2 * n_espacio * np.log(0.01))
    
    print(">>> Resultados")
    print(f"Espacio total simulado (n): {n_espacio:,}")
    print(f"Mensajes necesarios para 50% de éxito: {int(d_50):,}")
    print(f"Mensajes para que el ataque sea efectivo (~99%): {int(d_efectivo):,}")
    
    # Gráfica
    plt.figure(figsize=(10, 6))
    plt.plot(mensajes_interceptados, probabilidades, label='Probabilidad de colisión', color="#5F1054", linewidth=2)
    
    # Punto del 50%
    plt.axhline(0.5, color='green', linestyle='--', alpha=0.5)
    plt.axvline(d_50, color='green', linestyle='--', label=f'50% éxito (d≈{int(d_50)})')
    plt.scatter([d_50], [0.5], color='green', zorder=5)
    
    # Punto de inflexión
    plt.axhline(0.99, color='blue', linestyle=':', alpha=0.5)
    plt.axvline(d_efectivo, color='blue', linestyle='--', label=f'Efectividad ≈ 1 (d≈{int(d_efectivo)})')
    plt.scatter([d_efectivo], [0.99], color='blue', zorder=5)
    
    # Formato de la gráfica
    plt.title('Birthday Attack aplicado a RSA')
    plt.xlabel('Número de mensajes interceptados (d)')
    plt.ylabel('Probabilidad de colisión')
    plt.legend()
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.show()

if __name__ == "__main__":
    main()