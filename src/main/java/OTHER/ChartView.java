/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTHER;

/**
 *
 * @author ANDRE
 */

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;


@ManagedBean
public class ChartView implements Serializable {
 
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    
    private MeterGaugeChartModel meterGaugeModel1;
    private MeterGaugeChartModel meterGaugeModel2;
    
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel livePieModel;

    @PostConstruct
    public void init() {
        createAnimatedModels();
        createMeterGaugeModels();
        createPieModels();
    }
 
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
    
    public MeterGaugeChartModel getMeterGaugeModel1() {
        return meterGaugeModel1;
    }
    
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
 
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
    
    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createLivePieModel();
    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
 
        pieModel1.set("Antiojeras", 540);
        pieModel1.set("Pasta Dentífrica", 325);
        pieModel1.set("Desodorante", 702);
        pieModel1.set("Gel", 421);
 
        pieModel1.setTitle("Productos mas vendidos");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }
 
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
 
        pieModel2.set("Labios", 540);
        pieModel2.set("Medicamentos", 325);
        pieModel2.set("Facial", 702);
        pieModel2.set("Cabello", 421);
 
        pieModel2.setTitle("Categorias");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
        pieModel2.setShadow(false);
    }
    
    
    private void createLivePieModel() {
        livePieModel = new PieChartModel();
 
        livePieModel.set("Candidate 1", 540);
        livePieModel.set("Candidate 2", 325);
    }
    
    
    
    private void createAnimatedModels() {
        
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Ventas del mes");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
         
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("comparacion de Ventas por año");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("USD");
        boys.set("2012", 60);
        boys.set("2013", 70);
        boys.set("2014", 80);
        boys.set("2015", 120);
        boys.set("2016", 100);
        boys.set("2017", 44);
        boys.set("2018", 150);
        boys.set("2019", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("GTQ");
            
        girls.set("2012", 52);
        girls.set("2013", 92);
        girls.set("2014", 152);
        girls.set("2015", 52);
        girls.set("2016", 60);
        girls.set("2017", 110);
        girls.set("2018", 135);
        girls.set("2019", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Ventas en USD");
 
        series1.set(1, 7);
        series1.set(2, 9);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Ventas en GTQ");
 
        series2.set(1, 6);
        series2.set(2, 4);
        series2.set(3, 5);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    } 
    
    private MeterGaugeChartModel initMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(20);
                add(50);
                add(120);
                add(220);
            }
        };
 
        return new MeterGaugeChartModel(140, intervals);
    }
    
    private void createMeterGaugeModels() {
        meterGaugeModel1 = initMeterGaugeModel();
        meterGaugeModel1.setTitle("Clientes registrados");
        meterGaugeModel1.setGaugeLabel("miles");
        meterGaugeModel1.setGaugeLabelPosition("bottom");
 
        meterGaugeModel2 = initMeterGaugeModel();
        meterGaugeModel2.setTitle("Custom Options");
        meterGaugeModel2.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
        meterGaugeModel2.setGaugeLabel("km/h");
        meterGaugeModel2.setGaugeLabelPosition("bottom");
        meterGaugeModel2.setShowTickLabels(false);
        meterGaugeModel2.setLabelHeightAdjust(110);
        meterGaugeModel2.setIntervalOuterRadius(100);
    }
     

}