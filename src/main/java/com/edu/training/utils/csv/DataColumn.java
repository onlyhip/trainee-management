package com.edu.training.utils.csv;

import java.io.Serializable;

/**
 * Get Data from column in CSV file.
 * @author NguyenNK5
 */
public class DataColumn implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name = "";
    private int columnIndex;

    public DataColumn() {}

    public DataColumn(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Clone new DataColumn
     * @return cloned DataColumn.
     */
    public DataColumn makeClone() {
        DataColumn clone = new DataColumn(name);
        clone.setColumnIndex(columnIndex);
        return clone;
    }
}
