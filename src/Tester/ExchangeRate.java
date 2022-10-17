/**
  * @class name COP 3530
  * @author Tao Lin
  * @version (07/25/2021)
  * The ExchangeRate class has three parts. The first part is to input file "exchange rate" 
  * and store the exchange rates into 2D array. The second part is to find the most profitable 
  * exchange rate sequence from a source currency to all other currencies that can be modeled
  * to a SSSP. The third part is to use the Bellman-Ford algorithm to implement and solve the 
  * SSSP given a source in a graph with both positive and negative weights.
  */

package Tester;
import java.util.Scanner;            
import java.io.File;
import java.io.IOException;

public class ExchangeRate
{   
    //Main method
    public static void main(String[] args) throws IOException
    {       
        //Step 0: Read the input file "exchange rates.csv" and save the exchange rate into 2D array 
        Scanner inputFile = new Scanner(new File(System.getProperty("user.dir") + "/input/exchange rates.csv"));
        double[][] rate = new double[54][54];
        inputFile.nextLine();//skip the header
        String[] currency = new String[54];
        for (int row = 0; row < 54; row++)
        {
            Scanner lineScanner = new Scanner(inputFile.nextLine());
            lineScanner.useDelimiter(",");//use "," to split each element
            //use a currency array to store the first element of each row
            currency[row] = lineScanner.next();//currencies of different countries 
            for (int col = 0; col < 54; col++)
            {
                rate[row][col] = lineScanner.nextDouble();//store exchange rates into 2D array  
            }
            lineScanner.close();
        }
        inputFile.close();
        
        //Step 1: Initialize distance from src to all other vertices as INFINITE. While the 
        //src is the index of source currency, and dist is an array of doubles.
        int src = Integer.MAX_VALUE;//assume src index is infinite
        Scanner in = new Scanner(System.in);
        System.out.print("Please input a source currency: ");
        String currencySrc = in.nextLine();
        for (int i = 0; i < 54; i++)
        {   
            if (currency[i].equalsIgnoreCase(currencySrc))
            {   
                src = i;
                System.out.println("Source currency is " + currency[i]);
                break;
            }
        }
        if (src >= 54)//Not found
        { 
            System.out.println("Input error!");
        }
        
        double dist[] = new double[54];
        for (int i = 0; i < 54; ++i)
            dist[i] = Double.MAX_VALUE;//initializes source from other vertices distance as infinity
        dist[src] = 0;//initializes the source vertex distance as 0
        
        //Step 2: Relax all edges |V| - 1 times. A simple shortest path from src to any other vertex can 
        //have at-most |V| - 1 edges. The data in the input .csv file is loaded into 2D array rates[][]
        for (int i = 1; i < 54; ++i)//Relax all edges |V| - 1 = 53 times
        {
            for (int j = 0; j < 54; ++j)
            {
                for(int k = 0; k < 54;k++)
                {
                    double weight = -Math.log(rate[j][k]);
                    if (dist[j] != Double.MAX_VALUE && dist[j] + weight < dist[k])//relaxation of edges
                          dist[k] = dist[j] + weight;
                }
            }
        }
        
        //Step3: Find the most-profitable exchange rate and use Math.exp(-dist[i]) where i is the index of target currency
        double[] maxExChangeRate = new double[54];
        for (int i = 0; i < 54; ++i)
        {
            maxExChangeRate[i] = Math.exp(-1 * dist[i]);
        }
        
        //The directRate array is used to store the direct rate from the source currency to the target currency
        double[] directRate = new double[54];
        for(int j = 0; j < 54; ++j)
        {
            directRate[j] = rate[src][j];
        }
        
        //Print out result  
        for (int k = 0; k < 54; ++k)
        {   
            System.out.println(currency[k] + ": max Exchange Rate is " + maxExChangeRate[k] + ", and direct rate is " + directRate[k]);
        }
        
    }
    
}
