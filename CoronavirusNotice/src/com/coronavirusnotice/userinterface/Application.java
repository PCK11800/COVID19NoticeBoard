package com.coronavirusnotice.userinterface;

import resources.font.Inconsolata;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Application extends JFrame {

    static final int APP_WIDTH = 720;
    static final int APP_HEIGHT = 600;
    static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    JTextArea textArea;

    public Application()
    {
        initUI();
        setVisible(true);
    }

    private void initUI()
    {
        setTitle("COVID Notice Board");
        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(
                (int) (SCREEN_WIDTH / 2) - (APP_WIDTH / 2),
                (int) (SCREEN_HEIGHT / 2) - (APP_HEIGHT / 2)
        );
        setResizable(true);

        initTextArea();

        pack();
        repaint();
    }

    private void initTextArea()
    {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Inconsolata().getFont(20));
        textArea.setForeground(new Color(195, 181, 171));
        textArea.setBackground(new Color(32, 32, 32));

        textPanel.setLocation(0, 0);
        textPanel.setVisible(true);
        textPanel.setMinimumSize(textArea.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVisible(true);
        scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));

        MessageConsole mc = new MessageConsole(textArea);
        mc.redirectOut();
        
        add(textPanel);
        textPanel.add(scrollPane);
        repaint();
    }

}
