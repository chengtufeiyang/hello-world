package encrypt_decrypt.FileToolKit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FileTool {

    /**
     * 生成日志
     */
    public static final Logger log = LoggerFactory.getLogger(FileTool.class);

    /**
     * 完整路径，包括文件名称,创建新文件
     * @param path
     */
    public static void createFile(String path){

        File file = null;
        try {
            file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            log.info("创建文件失败e:{},file:{}",e,file);
        }
    }


    /**
     * 路径，包括文件夹名称,创建新文件夹
     * @param path
     */
    public static void createFileFolder(String path){

        File file = null;
        file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
    }


    /**
     * 路径，包括文件夹名称,创建新文件夹(递归创建多个文件夹)
     * @param path
     */
    public static void createFileFolders(String path){

        File file = null;
        file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
    }


    /**
     * 获取某个目录下全部csv文件数组（默认涉及文件名称英文+数字.csv）
     * 英文部分一致，数字部分与数据时间一致
     * @param path
     */
    public  static  String[] achieveCsvFile(String path){

        File  file = new File(path);
        String[] arr = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //把dir 和name都封装到一个文件对象里
                File file = new File(dir, name);
                return file.isFile() && file.getName().endsWith(Constants.CSV_SUFFIX);
            }
        });
        return arr;
    }


    /**
     * 文件名称数组（默认涉及文件名称英文+数字.csv或者.sql等其他后缀）
     * 取得排序最大的名称(含有csv后缀)
     * @param path
     */
    public  static  String achieveCsvFileMaxName(String path){
        String[] str = achieveCsvFile(path);
        List<String> list = Arrays.asList(str);

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1 == null || o2 == null){
                    return  0;
                }
                //倒叙排列
                return o2.compareTo(o1);
            }
        });
        return list.get(0);
    }

    /**
     * 文件名称数组（默认涉及文件名称英文+数字.csv）
     * 取得排序最大的名称(不含csv后缀)
     * @param path
     */
    public  static  String achieveCsvFileMaxNameSingle(String path){
        String str = achieveCsvFileMaxName(path);
        return str.split("\\.")[0];
    }
    /**
     *
     * 取得字符串中数字部分
     * @param path
     */
    public  static  String achieveNumfromString(String path){
        String str = achieveCsvFileMaxName(path);
        return str.replaceAll("[a-zA-Z]","").replaceAll("\\.","");
    }

    /**
     * 取得排序最大的名称完整路径(包含文件名)
     * @param path
     */
    public  static  String achieveCsvFilePathName(String path){
        return path+File.separator+achieveCsvFileMaxName(path);
    }


    /**
     * 取得排序最大的名称完整路径(不含文件名)
     * @param path---jar包路径
     *            folderName---存放某种类型文件文件夹  如：txt文件夹(与jar包路径同级)存放txt文件
     */
    public  static  String achieveCsvFilePath(String path,String folderName){
        return path+File.separator+folderName;
    }
}
