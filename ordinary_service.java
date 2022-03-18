/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem1;

import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class ordinary_service  {
    int service_time;
    double service_time_prob;
    double cum_prob;
    int rangeStart;
    int rangeEnd;
    public ArrayList<ordinary_service> ordServ_init= new ArrayList<>();

    public ordinary_service() {
    }

    public ordinary_service(int service_time, double service_time_prob) {
        this.service_time = service_time;
        this.service_time_prob = service_time_prob;
    }
    
    public void initiate_ordinary_serv()
    {
        ordServ_init.add(new ordinary_service(1,0.20));
        ordServ_init.add(new ordinary_service(2,0.40));
        ordServ_init.add(new ordinary_service(3,0.28));
        ordServ_init.add(new ordinary_service(4,0.12));
    }
    public void cumProb_and_range()
    {
        double cum=0;
        //cumulative_prob
        for (int i = 0; i < ordServ_init.size(); i++)
        {
            cum+=ordServ_init.get(i).service_time_prob;
            ordServ_init.get(i).cum_prob=cum;
        }
        double r=0;
        ordServ_init.get(0).rangeStart=0;
        ordServ_init.get(0).rangeEnd=(int) (ordServ_init.get(0).cum_prob*100);
        for (int i = 1; i < ordServ_init.size(); i++) 
        {
            ordServ_init.get(i).rangeStart=ordServ_init.get(i-1).rangeEnd+1;
            ordServ_init.get(i).rangeEnd=(int) (ordServ_init.get(i).cum_prob*100);
        }
//        for (int i = 0; i < ordServ_init.size(); i++) 
//        {
//            System.out.println(ordServ_init.get(i).rangeStart+"\t"+ordServ_init.get(i).rangeEnd);
//        }
    }
}
