
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
public class ShortestRemainTimeFirst {
     float avgwaitingTime=0;
     float avgTurnaroundtime=0;
     int overHeadTime;
     int CurrentTime=0;
     int contextSwitch;
     ArrayList<Process>Schedule=new ArrayList<Process>();
     public ArrayList<Process>Arrange(Process proc[],int numberOfProcess)
     {
         
			
	
        int complete = 0, minm = Integer.MAX_VALUE; 
        int shortest = -1; 
          boolean check = false; 
        // Process until all processes gets 
        // completed 
        int counter=-1;
        ArrayList<Process>procschedule=new ArrayList<Process>();
        while (complete != numberOfProcess) { 
       
            // Find process with minimum 
            // remaining time among the 
            // processes that arrives till the 
            // current time`
            for (int j = 0; j < numberOfProcess; j++)  
            { 
                if ((proc[j].ArrivalTime <= CurrentTime)) {
                    if(((proc[j].age >= 10) ||(proc[j].RemainingTime < minm))&&( proc[j].RemainingTime > 0)){
                    minm =proc[j].RemainingTime; 
                    shortest = j;
                    check = true;
                    }
                } 
            } 
          
             
            if (check == false) { 
                CurrentTime++; 
                continue; 
            } 
            for (int j = 0; j < numberOfProcess; j++)  
                { 
                    if ((proc[shortest]!=proc[j])&&(proc[j].ArrivalTime<=CurrentTime)&&(proc[j].RemainingTime>0)) {
                         proc[j].waitingTime++;
                         proc[j].age++;
                     }
                }
            procschedule.add(proc[shortest]);
            CurrentTime++;
            // Reduce remaining time by one 
            proc[shortest].RemainingTime--; 
            proc[shortest].age-=4;
           
            
            // Update minimum 
            minm = proc[shortest].RemainingTime; 
            if (minm == 0){
                minm = Integer.MAX_VALUE; 
            }
            // If a process gets completely 
            // executed 
            if (proc[shortest].RemainingTime== 0) { 
       
                // Increment complete 
                complete++; 
                check = false; 

            }  
	}
        
           ContextSwitch(procschedule);
            TurnAroundTime(proc, numberOfProcess);
            return procschedule;
     }
       public void ContextSwitch(ArrayList<Process>process)
     {
            process.get(0).waitingTime+=contextSwitch;
         for(int i=1;i<process.size();i++)
         {  
             if(process.get(i-1)!=process.get(i))
             {
                 process.get(i).waitingTime+=contextSwitch;
             }
             CurrentTime++;
         }
     }
     public void TurnAroundTime(Process proc[],int NumberOfProcess)
{
	for(int i=0;i<NumberOfProcess;i++)
	{
		//Solving the context switching by adding the OverHeadTime to each process' turn around time.
		proc[i].turnAroundTime=proc[i].waitingTime+proc[i].BurstTime;
	}
}
     
     
     public  float AvgWaitingTime(Process proc[],int NumberOfProcess)
	{
		for(int i=0;i<NumberOfProcess;i++)
		{
			avgwaitingTime =(avgwaitingTime+proc[i].waitingTime) ;
		}
		return avgwaitingTime/NumberOfProcess;
	}
	
	//Function to find the avg turn around time
	
	public float AvgTurnAroundTime(Process proc[],int NumberOfProcess)
	{
		for(int i=0;i<NumberOfProcess;i++)
		{
			
			avgTurnaroundtime=(avgTurnaroundtime+proc[i].turnAroundTime) ;
                      
		}
		return (avgTurnaroundtime/NumberOfProcess);
	}

    public void setContextSwitch(int contextSwitch) {
        this.contextSwitch = contextSwitch;
    }

  
        

    
}
