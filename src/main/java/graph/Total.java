package graph;

import java.io.*;
import java.util.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class Total {
    private String fileName;
    public ChartPanel panel;
    private DefaultCategoryDataset data = new DefaultCategoryDataset();
    public double mean;
    public Total(String nm) {
        fileName = nm;
        computeTotal();
    }
    private void computeTotal() {
        ArrayList < Double > total = new ArrayList < Double > ();
        ArrayList < Double > total2 = new ArrayList < Double > ();
        ArrayList < Integer > fr = new ArrayList < Integer > ();
        try {
            BufferedReader obj = new BufferedReader(new FileReader("data/" + fileName + "_Edited.TXT"));
            String line;
            while ((line = obj.readLine()) != null) {
                String[] lar = line.split("(?!\\w)");
                int siz = lar.length;
                int sum = 0, cnt = 0;
                int min = Integer.parseInt(lar[2].trim());
                double perc;
                for (int a = 1; a < siz - 4; a += 3) {
                    if (Integer.parseInt(lar[a + 1].trim()) < min) 
                        min = Integer.parseInt(lar[a + 1].trim());
                    sum += Integer.parseInt(lar[a + 1].trim());
                    cnt++;
                }
                if (lar[siz - 1].equals("*")) {
                    total.add(0.0);
                    continue;
                }
                if (cnt == 6) 
                    sum -= min;
                perc = ((double)(sum * 100)) / 500;
                total.add(perc);
            }
            double cp = 0.0;
            for (int a = 0; a < 501; a++) {
                int ct = 0;
                for (int b = 0; b < total.size(); b++) {
                    if (cp == total.get(b)) 
                        ct++;
                }
                if (ct != 0) {
                    total2.add(cp);
                    fr.add(ct);
                }
                cp = (double)(Math.round((cp + 0.2) * 10)) / 10;
            }
            for (int a = 0; a < total2.size(); a++) {
                if (total2.get(a) != 0.0) {
                    data.setValue(fr.get(a), "Number of Students", total2.get(a));
                }
            }
            if (total2.get(0) == 0.0) {
                data.setValue(fr.get(0), "Number of Students", "AB");
            }
            JFreeChart chart = ChartFactory.createBarChart("Total Percentage", "Marks", "Number of students", data, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot bc = chart.getCategoryPlot();
            bc.setRangeGridlinePaint(Color.BLUE);
            panel = new ChartPanel(chart);
            mean = 0;
            for (int a = 0; a < total.size(); a++) {
                mean += total.get(a);
            }
            mean = mean / total.size();
        } catch (Exception e) {}
    }
}