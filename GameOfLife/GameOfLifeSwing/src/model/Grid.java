/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Computer
 */
public class Grid {

    private int cellSpaces;
    private int maxRows;
    private int maxCols;
    private Cell[] cells;

    public Grid() {
        cellSpaces = 1600;
        cells = new Cell[cellSpaces];
        maxRows = (int) Math.sqrt(cellSpaces);
        maxCols = (int) Math.sqrt(cellSpaces);

    }

    public void addCells(JPanel pnl) {

        GridBagConstraints gbc = new GridBagConstraints();

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {

                gbc.gridx = col;
                gbc.gridy = row;

                Cell cell = new Cell(false);
                Border border = null;

                if (row < maxRows - 1) {
                    if (col < maxRows - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < maxRows - 1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cell.setBorder(border);

                int position = 0;

                if (row < maxRows) {
                    position = row * maxRows + col;
                }

                cell.setPosition(position);
                cells[position] = cell;
                pnl.add(cell, gbc);
            }
        }
    }

    public void repopulate() {
        for (int i = 0; i < cells.length; i++) {

            Cell cell = cells[i];
            if (cell.isAlive() && !cell.isSurvivor()) {
                cell.die();

            } else if (!cell.isAlive() && cell.isSurvivor()) {
                cell.comeAlive();
            }
        }
    }

    public int getCellSpaces() {
        return cellSpaces;
    }

    public void setCellSpaces(int cellCount) {
        this.cellSpaces = cellCount;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public int getMaxRow() {
        return maxRows;
    }

    public void setMaxRow(int maxRow) {
        this.maxRows = maxRow;
    }

    public int getMaxCol() {
        return maxCols;
    }

    public void setMaxCol(int maxCol) {
        this.maxCols = maxCol;
    }

    //
    public class CellChecker {

        public CellChecker() {
        }

        public int checkNeighbors(Cell cell) {

            int aliveNeighbors = 0;

            int position = cell.getPosition();
            int cellsColIndex = position % maxRows; //hanyas indexű oszlopban van, pl. cells[123] a 3-as indexű oszlopban van (123%4=3)

            if (cellsColIndex  > 0) {
                if (cells[position - 1].isAlive()) {
                    aliveNeighbors++;
                }
            } else if (cellsColIndex == 0){
                if (cells[position+maxCols-1].isAlive()){
                    aliveNeighbors++;
                }
            }

            if (cellsColIndex < maxRows - 1) {
                if (cells[position + 1].isAlive()) {
                    aliveNeighbors++;
                }
            } else if (cellsColIndex == maxRows-1) {
                if (cells[position-maxCols+1].isAlive()){
                    aliveNeighbors++;
                }
            }

            return aliveNeighbors;
        }

        public int checkAllNeighbors(Cell cell) {

            int sumAliveNeighbors = 0;

            int position = cell.getPosition();
            int cellsRowIndex = position / maxRows; //hanyadik indexű sorban van (pl. cells[123] a 3-as indexű sorban van, 123/40=3)
            sumAliveNeighbors += checkNeighbors(cell);
            Cell above=null;
            Cell below=null;

            if (cellsRowIndex > 0) {
                above = cells[position - maxRows];
                if (above.isAlive()) {
                    sumAliveNeighbors++;
                }
                sumAliveNeighbors += checkNeighbors(above);
                
            } else if (cellsRowIndex == 0) {
                above = cells[position+(maxRows * maxCols-maxCols)];
                if(above.isAlive()){
                    sumAliveNeighbors++;
                }
                sumAliveNeighbors+=checkNeighbors(above);
            }

            if (cellsRowIndex < maxRows - 1) {
                below = cells[position + maxRows];
                if (below.isAlive()) {
                    sumAliveNeighbors++;
                }
                sumAliveNeighbors += checkNeighbors(below);
            } else if (cellsRowIndex == maxRows-1) {
                below= cells[position-(cellsRowIndex*maxRows)];
                if(below.isAlive()){
                    sumAliveNeighbors++;
                }
                sumAliveNeighbors+=checkNeighbors(below);
            }

            return sumAliveNeighbors;
        }

        public void checkSurvivors(Cell[] cells) {

            for (int i = 0; i < cells.length; i++) {

                Cell cell = cells[i];
                int neighbors = checkAllNeighbors(cell);

                if (neighbors == 2 || neighbors == 3) {
                    
                    if (cell.isAlive()) {
                        cell.setSurvivor(true);
                        System.out.println("cell[" + cell.getPosition() + "] has 2 or 3 neighbors, will survive");
                    } else if (!cell.isAlive() && neighbors == 3) {
                        cell.setSurvivor(true);
                        System.out.println("cell[" + cell.getPosition() + "] has exactly 3 neighbors, will resurrect");
                    }

                } else if (cell.isAlive() && neighbors >= 4) {
                    cell.setSurvivor(false);
                    System.out.println("cell[" + cell.getPosition() + "] will die of overpopulation");

                } else if (cell.isAlive() && neighbors < 2) {
                    cell.setSurvivor(false);
                    System.out.println("cell[" + cell.getPosition() + "] will die of loneliness");

                }
            }
        }
    }
}
