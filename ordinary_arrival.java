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
public class ordinary_arrival  {
    int inter_arrival;
    double inter_arrival_prob;
    double cum_prob;
    int rangeStart;
    int rangeEnd;
    
    public ArrayList<ordinary_arrival> ordArr_init= new ArrayList<>();

    public ordinary_arrival() {
    }

    public ordinary_arrival(int inter_arrival, double inter_arrival_prob) {
        this.inter_arrival = inter_arrival;
        this.inter_arrival_prob = inter_arrival_prob;
    }
    
    public void initiate_ordinary_arr()
    {
        ordArr_init.add(new ordinary_arrival(0,0.09));
        ordArr_init.add(new ordinary_arrival(1,0.17));
        ordArr_init.add(new ordinary_arrival(2,0.27));
        ordArr_init.add(new ordinary_arrival(3,0.20));
        ordArr_init.add(new ordinary_arrival(4,0.15));
        ordArr_init.add(new ordinary_arrival(5,0.12));
    }
    public void cumProb_and_range()
    {
        double cum=0;
        //cumulative_prob
        for (int i = 0; i < ordArr_init.size(); i++)
        {
            cum+=ordArr_init.get(i).inter_arrival_prob;
            ordArr_init.get(i).cum_prob=cum;
        }
        double r=0;
        ordArr_init.get(0).rangeStart=1;
        ordArr_init.get(0).rangeEnd=(int) (ordArr_init.get(0).cum_prob*100);
        for (int i = 1; i < ordArr_init.size(); i++) 
        {
            ordArr_init.get(i).rangeStart=ordArr_init.get(i-1).rangeEnd+1;
            ordArr_init.get(i).rangeEnd=(int) (ordArr_init.get(i).cum_prob*100);
        }
        
    }
}
