import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: mickeys
 * Date: Mar 21, 2016
 * Time: 9:27:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountingHelper {

    String year;
    String month;
    final static String moneyFolder = "c:\\Temp\\Money";

    private void delFolder(File f) {
        print(f.getAbsolutePath());
        if (f.listFiles() == null || f.listFiles().length == 0) {
            f.delete();
            return;
        }        
        delFolder(f.listFiles()[0]);
    }

    public static void main(String[] args) throws IOException {
        Boolean a = new Boolean(true);
        Boolean b = getBoolean();
        System.out.println(b==Boolean.TRUE);
        System.exit(1);

//        readURL(args);
        AccountingHelper helper = new AccountingHelper();
        File f = new File(moneyFolder + "\\backup");
        helper.delFolder(f);
        System.exit(1);
        helper.backUp(moneyFolder);
        double[] sums = helper.readExcel("C:\\Temp\\Money\\Account 01-16.xls");
        helper.updateInOutExcel("C:\\Temp\\Money\\in_out.xlsx", sums);

    }

    private static Boolean getBoolean() {
        boolean b = true;
        return b;
    }

    public void backUp(String folderName) {
        try {
            FileUtils.copyDirectoryToDirectory(new File(folderName), new File(folderName + "\\backup"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public double[] readExcel(String file) {
        year = "20" + file.substring(file.lastIndexOf('.') - 2, file.lastIndexOf('.'));
        month = file.substring(file.lastIndexOf('-') - 2, file.lastIndexOf('-'));

        String startTable = "????? ";
        double sumDebt = 0;
        double sumCredit = 0;

        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            boolean startCalc = false;
            for (int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null) {
//                    if (    row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING &&
//                            row.getCell(0).getStringCellValue().equals(startTable))
//                         startCalc = true;
//
//                    if (startCalc) {
                        Cell dCell = row.getCell(3);
                        if ( dCell != null && dCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            sumDebt += dCell.getNumericCellValue();
                        }
                        Cell cCell = row.getCell(4);
                        if ( cCell != null && cCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            sumCredit += cCell.getNumericCellValue();
                        }

//                        System.out.println(" Debt:" + row.getCell(3));
//                        System.out.println(" Credit:" + row.getCell(4));
//                    }
                }
            }
            System.out.println("Total debt = " + sumDebt);
            System.out.println("Total credit = " + sumCredit);
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        return new double[]{sumCredit, sumDebt};
    }

    public void updateInOutExcel(String inOutFile, double[] sums) {
        if (year == null) {
            print (" year is null, did you read from the Excel? ");
            return;
        }
        double sumCredit = sums[0];
        double sumDebit = sums[1];
        try {
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(inOutFile)));
//            XSSFWorkbook wb = new XSSFWorkbook( inOutFile);
            XSSFSheet sheet = wb.getSheet(year);
            XSSFRow row;
            int mon = Integer.parseInt(month);
            row = sheet.getRow(mon);
            XSSFCell dCell = row.getCell(2);
            if (dCell == null) {
                dCell = row.createCell(2);
                dCell.setCellValue(sumDebit);
            }
            XSSFCell cCell = row.getCell(3);
            if (cCell == null) {
                cCell = row.createCell(3);
                cCell.setCellValue(sumCredit);
            }

            FileOutputStream outFile = new FileOutputStream(new File(inOutFile) );
//            FileOutputStream outFile = new FileOutputStream(new File(inOutFile.replace(".xlsx","2.xlsx")) );
            wb.write(outFile);
            outFile.close();

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void readURL(String[] args) throws UnsupportedEncodingException, IOException {
    URL url = new URL("http://www.ynet.co.il/home/0,7340,L-8,00.html");
    InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
    BufferedReader in = new BufferedReader(isr);

    JEditorPane editorPane = new JEditorPane();
    editorPane.setPage(url);
    editorPane.setPreferredSize(new Dimension(600, 400));

    JOptionPane.showMessageDialog(null, new JScrollPane(editorPane));
    String inputLine;
    JTextArea textarea = new JTextArea(40, 80);
    while ((inputLine = in.readLine()) != null) {
         System.out.println(inputLine);
         textarea.append(inputLine + "\n");
    }
    JOptionPane.showMessageDialog(null, new JScrollPane(textarea));

  }
}
