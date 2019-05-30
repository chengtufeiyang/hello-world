package encrypt_decrypt.startup;

import encrypt_decrypt.FileToolKit.Constants;
import encrypt_decrypt.encrypt.FileandDatabase;
import encrypt_decrypt.encrypt.FileandDatabaseUtile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDataManagement {
    /**
     * 生成日志
     */
    public static final Logger log = LoggerFactory.getLogger(FileDataManagement.class);
    public static void main(String[] args) {
        FileandDatabase fileandDatabase = null;
        try {

            if (args == null){
                args[0] = "1";
            }
            Constants constants = Constants.newInstance();
            FileandDatabaseUtile fileandDatabaseUtile = FileandDatabaseUtile.newInstance();

            fileandDatabase = new FileandDatabase();
              //生成以.sql后缀的查询脚本
            fileandDatabase.insertStatementFile(args[0]);
            //执行.sql后缀的查询脚本的.sh文件
            fileandDatabase.executeFilesql();
            //生成执行cat命令的.sh文件
            fileandDatabase.executeFileCat();
        }catch (Exception e){
            e.printStackTrace();
            log.error("run Start Exception",fileandDatabase);
        }
    }
}
