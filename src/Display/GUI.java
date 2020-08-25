package Display;

import LSClasses.Item;
import Main.LifeSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class GUI extends JFrame
{
    private JPanel panel;
    private LifeSimulation ls;
    private final int COLS;
    private final int ROWS;
    private final int IMAGE_SIZE = 50;

    public GUI(int rows, int cols, LifeSimulation ls)
    {
        ROWS = rows;
        COLS = cols;
        this.ls = ls;
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel()
    {
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ArrayList<Item> list = ls.getField();
                for (Item item : list) {
                    g.drawImage((Image) item.box.image, item.coord.y * IMAGE_SIZE,
                            item.coord.x * IMAGE_SIZE,  this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) // левая кнока мыши - делаем шаг или начинаем игру, если не заполняли поле
                    if (ls.isStopped()) {
                        ls.start();
                    }else{
                        ls.update();
                    }
                if (e.getButton() == MouseEvent.BUTTON3) // правая кнока мыши - создаем новое поле
                    ls.start();
                panel.repaint(); // после каждого действия мыши перерисовываем панель
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    ls.setStopped(false);
                    panel.repaint();
                }
            }
        });
        panel.setPreferredSize(new Dimension(ROWS * IMAGE_SIZE, COLS * IMAGE_SIZE ));
        add(panel);
    }

    private void initFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("cow"));
        pack();
    }

    private Image getImage(String name)
    {
        String filename = "res/img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(filename);
        return icon.getImage();
    }

    private void setImages()
    {
        for (Box box: Box.values()) {
            box.image = getImage(box.name());
        }
    }
}