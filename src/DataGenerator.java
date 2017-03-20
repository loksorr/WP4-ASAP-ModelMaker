package ASAPModel;

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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;
import java.util.Collections;
import static java.lang.Math.random;

public class DataGenerator {

    String[] MetricNamesArray = {"uploadSpeed","rtt","troughtput","numberOfJumps","packetLoss","numberOfCores","AvailableMemory","FileSize","cpuIOWait","numberofContainers","uploadTime"};
    Vector<String> MetricNames = new Vector<String>(Arrays.asList(MetricNamesArray));
    Vector<Vector<Double>> MetricValues = new Vector<Vector<Double>>();

    public void generateNewMetricVector()
    {
        double uploadSpeed;
        double rtt;
        double troughtput;
        double numberOfJumps;
        double packetLoss;
        double numberOfCores;
        double AvailableMemory;
        double FileSize;
        double cpuIOWait;
        double numberofContainers;
        double uploadTime;

        numberOfJumps = (int)(random() * 20);
        rtt = (int)(numberOfJumps * (1+ random()* 10));
        troughtput = 100000 - random()* 80000 - numberOfJumps * 1000 ; //bps
        packetLoss = random() * numberOfJumps * 0.01 + random() * 0.01;
        numberOfCores = (int)(1 + random() *7);
        AvailableMemory = ((int)(1 + random() * 15)) * 1000000; //kb
        FileSize = (int)(100 + random() * 2000000); //kb
        cpuIOWait = random() * 100;
        numberofContainers = (double)(int)(random() * 20);
        uploadTime = rtt *0.1 + rtt*FileSize/2 *0.001 + 1000*FileSize/troughtput + packetLoss * 1000*FileSize/troughtput;
        if (FileSize > AvailableMemory)
        {
         uploadTime = uploadTime + (FileSize-AvailableMemory)*0.001;
        }
        uploadSpeed = FileSize/uploadTime;
        Vector<Double> MeasurmentInstance = new Vector<Double>();

        MeasurmentInstance.add(uploadSpeed);
        MeasurmentInstance.add(rtt);
        MeasurmentInstance.add(troughtput);
        MeasurmentInstance.add(numberOfJumps);
        MeasurmentInstance.add(packetLoss);
        MeasurmentInstance.add(numberOfCores);
        MeasurmentInstance.add(AvailableMemory);
        MeasurmentInstance.add(FileSize);
        MeasurmentInstance.add(cpuIOWait);
        MeasurmentInstance.add(numberofContainers);
        MeasurmentInstance.add(uploadTime);

        MetricValues.add(MeasurmentInstance);

    }

    public void generateNewMetricVector(double Jumps, double BW)
    {
        double uploadSpeed;
        double rtt;
        double troughtput;
        double numberOfJumps;
        double packetLoss;
        double numberOfCores;
        double AvailableMemory;
        double FileSize;
        double cpuIOWait;
        double numberofContainers;
        double uploadTime;

        numberOfJumps = Jumps;
        rtt = (int)(numberOfJumps * (1+ random()* 2));
        troughtput = BW ; //bps
        packetLoss = random() * numberOfJumps * 0.01;
        numberOfCores = (int)(1 + random() *1);
        AvailableMemory = ((int)(1 + random() * 1)) * 1000000; //kb
        FileSize = (int)(2000000 + random() * 2000000); //kb
        cpuIOWait = (int)(random() * 10);
        numberofContainers = (double)(int)(random() * 2);
        uploadTime = rtt *0.1 + rtt*FileSize/2 *0.01 + 10000*FileSize/troughtput + packetLoss * 1000*FileSize/troughtput;
        if (FileSize > AvailableMemory)
        {
            uploadTime = uploadTime + (FileSize-AvailableMemory)*0.001;
        }
        uploadSpeed = FileSize/uploadTime;
        Vector<Double> MeasurmentInstance = new Vector<Double>();

        MeasurmentInstance.add(uploadSpeed);
        MeasurmentInstance.add(rtt);
        MeasurmentInstance.add(troughtput);
        MeasurmentInstance.add(numberOfJumps);
        MeasurmentInstance.add(packetLoss);
        MeasurmentInstance.add(numberOfCores);
        MeasurmentInstance.add(AvailableMemory);
        MeasurmentInstance.add(FileSize);
        MeasurmentInstance.add(cpuIOWait);
        MeasurmentInstance.add(numberofContainers);
        MeasurmentInstance.add(uploadTime);

        MetricValues.add(MeasurmentInstance);

    }

    public void sort()
    {
        for (Vector<Double> MeasurmentInstance : MetricValues) {
            for (Double Value : MeasurmentInstance) {
                System.out.printf("%.2f\t", Value );
            }
            System.out.println();
        }


    }

    public void clean()
    {

     MetricValues = new Vector<Vector<Double>>();
    }
}
