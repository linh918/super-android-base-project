//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bkdev.translation.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Pattern;

public class FileUtil {
    public static final int BUFFER_SIZE = 4096;
    private static final Pattern SAFE_FILENAME_PATTERN = Pattern.compile("[\\w%+,./=_-]+");

    private FileUtil() {
    }

    public static boolean writeToFile(byte[] write, File destFile) {
        try {
            FileOutputStream e = new FileOutputStream(destFile);

            try {
                e.write(write);
            } finally {
                e.close();
            }

            return true;
        } catch (IOException var7) {
            return false;
        }
    }

    public static boolean writeToFile(InputStream inputStream, File destFile) {
        try {
            FileOutputStream e = new FileOutputStream(destFile);

            try {
                byte[] buffer = new byte[4096];

                int bytesRead;
                while((bytesRead = inputStream.read(buffer)) >= 0) {
                    e.write(buffer, 0, bytesRead);
                }
            } finally {
                e.close();
            }

            return true;
        } catch (IOException var9) {
            return false;
        }
    }

    public static byte[] writeToByteArray(File srcFile) throws IOException {
        return writeToByteArray((InputStream)(new BufferedInputStream(new FileInputStream(srcFile))));
    }

    public static byte[] writeToByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        write((InputStream)in, (OutputStream)out);
        return out.toByteArray();
    }

    public static boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        if(destFile.isDirectory()) {
            destFile = new File(destFile, srcFile.getName());
        }

        try {
            FileInputStream e = new FileInputStream(srcFile);

            try {
                result = writeToFile((InputStream)e, destFile);
            } finally {
                e.close();
            }
        } catch (IOException var8) {
            result = false;
        }

        return result;
    }

    public static long copyPipe(InputStream in, OutputStream out, int bufferSizeHint) throws IOException {
        if(bufferSizeHint == 0) {
            bufferSizeHint = 1024;
        }

        try {
            long byteCount = 0L;
            byte[] buffer = new byte[bufferSizeHint];

            int bytesRead1;
            for(boolean bytesRead = true; (bytesRead1 = in.read(buffer)) != -1; byteCount += (long)bytesRead1) {
                out.write(buffer, 0, bytesRead1);
            }

            out.flush();
            long var7 = byteCount;
            return var7;
        } finally {
            try {
                in.close();
            } catch (IOException var18) {
                ;
            }

            try {
                out.close();
            } catch (IOException var17) {
                ;
            }

        }
    }

    public static boolean isFilenameSafe(File file) {
        return SAFE_FILENAME_PATTERN.matcher(file.getPath()).matches();
    }

    public static boolean isFileExists(File file) {
        return file == null?false:file.exists();
    }

    public static boolean isFileExists(String fileName) {
        if(fileName != null && !(fileName = trimWhitespace(fileName)).equals("")) {
            File file = new File(fileName);
            return !isFileExists(file)?false:isFileExists(file);
        } else {
            return false;
        }
    }

    public static boolean isDirectory(String fileName) {
        if(fileName != null && !(fileName = trimWhitespace(fileName)).equals("")) {
            File file = new File(fileName);
            return !isFileExists(file)?false:file.isDirectory();
        } else {
            return false;
        }
    }

    public static void copyToDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
        File nextDirectory = new File(destDir, srcDir.getName());
        if(!isFileExists(nextDirectory) && !nextDirectory.mkdirs()) {
            String var6 = "DirCopyFailed";
            throw new IOException(var6);
        } else {
            File[] files = srcDir.listFiles();

            for(int n = 0; n < files.length; ++n) {
                if(filter != null && !filter.accept(files[n])) {
                    copyFile(files[n], nextDirectory);
                } else {
                    copyToDirectory(files[n], nextDirectory, filter);
                }
            }

        }
    }

    public static void copyToDirectory(File srcDir, File destDir) throws IOException {
        copyToDirectory(srcDir, destDir, (FileFilter)null);
    }

    public static boolean deleteDirectory(File dir) throws IOException {
        if(dir.isDirectory()) {
            File[] arr$ = dir.listFiles();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                File file = arr$[i$];
                String message;
                if(file.isDirectory()) {
                    try {
                        if(!file.getCanonicalFile().getParentFile().equals(dir.getCanonicalFile())) {
                            message = "Warning : " + file + " may be a symlink. Ignoring.";
                            throw new IOException(message);
                        }

                        deleteDirectory(file);
                        if(file.exists() && !file.delete()) {
                            message = "Can\'t delete" + file;
                            throw new IOException(message);
                        }
                    } catch (IOException var6) {
                        ;
                    }
                } else if(file.exists() && !file.delete()) {
                    message = "Can\'t delete" + file;
                    throw new IOException(message);
                }
            }

            return dir.delete();
        } else {
            return false;
        }
    }

    public static long getDirectorySize(File dirPath) {
        long totalSize = 0L;
        if(dirPath == null) {
            return 0L;
        } else {
            File[] files = dirPath.listFiles();
            File[] arr$ = files;
            int len$ = files.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                File file = arr$[i$];
                if(file.isFile()) {
                    totalSize += file.length();
                } else if(file.isDirectory()) {
                    totalSize += file.length();
                    totalSize += getDirectorySize(file);
                }
            }

            return totalSize;
        }
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static long getFileSize(String path) {
        long totalSize = 0L;
        if(path == null) {
            return 0L;
        } else {
            File file = new File(path);
            if(file.exists()) {
                totalSize = file.length();
            }

            return totalSize;
        }
    }

    private static String trimWhitespace(String str) {
        if(!hasLength(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str);

            while(sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
                sb.deleteCharAt(0);
            }

            while(sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }

    private static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    private static long write(InputStream in, OutputStream out) throws IOException {
        return copyPipe(in, out, 4096);
    }

    private static long write(Reader in, Writer out) throws IOException {
        try {
            long byteCount = 0L;
            char[] buffer = new char[4096];

            int bytesRead1;
            for(boolean bytesRead = true; (bytesRead1 = in.read(buffer)) != -1; byteCount += (long)bytesRead1) {
                out.write(buffer, 0, bytesRead1);
            }

            out.flush();
            long var6 = byteCount;
            return var6;
        } finally {
            try {
                in.close();
            } catch (IOException var17) {
                ;
            }

            try {
                out.close();
            } catch (IOException var16) {
                ;
            }

        }
    }
}
