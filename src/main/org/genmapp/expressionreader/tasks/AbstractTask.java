package org.genmapp.expressionreader.tasks;

import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;

/**
 *
 * @author djiao
 */
public abstract class AbstractTask implements Task {

    protected TaskMonitor taskMonitor = null;

    public void halt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTaskMonitor(TaskMonitor tm) throws IllegalThreadStateException {
        if (this.taskMonitor != null) {
            throw new IllegalStateException("Task Monitor is already set.");
        }
        this.taskMonitor = tm;
    }
}
