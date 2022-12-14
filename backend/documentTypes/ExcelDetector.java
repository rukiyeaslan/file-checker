
package com.example.FileCheck.documentTypes;

import com.aspose.cells.*;
import com.example.FileCheck.repository.DocumentDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of the detector for Microsoft Excel workbook.
 */
public class ExcelDetector implements DocumentDetector {

    /**
     * LOGGER
     */
    private static final Logger LOG = LoggerFactory.getLogger(ExcelDetector.class);

    /**
     * List of allowed Excel format<br>
     * Allow also XLSM/XSLB because both can exists without macro inside.<br>
     * Allow also XLT/XLTM because both can exists without macro inside.<br>
     */
    private static final List<String> ALLOWED_FORMAT = Arrays.asList("xls", "xlsx", "xlsm", "xlsb", "xlt", "xltm");

    @Override
    public boolean isSafe(File f) {
        boolean safeState = false;
        try {
            if ((f != null) && f.exists() && f.canRead()) {
                // Perform a first check on Excel document format
                FileFormatInfo formatInfo = FileFormatUtil.detectFileFormat(f.getAbsolutePath());
                String formatExtension = FileFormatUtil.loadFormatToExtension(formatInfo.getLoadFormat());
                if ((formatExtension != null) && ALLOWED_FORMAT.contains(formatExtension.toLowerCase(Locale.US).replaceAll("\\.", ""))) {
                    // Load the file into the Excel document parser
                    Workbook book = new Workbook(f.getAbsolutePath());
                    // Get safe state from Macro presence
                    safeState = !book.hasMacro();
                    // If document is safe then we pass to OLE objects analysis
                    if (safeState) {
                        // Search OLE objects in all workbook sheets
                        Worksheet sheet = null;
                        OleObject oleObject = null;
                        int totalOLEObjectCount = 0;
                        for (int i = 0; i < book.getWorksheets().getCount(); i++) {
                            sheet = book.getWorksheets().get(i);
                            for (int j = 0; j < sheet.getOleObjects().getCount(); j++) {
                                oleObject = sheet.getOleObjects().get(j);
                                if (oleObject.getMsoDrawingType() == MsoDrawingType.OLE_OBJECT) {
                                    totalOLEObjectCount++;
                                }
                            }
                        }
                        // Update safe status flag according to number of OLE object found
                        if (totalOLEObjectCount != 0) {
                            safeState = false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.warn("Error during Excel file analysis !", e);
        }
        return safeState;
    }

}