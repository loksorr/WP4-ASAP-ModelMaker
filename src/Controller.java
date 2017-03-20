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



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.xml.crypto.Data;
import java.util.Vector;

public class Controller {

    private DataGenerator dataGenerator = new DataGenerator();
    private VectorPrinter vectorPrinter = new VectorPrinter();
    private ModelMaker modelMaker = new ModelMaker();



    @FXML
    private TextArea textBox;

    @FXML
    void storeData(ActionEvent event) {


        vectorPrinter.storeStringValues(dataGenerator.MetricNames,"data.txt");
        vectorPrinter.storeVectorValues(modelMaker.MetricValues,"data.txt");

        vectorPrinter.storeStringValues(dataGenerator.MetricNames,"differential.txt");
        vectorPrinter.storeVectorValues(modelMaker.DiferentialTable, "differential.txt");

        vectorPrinter.storeStringValues(dataGenerator.MetricNames, "result.txt");
        vectorPrinter.storeDoubleValues(modelMaker.RegressionVector, "result.txt");

    }

        @FXML
    void generateData(ActionEvent event)
    {
        dataGenerator.MetricValues = new Vector<>();
        for (int i = 0; i < 10; i++) {
            dataGenerator.generateNewMetricVector(20, 10000);
            dataGenerator.generateNewMetricVector(15, 10000);
            dataGenerator.generateNewMetricVector(5, 300000);
            dataGenerator.generateNewMetricVector(15, 300000);
            dataGenerator.generateNewMetricVector(5, 20000);
            dataGenerator.generateNewMetricVector(20, 20000);
            dataGenerator.generateNewMetricVector(15, 20000);
            dataGenerator.generateNewMetricVector(22, 10000);
            dataGenerator.generateNewMetricVector(16, 10000);
            dataGenerator.generateNewMetricVector(4, 300000);
            dataGenerator.generateNewMetricVector(25, 300000);
            dataGenerator.generateNewMetricVector(53, 20000);
            dataGenerator.generateNewMetricVector(45, 20000);
            dataGenerator.generateNewMetricVector(40, 20000);

        }
        String s = new String();
        for (int i = 0; i < dataGenerator.MetricValues.size(); i++) {
           s +=  dataGenerator.MetricValues.elementAt(i).toString();
           s += "\n";

        }

        textBox.setText(s);
        vectorPrinter.printStringValues(dataGenerator.MetricNames);
        vectorPrinter.printValues(dataGenerator.MetricValues);
    }

    @FXML
    void calculateModel(ActionEvent event)
    {
        modelMaker.MetricValues = dataGenerator.MetricValues;
        modelMaker.MetricNames = dataGenerator.MetricNames;

        modelMaker.calculateDiferential();


       // vectorPrinter.printStringValues(dataGenerator.MetricNames);
       // vectorPrinter.printValues(modelMaker.DiferentialTable);

        modelMaker.calculateRegresion();

        //vectorPrinter.printStringValues(dataGenerator.MetricNames);
        //vectorPrinter.printDoubleValues(modelMaker.RegressionVector);
        String modelString = modelMaker.ModelMetricNames.toString() + "\n" + modelMaker.RegressionVector.toString();
        modelString = modelString.replace(',','\t');
        modelString = modelString.replace('[',' ');
        modelString = modelString.replace(']',' ');
        modelString = modelString.replace('.',',');
        textBox.setText(modelString);
        //vectorPrinter.storeVectorValues(modelMaker.DiferentialTable,"diff.txt");

    }


}