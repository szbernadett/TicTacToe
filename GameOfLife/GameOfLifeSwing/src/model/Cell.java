/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 *
 * @author Computer
 */
public class Cell extends JPanel implements Serializable {

    private boolean alive;
    private boolean survivor;
    private int position;

    public Cell() {
    }

    public Cell(boolean isAlive) {
        this.alive = isAlive;

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                comeAlive();
            }
        });

    }

    public void die() {
        setBackground(null);
        repaint();
        setAlive(false);
    }

    public void comeAlive() {
        setBackground(Color.BLACK);
        repaint();
        setAlive(true);
    }

    public void cellClicked() {

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSurvivor() {
        return survivor;
    }

    public void setSurvivor(boolean survivor) {
        this.survivor = survivor;
    }

}
