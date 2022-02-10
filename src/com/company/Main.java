package com.company;
import java.util.Scanner;
public class Main {
    public static void roundRobin(String p[], int a[], int b[], int n)
    {
        int res = 0;
        int rest = 0;
        int res_b[] = new int[b.length];
        int res_a[] = new int[a.length];
        for (int i = 0; i < res_b.length; i++) {
            res_b[i] = b[i];
            res_a[i] = a[i];
        }
        int t = 0;
        int w[] = new int[p.length];
        int comp[] = new int[p.length];
        int tat[] = new int[p.length];
        while (true) {
            boolean flag = true;
            for (int i = 0; i < p.length; i++) {
                if (res_a[i] <= t) {
                    if (res_a[i] <= n) {
                        if (res_b[i] > 0) {
                            flag = false;
                            if (res_b[i] > n) {
                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                            }
                            else {
                                t = t + res_b[i];
                                comp[i] = t;
                                tat[i] = t - a[i];
                                w[i] = t - b[i] - a[i];
                                res_b[i] = 0;
                            }
                        }
                    }
                    else if (res_a[i] > n) {
                        for (int j = 0; j < p.length; j++) {
                            if (res_a[j] < res_a[i]) {
                                if (res_b[j] > 0) {
                                    flag = false;
                                    if (res_b[j] > n) {
                                        t = t + n;
                                        res_b[j] = res_b[j] - n;
                                        res_a[j] = res_a[j] + n;
                                    }
                                    else {
                                        t = t + res_b[j];
                                        comp[j] = t;
                                        tat[j] = t - a[j];
                                        w[j] = t - b[j] - a[j];
                                        res_b[j] = 0;
                                    }
                                }
                            }
                        }
                        if (res_b[i] > 0) {
                            flag = false;
                            if (res_b[i] > n) {
                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                            }
                            else {
                                t = t + res_b[i];
                                comp[i] = t;
                                tat[i] = t - a[i];
                                w[i] = t - b[i] - a[i];
                                res_b[i] = 0;
                            }
                        }
                    }
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println("\nProcess_Id    Arrival_Time    Burst_Time    Completion_Time    Turnaround_Time    Waiting_Time");
        for (int i = 0; i < p.length; i++) {
            System.out.println(" \t\t" + p[i] + " \t\t\t" + a[i] + " \t\t\t" + b[i] + " \t\t\t\t" + comp[i] + " \t\t\t\t\t" + tat[i] + " \t\t\t\t\t\t"+ w[i]);

            res = res + w[i];
            rest = rest + tat[i];
        }
        System.out.println("Average waiting time is "
                + (float)res / p.length);
        System.out.println("Average Turnaround  time is "
                + (float)rest / p.length);
    }

    public static void main(String[] args) {
        // write your code here
        while (true) {
            System.out.println("\n***  Welcome to CPU Scheduling Calculator  ***");
            System.out.println("Choose your calculating method : \n 1) First Come First Serve (FCFS).\n 2) Shortest Job First (Non-Preemptive).\n 3) Shortest Job First (Preemptive).\n 4) Round Robin.\n 5) Exit.\n");
            System.out.println("Enter your option : ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("*** You are using FCFS CPU Scheduling Calculator ***");
                    System.out.println("Enter no of process: ");
                    int nop = scanner.nextInt();
                    int p_id[] = new int[nop];
                    int arrivalTimes[] = new int[nop];
                    int burstTimes[] = new int[nop];
                    int completionTimes[] = new int[nop];
                    int turnaroundTimes[] = new int[nop];
                    int waitingTimes[] = new int[nop];
                    int temp;
                    float avg_w_t=0,avg_ta=0;

                    for(int i = 0; i < nop; i++)
                    {
                        System.out.println("enter process " + (i+1) + " arrival time: ");
                        arrivalTimes[i] = scanner.nextInt();
                        System.out.println("enter process " + (i+1) + " brust time: ");
                        burstTimes[i] = scanner.nextInt();
                        p_id[i] = i+1;
                    }

                    for(int i = 0 ; i <nop; i++)
                    {
                        for(int  j=0;  j < nop-(i+1) ; j++)
                        {
                            if( arrivalTimes[j] > arrivalTimes[j+1] )
                            {
                                temp = arrivalTimes[j];
                                arrivalTimes[j] = arrivalTimes[j+1];
                                arrivalTimes[j+1] = temp;
                                temp = burstTimes[j];
                                burstTimes[j] = burstTimes[j+1];
                                burstTimes[j+1] = temp;
                                temp = p_id[j];
                                p_id[j] = p_id[j+1];
                                p_id[j+1] = temp;
                            }
                        }
                    }

                    for(int  i = 0 ; i < nop; i++)
                    {
                        if( i == 0)
                        {
                            completionTimes[i] = arrivalTimes[i] + burstTimes[i];
                        }
                        else
                        {
                            if( arrivalTimes[i] > completionTimes[i-1])
                            {
                                completionTimes[i] = arrivalTimes[i] + burstTimes[i];
                            }
                            else
                                completionTimes[i] = completionTimes[i-1] + burstTimes[i];
                        }
                        turnaroundTimes[i] = completionTimes[i] - arrivalTimes[i] ;
                        waitingTimes[i] = turnaroundTimes[i] - burstTimes[i] ;
                        avg_w_t += waitingTimes[i] ;
                        avg_ta += turnaroundTimes[i] ;
                    }
                    System.out.println("\nProcess_Id    Arrival_Time    Burst_Time    Completion_Time    Turnaround_Time    Waiting_Time");
                    for(int  i = 0 ; i< nop;  i++)
                    {
                        System.out.println("\t" + p_id[i] + "  \t\t\t " + arrivalTimes[i] + "\t\t\t\t" + burstTimes[i] + "\t\t\t\t" + completionTimes[i] + "\t\t\t\t\t" + turnaroundTimes[i] + "\t\t\t\t\t"  + waitingTimes[i] ) ;
                    }
                    System.out.println("\naverage waiting time: "+ (avg_w_t/nop));
                    System.out.println("average turnaround time:"+(avg_ta/nop));
                    break;
                case 2:
                    System.out.println("*** You are using SJF (Non-Preemptive) CPU Scheduling Calculator ***");
                    System.out.println("Enter No of Process : ");
                    int n = scanner.nextInt();
                    int process_Id[] = new int[n];
                    int arrival_times[] = new int[n];
                    int burst_times[] = new int[n];
                    int complete_times[] = new int[n];
                    int turnaround_times[] = new int[n];
                    int waiting_times[] = new int[n];
                    int flag[] = new int[n];
                    int st = 0, tot = 0;
                    float avg_waiting_time = 0, avg_turnaround_time = 0;

                    for (int i=0;i<n;i++){
                        System.out.println("Enter process " + (i+1) + " arrival time : ");
                        arrival_times[i] = scanner.nextInt();
                        System.out.println("Enter process " + (i+1) + " burst time : ");
                        burst_times[i] = scanner.nextInt();
                        process_Id[i] = i+1;
                        flag[i] = 0;
                    }

                    boolean a = true;
                    while (true){
                        int c = n, min = 999;
                        if (tot == n)
                            break;
                        for (int i=0;i<n;i++){
                            if ((arrival_times[i] <= st) && (flag[i] == 0) && burst_times[i] < min){
                                min = burst_times[i];
                                c = i;
                            }
                        }
                        if (c==n){
                            st++;
                        }
                        else {
                            complete_times[c] = st + burst_times[c];
                            st+=burst_times[c];
                            turnaround_times[c] = complete_times[c] - arrival_times[c];
                            waiting_times[c] = turnaround_times[c] - burst_times[c];
                            flag[c] = 1;
                            tot++;
                        }
                    }

                    System.out.println("\nProcess_Id    Arrival_Time    Burst_Time    Completion_Time    Turnaround_Time    Waiting_Time");
                    for (int i=0;i<n;i++){
                        avg_waiting_time+= waiting_times[i];
                        avg_turnaround_time+= turnaround_times[i];
                        System.out.println(" \t\t" + process_Id[i] + " \t\t\t" + arrival_times[i] + " \t\t\t" + burst_times[i] + " \t\t\t\t" + complete_times[i] + " \t\t\t\t\t" + turnaround_times[i] + " \t\t\t\t\t\t"+ waiting_times[i]);
                    }
                    System.out.println("\nAverage Turnaround Time = " + (float)(avg_turnaround_time/n));
                    System.out.println("\nAverage Waiting Time = " + (float)(avg_waiting_time/n));
                    break;
                case 3:
                    System.out.println("*** You are using SJF (Preemptive) CPU Scheduling Calculator ***");
                    System.out.println("Enter No of Process : ");
                    int m = scanner.nextInt();
                    int process_id[] = new int[m];
                    int arrival_time[] = new int[m];
                    int burst_time[] = new int[m];
                    int complete_time[] = new int[m];
                    int turnaround_time[] =new int[m];
                    int waiting_time[] = new int[m];
                    int flagg[] = new int[m];
                    int k[] = new int[m];
                    int s_t = 0, tots = 0;
                    float avg_waiting_times = 0, avg_turnaround_times = 0;

                    for (int i=0;i<m;i++){
                        process_id[i] = i + 1;
                        System.out.println("Enter Process " + (i+1) + " arrival time : ");
                        arrival_time[i] = scanner.nextInt();
                        System.out.println("Enter Process " + (i+1) + " burst time : ");
                        burst_time[i] = scanner.nextInt();
                        k[i] = burst_time[i];
                        flagg[i] = 0;
                    }

                    while (true){
                        int min = 99, c = m;
                        if (tots == m)
                            break;
                        for (int i=0;i<m;i++){
                            if ((arrival_time[i] <= s_t) && (flagg[i] == 0) && (burst_time[i] < min)){
                                min = burst_time[i];
                                c = i;
                            }
                        }
                        if (c==m){
                            s_t++;
                        }
                        else {
                            burst_time[c]--;
                            s_t++;
                            if (burst_time[c] == 0){
                                complete_time[c] = s_t;
                                flagg[c] = 1;
                                tots++;
                            }
                        }
                    }

                    for (int i=0;i<m;i++){
                        turnaround_time[i] = complete_time[i] - arrival_time[i];
                        waiting_time[i] = turnaround_time[i] - k[i];
                        avg_waiting_times+= waiting_time[i];
                        avg_turnaround_times+= turnaround_time[i];
                    }

                    System.out.println("\nProcess_Id    Arrival_Time    Burst_Time    Completion_Time    Turnaround_Time    Waiting_Time");
                    for (int i=0;i<m;i++){
                        System.out.println(" \t\t" + process_id[i] + " \t\t\t" + arrival_time[i] + " \t\t\t" + k[i] + " \t\t\t\t" + complete_time[i] + " \t\t\t\t\t" + turnaround_time[i] + " \t\t\t\t\t\t"+ waiting_time[i]);
                    }

                    System.out.println("\nAverage Turnaround Time = " + (float)(avg_turnaround_times/m));
                    System.out.println("\nAverage Waiting Time = " + (float)(avg_waiting_times/m));
                    break;
                case 4:
                    System.out.println("*** You are using Round Robin CPU Scheduling Calculator ***");
                    System.out.println("Enter No of Process : ");
                    int in = scanner.nextInt();
                    String name[] = new String[in];
                    int arrivaltime[] = new int[in];
                    int bursttime[] = new int[in];
                    System.out.println("Enter Process Name : ");
                    for (int i = 0;i<in;i++){
                        name[i] = scanner.next();
                    }
                    System.out.println("Enter Arrival Time of Process : ");
                    for (int i = 0;i<in;i++){
                        arrivaltime[i] = scanner.nextInt();
                    }
                    System.out.println("Enter Burst Time of Process : ");
                    for (int i = 0;i<in;i++){
                        bursttime[i] = scanner.nextInt();
                    }
                    System.out.println("Enter Time Quantum : ");
                    int q = scanner.nextInt();
                    roundRobin(name, arrivaltime, bursttime, q);
                    break;
                case 5:
                    System.out.println("**** Thanks for using ****");
                    System.exit(0);
                    break;
            }
        }
    }
}