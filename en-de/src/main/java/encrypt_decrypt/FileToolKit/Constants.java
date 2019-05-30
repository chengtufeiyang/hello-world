package encrypt_decrypt.FileToolKit;

public class Constants {

    /**
     * csv文件后缀标识符
     */
    public static  final String CSV_SUFFIX = "csv";

    /**
     * sql文件后缀标识符
     */
    public static  final String SQL_SUFFIX = "sql";

    /**
     * txt文件后缀标识符
     */
    public static  final String TXT_SUFFIX = "txt";

    /**
     * sh文件后缀标识符
     */
    public static  final String SH_SUFFIX = "sh";

    /**
     * 取得当前jar包路径
     */
    public static  final  String JARFIL_EPATH = JarTool.getJarDir();

    /**
     * 配置文件名称
     */
    public static  final String PROFILE_PROPERTIES = "message.properties";

    /**
     * 文件格式gbk
     */
    public static  final String GBK_FILEFORMAT = "gbk";


    public enum QueryType{
        //整月
        ONE_DAY("day","2"),
        //单天
        ALL_MONTH("month","1");

        private String name;
        private String value;

        public String getName(){
            return name;
        }

        public String getValue(){
            return value;
        }

        private QueryType(String name,String value){
            this.name = name;
            this.value = value;
        }
    }


    /**
     * 静态工厂方法
     * @return
     */
    public static Constants newInstance(){
        return new Constants();
    }
}
