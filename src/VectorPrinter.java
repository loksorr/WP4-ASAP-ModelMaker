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
import java.util.Vector;

/**
 * Created by mcigale on 12. 01. 2017.
 */
public class VectorPrinter {

    public void printValues(Vector<Vector<Double>> MetricValues)
    {

        System.out.println();
        for (Vector<Double> MeasurementInstance : MetricValues) {
            for (Double Value : MeasurementInstance) {
                System.out.printf(" %.2f \t", Value );
            }
            System.out.println();
        }

    }

    public void printDoubleValues(Vector<Double> MeasurementInstance)
    {

          for (Double Value : MeasurementInstance) {

              System.out.printf(" %,2f \t", Value );
            }
            System.out.println();

    }
    
    public void printStringValues(Vector<String> MeasurementInstance)
    {

        for (String Value : MeasurementInstance) {
            System.out.printf(Value + " \t");
        }
        System.out.println();

    }

    public void printIntValues(Vector<Integer> MeasurementInstance)
    {

        for (Integer Value : MeasurementInstance) {
            System.out.printf(Value + " \t");
        }
        System.out.println();

    }

    public void storeVectorValues(Vector<Vector<Double>> MetricValues, String Filename)
    {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Filename, true), "utf-8"))) {
            for (Vector<Double> MeasurmentInstance : MetricValues) {
                for (Double Value : MeasurmentInstance) {
                    String s = String.format("%.2f\t", Value);
                    writer.write(s);
                }
                writer.newLine();
            }
        }
        catch (Exception e)
        {}
    }

    public void storeDoubleValues(Vector<Double> MeasurementInstance, String Filename)
    {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Filename, true), "utf-8"))) {
                for (Double Value : MeasurementInstance) {
                    String s = String.format("%.2f\t", Value);
                    writer.write(s);
                }
                writer.newLine();
        }
        catch (Exception e)
        {}
    }
    public void storeStringValues(Vector<String> MeasurementInstance, String Filename)
    {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Filename, true), "utf-8"))) {
            for (String Value : MeasurementInstance) {
                String s = String.format(Value + "\t");
                writer.write(s);
            }
            writer.newLine();
        }
        catch (Exception e)
        {}
    }


}
