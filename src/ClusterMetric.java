package ASAPModel;

import java.util.Vector;

/******************************************************************************
 * Created by mcigale on 7. 03. 2017.                                         *
 * THSI SHOUD BE RENAMED                                                      *
 * A class that keeps ClusterID connected to the Metrics and metrics Names.   *
 * It also takes care of reading data from TSDB. (For no particular reason.)  *
 * Fields are public, because reasons.                                        *
 ******************************************************************************/

public class ClusterMetric {

    public String ClusterID;
    public Vector<Double> ClusterMetrics;
    public Vector<String> MetricNames;
    public Integer NumberOfMetrics;


    //Constructors
    public ClusterMetric(String ClusterIDInput, Vector<String> MetricNamesInput)
    {
        ClusterID = ClusterIDInput;
        MetricNames = MetricNamesInput;
        NumberOfMetrics = MetricNames.size();
        ClusterMetrics = getMetricsFromCluster(ClusterID, MetricNames);
    }


    //Public functions
    public Integer size()
    {
        return NumberOfMetrics;
    }


    //Helper functions
    private Vector<Double> getMetricsFromCluster(String ClusterID, Vector<String> MetricNames)
    {
        Vector<Double> MetricData = new Vector<Double>();

        switch(ClusterID) {
            case "Arnes":
                MetricData.add(2.0);
                MetricData.add(5.0);
                MetricData.add(1000.0);
                MetricData.add(1.0);
                MetricData.add(1000.0);
                break;
            case "GoogleWest":
                MetricData.add(200.0);
                MetricData.add(25.0);
                MetricData.add(2000.0);
                MetricData.add(2.0);
                MetricData.add(10000.0);
                break;
            case "GoogleAsia":
                MetricData.add(200.0);
                MetricData.add(35.0);
                MetricData.add(2500.0);
                MetricData.add(2.0);
                MetricData.add(10000.0);
                break;
            default:
                MetricData.add(1000.0);
                MetricData.add(200.0);
                MetricData.add(500.0);
                MetricData.add(0.0);
                MetricData.add(0.0);
        }

        return MetricData;
    }
}
