package graph;

import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class Graph {
    public ChartPanel panel;
    private DefaultCategoryDataset data = new DefaultCategoryDataset();
    public String nm;
    public double mn;

    void createGraph(Subject obj) {
        nm = obj.name;
        mn = obj.mean;
        for (int a = 0; a < obj.size2; a++) {
            if (obj.marks2.get(a) != 0) {
                data.setValue(obj.fr.get(a), "Number of Students", obj.marks2.get(a));
            }
        }
        if (obj.marks2.get(0) == 0) {
            data.setValue(obj.fr.get(0), "Number of Students", "AB");
        }
        JFreeChart chart = ChartFactory.createBarChart(obj.name, "Marks", "Number of students", data, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot bc = chart.getCategoryPlot();
        bc.setRangeGridlinePaint(Color.BLUE);
        panel = new ChartPanel(chart);
    }
}