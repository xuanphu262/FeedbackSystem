package android.mobile.feedbacksystem.common;

import android.content.Context;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.model.FeedbackModel;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AppUtils {
    public static int[] FeedbackResource = {
            R.drawable.ic_feedback_1,
            R.drawable.ic_feedback_2,
            R.drawable.ic_feedback_3,
            R.drawable.ic_feedback_4,
            R.drawable.ic_feedback_5
    };
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static File saveExcelFile(Context context, String fileName) {
        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIME.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("report");

        // Generate column headings
        Row row = sheet1.createRow(0);

        c = row.createCell(0);
        c.setCellValue("No.");
        c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("Rating");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Location");
        c.setCellStyle(cs);

        c = row.createCell(3);
        c.setCellValue("CreatedDate");
        c.setCellStyle(cs);

        List<FeedbackModel> list = DataHelper.feedbackList;
        for (int i = 1; i < list.size(); i++) {

            // Generate column headings
            row = sheet1.createRow(i);

            c = row.createCell(0);
            c.setCellValue(i);
            c.setCellStyle(cs);

            c = row.createCell(1);
            c.setCellValue(list.get(i).getRating());
            c.setCellStyle(cs);

            c = row.createCell(2);
            c.setCellValue(list.get(i).getLocationId());
            c.setCellStyle(cs);

            c = row.createCell(3);
            c.setCellValue(dateFormat.format(list.get(i).getCreatedDate()));
            c.setCellStyle(cs);
        }
        sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (15 * 500));
        sheet1.setColumnWidth(2, (15 * 500));
        sheet1.setColumnWidth(3, (15 * 500));

        // Create a path where we will place our List of objects on external storage
        File savedFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(savedFile);
            wb.write(os);
            Log.d("FileUtils", "Writing file" + savedFile);
        } catch (IOException e) {
            Log.d("FileUtils", "Error writing " + savedFile, e);
            return null;
        } catch (Exception e) {
            Log.d("FileUtils", "Failed to save file", e);
            return null;
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }

        return savedFile;
    }
}
