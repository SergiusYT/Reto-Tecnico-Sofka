package view;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ReportesView extends CustomizacionComponentes{

    public ReportesView() {
        setLayout(new GridLayout(1, 2));
        // Panel 1: Atracciones más y menos visitadas
        JPanel panelAtracciones = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g, obtenerDatosAtracciones(), "Preferencias de Atracciones");
            }
        };
        panelAtracciones.setPreferredSize(new Dimension(400, 400));
        add(panelAtracciones);

        // Panel 2: Clientes frecuentes
        JPanel panelClientes = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g, obtenerDatosClientesFrecuentes(), "Clientes Frecuentes");
            }
        };
        panelClientes.setPreferredSize(new Dimension(400, 400));
        add(panelClientes);
    }

    private Map<String, Integer> obtenerDatosAtracciones() {
        // Simulación de datos. Reemplazar con una consulta a la base de datos.
        Map<String, Integer> datos = new HashMap<>();
        datos.put("Rueda Capital 360", 30);
        datos.put("Apocalipsis", 10);
        return datos;
    }

    private Map<String, Integer> obtenerDatosClientesFrecuentes() {
        // Simulación de datos. Reemplazar con una consulta a la base de datos.
        Map<String, Integer> datos = new HashMap<>();
        datos.put("3+ visitas", 10); // Mayores de edad con más de 3 visitas
        datos.put("1-2 visitas", 40); // Mayores de edad con 1-2 visitas
        return datos;
    }

    private void drawPieChart(Graphics g, Map<String, Integer> data, String title) {
        int total = data.values().stream().mapToInt(Integer::intValue).sum();
        int startAngle = 0;
        int x = 50, y = 50, width = 300, height = 300;

        // Dibujar las secciones del pastel
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            int value = entry.getValue();
            int angle = (int) Math.round((value * 360.0) / total);
            g.setColor(getRandomColor());
            g.fillArc(x, y, width, height, startAngle, angle);
            startAngle += angle;
        }

        // Dibujar leyenda
        int legendX = x + width + 20;
        int legendY = y;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            g.setColor(getRandomColor());
            g.fillRect(legendX, legendY, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(entry.getKey() + " (" + entry.getValue() + ")", legendX + 30, legendY + 15);
            legendY += 30;
        }

        // Dibujar título
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(title, x + (width / 4), y - 20);
    }

    private Color getRandomColor() {
        // Generar colores aleatorios para las secciones del gráfico
        return new Color((int) (Math.random() * 0x1000000));
    }

}