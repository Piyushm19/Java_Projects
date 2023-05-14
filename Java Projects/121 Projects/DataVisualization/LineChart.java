import java.awt.Dimension;
import java.awt.Color;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends ApplicationFrame {

       /**
     * Creates a new LineChart instance. The frame title and
     * a JFreeChart dataset object are passed in.
     */
    public LineChart(String title, String hAxisLabel, String vAxisLabel, CategoryDataset dataset) {
        super(title);
        JFreeChart chart = createChart(title, hAxisLabel, vAxisLabel, dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(1100, 300));
        setContentPane(chartPanel);
    }
    
    /**
     * Create and return the bar chart. This method contains several
     * JFreeChart-specific customizations for rendering the chart.
     */
    private JFreeChart createChart(String title, String hAxisLabel, String vAxisLabel, CategoryDataset dataset) {
      //Creat chart passing in title, axes labels and data. 
      JFreeChart lineChart = ChartFactory.createLineChart(title, hAxisLabel, vAxisLabel, dataset,
            PlotOrientation.VERTICAL, true,true,false);
            
      // background color
      lineChart.setBackgroundPaint(Color.white);
     return lineChart;
   }
   
   public void display(){
     this.pack();
     RefineryUtilities.centerFrameOnScreen(this);
     this.setVisible(true);
  }
}
