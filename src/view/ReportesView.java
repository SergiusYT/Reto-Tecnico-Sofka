package view;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReportesView extends CustomizacionComponentes{

    private JTable tablaAtracciones;
    private JTable tablaClientes;
    private PieChartPanel graficoAtracciones;
    private PieChartPanel graficoClientes;



    public ReportesView() {
        setLayout(new BorderLayout());

        // Crear el banner
        JPanel banner = crearBanner("Reporte de Visitas");

        // Panel superior con tablas
        JPanel panelTablas = new JPanel(new GridLayout(1, 2, 10, 10));
        tablaAtracciones = new JTable(new DefaultTableModel(new Object[]{"Atracción", "Visitas"}, 0));
        tablaClientes = new JTable(new DefaultTableModel(new Object[]{"Cliente", "Visitas"}, 0));

        JScrollPane scrollAtracciones = new JScrollPane(tablaAtracciones);
        JScrollPane scrollClientes = new JScrollPane(tablaClientes);

        panelTablas.add(scrollAtracciones);
        panelTablas.add(scrollClientes);
        panelTablas.setBorder(BorderFactory.createTitledBorder("Tablas de Datos"));

        // Panel inferior con gráficos
        JPanel panelGraficos = new JPanel(new GridLayout(1, 2, 20, 20));
        graficoAtracciones = new PieChartPanel();
        graficoClientes = new PieChartPanel();

        // Asegurar tamaño mínimo para los gráficos
        Dimension graficoDimension = new Dimension(400, 400);
        graficoAtracciones.setPreferredSize(graficoDimension);
        graficoClientes.setPreferredSize(graficoDimension);

        graficoAtracciones.setBorder(BorderFactory.createTitledBorder("Gráfico de Atracciones"));
        graficoClientes.setBorder(BorderFactory.createTitledBorder("Gráfico de Clientes"));

        panelGraficos.add(graficoAtracciones);
        panelGraficos.add(graficoClientes);

        // Usar un JSplitPane para dividir tablas y gráficos con ajuste dinámico
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelTablas, panelGraficos);
        splitPane.setResizeWeight(0.4); // Proporción inicial: tablas 40%, gráficos 60%
        splitPane.setOneTouchExpandable(true); // Botones para ajustar tamaño

        // Añadir los componentes al diseño principal
        add(banner, BorderLayout.NORTH); // Banner en la parte superior
        add(splitPane, BorderLayout.CENTER); // Panel principal con tablas y gráficos
    }

    public void actualizarTablaAtracciones(Map<String, Integer> datos) {
        DefaultTableModel model = (DefaultTableModel) tablaAtracciones.getModel();
        model.setRowCount(0); // Limpiar tabla
        datos.forEach((atraccion, visitas) -> model.addRow(new Object[]{atraccion, visitas}));
    }

    public void actualizarTablaClientes(Map<String, Integer> datos) {
        DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
        model.setRowCount(0); // Limpiar tabla
        datos.forEach((cliente, visitas) -> model.addRow(new Object[]{cliente, visitas}));
    }

    public void actualizarGraficoAtracciones(Map<String, Integer> datos) {
        graficoAtracciones.setDatos(datos);
    }

    public void actualizarGraficoClientes(Map<String, Integer> datos) {
        graficoClientes.setDatos(datos);
    }

    // Clase interna para manejar el gráfico de torta
    private static class PieChartPanel extends JPanel {
        private Map<String, Integer> datos;

        public void setDatos(Map<String, Integer> datos) {
            this.datos = datos;
            repaint(); // Redibujar el gráfico
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (datos == null || datos.isEmpty()) {
                g.drawString("No hay datos disponibles", getWidth() / 2 - 50, getHeight() / 2);
                return;
            }

            // Calcular el total
            int total = datos.values().stream().mapToInt(Integer::intValue).sum();
            int startAngle = 0;
            int x = 20, y = 20, width = Math.min(getWidth() - 40, getHeight() - 40);

            // Dibujar cada segmento
            int colorIndex = 0;
            for (Map.Entry<String, Integer> entry : datos.entrySet()) {
                String label = entry.getKey();
                int value = entry.getValue();
                int angle = (int) Math.round((value / (double) total) * 360);

                g.setColor(getColor(colorIndex++));
                g.fillArc(x, y, width, width, startAngle, angle);

                startAngle += angle;
            }

            // Dibujar leyenda
            int legendY = 30;
            colorIndex = 0;
            for (String label : datos.keySet()) {
                g.setColor(getColor(colorIndex++));
                g.fillRect(20, legendY, 10, 10);
                g.setColor(Color.BLACK);
                g.drawString(label, 40, legendY + 10);
                legendY += 20;
            }
        }

        private Color getColor(int index) {
            Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.CYAN};
            return colors[index % colors.length];
        }
    }

    public JButton getBotonHome() {
        return botonHome;
    } 

}