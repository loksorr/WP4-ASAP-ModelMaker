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

public class ModelMaker {
    Vector<Vector<Double>> MetricValues = new Vector<Vector<Double>>();
    Vector<Vector<Double>> DiferentialTable = new Vector<Vector<Double>>();
    Vector<Double> CorelationVector = new Vector<Double>();
    Vector<Double> RegressionVector;
    Vector<String> MetricNames = new Vector<String>();
    Vector<String> ModelMetricNames = new Vector<>();

    VectorPrinter printer = new VectorPrinter();

    public void calculateDiferential()
    {
        Integer NumberOfExamples = MetricValues.size();
        Integer NumberOfMetrics = MetricValues.firstElement().size();
        for (int i = 0; i < NumberOfExamples; i++)
        {
            Vector<Double> Master = MetricValues.elementAt(i);
            Vector<Double> Worker;
            for (int j = i + 1; j < NumberOfExamples; j++) {
                Vector<Double> Diff = new Vector<Double>();
                for (int k = 0; k < NumberOfMetrics; k++)  {
                    Worker = MetricValues.elementAt(j);
                    Double a = Worker.elementAt(k);
                    Double b = Master.elementAt(k);
                    Diff.add(a - b);
                }
                DiferentialTable.add(Diff);
            }
        }
    }

    public void calculateCorelation() //not implemented ATM
    {}

    public void calculateRegresion()
    {
        Integer NumberOfExamples = DiferentialTable.size();
        Integer NumberOfMetrics = DiferentialTable.firstElement().size();
        RegressionVector = new Vector<Double>();
       // RegressionVector.add((double)(-NumberOfExamples/2));
        for (int k = 1; k < NumberOfMetrics; k++)  {
            RegressionVector.add((double)(-NumberOfExamples/2));
            ModelMetricNames.add(MetricNames.elementAt(k));
        }

        for (int i = 0; i < NumberOfExamples; i++)
        {
            Vector<Double> Master = DiferentialTable.elementAt(i);
            for (int j = 0; j < NumberOfMetrics-1; j++) {
                Double a = Master.elementAt(0);
                Double b = Master.elementAt(j+1);

                if(a >= 0 && b >=0 ){
                    RegressionVector.set(j, (RegressionVector.elementAt(j) + 1));
                }
                if(a < 0 && b < 0 ){
                    RegressionVector.set(j, (RegressionVector.elementAt(j) + 1));
                }
            }
        }

        for (int i = 0; i < NumberOfMetrics-1 ; i++) {
            RegressionVector.set(i, (RegressionVector.elementAt(i)/NumberOfExamples * 2));

        }

        System.out.println(NumberOfExamples);

    }

    public void clean()
    {
        MetricValues = new Vector<Vector<Double>>();
        DiferentialTable = new Vector<Vector<Double>>();
        CorelationVector = new Vector<Double>();
        RegressionVector = new Vector<Double>();
        MetricNames = new Vector<String>();
    }
}
