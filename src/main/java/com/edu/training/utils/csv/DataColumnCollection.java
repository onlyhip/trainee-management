package com.edu.training.utils.csv;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * Data column collection.
 * @author NguyenNK5
 */
public class DataColumnCollection implements Serializable, Iterable<DataColumn> {

    private static final long serialVersionUID = 1L;

    private List<DataColumn> columns = new ArrayList<>();
    private Map<String, Integer> columnNames = new HashMap<>();

    public DataColumnCollection() {}

    public DataColumnCollection(List<DataColumn> columns) {
        for (int i = 0; i < columns.size(); i++) {
            add(columns.get(i).makeClone());
        } 
    }

    public DataColumn add(String columnName) {
        return add(new DataColumn(columnName));
    }

    public DataColumn add(DataColumn column) {
        int columnIndex = columns.size();
        columns.add(column);
        columnNames.put(column.getName(), columnIndex);
        column.setColumnIndex(columnIndex);
        return column;
    }

    public int columnCount() {
        return columns.size();
    }

    public List<DataColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataColumn> columns) {
        this.columns = columns;
    }

    public Map<String, Integer> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(Map<String, Integer> columnNames) {
        this.columnNames = columnNames;
    }

    public DataColumnCollection makeClone() {
        return new DataColumnCollection(columns);
    }

    @Override
    public Iterator<DataColumn> iterator() {
        return columns.iterator();
    }

    @Override
    public void forEach(Consumer<? super DataColumn> action) {
        columns.forEach(action);
    }

    @Override
    public Spliterator<DataColumn> spliterator() {
        return columns.spliterator();
    }

    public int indexOf(String name) {
        return columnNames.getOrDefault(name, -1);
    }

    public DataColumn column(String name) {
        return column(indexOf(name));
    }

    public DataColumn column(int index) {
        return columns.get(index);
    }
}
