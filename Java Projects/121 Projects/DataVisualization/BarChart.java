import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * This class utilizes the JFreeChart library to render the average high 
 * and low temperatures for Amherst MA.
 * It is passed an object of type CategoryDataset that contains the data and metadata
 * required for the chart to be rendered.
 */
public class BarChart extends ApplicationFrame {

    /**
     * Creates a new BarChart instance. The frame title and
     * a JFreeChart dataset object are passed in.
     */
    public BarChart(String title, String hAxisLabel, String vAxisLabel, CategoryDataset dataset) {
        super(title);
        JFreeChart chart = createChart(title, hAxisLabel, vAxisLabel, dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(700, 400));
        setContentPane(chartPanel);
    }

    /**
     * Create and return the bar chart. This method contains several
     * JFreeChart-specific customizations for rendering the chart.
     */
    private JFreeChart createChart(String title, String hAxisLabel, String vAxisLabel, CategoryDataset dataset) {
        //Creat chart passing in title, axes labels and data. 
        JFreeChart barChart = ChartFactory.createBarChart(title, hAxisLabel, vAxisLabel, dataset,
            PlotOrientation.VERTICAL, true,true,false);

        //Now set specific visual aspects of the chart:

        // background color
        barChart.setBackgroundPaint(Color.white);

        // line customizations
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series
        GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, new Color(0, 0, 64)
        );
        GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, new Color(0, 64, 0)
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // end of chart customizations
        return barChart;
    }
    
    public void display(){
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }
}