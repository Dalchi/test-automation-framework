/*
 ===========================================================================
 @    $Author$
 @  $Revision$
 @      $Date$
 @
 ===========================================================================
 */
package com.baloise.testautomation.taf.swing.client.elements;

import com.baloise.testautomation.taf.base._base.AElement;
import com.baloise.testautomation.taf.common.interfaces.ITableData;
import com.baloise.testautomation.taf.swing.base._interfaces.ISwTable;

/**
 * 
 */
public class SwTable extends AElement {

  @Override
  public void click() {}

  public ISwTable<?> find() {
    return (ISwTable<?>)swFind(ISwTable.type);
  }

  public void rightClickCell(int row, int col) {
    find().rightClickCell(row, col);
  }

  public void rightClickCell(String text) {
    find().rightClickCell(text);
  }

  public void clickCell(int row, int col) {
    find().clickCell(row, col);
  }

  public void clickCell(String text) {
    find().clickCell(text);
  }

  public void clickHeader(String columnName) {
    find().clickHeader(columnName);
  }

  public void doubleClickCell(int row, int col) {
    find().doubleClickCell(row, col);
  }

  public void doubleClickCell(String text) {
    find().doubleClickCell(text);
  }

  public String getCellText(int row, int col) {
    return find().getCellText(row, col);
  }

  public Long getCellRow(String value) {
    return find().getCellRow(value);
  }

  public void enterValue(int row, int col, String value) {
    find().enterValue(row, col, value);
  }

  public void selectRows(int... rows) {
    find().selectRows(rows);
  }

  public boolean cellExists(String value) {
    return find().cellExists(value);
  }

  public ITableData getData() {
    return find().getData();
  }
  
}
