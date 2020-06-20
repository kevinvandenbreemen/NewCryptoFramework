package com.vandenbreemen.mobilesecurestorage.file.api;

import com.vandenbreemen.mobilesecurestorage.file.FileMeta;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author kevin
 */
public class FileDetails implements Serializable {

    private static final long serialVersionUID = 6727627730465858239L;

    private FileMeta fileMeta;
    private long createDate;

    public FileDetails() {
        //  Default constructor
    }

    public FileMeta getFileMeta() {
        return fileMeta;
    }


    public void setFileMeta(FileMeta fileMeta) {
        this.fileMeta = fileMeta;
    }

    public FileType getFileType() {
        return fileMeta.getFileType();
    }

    /**
     * Set the create date on the file.  If this has already been performed then this method will do nothing.
     * @param date  Desired creation date
     */
    public final void setCreateDate(Calendar date) {
        if(createDate == 0) {
            createDate = date.getTimeInMillis();
        }
    }

    public final Calendar getCreateDate() {
        if(createDate > 0) {
            Calendar ret = Calendar.getInstance();
            ret.setTimeInMillis(createDate);
            return ret;
        }
        return null;
    }
}
