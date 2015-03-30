package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
public class ExcelOpt { 
/** 
  * 生成一个Excel文件 jxl 
     * @param fileName  要生成的Excel文件名 
     * @jxl.jar 版本：2.6 
     */  
    public static void writeExcel(String fileName){   
        WritableWorkbook wwb = null;   
        try {   
            //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象   
            wwb = Workbook.createWorkbook(new File(fileName)); 
          
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        if(wwb!=null){   
            //创建一个可写入的工作表   
            //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置   
            WritableSheet ws = wwb.createSheet("工作表名称", 0);   
               
            //下面开始添加单元格   
            for(int i=0;i<10;i++){   
                for(int j=0;j<5;j++){   
                    //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行   
                    Label labelC = new Label(j, i, "这是第"+(i+1)+"行，第"+(j+1)+"列"); 
                    try {   
                        //将生成的单元格添加到工作表中   
                        ws.addCell(labelC);   
                    } catch (RowsExceededException e) {   
                        e.printStackTrace();   
                    } catch (WriteException e) {   
                        e.printStackTrace();   
                    }   
  
                }   
            }   
  
            try {   
                //从内存中写入文件中   
                wwb.write();   
                //关闭资源，释放内存   
                wwb.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            } catch (WriteException e) {   
                e.printStackTrace();   
            }   
        }   
    } 
   
    /**   
     *   生成一个Excel文件POI 
     *   @param   inputFile   输入模板文件路径  
     *   @param   outputFile   输入文件存放于服务器路径  
     *   @param   dataList   待导出数据  
     *   @throws   Exception  
     *   @roseuid:  
     */ 

    public static void exportExcelFile(String inputFile,String outputFile,List dataList) throws Exception{ 
  //用模板文件构造poi  
  POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile)); 
  //创建模板工作表  
  HSSFWorkbook templatewb = new HSSFWorkbook(fs); 
  //直接取模板第一个sheet对象  
  HSSFSheet templateSheet = templatewb.getSheetAt(1); 
  //得到模板的第一个sheet的第一行对象   为了得到模板样式  
  HSSFRow templateRow = templateSheet.getRow(0); 

  //HSSFSheet   timplateSheet   =   templatewb.getSheetAt(1);  
  //取得Excel文件的总列数  
  int columns = templateSheet.getRow((short) 0) 
    .getPhysicalNumberOfCells(); 
//  Debug.println("columns   is   :   " + columns);  //========================= 
  //创建样式数组  
  HSSFCellStyle styleArray[] = new HSSFCellStyle[columns]; 

  //一次性创建所有列的样式放在数组里  
  for (int s = 0; s < columns; s++) { 
   //得到数组实例  
   styleArray[s] = templatewb.createCellStyle(); 
  } 
  //循环对每一个单元格进行赋值    
  //定位行  
  for (int rowId = 1; rowId < dataList.size(); rowId++) { 
   //依次取第rowId行数据   每一个数据是valueList  
   List valueList = (List) dataList.get(rowId - 1); 
   //定位列  
   for (int columnId = 0; columnId < columns; columnId++) { 
    //依次取出对应与colunmId列的值  
    //每一个单元格的值  
    String dataValue = (String) valueList.get(columnId); 
    //取出colunmId列的的style  
    //模板每一列的样式  
    HSSFCellStyle style = styleArray[columnId]; 
    //取模板第colunmId列的单元格对象  
    //模板单元格对象  
    HSSFCell templateCell = templateRow.getCell((short) columnId); 
    //创建一个新的rowId行   行对象  
    //新建的行对象    
    HSSFRow hssfRow = templateSheet.createRow(rowId); 
    //创建新的rowId行   columnId列   单元格对象  
    //新建的单元格对象  
    HSSFCell cell = hssfRow.createCell((short) columnId); 
    //如果对应的模板单元格   样式为非锁定  
    if (templateCell.getCellStyle().getLocked() == false) { 
     //设置此列style为非锁定  
     style.setLocked(false); 
     //设置到新的单元格上  
     cell.setCellStyle(style); 
    } 
    //否则样式为锁定  
    else { 
     //设置此列style为锁定  
     style.setLocked(true); 
     //设置到新单元格上  
     cell.setCellStyle(style); 
    } 
    //设置编码  
//    cell.setEncoding(HSSFCell.ENCODING_UTF_16); 
    //Debug.println("dataValue   :   "   +   dataValue);  
    //设置值   统一为String  
    cell.setCellValue(dataValue); 
   } 
  } 
  //设置输入流  
  FileOutputStream fOut = new FileOutputStream(outputFile); 
  //将模板的内容写到输出文件上  
  templatewb.write(fOut); 
  fOut.flush(); 

  //操作结束，关闭文件  
  fOut.close(); 

} 
   
    /** 
     * 导出数据为XLS格式 
     * @param fos 生成Excel文件Path 
     * @param bo 要导入的数据 
     */ 
    public static void  writeExcelBo(String fos, java.util.List ve) 
    { 
     jxl.write.WritableWorkbook wwb; 
     try 
     { 
      wwb= Workbook.createWorkbook(new File(fos)); 
      jxl.write.WritableSheet ws= wwb.createSheet("上市新书", 10); 
      ws.addCell(new jxl.write.Label(0, 1, "书名")); 
      ws.addCell(new jxl.write.Label(1, 1, "作者")); 
      ws.addCell(new jxl.write.Label(2, 1, "定价")); 
      ws.addCell(new jxl.write.Label(3, 1, "出版社")); 
      int bookSize=ve.size(); 
      BookVO book = new BookVO(); 
      for (int i= 0; i < bookSize; i++) 
      { 
       book= (BookVO)ve.get(i); 
       ws.addCell(new jxl.write.Label(0, i + 2, "" + book.getBookName())); 
       ws.addCell(new jxl.write.Label(1, i + 2, book.getBookAuthor())); 
       ws.addCell(new jxl.write.Label(2, i + 2, "" + book.getBookPrice())); 
       ws.addCell(new jxl.write.Label(3, i + 2, book.getBookConcern())); 

      } 
      ws.addCell(new jxl.write.Label(0, 0, "2007年07月即将上市新书！")); 
      wwb.write(); 
      // 关闭Excel工作薄对象 
      wwb.close(); 
     } catch (IOException e){ 
     } catch (RowsExceededException e){ 
     
     } catch (WriteException e){ 
     } 
    } 
   
    public static void main(String[] args) { 
     writeExcel("d:\\Test测试Excel.xls"); 
     System.out.println("OK"); 
     ArrayList list = new ArrayList(); 
     
     for (int i = 0; i < 10; i++) { 
      BookVO book = new BookVO(); 
      book.setBookName("WebWork in action+"+i); 
      book.setBookAuthor("唐勇+"+i); 
      book.setBookPrice("39元+"+i); 
      book.setBookConcern("飞思科技+"+i); 
      list.add(book); 
  } 
     
     writeExcelBo("d:\\上市新书.xls",list); 
     System.err.println("Book OK!!!"); 
     
} 

}
