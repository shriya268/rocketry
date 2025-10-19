import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;

public class rocketry {
    public static void Rocketry(double thrust, double radius, double mass, double fuelMass, double impulse, double parachuteRadius){
        //takes average thrust from motor specifications as input
        //measured radius in meters
        //velocity-time graph
        double altitude=0.0;
        double time=0.0;
        double timeChange=0.1;
        double burnTime=impulse/thrust;
        double vOld=0.0;
        double vNew=0.0;
        double drag=0;
        double accel;
        double currentMass = 0;
        double dryMass=mass-fuelMass;
        double apogeeTime = 0.0;
       // boolean apogeePassed=false;
        double parachuteDeployTime=0;
        double dragCoefficient=0.8;
        double maxAltitude=0.0;
        double apogee=0.0;

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
            //Apogee
            if(altitude>maxAltitude){
                maxAltitude=altitude;
                apogee=maxAltitude;
                apogeeTime=time;
            }
            //Parachute deployment
            parachuteDeployTime=apogeeTime+1;
            if (time>=parachuteDeployTime){
                radius=parachuteRadius;
                dragCoefficient=1.5;
                //drag=(0.5*1.225*1.5*Math.pow(parachuteRadius,2))*Math.PI*Math.pow(vOld,2);
            }

            //find current veloicty at each time and add to graph
            drag=(0.5*1.225*dragCoefficient*Math.pow(radius,2)*Math.PI*Math.pow(vOld,2));
            accel=((thrust-drag-(currentMass*9.8)))/currentMass;
            vNew=vOld+(accel)*(timeChange);
            velocitySeries.add(time,vOld);
            altitude=altitude+(vOld*timeChange);
            altitudeSeries.add(time,altitude);
            vOld=vNew;
            time+=timeChange;
        }
        System.out.println("Apogee Time: "+ apogeeTime + " seconds");
        System.out.println("Parachute Deploy Time: "+parachuteDeployTime + " seconds");
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
    public static void main(String[] args) {
        Rocketry(20.0, 0.08, 1.5, 1.0, 20.0,0.08);
    }
}



