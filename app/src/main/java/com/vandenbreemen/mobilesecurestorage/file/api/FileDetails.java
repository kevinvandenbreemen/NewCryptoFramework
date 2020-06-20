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

    public void setCreateDate(Calendar date) {
        createDate = date.getTimeInMillis();
    }

    Calendar getCreateDate() {
        if(createDate > 0) {
            Calendar ret = Calendar.getInstance();
            ret.setTimeInMillis(createDate);
            return ret;
        }
        return null;
    }
}
