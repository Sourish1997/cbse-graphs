package graph;

import graph.Graph;

import java.io.*;
import javax.swing.*;
public class Sorter 
{
    String f;
    public Sorter(String fname) 
    {
        f = fname;
    }
    public Graph[] returnGraphs()
    {
        try 
        {
            BufferedReader obj = new BufferedReader(new FileReader(f + ".TXT"));
            String line, line2, line3;
            String word = "AB";
            boolean checker = true;
            while ((line = obj.readLine()) != null) 
            {
                line2 = line.replaceAll(" AB ", " 000 ");
                line2 = line2.replaceAll(" ABST", " * ");
                line2 = line2.replaceAll("[^0-9 *]", "");
                line3 = line2.trim().replaceAll(" +", " ");
                if (line3.length() > 17) 
                {
                    BufferedWriter obj2;
                    if (checker) 
                    {
                        obj2 = new BufferedWriter(new FileWriter(f + "_Edited.TXT"));
                        checker = false;
                    } 
                    else 
                    {
                        obj2 = new BufferedWriter(new FileWriter(f + "_Edited.TXT", true));
                    }
                    obj2.write(line3);
                    obj2.newLine();
                    obj2.close();
                }
            }
            obj.close();
            obj = new BufferedReader(new FileReader(f + "_Edited.TXT"));
            String[] subs = {" 105", " 027", " 028", " 029", " 030", " 037", " 039", " 040", " 041", " 042", " 043", " 044", " 045", " 048", " 054", " 055", " 065", " 066", " 067", " 072", " 083", " 301", " 302"};
            String[] names = {"BENGALI", "HISTORY", "POLITICAL SCIENCE", "GEOGRAPHY", "ECONOMICS", "PSYCHOLOGY", "SOCIOLOGY", "PHILOSOPHY", "MATHEMATICS", "PHYSICS", "CHEMISTRY", "BIOLOGY", "BIOTECHNOLOGY", "PHYSICAL EDUCATION", "BUSINESS STUDIES", "ACCOUNTANCY", "INFORMATICS PRACTICE", "ENTREPRENEURSHIP", "MULTIMEDIA & WEB TECHNOLOGY", "MASS MEDIA STUDIES", "COMPUTER SCIENCE", "ENGLISH CORE", "HINDI CORE"};
            String subv[] = new String[subs.length];
            for (int a = 0; a < subv.length; a++) 
            {
                subv[a] = "0";
            }
            int cnt = 0;
            while ((line = obj.readLine()) != null) 
            {
                String[] lar = line.split("(?!\\w)");
                int siz = lar.length;
                if (!lar[siz - 1].equals("*")) 
                {
                    for (int a = 1; a < siz - 4; a += 3) 
                    {
                        for (int b = 0; b < subs.length; b++) 
                        {
                            if (lar[a].equals(subs[b])) 
                            {
                                if (lar[a + 1].length() == 4) 
                                    subv[b] = "1";
                            }
                        }
                    }
                }
            }
            for (int a = 0; a < subv.length; a++) 
            {
                if (subv[a].equals("1"))
                     cnt++;
            }
            obj.close();
            Subject[] sub = new Subject[cnt];
            int c = 0;
            for (int a = 0; a < subv.length; a++) 
            {
                if (subv[a].equals("1")) 
                {
                    sub[c] = new Subject(names[a]);
                    c++;
                }
            }
            for (int a = 0; a < cnt; a++) 
            {
                String comp = "";
                for (int b = 0; b < names.length; b++) 
                {
                    if (sub[a].name.equals(names[b])) 
                    {
                        comp = subs[b];
                        break;
                    }
                }
                obj = new BufferedReader(new FileReader(f + "_Edited.TXT"));
                String lin;
                while ((lin = obj.readLine()) != null) 
                {
                    String[] lar = lin.split("(?!\\w)");
                    int siz = lar.length;
                    if (!lar[siz - 1].equals("*")) 
                    {
                        for (int b = 1; b < siz - 4; b += 3) 
                        {
                            if (lar[b].equals(comp)) 
                            {
                                sub[a].addMarks(Integer.parseInt(lar[b + 1].trim()));
                            }
                        }
                    }
                }
                obj.close();
                sub[a].computeData();
            }
            Graph[] gr = new Graph[cnt];
            for (int a = 0; a < cnt; a++) 
            {
                gr[a] = new Graph();
                gr[a].createGraph(sub[a]);
            }
            return gr;
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "File entered does not exist!");
            return null;
        }
    }
}