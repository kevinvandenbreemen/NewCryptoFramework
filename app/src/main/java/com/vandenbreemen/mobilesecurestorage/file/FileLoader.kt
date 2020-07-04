@file:JvmName("FileImporterJavaInteractor")
package com.vandenbreemen.mobilesecurestorage.file

import com.vandenbreemen.mobilesecurestorage.message.ApplicationError
import com.vandenbreemen.mobilesecurestorage.message.MSSRuntime
import com.vandenbreemen.mobilesecurestorage.security.Bytes
import java.io.File

/**
 * <h2>Intro</h2>
 * Specialists in importing file data
 * <h2>Other Details</h2>
 * @author kevin
 */
interface FileLoader {

    @Throws(ApplicationError::class)
    fun loadFile(fileToImport: File): ImportedFileData

    fun getFilenameToUseWhenImporting(file: File): String

    fun getAlternateNameForFileImport(file: File, indexedFile: IndexedFile): String
}

private class FileLoaderImpl : FileLoader {
    override fun getAlternateNameForFileImport(file: File, indexedFile: IndexedFile): String {
        if (!indexedFile.exists(getFilenameToUseWhenImporting(file))) {
            return getFilenameToUseWhenImporting(file)
        }
        val filename = getFilenameToUseWhenImporting(file)
        var index = 0
        while (indexedFile.exists(filename)) {
            val altFilename = "${filename}_${++index}"
            if (!indexedFile.exists(altFilename)) {
                return altFilename
            }
        }

        throw MSSRuntime("Impossible condition - infinitely large file system")
    }

    override fun getFilenameToUseWhenImporting(file: File): String {
        return file.name
    }

    override fun loadFile(fileToImport: File): ImportedFileData {
        if (fileToImport.isDirectory) {
            throw ApplicationError("Cannot import a directory")
        }
        if (!fileToImport.exists()) {
            throw ApplicationError("Cannot import non-existent file ${fileToImport.name}")
        }
        return ImportedFileData(Bytes.loadBytesFromFile(fileToImport))
    }

}

fun getFileImporter(): FileLoader {
    return FileLoaderFactory.getFileImporter()
}

class FileLoaderFactory private constructor() {

    companion object {
        fun getFileImporter(): FileLoader {
            return FileLoaderImpl()
        }
    }

}