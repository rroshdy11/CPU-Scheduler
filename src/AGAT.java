
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */



public class AGAT {
    
    
   	int v1;
	int v2;
	int maxBurst;
	float avgwaitingTime=0;
        float avgTurnaroundtime=0;
        int overHeadTime;
        int CurrentTime=0;
        int contextSwitch;
        int Factor;
        int completed=0;
     
        
        

         public int V2_Calculate( ArrayList<Process> process)   {
             maxBurst=0;
             for (int i = 0; i <process.size(); i++)
             {
                 if(process.get(i).RemainingTime > maxBurst){

                     maxBurst=process.get(i).RemainingTime;
                 }

             }
             if(maxBurst>10)
          {
              maxBurst=(int) Math.ceil(maxBurst/10);
            
              v2=maxBurst;
              return v2;
          }
          else
          {
              v2=1;
              return 1;
          }

    
        }
         public int V1_Calculate(ArrayList<Process> Process)   {
            int maximum=0;
             
             for (int i = 0; i <Process.size(); i++)  
             { 
               if(Process.get(i).ArrivalTime>maximum)
               {
                   maximum=Process.get(i).ArrivalTime;
               }
             }
             if(maximum>10)
             {
                 maximum= (int) Math.ceil(maximum/10);
                 v1=maximum;
                 return v1;
             }
             else{
                 v1=1;
                 return 1;
             }
         }
     public void Factor(ArrayList<Process> process )
     {
         
         for (int i=0;i<process.size();i++){
            process.get(i).AGATFactor =(10-process.get(i).priority)+(process.get(i).ArrivalTime/v1) +(process.get(i).RemainingTime/v2);
         }
     }
     
   public ArrayList<Process> ArrangeAGAT(ArrayList<Process>process)
   {
        ArrayList<Process>Q;
        Q=process;
        
        ArrayList<Process>proschedule=new ArrayList<>();
        int minimAGAT=Integer.MAX_VALUE;
        int best=-1;
        boolean check= false;
        boolean premetive=true;
        int complete=0;
       V1_Calculate(process);
        while(complete<Q.size())
        {
            V2_Calculate(Q);
            Factor(Q);
           
            for(int i=0;i<Q.size();i++)
            {
                if(Q.get(i).AGATFactor<minimAGAT && Q.get(i).ArrivalTime<=CurrentTime && premetive && Q.get(i).RemainingTime>0)
                {
                    minimAGAT=Q.get(i).AGATFactor;
                    best=i;
                    check=true; 
                    
                    
                }
            }
            if(check==false)
            {
                CurrentTime++;
                continue;
            }
            
            CurrentTime++;
            proschedule.add(Q.get(best));
            premetive=false;
            Q.get(best).QuantamTime--;
            Q.get(best).RemainingTime--;
              for (int j = 0; j <Q.size(); j++)  
                { 
                    //To Calculate the waiting Time of the process
                    if (Q.get(best)!=Q.get(j)&&(Q.get(j).ArrivalTime<=CurrentTime)&&(Q.get(j).RemainingTime>0)) {
                         Q.get(j).waitingTime++;
                     }
                }
             
            if(Q.get(best).RemainingTime==0)
            {
                minimAGAT=Integer.MAX_VALUE;
                complete++;
                check=false;
            }
            if(Q.get(best).QuantamTime==0&&Q.get(best).RemainingTime>0)
            {
                
                Q.get(best).QuantamTime=proschedule.get(proschedule.size()-1).QuantamTime+2;
            }
            if(Q.get(best).QuantamTime==Math.ceil(proschedule.get(proschedule.size()-1).QuantamTime*0.4))
            {
                premetive=true;
            }
        }
        TurnAroundTime(Q, Q.size());
        return proschedule;
   }
       public void TurnAroundTime(ArrayList<Process>proc,int NumberOfProcess)
{
	for(int i=0;i<NumberOfProcess;i++)
	{
		//Solving the context switching by adding the OverHeadTime to each process' turn around time.
		proc.get(i).turnAroundTime=proc.get(i).waitingTime+proc.get(i).BurstTime;
	}
}
     
     
     public  float AvgWaitingTime(ArrayList<Process>proc,int NumberOfProcess)
	{
		for(int i=0;i<NumberOfProcess;i++)
		{
			avgwaitingTime =(avgwaitingTime+proc.get(i).waitingTime) ;
		}
		return avgwaitingTime/NumberOfProcess;
	}
	
	//Function to find the avg turn around time
	
	public float AvgTurnAroundTime(ArrayList<Process>proc,int NumberOfProcess)
	{
		for(int i=0;i<NumberOfProcess;i++)
		{
			
			avgTurnaroundtime=(avgTurnaroundtime+proc.get(i).turnAroundTime) ;
                      
		}
		return (avgTurnaroundtime/NumberOfProcess);
	}
     	
    
    

    
}
