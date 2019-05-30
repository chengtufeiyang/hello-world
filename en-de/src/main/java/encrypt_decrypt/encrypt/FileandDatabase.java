package encrypt_decrypt.encrypt;

import encrypt_decrypt.FileToolKit.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Properties;

public class FileandDatabase {

    /**
     * 读取配置文件
      */
    public static final Properties prop = new Properties();

    /**
     * 这里的填写的参数是配置文件的相对路径
      */
    public static final ClassPathResource classPathResource = new ClassPathResource(
            Constants.PROFILE_PROPERTIES, FileandDatabase.class);
    /**
     * 生成日志
     */
    public static final Logger log = LoggerFactory.getLogger(FileandDatabase.class);


    /**
     *  文件流的编码方式
     */
    static {
        try {
            prop.load(new InputStreamReader(classPathResource.getInputStream(), Constants.GBK_FILEFORMAT));
        }catch (IOException e){
            log.info("静态代码块中：prop加载错误：prop:{}",prop);
        }
    }

    /**
     * 读取一列数据，并以此列数据为条件生成以.sql后缀的查询脚本
     * @throws Exception
     */
    public void insertStatementFile(String queryType) throws Exception
    {
        InputStreamReader reader = null;
        FileWriter writer = null;
        try
        {
            // read file content from file
            StringBuffer sb = new StringBuffer("");
            //写入spool命令行
            System.out.println(prop.getProperty("sqlFilePrefix"));
            System.out.println(FileandDatabaseUtile.filePathTXT);
            sb.append(prop.getProperty("sqlFilePrefix").replaceAll(",","\r\n")+"spool "+FileandDatabaseUtile.filePathTXT+
                    File.separator+FileandDatabaseUtile.fileNameNoCsv+".txt"+ "\r\n");
            //以gbk格式读取文件
            reader = new InputStreamReader(new FileInputStream(new File(FileandDatabaseUtile.filePathNameCsv)),
                    Constants.GBK_FILEFORMAT);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while ((str = br.readLine()) != null)
            {

                if (Constants.QueryType.ALL_MONTH.getValue().equals(queryType)){
                    sb.append(prop.getProperty("sqlStatementPrefix")+
                            "'"+str+"'" +
                            prop.getProperty("sqlStatementMid")+
                            FileandDatabaseUtile.fileNameTime+   //表格时间后缀
                            prop.getProperty("sqlStatementSuffix")+ "\r\n");
                }
                if (Constants.QueryType.ONE_DAY.getValue().equals(queryType)){
                    sb.append(prop.getProperty("sqlFilePrefixSpecificTime")+
                            "'"+str+"'" +
                            prop.getProperty("sqlStatementMid")+
                            FileandDatabaseUtile.fileNameTime+   //表格时间后缀
                            prop.getProperty("sqlStatementSuffixSpecificTime")+ "\r\n");
                }
                System.out.println(str);
            }
            sb.append("spool off;");
            br.close();
            reader.close();
            //写入文件数据
            writer = new FileWriter(FileandDatabaseUtile.filePathSql+File.separator+FileandDatabaseUtile.fileNameNoCsv+".sql");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());
            bw.close();
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            log.error(
                    "FileNotFoundException.insertStatementFile方法未找到！,e:{}  and reader:{}  and writer:{} ",
                    e, reader, writer);
        }
        catch (IOException e)
        {
            log.error("IOException.insertStatementFile方法读取失败！,e:{}  and reader:{}  and writer:{}", e,
                    reader, writer);
        }
    }

    /**
     * 生成执行.sql后缀的查询脚本的.sh文件
     * @throws Exception
     */
    public void executeFilesql() throws Exception, IOException {
        FileWriter writer = null;
        // read file content from file
        StringBuffer sb = new StringBuffer("");
        BufferedWriter bw = null;
        try
        {
            //sqlplus asiainfo/asia123SH@pdb1      ccc/ccc123@ldims
            // "$sql_result  >   ./log/sql_result_`date \"+%Y%m%d_%H%M%S\"`.log"+ "\r\n"+
            sb.append(prop.getProperty("executeFilesqlSh").replaceAll(",","\r\n")+"\r\n"+
                    "export import_oracle_file=\""+FileandDatabaseUtile.filePathSql+File.separator+
                    FileandDatabaseUtile.fileNameNoCsv+".sql"+"\""+ "\r\n"+
                    "$ora_db_conn @$import_oracle_file > ./import_oracle_`date \"+%Y%m%d_%H%M%S\"`.log"+ "\r\n");

            //写入文件数据
            writer = new FileWriter(FileandDatabaseUtile.filePathSh+File.separator+FileandDatabaseUtile.fileNameNoCsv+".sh");
            bw = new BufferedWriter(writer);
            bw.write(sb.toString());
        }
        catch (FileNotFoundException e)
        {
            log.error(
                    "FileNotFoundException.executeFilesql方法未找到！,e:{}  and writer:{}",
                    e,  writer);
        }
        catch (IOException e)
        {
            log.error("IOException.executeFilesql方法读取失败！,e:{}  and writer:{}", e,
                     writer);
        }finally {
            if(bw !=null){
                bw.close();
            }
            if (writer != null){
                writer.close();
            }
        }
    }

    /**
     * 生成执行cat命令的.sh文件
     * @throws Exception
     */
    public void executeFileCat() throws Exception
    {
        FileWriter writer = null;
        // read file content from file
        StringBuffer sb = new StringBuffer("");
        BufferedWriter bw = null;
        try
        {
            // "$sql_result  >   ./log/sql_result_`date \"+%Y%m%d_%H%M%S\"`.log"+ "\r\n"+
            sb.append("export converformat=$(cat "+FileandDatabaseUtile.filePathTXT+File.separator+
                    FileandDatabaseUtile.fileNameNoCsv+".txt" +" | sed 's/[ ][ ]*/,/g' > "+ FileandDatabaseUtile.filePathTXT+File.separator+FileandDatabaseUtile.fileNameCsv+")"+ "\r\n"+
                    "$converformat > ./result_converformat_`date \"+%Y%m%d_%H%M%S\"`.log"+ "\r\n");
            //写入文件数据
            writer = new FileWriter(FileandDatabaseUtile.filePathTxtSh+
                    File.separator+FileandDatabaseUtile.fileNameNoCsv+".sh");
            bw = new BufferedWriter(writer);
            bw.write(sb.toString());
        }
        catch (FileNotFoundException e)
        {
            log.error(
                    "FileNotFoundException.executeFileCat方法未找到！,e:{}  and writer:{}",
                    e,  writer);
        }
        catch (IOException e)
        {
            log.error("IOException.executeFileCat方法读取失败！,e:{}  and writer:{}", e,
                    writer);
        }finally {
            try {
                if(bw !=null){
                    bw.close();
                }
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(Constants.JARFIL_EPATH);
//        System.out.println(Constants.JARFIL_EPATH+File.separator+Constants.CSV_SUFFIX);
//System.out.println(FileTool.achieveCsvFileMaxName(Constants.JARFIL_EPATH+File.separator+Constants.CSV_SUFFIX));
        System.out.println(Constants.QueryType.ONE_DAY.getValue());

    }
}
