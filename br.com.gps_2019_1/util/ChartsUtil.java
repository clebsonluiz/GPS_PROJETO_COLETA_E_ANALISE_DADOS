package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public interface ChartsUtil {

	public static Paint[] CORES_1 = {
			Color.blue, Color.RED, Color.GREEN, Color.CYAN, Color.DARK_GRAY,
			Color.ORANGE, Color.yellow, Color.pink, Color.MAGENTA,Color.LIGHT_GRAY };
	
	public static Paint[] CORES_2 = {
			Color.ORANGE, Color.GRAY, Color.CYAN, Color.DARK_GRAY, Color.yellow, Color.blue,
			Color.RED, Color.pink, Color.MAGENTA, Color.GREEN, Color.LIGHT_GRAY }; 
	
	public static void changeColorsPiePlot(PiePlot piePlot, Paint[] cores)
	{
		for(int i=0;i<piePlot.getDataset().getItemCount(); i++)
		{
			 piePlot.setSectionPaint(piePlot.getDataset().getKey(i), cores[i]); 
		} 
	}
	
	
	public static ChartPanel getChartPanel(JFreeChart chart)
	{
		return new ChartPanel(chart);
	}
	
	public static JFreeChart getBarras(String titulo, String xAlias, String yAlias) {
		
		JFreeChart	graficoBarra = ChartFactory.
				createBarChart(titulo, 
						xAlias,
						yAlias,
						new DefaultCategoryDataset()); 
		graficoBarra.setBackgroundPaint(Color.white); 
		CategoryPlot plot = (CategoryPlot) 
				graficoBarra.getCategoryPlot(); 
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setRangeGridlinePaint(Color.gray);
		plot.setOutlineVisible(false);
		plot.setOrientation(PlotOrientation.VERTICAL);  
		BarRenderer renderer = (BarRenderer) plot.getRenderer(); 
		renderer.setBarPainter( new StandardBarPainter());
		renderer.setDrawBarOutline(true);  

		return graficoBarra;
	}
	
	public static JFreeChart getPizza(String titulo) {
		JFreeChart graficoPizza = ChartFactory. 
				createPieChart(titulo, 
						new DefaultPieDataset(), 
						true, //Legendas 
						true, // Exibir ao passar mouse 
						true //Urls 
						); 
		graficoPizza.setBackgroundPaint(Color.white); 
		PiePlot plot = (PiePlot) graficoPizza.getPlot(); 
		plot.setBackgroundPaint(Color.white);

		PieSectionLabelGenerator labelGenerator	= 
				new StandardPieSectionLabelGenerator( "{0} : {1} : ({2})"); 
		plot.setLabelGenerator(labelGenerator);
		plot.setOutlineVisible(false); 
		plot.setShadowPaint(Color.white);

		return graficoPizza;
	}
	
	public static JFreeChart getAnel(String titulo) {
		
		JFreeChart graficoAnel = ChartFactory.createRingChart(titulo, 
				new DefaultPieDataset(), 
				true, //Legendas 
				true, // Exibir ao passar mouse 
				true //Urls 
				); 
		graficoAnel.setBackgroundPaint(Color.white); 
		PiePlot plot = (PiePlot) graficoAnel.getPlot(); 
		plot.setBackgroundPaint(Color.white);
		
		PieSectionLabelGenerator labelGenerator	= 
		new StandardPieSectionLabelGenerator( "{0} : {1} : ({2})"); 
		plot.setLabelGenerator(labelGenerator);
		plot.setOutlineVisible(false); 
		plot.setShadowPaint(Color.white);

		return graficoAnel;
	}
	
	public static JFreeChart getLinha(String titulo, String xAlias, String yAlias) {
		JFreeChart chart = ChartFactory.
				createLineChart(titulo, xAlias, yAlias, new DefaultCategoryDataset());
		
		chart.setBackgroundPaint(Color.WHITE);
		CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.GRAY);
		plot.setOutlineVisible(false);
		
		LineAndShapeRenderer renderer =  
				(LineAndShapeRenderer)plot.getRenderer();
		
		renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultShapesVisible(true);
		
		for(int i = 0; i< plot.getDataset().getRowCount(); i++)
			renderer.setSeriesStroke(i, new BasicStroke(3.0f)); //Grossura da linha

		return chart;
	}
	
}
