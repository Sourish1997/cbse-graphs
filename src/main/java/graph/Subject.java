package graph;

import java.util.*;

public class Subject
{
    ArrayList < Integer > marks = new ArrayList < Integer > ();
    ArrayList < Integer > marks2 = new ArrayList < Integer > ();
    ArrayList < Integer > fr = new ArrayList < Integer > ();
    private int size;
    int size2;
    double mean;
    String name;
    Subject(String nm)
    {
        size = marks.size();
        size2 = marks2.size();
        name = nm;
    }
    private void mean()
    {
        mean = 0;
        for (int m: marks)
            mean += m;
        mean = mean / marks.size();
    }
    private void update()
    {
        size = marks.size();
        size2 = marks2.size();
    }
    void addMarks(int mark)
    {
        marks.add(mark);
        update();
    }
    void computeData()
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