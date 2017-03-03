package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import graph.Graph;
import graph.Sorter;
import graph.Total;

public class Main extends JPanel
{
    static JMenuBar obj2 = new JMenuBar();
    static JMenu obj3[] = new JMenu[2];;
    static JMenuItem obj4[] = new JMenuItem[4];
    static JTabbedPane tp = new JTabbedPane();
    static JPanel[] p;
    static Main ob;
    static String fname;
    static Graph[] grph;
    static void init() 
    {
        obj3[0] = new JMenu("        File        ");
        obj3[1] = new JMenu("        Help        ");
        obj4[0] = new JMenuItem("        Load        ");
        obj4[1] = new JMenuItem("        Help        ");
        obj4[2] = new JMenuItem("        About       ");
        obj4[3] = new JMenuItem("        Exit       ");
        obj3[0].add(obj4[0]);
        obj3[0].add(new JSeparator());
        obj3[0].add(obj4[3]);
        obj3[1].add(obj4[1]);
        obj3[1].add(new JSeparator());
        obj3[1].add(obj4[2]);
        obj2.add(obj3[0]);
        obj2.add(obj3[1]);
        obj4[0].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                fname = JOptionPane.showInputDialog("Enter file name(excluding extension): ");
                if (fname == null) 
                    return;
                if (!fname.equals("")) 
                {
                    Sorter sort = new Sorter(fname);
                    grph = sort.returnGraphs();
                    if (grph == null) 
                        return;
                    p = new JPanel[grph.length];
                    for (int a = 0; a < grph.length; a++) 
                    {
                        p[a] = new JPanel();
                        p[a].removeAll();
                        p[a].setLayout(new BorderLayout());
                        p[a].add(grph[a].panel, BorderLayout.CENTER);
                        p[a].add(new JLabel("Mean: " + grph[a].mn), BorderLayout.SOUTH);
                        p[a].validate();
                    }
                    tp.removeAll();
                    Total tot = new Total(fname);
                    JPanel totp = new JPanel();
                    totp.removeAll();
                    totp.setLayout(new BorderLayout());
                    totp.add(tot.panel, BorderLayout.CENTER);
                    totp.add(new JLabel("Mean: " + tot.mean), BorderLayout.SOUTH);
                    totp.validate();
                    tp.add("graph.Total Percentage", totp);
                    for (int a = 0; a < p.length; a++) 
                    {
                        tp.add(grph[a].nm, p[a]);
                    }
                    ob.removeAll();
                    ob.add(tp, BorderLayout.CENTER);
                    ob.validate();
                }
            }
        });
        obj4[1].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(null, "To view performance of any school, follow the steps given below: \n\n1. Copy the results file into the folder in which this application is present\n\n2. Click on File > Load and enter the name of the results file\n\nNote: Only the following subjects are supported: BENGALI, HISTORY, POLITICAL SCIENCE, \nGEOGRAPHY, ECONOMICS, PSYCHOLOGY, SOCIOLOGY, PHILOSOPHY, \nMATHEMATICS, PHYSICS, CHEMISTRY, BIOLOGY, BIOTECHNOLOGY,\n PHYSICAL EDUCATION, BUSINESS STUDIES, ACCOUNTANCY, INFORMATICS PRACTICE,\nENTREPRENEURSHIP, MULTIMEDIA & WEB TECHNOLOGY, MASS MEDIA STUDIES,\nCOMPUTER SCIENCE, ENGLISH CORE, HINDI CORE");
            }
        });
        obj4[2].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(null, "Created by Sourish Banerjee. " + "\n" + "        Achieved with java. ");
            }
        });
        obj4[3].addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        });
    }
    public Main() 
    {
        this.setLayout(new BorderLayout());
    }
    public static void main(String args[]) 
    {
        JFrame obj = new JFrame("Performance Calculator");
        ob = new Main();
        init();
        obj.setJMenuBar(obj2);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setExtendedState(JFrame.MAXIMIZED_BOTH);
        obj.add(ob);
    }
}