package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import graph.Graph;
import graph.Sorter;
import graph.Total;

public class Main extends JPanel
{
    private static JMenuBar menuBar;
    private static JMenu menus[];
    private static JMenuItem menuItems[];
    private static JTabbedPane tabbedPane;
    private static JPanel[] panels;
    private static Main mainPanel;
    private static String fname;
    private static Graph[] grph;
    private static void init()
    {
        menuBar = new JMenuBar();
        menus = new JMenu[2];
        menuItems = new JMenuItem[4];
        tabbedPane = new JTabbedPane();
        menus[0] = new JMenu("        File        ");
        menus[1] = new JMenu("        Help        ");
        menuItems[0] = new JMenuItem("        Load        ");
        menuItems[1] = new JMenuItem("        Help        ");
        menuItems[2] = new JMenuItem("        About       ");
        menuItems[3] = new JMenuItem("        Exit       ");
        menus[0].add(menuItems[0]);
        menus[0].add(new JSeparator());
        menus[0].add(menuItems[3]);
        menus[1].add(menuItems[1]);
        menus[1].add(new JSeparator());
        menus[1].add(menuItems[2]);
        menuBar.add(menus[0]);
        menuBar.add(menus[1]);
        menuItems[0].addActionListener(new ActionListener()
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
                    panels = new JPanel[grph.length];
                    for (int a = 0; a < grph.length; a++) 
                    {
                        panels[a] = new JPanel();
                        panels[a].removeAll();
                        panels[a].setLayout(new BorderLayout());
                        panels[a].add(grph[a].panel, BorderLayout.CENTER);
                        panels[a].add(new JLabel("Mean: " + grph[a].mn), BorderLayout.SOUTH);
                        panels[a].validate();
                    }
                    tabbedPane.removeAll();
                    Total tot = new Total(fname);
                    JPanel totp = new JPanel();
                    totp.removeAll();
                    totp.setLayout(new BorderLayout());
                    totp.add(tot.panel, BorderLayout.CENTER);
                    totp.add(new JLabel("Mean: " + tot.mean), BorderLayout.SOUTH);
                    totp.validate();
                    tabbedPane.add("Total Percentage", totp);
                    for (int a = 0; a < panels.length; a++)
                    {
                        tabbedPane.add(grph[a].nm, panels[a]);
                    }
                    mainPanel.removeAll();
                    mainPanel.add(tabbedPane, BorderLayout.CENTER);
                    mainPanel.validate();
                }
            }
        });
        menuItems[1].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(null, "To view performance of any school, follow the steps given below: \n\n1. Copy the results file into the folder in which this application is present\n\n2. Click on File > Load and enter the name of the results file\n\nNote: Only the following subjects are supported: BENGALI, HISTORY, POLITICAL SCIENCE, \nGEOGRAPHY, ECONOMICS, PSYCHOLOGY, SOCIOLOGY, PHILOSOPHY, \nMATHEMATICS, PHYSICS, CHEMISTRY, BIOLOGY, BIOTECHNOLOGY,\n PHYSICAL EDUCATION, BUSINESS STUDIES, ACCOUNTANCY, INFORMATICS PRACTICE,\nENTREPRENEURSHIP, MULTIMEDIA & WEB TECHNOLOGY, MASS MEDIA STUDIES,\nCOMPUTER SCIENCE, ENGLISH CORE, HINDI CORE");
            }
        });
        menuItems[2].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(null, "Created by Sourish Banerjee. " + "\n" + "        Achieved with java. ");
            }
        });
        menuItems[3].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        });
    }

    private Main()
    {
        this.setLayout(new BorderLayout());
    }

    public static void main(String args[]) 
    {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}

        JFrame mainFrame = new JFrame("Performance Calculator");
        mainPanel = new Main();
        init();
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);
        mainFrame.setSize(700, 600);
        mainFrame.setVisible(true);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.add(mainPanel);
    }
}