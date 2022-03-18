/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem1;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author apple
 */
public class Ordinary_Cust 
{
    int cust;
    int rand;
    int interArrival;
    int serviceTime;
    double interArrProb;
    double ServProb;
    ArrayList<Ordinary_Cust> ordCust_interArr= new ArrayList<>();
    ArrayList<Ordinary_Cust> ordCust_Servicer= new ArrayList<>();
    public Ordinary_Cust() {
    }

    public Ordinary_Cust(int cust, int rand) {
        this.cust = cust;
        this.rand = rand;
    }
    
    public void ord_rand_interArr_service(ordinary_arrival oa,int customerNum)
    {
        /*Assingnig random values to customers to get inter-arrival time*/
        /*--------------------------------------------------------------*/

        Random RandNumber_Gen=new Random();
        //first loop to assign customer number and random number to each customer
        for (int i = 0; i < customerNum; i++) 
        {
            ordCust_interArr.add(new Ordinary_Cust(i+1,RandNumber_Gen.nextInt(100)));
        }
        //second loop checking the assigned random number within wich range to get the inter-arrival value
        for (int j = 0; j < customerNum; j++) 
        {
            for (int k = 0; k < oa.ordArr_init.size(); k++) {
                if(oa.ordArr_init.get(k).rangeEnd>=ordCust_interArr.get(j).rand && ordCust_interArr.get(j).rand>=oa.ordArr_init.get(k).rangeStart)
                {
                    ordCust_interArr.get(j).interArrival=oa.ordArr_init.get(k).inter_arrival;
                    ordCust_interArr.get(j).interArrProb=oa.ordArr_init.get(k).inter_arrival_prob;
                }
            }
        }
//        System.out.println("Ordinary Customers Inter-arrival times:");
//        for (int i = 0; i < customerNum; i++) 
//        {
//            System.out.println(ordCust_interArr.get(i).cust+"\t"+ordCust_interArr.get(i).rand+"\t"+ordCust_interArr.get(i).interArrival);
//            
//        }
//        System.out.println("\n");
        /*--------------------------------------------------------------*/
        /*--------------------------------------------------------------*/
        
        
        /*Assingnig random values to customers to get service time*/
        /*--------------------------------------------------------------*/
        ordinary_service os=new ordinary_service();
        os.initiate_ordinary_serv();
        os.cumProb_and_range();
        Random RandNumber_Gen2=new Random();
        //first loop to assign customer number and random number to each customer
        for (int i = 0; i < customerNum; i++) 
        {
            ordCust_Servicer.add(new Ordinary_Cust(i+1,RandNumber_Gen2.nextInt(100)));
//            System.out.println(ordCust_Servicer.get(i).rand);
        }
        //second loop checking the assigned random number within wich range to get the inter-arrival value
        for (int j = 0; j < customerNum; j++) 
        {
            for (int k = 0; k < os.ordServ_init.size(); k++) {
                if(os.ordServ_init.get(k).rangeEnd>=ordCust_Servicer.get(j).rand && ordCust_Servicer.get(j).rand>=os.ordServ_init.get(k).rangeStart)
                {
                    ordCust_Servicer.get(j).serviceTime=os.ordServ_init.get(k).service_time;
                    ordCust_Servicer.get(j).ServProb=os.ordServ_init.get(k).service_time_prob;
                }
            }
            
        }
//        System.out.println("Ordinary Customers Service times:");
//        for (int i = 0; i < customerNum; i++) 
//        {
//            System.out.println(ordCust_Servicer.get(i).cust+"\t"+ordCust_Servicer.get(i).rand+"\t"+ordCust_Servicer.get(i).serviceTime);
//        }
//        System.out.println("\n");
        /*--------------------------------------------------------------*/
        /*--------------------------------------------------------------*/
    }
}
