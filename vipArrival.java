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
public class vipArrival {
    int inter_arrival;
    double inter_arrival_prob;
    double cum_prob;
    int rangeStart;
    int rangeEnd;
    
    public ArrayList<vipArrival> VipArr_init= new ArrayList<>();

    public vipArrival() {
    }
    public vipArrival(int inter_arrival, double inter_arrival_prob) {
        this.inter_arrival = inter_arrival;
        this.inter_arrival_prob = inter_arrival_prob;
    }
    
    public void initiate_vip_arr()
    {
        VipArr_init.add(new vipArrival(1,0.1));
        VipArr_init.add(new vipArrival(2,0.2));
        VipArr_init.add(new vipArrival(3,0.3));
        VipArr_init.add(new vipArrival(4,0.4));
    }
    public void cumProb_and_range()
    {
        double cum=0;
        //cumulative_prob
        for (int i = 0; i < VipArr_init.size(); i++)
        {
            cum+=VipArr_init.get(i).inter_arrival_prob;
            VipArr_init.get(i).cum_prob=cum;
        }
        double r=0;
        VipArr_init.get(0).rangeStart=1;
        VipArr_init.get(0).rangeEnd=(int) (VipArr_init.get(0).cum_prob*100);
        for (int i = 1; i < VipArr_init.size(); i++) 
        {
            VipArr_init.get(i).rangeStart=VipArr_init.get(i-1).rangeEnd+1;
            VipArr_init.get(i).rangeEnd=(int) (VipArr_init.get(i).cum_prob*100);
        }
    }
}
