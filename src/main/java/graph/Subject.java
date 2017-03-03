package graph;

import java.util.*;
public class Subject 
{
    ArrayList < Integer > marks = new ArrayList < Integer > ();
    ArrayList < Integer > marks2 = new ArrayList < Integer > ();
    ArrayList < Integer > fr = new ArrayList < Integer > ();
    int size, size2;
    double mean;
    String name;
    public Subject(String nm) 
    {
        size = marks.size();
        size2 = marks2.size();
        name = nm;
    }
    public void mean() 
    {
        mean = 0;
        for (int m: marks)
            mean += m;
        mean = mean / marks.size();
    }
    public void update() 
    {
        size = marks.size();
        size2 = marks2.size();
    }
    public void addMarks(int mark) 
    {
        marks.add(mark);
        update();
    }
    public void computeData() 
    {
        for (int a = 0; a <= 100; a++) 
        {
            int sum = 0;
            for (int b = 0; b < size; b++) 
            {
                if (marks.get(b) == a) 
                {
                    sum++;
                }
            }
            if (sum != 0) 
            {
                marks2.add(a);
                fr.add(sum);
                update();
            }
        }
        mean();
    }
}