import java.util.Scanner;
import java.util.*;
import java.io.*;
class Task
{
    String title;
    boolean isCompleted;
}
class TodoApp {
    public static void main(String[] args) {
        ArrayList<Task> tasks=new ArrayList<>();
        Scanner s=new Scanner(System.in);
        try 
        {
            File file = new File("tasks.txt");
            if(file.exists()) 
            {
                Scanner fileScanner = new Scanner(file);
                while(fileScanner.hasNextLine())
               {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                Task t = new Task();
                t.title = parts[0];
                t.isCompleted = Boolean.parseBoolean(parts[1]);
                tasks.add(t);
               }
               fileScanner.close();
            }
        }
        catch(Exception e) 
        {
         System.out.println("Error loading tasks");
        }
        while(true)
        {
            System.out.println("1.Add task");
            System.out.println("2.View task");
            System.out.println("3.Delete task");
            System.out.println("4.Mark task");
            System.out.println("5.Exit");
            System.out.print("enter your choice:");
            int ch=s.nextInt();
            s.nextLine();
            switch(ch)
            {
                case 1:
                    Task t=new Task();
                    System.out.print("enter a task:");
                    String title=s.nextLine();
                    t.title=title;
                    t.isCompleted=false;
                    tasks.add(t);
                    break;
                case 2:
                    if(tasks.size()==0)
                    {
                        System.out.println("----No tasks are available----");
                    }
                    else
                    {
                        System.out.println("Tasks are.....");
                        for(int i=0;i<tasks.size();i++)
                        {
                            Task temp = tasks.get(i);
                            String status = temp.isCompleted ? "[Completed]" : "[Pending]";
                            System.out.println((i+1) + ". " + temp.title + " " + status);
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    if(tasks.size()==0)
                    {
                        System.out.println("----No tasks are available----");
                    }
                    else
                    {
                        System.out.print("Enter the task number to delete:");
                        int index=s.nextInt();
                        s.nextLine();
                        if(index<1 || index>tasks.size())
                        {
                           System.out.println("---Invalid index---");
                        }
                        else
                        {
                            tasks.remove(index-1);
                            System.out.println("----Task deleted successfully----");
                        }
                    }
                    break;
                case 4:
                     if(tasks.size()==0)
                    {
                        System.out.println("No tasks are available");
                    }
                    else
                    {
                     System.out.print("Enter the task number to mark as completed:");
                        int index=s.nextInt();
                        s.nextLine();
                        if(index<1 || index>tasks.size())
                        {
                           System.out.println("---Invalid index---");
                        }
                        else
                        {
                            tasks.get(index-1).isCompleted=true;
                            System.out.println("----Task marked as completed----");
                        }
                    }
                        break;
                case 5:
                    System.out.println("-----Exiting----");
                    try
                    {
                         FileWriter fw = new FileWriter("tasks.txt");
                        for(Task tk: tasks) 
                        {
                             fw.write(tk.title + "," + tk.isCompleted + "\n");
                        }

                       fw.close();
                       System.out.println("Tasks saved successfully");
                      } 
                      catch(IOException e) {
                      System.out.println("Error saving file");
                    }
                    return;
                default:
                    System.out.println("-----Invalid choice-----");
            }
        }
    }
}
    