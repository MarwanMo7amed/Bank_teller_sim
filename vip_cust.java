/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author apple
 */
public class vip_cust 
{
    int cust;
    int rand;
    int interArrival;
    int serviceTime;
    double interArrProb;
    double ServProb;
    ArrayList<vip_cust> vipCust_interArr= new ArrayList<>();
    ArrayList<vip_cust> vipCust_Servicer= new ArrayList<>();

    public vip_cust() {
    }

    public vip_cust(int cust, int rand) {
        this.cust = cust;
        this.rand = rand;
    }
    
    public void vip_rand_interArr_service(vipArrival va,int customerNum)
    {
        /*Assingnig random values to customers to get inter-arrival time*/
        /*--------------------------------------------------------------*/
        Random RandNumber_Gen=new Random();
        //first loop to assign customer number and random number to each customer
        for (int i = 0; i < customerNum; i++) 
        {
            vipCust_interArr.add(new vip_cust(i+1,RandNumber_Gen.nextInt(100)));
        }
        //second loop checking the assigned random number within wich range to get the inter-arrival value
        for (int j = 0; j < customerNum; j++) 
        {
            for (int k = 0; k < va.VipArr_init.size(); k++) {
                if(va.VipArr_init.get(k).rangeEnd>=vipCust_interArr.get(j).rand && vipCust_interArr.get(j).rand>=va.VipArr_init.get(k).rangeStart)
                {
                    vipCust_interArr.get(j).interArrival=va.VipArr_init.get(k).inter_arrival;
                    vipCust_interArr.get(j).interArrProb=va.VipArr_init.get(k).inter_arrival_prob;
                    
                }
            }
        }

        /*Assingnig random values to customers to get service time*/
        /*--------------------------------------------------------------*/
        vipService vs=new vipService();
        vs.initiate_vip_serv();
        vs.cumProb_and_range();
        Random RandNumber_Gen2=new Random();
        //first loop to assign customer number and random number to each customer
        for (int i = 0; i < customerNum; i++) 
        {
            vipCust_Servicer.add(new vip_cust(i+1,RandNumber_Gen2.nextInt(100)));
//            System.out.println(ordCust_Servicer.get(i).rand);
        }
        //second loop checking the assigned random number within wich range to get the inter-arrival value
        for (int j = 0; j < customerNum; j++) 
        {
            for (int k = 0; k < vs.VipServ_init.size(); k++) {
                if(vs.VipServ_init.get(k).rangeEnd>=vipCust_Servicer.get(j).rand && vipCust_Servicer.get(j).rand>=vs.VipServ_init.get(k).rangeStart)
                {
                    vipCust_Servicer.get(j).serviceTime=vs.VipServ_init.get(k).service_time;
                    vipCust_Servicer.get(j).ServProb=vs.VipServ_init.get(k).service_time_prob;
                }
            }
            
        }
//        System.out.println("VIP Customers service times:");
//        for (int i = 0; i < customerNum; i++) 
//        {
//            System.out.println(vipCust_Servicer.get(i).cust+"\t"+vipCust_Servicer.get(i).rand+"\t"+vipCust_Servicer.get(i).serviceTime);
//        }
        /*--------------------------------------------------------------*/
        /*--------------------------------------------------------------*/
    }
}
