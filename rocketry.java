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
        double timeChange=1;
        double burnTime=impulse/thrust;
        double vOld=0.0;
        double vNew=0.0;
        double drag=0;
        double accel;
        double currentMass = 0;
        double dryMass=mass-fuelMass;
        XYSeries velocitySeries= new XYSeries("Veloicty");
        XYSeriesCollection dataset= new XYSeriesCollection(velocitySeries);


        while (time<burnTime+10){
            if (time<=burnTime){
            currentMass=mass-(fuelMass*(time/burnTime));
            }
            else{
                currentMass=dryMass;
                thrust=0;
            }
            
            //find current veloicty at each time and add to graph
            drag=(0.5*1.225*0.8*Math.pow(radius,2)*Math.PI*Math.pow(vOld,2));
            accel=((thrust-drag-(currentMass*9.8)))/currentMass;
            vNew=vOld+(accel)*(timeChange);
            
            velocitySeries.add(time,vNew);
            vOld=vNew;
            time+=timeChange;
        }
        JFreeChart chart=ChartFactory.createXYLineChart("Velocity v.s. Time", "Time (s)", "Velocity (m/s)",dataset);
        ChartPanel chartPanel=new ChartPanel(chart);
        JFrame frame=new JFrame("Velocity graph");
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
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
        updateVelocity(20.0, 0.04, 1.0, 0.5, 20.0);
    }
}



