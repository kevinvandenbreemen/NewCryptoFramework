package com.vandenbreemen.mobilesecurestorage.file.api

import java.util.*

/**
 * Information about a file stored on the SFS
 * @author kevin
 */
data class FileInfo(val fileName: String, val size: Int, val createDate: Calendar?)