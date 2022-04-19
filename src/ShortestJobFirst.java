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
public class ShortestJobFirst {
	
	int  ShortJob=0; //from begging of array (Shortest time)
	int LargeJob=0;  //from end of array (largest time)
         float avgwaitingTime=0;
	float avgTurnaroundtime=0;
	 int overHeadTime;
        int CurrentTime=0;
        int contextSwitch;
        ArrayList<Process>Schedule=new ArrayList<Process>();
        
        public  ArrayList<Process> ShortestJobFirstSchedul(Process proc[],int numberOfProcess)
	{
            
              int complete = 0, minm = Integer.MAX_VALUE; 
        int firstpriority = -1; 
          boolean check = false;
          boolean processcompleted=false;
        // Process until all processes gets 
        // completed 
        int counter=-1;
        ArrayList<Process>procschedule=new ArrayList<>();
        while (complete != numberOfProcess) { 
       
            // Find process with minimum 
            // remaining time among the 
            // processes that arrives till the 
            // current time`
            for (int j = 0; j < numberOfProcess ; j++)  
            { 
                if ((proc[j].ArrivalTime <= CurrentTime)&&!processcompleted) {
                    if((proc[j].age > 10) && (proc[j].RemainingTime> 0 )||((proc[j].BurstTime < minm) && proc[j].RemainingTime> 0) ){
                    minm =proc[j].BurstTime; 
                    firstpriority = j;
                    check = true;
                    }
                } 
            } 
            
            
            if (check == false) { 
                CurrentTime++; 
                continue; 
            } 
            processcompleted=true;
             for (int j = 0; j < numberOfProcess; j++)  
                { 
                    //To Calculate the waiting Time of the process
                    if ((proc[firstpriority]!=proc[j])&&(proc[j].ArrivalTime<=CurrentTime)&&(proc[j].RemainingTime>0)) {
                         proc[j].waitingTime++;
                     }
                    //to solve the starvation problem by minusing the priority of the process by one
                    if ((proc[firstpriority]!=proc[j])&&(proc[j].ArrivalTime<=CurrentTime)&&(proc[j].RemainingTime>0)) {
                         proc[j].age++;
                     }
                } 
            procschedule.add(proc[firstpriority]);
            CurrentTime++;
            // Reduce remaining time by one 
            proc[firstpriority].RemainingTime--; 
            
            
            // Update minimum 
            minm = proc[firstpriority].RemainingTime; 
            if (minm == 0){
                minm = Integer.MAX_VALUE; 
               
            }
            // If a process gets completely 
            // executed 
            if (proc[firstpriority].RemainingTime== 0) { 
                // Increment complete 
                complete++; 
                check = false; 
                 processcompleted=false;

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
	
	public  void TurnAroundTime(Process proc[],int numberOfProcess)
	{
		for(int i=0;i<numberOfProcess;i++)
		{
			//Solving the context switching by adding the OverHeadTime to each process' turn around time.
			proc[i].turnAroundTime=proc[i].waitingTime+proc[i].BurstTime;
		}
	}
	
	public  float AvgWaitingTime(Process proc[],int Numberofprocess)
	{
		for(int i=0;i<Numberofprocess;i++)
		{
			avgwaitingTime =(avgwaitingTime+proc[i].waitingTime) ;
		}
		return (avgwaitingTime/Numberofprocess);
	}
	
	public float AvgTurnAroundTime(Process proc[],int Numberofprocess)
	{
		for(int i=0;i<Numberofprocess;i++)
		{
			
			avgTurnaroundtime=(avgTurnaroundtime+proc[i].turnAroundTime) ;
		}
		return (avgTurnaroundtime/Numberofprocess);
	}
}