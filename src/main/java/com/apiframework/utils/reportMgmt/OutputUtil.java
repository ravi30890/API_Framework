package com.apiframework.utils.reportMgmt;

import com.apiframework.utils.constants.PathConfig;

import java.io.File;
public class OutputUtil {
    public static void createOutputDirectory() {
        String timestamp = System.getProperty("current.date");
        String outputDirName = "Output_" + timestamp;

        PathConfig.setOutputDirName(outputDirName);
        PathConfig.setOutputDir("./Output/" + outputDirName);
        PathConfig.setReportsPath(PathConfig.getOutputDir() + File.separator + "Reports" + File.separator);
        PathConfig.setApplogsPath(PathConfig.getOutputDir() + File.separator + "Applogs" + File.separator);

        if (!new File("./Output").exists()) {
            new File("./Output").mkdir();
        }

        new File(PathConfig.getOutputDir()).mkdir();
        new File(PathConfig.getApplogsPath()).mkdir();
        new File(PathConfig.getReportsPath()).mkdir();
    }
}
