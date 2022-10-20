package com.example.owashaltungsanalyse.database.export;

import java.io.FileDescriptor;
import java.util.List;

public interface IFileExporter {

    void writeFileUTF8(FileDescriptor filePath, List<String> content);

}