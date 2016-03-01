package com.tianque.threeRecordsIssue.dataTrans;

import com.tianque.core.validate.ValidateResult;

/**
 * 代码库:sichuangrid1.9.1_account
 * 作  者:liuchuanjiang@hztianque.com
 * 日  期: 2015/3/16.
 */
public class SheetValidateResult extends ValidateResult {

    public void addErrorMessage(int sheetNum,int row, int column, String msg) {
        this.getMessages()
                .add("在第["+sheetNum+"]工作表的单元格[" + convertToExcelCell(sheetNum,row, column - 1) + "]发现数据错误，错误为：" + msg);
    }

    public void addErrorMessage(int sheetNum,int row, String msg) {
        if (row > 0) {
            this.getMessages().add("在第["+sheetNum+"]工作表的单元行第[" + row + "]行" + msg);
        } else if (row == -1) {
            this.getMessages().add(msg);
        } else {
            this.getMessages().add("在第["+sheetNum+"]工作表的单元行第[" + row + "]行" + msg);
        }

    }

    private String convertToExcelCell(int sheetNum,int row, int column) {
        return getCellColumnString(column) + row;
    }
    private String getCellColumnString(int column) {
        int letterPrefixIndex = column / 26;
        int letterIndex = column % 26;
        if (letterPrefixIndex > 0) {
            return getCellColumnString(letterPrefixIndex - 1) + getCellColumnString(letterIndex);
        }
        return String.valueOf((char) (65 + letterIndex));
    }

}
