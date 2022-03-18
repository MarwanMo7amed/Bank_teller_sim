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
public class simulation_table {
    int customer;
    int interArrival;
    int arrivalTime;
    int ServiceTime;
    int TimeServiceBegin;
    int WaitingTime;
    int TimeServiceEnd;
    int TimeSpentInSystem;
    int IDLE_Time;
    String CustomerType;
    double interArrivalProbability;
    double serviceProb;
    public ArrayList<simulation_table> Sim_Table= new ArrayList<>();
    public simulation_table() {
    }

    public simulation_table(int customer, int interArrival, int arrivalTime, int ServiceTime, int TimeServiceBegin, int WaitingTime, int TimeServiceEnd, int TimeSpentInSystem, int IDLE_Time, String CustomerType,double interArrivalProbability,double serviceProb) {
        this.customer = customer;
        this.interArrival = interArrival;
        this.arrivalTime = arrivalTime;
        this.ServiceTime = ServiceTime;
        this.TimeServiceBegin = TimeServiceBegin;
        this.WaitingTime = WaitingTime;
        this.TimeServiceEnd = TimeServiceEnd;
        this.TimeSpentInSystem = TimeSpentInSystem;
        this.IDLE_Time = IDLE_Time;
        this.CustomerType = CustomerType;
        this.interArrivalProbability=interArrivalProbability;
        this.serviceProb=serviceProb;
    }
    public void sim_Table(vip_cust vc,Ordinary_Cust oc, int customerNum)
    {
        int ordCount=0,vipCount=0;
        //initial customers type according to inter arrival time
        if(oc.ordCust_interArr.get(0).interArrival>vc.vipCust_interArr.get(0).interArrival || oc.ordCust_interArr.get(0).interArrival==vc.vipCust_interArr.get(0).interArrival)
        {
            Sim_Table.add(new simulation_table(1,0,0,vc.vipCust_Servicer.get(0).serviceTime,0,0,vc.vipCust_Servicer.get(0).serviceTime,vc.vipCust_Servicer.get(0).serviceTime,0,"distinguished",vc.vipCust_interArr.get(0).interArrProb,vc.vipCust_Servicer.get(0).ServProb));
            vipCount++;
        }
        else if(oc.ordCust_interArr.get(0).interArrival<vc.vipCust_interArr.get(0).interArrival)
        {
            ordCount++;
            Sim_Table.add(new simulation_table(1,0,0,oc.ordCust_Servicer.get(0).serviceTime,0,0,oc.ordCust_Servicer.get(0).serviceTime,oc.ordCust_Servicer.get(0).serviceTime,0,"Ordinary",oc.ordCust_interArr.get(0).interArrProb,oc.ordCust_Servicer.get(0).ServProb));
        }
        
        int count=2;
        //for loop to add all customers to simulation table
        loop1:for (int i = 1; i < customerNum*3; i++) 
        {
            //initializing values with zero
            int wait=0;
            int OrdCurrentArrival=0, VIPcurrentArrival=0;
            //getting the prevoius service end
            int pervServEnd=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd;
            
            //calculating the current arrival if the customer is ordinary
            if(ordCount<customerNum)
            {
                for (simulation_table s:Sim_Table) 
                {
                    if(s.CustomerType.equals("Ordinary"))
                    {
                        OrdCurrentArrival=s.arrivalTime;
                    }
                }
                OrdCurrentArrival+= oc.ordCust_interArr.get(ordCount).interArrival;
            }
            
            //calculating the current arrival if the customer is distinguished
            if(vipCount<customerNum)
            {
                for (simulation_table s:Sim_Table) 
                {
                    if(s.CustomerType.equals("distinguished"))
                    {
                        VIPcurrentArrival=s.arrivalTime;
                    }
                }
                VIPcurrentArrival+= vc.vipCust_interArr.get(vipCount).interArrival;
            }
            /*this condition check if the current arrival of vip customer is greater than or 
            equal to previous service end if the condition is true this mean if the ordinary is avaliable 
            at this time he can enter if the condition is false the vip will enter*/
            boolean f=false;
            if(Sim_Table.get(Sim_Table.size()-1).CustomerType.equals("distinguished"))
            {
                if(pervServEnd<=VIPcurrentArrival)
                {
                    f=true;
                }
                else{
                    if(vipCount<customerNum)
                    {
                        int ServBegin=0;
                        System.out.println("vip entered");
                        System.out.println("vip count:"+vipCount);
                        int idle=pervServEnd-VIPcurrentArrival;
                        if(idle<0)
                            idle=0;
                        if(VIPcurrentArrival>=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                        {
                            ServBegin=VIPcurrentArrival;
                        }
                        else if(VIPcurrentArrival<Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                        {
                            ServBegin=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd;
                        }
                        wait=ServBegin-VIPcurrentArrival;
                        if(wait<0)
                            wait=0;
                        Sim_Table.add(new simulation_table(count, vc.vipCust_interArr.get(vipCount).interArrival, VIPcurrentArrival, vc.vipCust_Servicer.get(vipCount).serviceTime, ServBegin, wait,ServBegin+vc.vipCust_Servicer.get(vipCount).serviceTime , wait+vc.vipCust_Servicer.get(vipCount).serviceTime,idle,"distinguished",vc.vipCust_interArr.get(vipCount).interArrProb,vc.vipCust_Servicer.get(vipCount).ServProb));
                        vipCount++;
                        count++;
                    }
                }
            }
            if(ordCount<customerNum)
            {
                /*Ordinary customer can enter if the current arrival of the ordinary is smaller than vip and if 
                the condition mentioned above is also true*/
                if(OrdCurrentArrival<VIPcurrentArrival &&ordCount<customerNum && f==true)
                {
                    int ServBegin=0;
                    System.out.println("ordinary entered");
                    System.out.println("Ord count:"+ordCount);
                    int idle=pervServEnd-OrdCurrentArrival;
                    if(idle<0)
                        idle=0;
                    if(OrdCurrentArrival>=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                    {
                        ServBegin=OrdCurrentArrival;
                    }
                    else if(OrdCurrentArrival<Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                    {
                        ServBegin=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd;
                    }
                    wait=ServBegin-OrdCurrentArrival;
                    if(wait<0)
                        wait=0;
                    Sim_Table.add(new simulation_table(count, oc.ordCust_interArr.get(ordCount).interArrival ,OrdCurrentArrival , oc.ordCust_Servicer.get(ordCount).serviceTime ,ServBegin , wait , ServBegin+oc.ordCust_Servicer.get(ordCount).serviceTime , wait+oc.ordCust_Servicer.get(ordCount).serviceTime , idle , "Ordinary",oc.ordCust_interArr.get(ordCount).interArrProb,oc.ordCust_Servicer.get(ordCount).ServProb));
                    ordCount++;
                    count++;
                }
            }
            if(vipCount<customerNum)
            {
                /*vip will enter in case vip current arrival is smaller than or equal ordinary currenta arrival
                or if the vip current arrival is greater than or equal previos service end*/
                if(OrdCurrentArrival>VIPcurrentArrival || pervServEnd<=VIPcurrentArrival || OrdCurrentArrival==VIPcurrentArrival && vipCount<customerNum)
                {
                    int ServBegin=0;
                    System.out.println("vip entered");
                    System.out.println("vip count:"+vipCount);
                    int idle=pervServEnd-VIPcurrentArrival;
                    if(idle<0)
                        idle=0;
                    if(VIPcurrentArrival>=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                    {
                        ServBegin=VIPcurrentArrival;
                    }
                    else if(VIPcurrentArrival<Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                    {
                        ServBegin=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd;
                    }
                    wait=ServBegin-VIPcurrentArrival;
                    if(wait<0)
                        wait=0;
                    Sim_Table.add(new simulation_table(count, vc.vipCust_interArr.get(vipCount).interArrival, VIPcurrentArrival, vc.vipCust_Servicer.get(vipCount).serviceTime, ServBegin, wait,ServBegin+vc.vipCust_Servicer.get(vipCount).serviceTime , wait+vc.vipCust_Servicer.get(vipCount).serviceTime,idle,"distinguished",vc.vipCust_interArr.get(vipCount).interArrProb,vc.vipCust_Servicer.get(vipCount).ServProb));
                    vipCount++;
                    count++;
                }
                //milestone to check that all entered
            if(ordCount==customerNum && vipCount==customerNum)
            {
                System.out.println("FINISHED !");
            }
        }
        }
        //filling the rest of the ordinary customers after all the distinguished entered
        while(ordCount<customerNum && vipCount==customerNum)
        {
            int wait=0;
            int OrdCurrentArrival=0;
            int ServBegin=0;
            for (simulation_table s:Sim_Table) 
            {
                if(s.CustomerType.equals("Ordinary"))
                {
                    OrdCurrentArrival=s.arrivalTime;
                }
            }
            OrdCurrentArrival+= oc.ordCust_interArr.get(ordCount).interArrival;
            for (int i = ordCount; i < customerNum; i++)
            {
                int pervServEnd=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd;
                System.out.println("ordinary entered");
                System.out.println("Ord c:"+ordCount);
                int idle=OrdCurrentArrival-pervServEnd;
                if(idle<0)
                    idle=0;
                if(OrdCurrentArrival>Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                {
                    ServBegin=OrdCurrentArrival;
                }
                else if(OrdCurrentArrival<Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd)
                {
                    ServBegin=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd;
                }
                wait=ServBegin-OrdCurrentArrival;
                if(wait<0)
                    wait=0;
                if(ordCount<customerNum )
                {
                    Sim_Table.add(new simulation_table(count, oc.ordCust_interArr.get(ordCount).interArrival ,OrdCurrentArrival , oc.ordCust_Servicer.get(ordCount).serviceTime ,ServBegin , wait , ServBegin+oc.ordCust_Servicer.get(ordCount).serviceTime , wait+oc.ordCust_Servicer.get(ordCount).serviceTime , idle , "Ordinary",oc.ordCust_interArr.get(ordCount).interArrProb,oc.ordCust_Servicer.get(ordCount).ServProb));
                    ordCount++;
                    count++;
                    break;
                }
            }
        }
//        if(ordCount==customerNum && vipCount<customerNum)
//        {
//            int wait=0;
//            int VIPcurrentArrival=0;
//            for (simulation_table s:Sim_Table) 
//            {
//                if(s.CustomerType.equals("distinguished"))
//                {
//                    VIPcurrentArrival=s.arrivalTime;
//                }
//            }
//            VIPcurrentArrival+= vc.vipCust_interArr.get(vipCount).interArrival;
//            for (int i = vipCount; i < customerNum; i++) 
//            {
//                int pervServEnd=Sim_Table.get(Sim_Table.size()-1).TimeServiceEnd;
//                int ServBegin=0;
//                System.out.println("vip entered");
//                System.out.println("vip c:"+vipCount);
//                int idle=VIPcurrentArrival-pervServEnd;
//                if(idle<0)
//                    idle=0;
//                if(VIPcurrentArrival>=pervServEnd)
//                {
//                    ServBegin=VIPcurrentArrival;
//                }
//                else if(VIPcurrentArrival<pervServEnd)
//                {
//                    ServBegin=pervServEnd;
//                }
//                wait=ServBegin-VIPcurrentArrival;
//                if(wait<0)
//                    wait=0;
//                Sim_Table.add(new simulation_table(count, vc.vipCust_interArr.get(vipCount).interArrival, VIPcurrentArrival, vc.vipCust_Servicer.get(vipCount).serviceTime, ServBegin, wait,ServBegin+vc.vipCust_Servicer.get(vipCount).serviceTime , wait+vc.vipCust_Servicer.get(vipCount).serviceTime,idle,"distinguished"));
//                vipCount++;
//                count++;
//            }
//        }
        for (int i = 1; i < Sim_Table.size(); i++) {
            Sim_Table.get(i).IDLE_Time=Sim_Table.get(i).TimeServiceBegin-Sim_Table.get(i-1).TimeServiceEnd;
        }
        System.out.println("Simiulation Table:");
        for (int i = 0; i < Sim_Table.size(); i++) {
            System.out.println(Sim_Table.get(i));
        }
        System.out.println("Ord count:"+ordCount);
        System.out.println("vip count:"+vipCount);
    }
    @Override
    public String toString() {
        return "{" + "customer=" + customer +", CustomerType=" + CustomerType+ ", interArrival=" + interArrival + ", arrivalTime=" + arrivalTime + ", ServiceTime=" + ServiceTime + ", TimeServiceBegin=" + TimeServiceBegin + ", WaitingTime=" + WaitingTime + ", TimeServiceEnd=" + TimeServiceEnd + ", TimeSpentInSystem=" + TimeSpentInSystem + ", IDLE_Time=" + IDLE_Time +  '}';
    }
    
}
