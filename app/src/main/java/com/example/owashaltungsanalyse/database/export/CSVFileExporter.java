package com.example.owashaltungsanalyse.database.export;

import javax.inject.Inject;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class CSVFileExporter implements IFileExporter {

    @Inject
    public CSVFileExporter() {
    }

    public void writeFileUTF8(FileDescriptor filePath, List<String> content) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filePath))) {
            for (String line : content) {
                writer.println(line);
            }
        }
    }
}