package encrypt_decrypt.encrypt;

import encrypt_decrypt.FileToolKit.Constants;
import encrypt_decrypt.FileToolKit.FileTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileandDatabaseUtile {

    /**
     * 生成日志
     */
    public static final Logger log = LoggerFactory.getLogger(FileandDatabaseUtile.class);

    /**
     * 若无jar包所在目录若无txt、sql,csv,sh，txtsh文件可预先创建,若已存在则不处理
     */
     static {
        try {
            FileTool.createFileFolder(Constants.JARFIL_EPATH+File.separator+Constants.TXT_SUFFIX);
            FileTool.createFileFolder(Constants.JARFIL_EPATH+File.separator+Constants.SQL_SUFFIX);
            FileTool.createFileFolder(Constants.JARFIL_EPATH+File.separator+Constants.CSV_SUFFIX);
            FileTool.createFileFolder(Constants.JARFIL_EPATH+File.separator+Constants.SH_SUFFIX);
            FileTool.createFileFolder(Constants.JARFIL_EPATH+File.separator+Constants.TXT_SUFFIX+Constants.SH_SUFFIX);
        } catch (Exception e){
            log.error("生成目录失败：createFolder()---",e);
        }
    }

    /**
     * 取得排序最大的名称(含有csv后缀)
     */
    public static final String fileNameCsv = FileTool.achieveCsvFileMaxName(Constants.JARFIL_EPATH+ File.separator+Constants.CSV_SUFFIX);

    /**
     * 取得排序最大的名称(不含csv后缀)
     */
    public static final String fileNameNoCsv = FileTool.achieveCsvFileMaxNameSingle(Constants.JARFIL_EPATH+File.separator+Constants.CSV_SUFFIX);

    /**
     * 取得月表中年月部分，如：201903
     */
    public static final String fileNameTime = FileTool.achieveNumfromString(Constants.JARFIL_EPATH+File.separator+Constants.CSV_SUFFIX);

    /**
     *取得数据文件完整路径（包含文件名包括csv、sql、txt等）
     */
    public static final String filePathNameCsv = FileTool.achieveCsvFilePathName(Constants.JARFIL_EPATH+File.separator+Constants.CSV_SUFFIX);

    /**
     * 取得数据文件路径（仅仅路径，不含文件名称）
     */
    public static final String filePathCsv = FileTool.achieveCsvFilePath(Constants.JARFIL_EPATH,Constants.CSV_SUFFIX);

    /**
     * 取得数据文件路径（仅仅路径，不含文件名称）
     */
    public static final String filePathTXT = FileTool.achieveCsvFilePath(Constants.JARFIL_EPATH,Constants.TXT_SUFFIX);

    /**
     * 取得数据文件路径（仅仅路径，不含文件名称）
     */
    public static final String filePathSql = FileTool.achieveCsvFilePath(Constants.JARFIL_EPATH,Constants.SQL_SUFFIX);

    /**
     * 取得数据文件路径（仅仅路径，不含文件名称）
     */
    public static final String filePathSh = FileTool.achieveCsvFilePath(Constants.JARFIL_EPATH,Constants.SH_SUFFIX);

    /**
     * 取得数据文件路径（仅仅路径，不含文件名称）
     */
    public static final String filePathTxtSh = FileTool.achieveCsvFilePath(Constants.JARFIL_EPATH,Constants.TXT_SUFFIX+Constants.SH_SUFFIX);



    /**
     * 类（FileandDatabaseUtile）静态工厂方法
     * @return
     */
    public static FileandDatabaseUtile newInstance(){
        return new FileandDatabaseUtile();
    }






}
