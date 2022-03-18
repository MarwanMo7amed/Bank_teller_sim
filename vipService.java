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
public class vipService {
    int service_time;
    double service_time_prob;
    double cum_prob;
    int rangeStart;
    int rangeEnd;
    public ArrayList<vipService> VipServ_init= new ArrayList<>();

    public vipService() {
    }
    public vipService(int service_time, double service_time_prob) {
        this.service_time = service_time;
        this.service_time_prob = service_time_prob;
    }
    
    public void initiate_vip_serv()
    {
        VipServ_init.add(new vipService(1,0.10));
        VipServ_init.add(new vipService(2,0.30));
        VipServ_init.add(new vipService(3,0.38));
        VipServ_init.add(new vipService(4,0.22));
    }
    
    public void cumProb_and_range()
    {
        double cum=0;
        //cumulative_prob
        for (int i = 0; i < VipServ_init.size(); i++)
        {
            cum+=VipServ_init.get(i).service_time_prob;
            VipServ_init.get(i).cum_prob=cum;
        }
        double r=0;
        VipServ_init.get(0).rangeStart=0;
        VipServ_init.get(0).rangeEnd=(int) (VipServ_init.get(0).cum_prob*100);
        for (int i = 1; i < VipServ_init.size(); i++) 
        {
            VipServ_init.get(i).rangeStart=VipServ_init.get(i-1).rangeEnd+1;
            VipServ_init.get(i).rangeEnd=(int) (VipServ_init.get(i).cum_prob*100);
        }
    }
}
