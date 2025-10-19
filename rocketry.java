import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;


public class rocketry {
    public static void showGraphs(double thrust, double radius, double mass, double fuelMass, double impulse){
        //takes average thrust from motor specifications as input
        //velocity-time graph
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
        XYSeries velocitySeries=new XYSeries("Velocity");
        XYSeriesCollection velocityDataset=new XYSeriesCollection(velocitySeries);
        XYSeries altitudeSeries=new XYSeries("Altitude");
        XYSeriesCollection altitudeDataset=new XYSeriesCollection(altitudeSeries);

        //when fuel burns out, thrust becomes 0 which makes the mass of the rocket the dryMass
        while (altitude>=0){
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
            velocitySeries.add(time,vOld);
            altitude=altitude+(vOld*timeChange);
            altitudeSeries.add(time,altitude);
            vOld=vNew;
            time+=timeChange;
        }
        //show velocity-time graph
        JFreeChart velocityChart=ChartFactory.createXYLineChart("Velocity v.s. Time", "Time (s)", "Velocity (m/s)",velocityDataset);
        ChartPanel velocityChartPanel=new ChartPanel(velocityChart);
        JFrame velocityFrame=new JFrame("Velocity graph");
        velocityFrame.add(velocityChartPanel);
        velocityFrame.pack();
        velocityFrame.setVisible(true);

        //show altitude-time graph
        JFreeChart altitudeChart=ChartFactory.createXYLineChart("Altitude v.s. Time", "Time (s)", "Altitude (m)", altitudeDataset);
        ChartPanel altitudeChartPanel=new ChartPanel(altitudeChart);
        JFrame altitudeFrame=new JFrame("Altitude graph");
        altitudeFrame.add(altitudeChartPanel);
        altitudeFrame.pack();
        altitudeFrame.setVisible(true);



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
        showGraphs(20.0, 0.04, 1.0, 0.5, 20.0);
    }
}



