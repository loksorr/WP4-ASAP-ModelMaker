package ASAPModel;

import java.util.Vector;

/*
* =================================================================================
* This file is part of: SWITCH ASAP Model Maker, e.g. Information Service
* Release version: 0.2
* =================================================================================
* Developer: Matej Cigale, University of Ljubljana, Slovenia
* Contributor: Sandi Gec, University of Ljubljana, Slovenia
*
* The project leading to this application has received funding
* from the European Union's Horizon 2020 research and innovation
* programme under grant agreement No 643963.
*
* Copyright 2016
* Contact: Matej Cigale (matej.cigale@fgg.uni-lj.si)
* =================================================================================
* Licensed under the Apache License, Version 2.0 (the "License");
* you must not use this file except in compliance with the License.
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*
* For details see the terms of the License (see attached file: README).
* The License is also available at http://www.apache.org/licenses/LICENSE-2.0.txt.
* ================================================================================
*/


public class DecisionMaker {

    Vector<Vector<Double>> MetricValues = new Vector<Vector<Double>>();
    Vector<Vector<Double>> DiferentialTable = new Vector<Vector<Double>>();
    Vector<Double> CorelationVector = new Vector<Double>();
    Vector<Double> RegressionVector;
    Vector<String> MetricNames = new Vector<String>();

    private static Vector<String> getMetricNamesFromKB(String AppName)
    {
        //Placeholder. Just dummy data. This will be in KB
        Vector<String> metricNames = new Vector<String>();
        switch(AppName) {
            case "MCU":
                metricNames.add("RTT");
                metricNames.add("hopCount");
                metricNames.add("memFree");
                metricNames.add("cpuNum");
                metricNames.add("diskFree");
                break;
            default:
        }
        return metricNames;
    }

    private static Vector<Double> getModelFromKB(String AppName)
    {
        //Placeholder. Just dummy data. This will be in KB
        Vector<Double> model = new Vector<Double>();
        switch(AppName) {
            case "MCU":
                model.add(0.9);
                model.add(-0.8);
                model.add(0.3);
                model.add(0.2);
                model.add(0.2);
                break;
            case "test":
                model.add(-0.1);
                model.add(-0.2);
                model.add(0.5);
                model.add(0.3);
                model.add(0.4);
        }
        return model;
    }

    private static Vector<String> getClustetrID()
    {
        //Placeholder. Just dummy data. But could work. This will be in KB?
        Vector<String> clusterID = new Vector<String>();
        clusterID.add("Arnes");
        clusterID.add("GoogleWest");
        clusterID.add("GoogleAsia");
        return clusterID;
    }

    private static Vector<Integer> getMetricOrder(Vector<Double> model)
    {

        Vector<Integer> orderMap = new Vector<Integer>();
        for (int i = 0; i < model.size(); i++) {
            Double aDouble = model.get(i);
            for (Integer anInteger : orderMap) {
                if (Math.abs(model.get(anInteger)) > Math.abs(aDouble))
                {

                }
                else
                {
                    orderMap.add(orderMap.indexOf(anInteger),i);
                    break;
                }
            }
            if(orderMap.indexOf(i) < 0)
                orderMap.add(model.indexOf(aDouble));
        }
        return orderMap;
    }

    public static ClusterMetric compareClusters(ClusterMetric A, ClusterMetric B, Vector<Integer> order, Vector<Double> model)
    {

        Integer MostRelevantMetric = order.get(0);

        if((model.get(MostRelevantMetric) * A.ClusterMetrics.get(MostRelevantMetric)) > (model.get(MostRelevantMetric) * B.ClusterMetrics.get(MostRelevantMetric)))
        {return A; }
        else if ((model.get(MostRelevantMetric) * A.ClusterMetrics.get(MostRelevantMetric)) < (model.get(MostRelevantMetric) * B.ClusterMetrics.get(MostRelevantMetric)))
        {return B; }
        else
        {
            if (order.size() > 0)
            {
                Vector<Integer> tempOrder = order;
                tempOrder.remove(0);
                ClusterMetric Temp = compareClusters(A,B,tempOrder,model);
                return Temp;
            }
            else
            {
                return A;
                //They are identical as far as collected metrics are concerned. So might as well return A.
            }
        }
    }

    public static String MakeDecision(String AppName, String IP)
    {

        Vector<String> metricNames = getMetricNamesFromKB("MCU");
        Vector<String> clustersID = getClustetrID();
        Vector<Double> model = getModelFromKB("test");
        Vector<Integer> order = getMetricOrder(model);

        ClusterMetric Cluster1 = new ClusterMetric(clustersID.elementAt(0), metricNames);
        ClusterMetric Cluster2 = new ClusterMetric(clustersID.elementAt(1), metricNames);
        ClusterMetric Cluster3 = new ClusterMetric(clustersID.elementAt(2), metricNames);

        ClusterMetric TempCluster = compareClusters(Cluster1,Cluster2,order,model);
        ClusterMetric BestCluster = compareClusters(TempCluster,Cluster3,order,model);


        System.out.printf(BestCluster.ClusterID);
        return BestCluster.ClusterID;
    }

}
