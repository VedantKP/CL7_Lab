/*
Name: Vedant Puranik
Roll No: 43152
Class: BE9
Batch: R9
Assignment 11: On the given data perform the performance measurements using Simple Naïve Bayes algorithm
 such as Accuracy, Error rate, precision, Recall, TPR,FPR,TNR,FPR etc. (Using Weka API through JAVA)
 */

package pkg43176_naivebayes;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class Main {

    public static final String TRAINING_DATA_SET_FILENAME = "C:\\Users\\vedan\\Documents\\College\\Final_Year\\ICS\\Assignment_11\\train.csv";
    public static final String TESTING_DATA_SET_FILENAME = "C:\\Users\\vedan\\Documents\\College\\Final_Year\\ICS\\Assignment_11\\test.csv";
    
    public static void csvToArff(String fileName,int flag) throws Exception 
    {
        // load CSV
        CSVLoader csv_loader = new CSVLoader();
        System.out.println(fileName);
        csv_loader.setSource(new File(fileName));
        Instances data = csv_loader.getDataSet();

        // save ARFF
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        if(flag == 0)
            saver.setFile(new File("C:\\Users\\vedan\\Documents\\College\\Final_Year\\ICS\\Assignment_11\\train.arff"));
        else
            saver.setFile(new File("C:\\Users\\vedan\\Documents\\College\\Final_Year\\ICS\\Assignment_11\\test.arff"));
        saver.writeBatch();       
    }
    
    public static Instances getDataSet(String fileName) throws Exception 
    {    
        System.out.println(fileName);
            
        StringToWordVector filter = new StringToWordVector();
        int classIdx = 8;
            
        ArffLoader loader = new ArffLoader();
        InputStream file = new FileInputStream(fileName);
        loader.setSource(file);
            
        Instances dataSet = loader.getDataSet();
                    
        dataSet.setClassIndex(classIdx);
        filter.setInputFormat(dataSet);
        dataSet = Filter.useFilter(dataSet, filter);
        return dataSet;
    }
    
    
    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args)throws Exception {    
        
    csvToArff(TRAINING_DATA_SET_FILENAME,0);
    csvToArff(TESTING_DATA_SET_FILENAME,1);
        
    Instances trainingDataSet = getDataSet("C:\\Users\\vedan\\Documents\\College\\Final_Year\\ICS\\Assignment_11\\train.arff");
	Instances testingDataSet = getDataSet("C:\\Users\\vedan\\Documents\\College\\Final_Year\\ICS\\Assignment_11\\test.arff");
		
	NaiveBayes classifier = new NaiveBayes();
	classifier.buildClassifier(trainingDataSet);
	Evaluation eval = new Evaluation(trainingDataSet);
	eval.evaluateModel(classifier, testingDataSet);
	
    System.out.println("** Naive Bayes Evaluation with Datasets **");
	System.out.println(eval.toSummaryString());
	System.out.print(" the expression for the input data as per alogorithm is ");
	System.out.println(classifier);
        
    }
    
}
