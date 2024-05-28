/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.SwingWorker;
import model.Grid;

/**
 *
 * @author Computer
 */
public class CellWorker extends SwingWorker<Void, Integer> {

    private Grid grid;

    public CellWorker(Grid grid) {
        this.grid = grid;
    }

    @Override
    protected Void doInBackground() throws Exception {
        
        while(true){
        Grid.CellChecker cc = grid.new CellChecker();
        cc.checkSurvivors(grid.getCells());
        grid.repopulate();
        Thread.sleep(100);
        }
        
    }

}
