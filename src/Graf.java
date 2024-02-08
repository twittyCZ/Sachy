import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import jdk.jfr.Category;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.*;

/**
 * Třída vykreslující graf trvání odehraných tahů
 */
public class Graf {
    /**
     * Inicializace okna a grafu
     */
    public static void zobraz(){
        JFrame win = new JFrame();
        win.setTitle("Graf trvání jednotlivých tahů");
        ChartPanel panel = new ChartPanel(
                createBarChart(DrawingPanel.casyTahu)
        );
        win.add(panel);
        win.pack();

        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }


    /**
     * Vytvoření grafu
     * @param data zobrazovaná data
     * @return
     */
        private static JFreeChart createBarChart(ArrayList<Long> data) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int j = 0;
            for (int i = 0; i < data.size(); i++) {
                if (i % 2 == 0) {
                    j ++;
                    dataset.addValue(data.get(i), "Hráč 1", "Tah " + (j));
                } else {
                    dataset.addValue(data.get(i), "Hráč 2", "Tah " + j);
                }
            }
            JFreeChart chart = ChartFactory.createBarChart(
                    "Doba trvání odehrní jednotlivých tahů",
                    "Tahy [číslo]", "Čas [ms]", dataset
            );

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE); // barva pozadí
            plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // barva vodorovných pruhů
            plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45); // natočení popísků na X
            plot.getDomainAxis().setCategoryMargin(0.2);
            CategoryItemRenderer renderer = plot.getRenderer();
            renderer.setDefaultItemLabelGenerator(
                    new StandardCategoryItemLabelGenerator(
                            "{2}ms", NumberFormat.getIntegerInstance()
                    ) // vypsání hodnot
            );
            renderer.setDefaultItemLabelsVisible(true);
            renderer.setDefaultItemLabelFont(new Font("Calibri",
                    Font.PLAIN, 20));
            BarRenderer br = (BarRenderer) renderer;
            br.setItemMargin(0.05);
            //br.setBarPainter(new StandardBarPainter());// ucelený sloupec
            br.setSeriesPaint(0, Color.GREEN); // změny barvy sloupců
            br.setSeriesPaint(1, Color.YELLOW); // změny barvy sloupců


            return chart;
        }

}
