import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;


public class rocketry {
    /* 
    public static double Force(double mass) {
        double accel=9.8;
        double force = (mass)*(accel);
        return force;
    }
*/
    public static void updateVelocity(double thrust, double radius, double mass, double fuelMass, double impulse){
        //takes average thrust from motor specifications as input
        double altitude=0.0;
        double time=0.0;
        double timeChange=0.01;
        double burnTime=impulse/thrust;
        double massPerTime=mass-(mass-(fuelMass))*(time/(burnTime));
        double vOld=0.0;
        double vNew=0.0;
        double drag=0;
        double accel;
        XYSeries velocitySeries= new XYSeries("Veloicty");
        XYSeriesCollection dataset= new XYSeriesCollection(velocitySeries);
        JFreeChart chart=ChartFactory.createXYLineChart("Velocity v.s. Time", "Time (s)", "Velocity (m/s)",dataset);
        ChartPanel chartPanel=new ChartPanel(chart);
        JFrame frame=new JFrame("Veloicty graph");
        frame.add(chartPanel);
        frame.setVisible(true);

        while (vNew>=0.0){
            if (time<=burnTime){
            thrust=0;
            }
            //find current veloicty at each time and add to graph
            drag=(0.5*1.225*0.8*Math.pow(radius,2)*Math.PI*vOld);
            accel=((thrust-drag-(massPerTime*9.8)))/massPerTime;
            vNew=vOld+(accel)*(timeChange);
            velocitySeries.add(time,vNew);
            vNew=vOld;
            time+=timeChange;
        }




    }
/* 
    public static double Apogee(double impulse, double thrustAvg, double diameter, double mass, double propMass, double currentTime){
        double airDensity=1.225;
        double frontalArea=(Math.PI*diameter)/4;
        double massPerTime=mass+propMass*(1-(currentTime/burnTime));
        double Drag=(1/2)*airDensity*0.4*frontalArea*Math.pow(velocity,2);
        return apogee;

    }
*/
    public static void main(String[] args) {
        updateVelocity(2.0, 4.0, 18.3, 5.6, 2.4);
    }
}



