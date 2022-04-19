import java.util.ArrayList;

public class Process {
	String processName;
	int BurstTime; 
	int priority;
	int ArrivalTime;
	int waitingTime=0;
	int turnAroundTime;	
        int RemainingTime;
        int QuantamTime;
        int AGATFactor=0;
        int comptime;
        int age=0;
	public Process(String processName,int priority,int BurstTime,int ArrivalTime,int QuantamTime)
	{
		this.BurstTime=BurstTime;
		this.priority=priority;
		this.processName=processName;
		this.ArrivalTime=ArrivalTime;
                this.RemainingTime=BurstTime;
                this.waitingTime=0;
                this.QuantamTime=QuantamTime;
	}
	public Process()
	{
		BurstTime=0;
		ArrivalTime=0;
		priority=0;
		processName=null;	
		waitingTime=0;
		turnAroundTime=0;
        }
        
	public static Process[] ArrivalTimeArrange(Process process[],int numberOfProcess)
	{
		Process temp;
		for(int i=0;i<numberOfProcess-1;i++)
		{
			for(int j=0;j<numberOfProcess-1;j++)
			{
				if ((process[j].ArrivalTime > process[j + 1].ArrivalTime))
                {
                        temp = process[j];                      
                        process[j] = process[j + 1];
                        process[j + 1] = temp;
                }
			}
		}
		return process;
	}
	
	

}