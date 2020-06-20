package com.vandenbreemen.mobilesecurestorage.file.api

import com.vandenbreemen.mobilesecurestorage.TestConstants
import com.vandenbreemen.mobilesecurestorage.file.IndexedFile
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import java.util.*

/**
 *
 * @author kevin
 */
class IndexedFileAdditionalFunctionalityTests {

    @Test
    fun `Generates Create date on File Creation`() {

        //  Arrange
        val tempFile =
            TestConstants.getTestFile("test_jpgimport_persist" + System.currentTimeMillis() + ".dat")
        var idf = IndexedFile(tempFile)

        //  Act
        idf.touch("newFile")

        //  Assert
        idf = IndexedFile(tempFile)
        val details = idf.getDetails("newFile")
        assertNotNull(details.createDate)

    }

    @Test
    fun `Renaming a file Preserves its Create Date`() {

        //  Arrange
        val tempFile =
            TestConstants.getTestFile("test_jpgimport_persist" + System.currentTimeMillis() + ".dat")
        var idf = IndexedFile(tempFile)

        //  Act
        idf.touch("newFile")
        val createDate = idf.getDetails("newFile").createDate
        idf = IndexedFile(tempFile)
        idf.rename("newFile", "UpdatedName")

        //  Assert
        idf = IndexedFile(tempFile)
        val details = idf.getDetails("UpdatedName")
        assertEquals(createDate, details.createDate)

    }

    @Test
    fun `Impossible to update file create date`() {

        //  Arrange
        val tempFile =
            TestConstants.getTestFile("test_jpgimport_persist" + System.currentTimeMillis() + ".dat")
        var idf = IndexedFile(tempFile)

        //  Act
        idf.touch("newFile")
        var details = idf.getDetails("newFile")
        val expectedCreateDate = details.createDate
        details.createDate = Calendar.getInstance()
        idf.touch("SomeOtherFileSoSFSGetsSaved")

        //  Assert
        idf = IndexedFile(tempFile)
        assertEquals(expectedCreateDate, idf.getDetails("newFile").createDate)

    }

    @Test
    fun `Stores Create date for Object saved to new file`() {

        //  Arrange
        val tempFile =
            TestConstants.getTestFile("test_jpgimport_persist" + System.currentTimeMillis() + ".dat")
        var idf = IndexedFile(tempFile)

        //  Act
        idf.storeObject("newFile", "This is a test")

        //  Assert
        idf = IndexedFile(tempFile)
        val details = idf.getDetails("newFile")
        assertNotNull(details.createDate)

    }

    @Test
    fun `Preserves create date on updating file type`() {

        //  Arrange
        val tempFile =
            TestConstants.getTestFile("test_jpgimport_persist" + System.currentTimeMillis() + ".dat")
        var idf = IndexedFile(tempFile)

        //  Act
        idf.touch("newFile")
        idf = IndexedFile(tempFile)
        val createDate = idf.getDetails("newFile").createDate
        idf.setFileType("newFile", FileTypes.DATA)

        //  Assert
        idf = IndexedFile(tempFile)
        val details = idf.getDetails("newFile")
        assertEquals(createDate, details.createDate)
        assertEquals(FileTypes.DATA, details.fileType)

    }

}