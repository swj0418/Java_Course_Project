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
	ChartPanel insidepanel;
	
	Double y_axis_min;
	Double y_axis_max;

	
	GraphFramePanel_Chart(){
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		setLayout(layout);
		
		data  = createDataset();
		chart = createChart(data);
		insidepanel = new ChartPanel(chart);
		add(insidepanel);

		
		validate();
		repaint();
	}
	
	public DefaultHighLowDataset createDataset() {
		stock = new Stock(Global.SYMBOL);
		
		symbol = stock.SYMBOL;
		date = stock.request("DATE", "2017-09-01", "2017-11-01");
		high = stock.request("HIGH", "2017-09-01", "2017-11-01");
		low = stock.request("LOW", "2017-09-01", "2017-11-01");
		open = stock.request("OPEN", "2017-09-01", "2017-11-01");
		adj_close = stock.request("ADJ_CLOSE", "2017-09-01", "2017-11-01");
		volume = stock.request("VOLUME", "2017-09-01", "2017-11-01");
		
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

	private JFreeChart createChart(final DefaultHighLowDataset dataset) {
		 
		chart = ChartFactory.createCandlestickChart("History of " + stock.SYMBOL, "Time", "Price", dataset, true);
		
		XYPlot plot = chart.getXYPlot();
		CandlestickRenderer renderer = (CandlestickRenderer) plot.getRenderer();
		renderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_SMALLEST);
		
		NumberAxis domain = (NumberAxis) plot.getRangeAxis();
		domain.setRange(y_axis_min - 1, y_axis_max + 1);
		
		return chart;
	}
}
