package com.example.FileCheck.shared;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String PDF_MIME_TYPE = "application/pdf";

    public static final String XLS_MIME_TYPE = "application/vnd.ms-excel";

    public static final String XLSX_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final String XLSM_MIME_TYPE = "application/vnd.ms-excel.sheet.macroEnabled.12";

    public static final String XLSB_MIME_TYPE = "application/vnd.ms-excel.sheet.binary.macroEnabled.12";

    public static final String XLTM_MIME_TYPE = "application/vnd.ms-excel.template.macroEnabled.12";

    public static final String PDF_TYPE = "pdf";

    public static final String XLS_TYPE = "xls";

    public static final String XLSX_TYPE = "xlsx";

    public static final String XLSM_TYPE = "xlsm";

    public static final String XLSB_TYPE = "xlsb";

    public static final String XLT_TYPE = "xlt";

    public static final String XLTM_TYPE = "xltm";

    public static final List<String> ALLOWED_POWERPOINT_FORMAT =
            Arrays.asList("application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation","application/vnd.openxmlformats-officedocument.presentationml.template","application/vnd.openxmlformats-officedocument.presentationml.slideshow","application/vnd.ms-powerpoint.addin.macroEnabled.12","application/vnd.ms-powerpoint.presentation.macroEnabled.12","application/vnd.ms-powerpoint.template.macroEnabled.12","application/vnd.ms-powerpoint.slideshow.macroEnabled.12");

    /**
     * list of accepted word extensions
     */
    public static final List<String> ALLOWED_WORD_FORMAT =
            Arrays.asList( "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/vnd.ms-word.document.macroEnabled.12", "application/vnd.ms-word.document.macroEnabled.12" );
    /**
     * list of accepted excel extensions
     */
    public static final List<String> ALLOWED_EXCEL_FORMAT = Arrays.asList("application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","application/vnd.ms-excel.sheet.macroEnabled.12","application/vnd.ms-excel.sheet.binary.macroEnabled.12","application/vnd.ms-excel.template.macroEnabled.12");

}
