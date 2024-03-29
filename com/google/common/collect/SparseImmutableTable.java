/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  javax.annotation.concurrent.Immutable
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.RegularImmutableTable;
import com.google.common.collect.Table;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.Immutable;

@GwtCompatible
@Immutable
final class SparseImmutableTable<R, C, V>
extends RegularImmutableTable<R, C, V> {
    static final ImmutableTable<Object, Object, Object> EMPTY = new SparseImmutableTable<Object, Object, Object>(ImmutableList.<Table.Cell<R, C, V>>of(), ImmutableSet.<E>of(), ImmutableSet.<E>of());
    private final ImmutableMap<R, Map<C, V>> rowMap;
    private final ImmutableMap<C, Map<R, V>> columnMap;
    private final int[] cellRowIndices;
    private final int[] cellColumnInRowIndices;

    SparseImmutableTable(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        ImmutableMap<R, Integer> rowIndex = Maps.indexMap(rowSpace);
        LinkedHashMap<E, LinkedHashMap<K, V>> rows = Maps.newLinkedHashMap();
        for (E row : rowSpace) {
            rows.put(row, new LinkedHashMap<K, V>());
        }
        LinkedHashMap<E, LinkedHashMap<K, V>> columns = Maps.newLinkedHashMap();
        for (E col : columnSpace) {
            columns.put(col, new LinkedHashMap<K, V>());
        }
        int[] cellRowIndices = new int[cellList.size()];
        int[] cellColumnInRowIndices = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); ++i) {
            Table.Cell cell = (Table.Cell)cellList.get((int)i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            V value = cell.getValue();
            cellRowIndices[i] = ((Integer)rowIndex.get(rowKey)).intValue();
            Map thisRow = (Map)rows.get(rowKey);
            cellColumnInRowIndices[i] = thisRow.size();
            V oldValue = thisRow.put(columnKey, value);
            if (oldValue != null) {
                throw new IllegalArgumentException((String)("Duplicate value for row=" + rowKey + ", column=" + columnKey + ": " + value + ", " + oldValue));
            }
            ((Map)columns.get(columnKey)).put(rowKey, value);
        }
        this.cellRowIndices = cellRowIndices;
        this.cellColumnInRowIndices = cellColumnInRowIndices;
        ImmutableMap.Builder<K, ImmutableMap<K, V>> rowBuilder = new ImmutableMap.Builder<K, ImmutableMap<K, V>>((int)rows.size());
        for (Map.Entry<K, V> row : rows.entrySet()) {
            rowBuilder.put(row.getKey(), ImmutableMap.copyOf((Map)row.getValue()));
        }
        this.rowMap = rowBuilder.build();
        ImmutableMap.Builder<K, ImmutableMap<K, V>> columnBuilder = new ImmutableMap.Builder<K, ImmutableMap<K, V>>((int)columns.size());
        Iterator<Map.Entry<K, V>> i$ = columns.entrySet().iterator();
        do {
            if (!i$.hasNext()) {
                this.columnMap = columnBuilder.build();
                return;
            }
            Map.Entry<K, V> col = i$.next();
            columnBuilder.put(col.getKey(), ImmutableMap.copyOf((Map)col.getValue()));
        } while (true);
    }

    @Override
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return this.columnMap;
    }

    @Override
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return this.rowMap;
    }

    @Override
    public int size() {
        return this.cellRowIndices.length;
    }

    @Override
    Table.Cell<R, C, V> getCell(int index) {
        int rowIndex = this.cellRowIndices[index];
        Map.Entry rowEntry = (Map.Entry)((ImmutableSet)this.rowMap.entrySet()).asList().get((int)rowIndex);
        ImmutableMap row = (ImmutableMap)rowEntry.getValue();
        int columnIndex = this.cellColumnInRowIndices[index];
        Map.Entry colEntry = (Map.Entry)((ImmutableSet)row.entrySet()).asList().get((int)columnIndex);
        return SparseImmutableTable.cellOf(rowEntry.getKey(), colEntry.getKey(), colEntry.getValue());
    }

    @Override
    V getValue(int index) {
        int rowIndex = this.cellRowIndices[index];
        ImmutableMap row = (ImmutableMap)((ImmutableCollection)this.rowMap.values()).asList().get((int)rowIndex);
        int columnIndex = this.cellColumnInRowIndices[index];
        return (V)((ImmutableCollection)row.values()).asList().get((int)columnIndex);
    }

    @Override
    ImmutableTable.SerializedForm createSerializedForm() {
        ImmutableMap<E, Integer> columnKeyToIndex = Maps.indexMap(this.columnKeySet());
        int[] cellColumnIndices = new int[((AbstractCollection)((Object)this.cellSet())).size()];
        int i = 0;
        Iterator i$ = ((ImmutableSet)this.cellSet()).iterator();
        while (i$.hasNext()) {
            Table.Cell cell = (Table.Cell)i$.next();
            cellColumnIndices[i++] = ((Integer)columnKeyToIndex.get(cell.getColumnKey())).intValue();
        }
        return ImmutableTable.SerializedForm.create(this, (int[])this.cellRowIndices, (int[])cellColumnIndices);
    }
}

