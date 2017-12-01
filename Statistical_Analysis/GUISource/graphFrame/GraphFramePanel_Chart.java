package graphFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Collections;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.Timeline;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.XYDataset;

import black.Utils;
import gray.Global;
import red.Stock;

public class GraphFramePanel_Chart extends JPanel{
	
	GraphFramePanel_Search search = new GraphFramePanel_Search();

	Stock stock; 
	
	String symbol;
	ArrayList<String> date;
	ArrayList<Double> high;
	ArrayList<Double> low;
	ArrayList<Double> open;
	ArrayList<Double> adj_close;
	ArrayList<Double> volume;
	
	BorderLayout layout = new BorderLayout();
	
	DefaultHighLowDataset data;


	JFreeChart chart;
	ChartPanel chartPanel;

	
	Double y_axis_min;
	Double y_axis_max;

	
	public String start_init = "2016-01-01";
	public String end_init = "2017-11-01";

	GraphFramePanel_Chart(){
		renderPanel(start_init, end_init);
	}
	
	public void renderPanel(String start, String end) {
		
		
		removeAll();
		revalidate();
		
		data  = createDataset(start, end);
		chart = createChart(data);
		chartPanel = new ChartPanel(chart);
		
		setLayout(layout);
		add(chartPanel);

		repaint();
	}
	
	/*public void refreshPanel() {
		removeAll();
		revalidate();
		
		data  = createDataset(search.start, search.end);
		chart = createChart(data);
		chartPanel = new ChartPanel(chart);
		
		setLayout(layout);
		add(chartPanel);

		repaint();
	}
	*/
	@SuppressWarnings("unchecked")
	public DefaultHighLowDataset createDataset(String start, String end) {
		stock = new Stock(Global.SYMBOL);
		
		symbol = stock.SYMBOL;
		date = stock.request("DATE", start, end);
		high = stock.request("HIGH", start, end);
		low = stock.request("LOW", start, end);
		open = stock.request("OPEN", start, end);
		adj_close = stock.request("ADJ_CLOSE", start, end);
		volume = stock.request("VOLUME", start, end);
		
		Date[] date_list = new Date[date.size()];
		date_list = Utils.StringToDate(date);
		double[] high_list = new double[high.size()];
		double[] low_list = new double[low.size()];
		double[] open_list = new double[open.size()];
		double[] adj_close_list = new double[adj_close.size()];
		double[] volume_list = new double[volume.size()];
		for (int i=0; i<high_list.length; i++) {
			high_list[i] = high.get(i);
			low_list[i] = low.get(i);
			open_list[i] = open.get(i);
			adj_close_list[i] = adj_close.get(i);
			volume_list[i] = volume.get(i);
		}
		
		y_axis_min = Collections.min(low);
		y_axis_max = Collections.max(high);
		
		DefaultHighLowDataset data = null;
		data = new DefaultHighLowDataset(symbol, date_list, high_list, low_list, open_list, adj_close_list, volume_list);
		
		return data;
	}

	public JFreeChart createChart(DefaultHighLowDataset dataset) {

		chart = ChartFactory.createCandlestickChart("History of " + stock.SYMBOL, "Time", "Price", dataset, true);
		
		XYPlot plot = chart.getXYPlot();
		CandlestickRenderer renderer = (CandlestickRenderer) plot.getRenderer();
		renderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_SMALLEST);
		
		NumberAxis domain = (NumberAxis) plot.getRangeAxis();
		domain.setRange(y_axis_min - 1, y_axis_max + 1);
		
		return chart;
	}
	
}
