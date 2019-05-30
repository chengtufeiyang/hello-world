
package encrypt_decrypt.encrypt;

import com.eastcom.ipms.udf.security.AESEncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Properties;

/**
 * @创建人 wuzhenjun
 * @版本
 * @创建时间 2018-9-3 下午11:00:44
 * @项目名称 en-decrypt
 * @包名称 encrypt
 * @类名称 ENCRYPT
 */
public class Encrypt
{

	// 读取配置文件
	public static Properties prop = null;
	public static Logger log = LoggerFactory.getLogger(Encrypt.class);

	public static void main(String[] args)
	{
		String abcString = "";
		try
		{
			//test04();
			//test05();
			// abcString = AESEncryptUtil.decrypt("7IPHGZXmt1CPHYpMh0Z9/Q==");
			// abcString = AESEncryptUtil.encrypt("10212483");
			prop = new Properties();
			// 这里的填写的参数是配置文件的相对路径
			ClassPathResource classPathResource = new ClassPathResource("message.properties", Encrypt.class);
			prop.load(new InputStreamReader(classPathResource.getInputStream(), "gbk")); // 文件流的编码方式
			System.out.println(prop.getProperty("originalFileandDatabase"));
		}
		catch (Exception e)
		{
			log.error("加密失败！", e);
		}
		//System.out.println(abcString);
	}

	public static void test04() throws Exception
	{
		InputStreamReader reader = null;
		FileWriter writer = null;
		try
		{
			// read file content from file
			StringBuffer sb = new StringBuffer("");
			// reader = new InputStreamReader(
			// new FileInputStream(
			// new File(
			// "C:\\Users\\Administrator\\Desktop\\temp\\20190227\\test02\\zhibiao.csv")),
			// "gbk");
			prop = new Properties();
			// 这里的填写的参数是配置文件的相对路径
			ClassPathResource classPathResource = new ClassPathResource(
					"message.properties", Encrypt.class);
			prop.load(new InputStreamReader(classPathResource.getInputStream(), "gbk")); // 文件流的编码方式
			System.out.println(prop.getProperty("c"));
			reader = new InputStreamReader(new FileInputStream(new File(
					prop.getProperty("originalFilePath"))), "gbk");
			// reader = new InputStreamReader(new FileInputStream(new File(
			// "/home/linkage/wuzj3/en_de/result/zhibiao.csv")), "gbk");
			// reader = new InputStreamReader(new FileInputStream(
			// new File("/export/home/hadoop/wuzj3/en-decrypt/data/zhibiao.csv")),
			// "gbk");
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null)
			{
				String strs[] = str.split(",");
				sb.append("insert into user_linshi values (" + "'" + strs[1] + "'" + ","
						+ "'" + AESEncryptUtil.encrypt(strs[0]) + "'" + "," + "'"
						+ strs[0] + "'" + "," + "'" + strs[2] + "'" + ");" + "\r\n");
				//System.out.println(str);
			}
			br.close();
			reader.close();
			// write string to file
			// writer = new FileWriter(
			// "C:\\Users\\Administrator\\Desktop\\temp\\20190227\\test02\\zhibiao.sql");
			writer = new FileWriter(prop.getProperty("resultFilePath"));
			// writer = new FileWriter("/home/linkage/wuzj3/en_de/result/zhibiao.sql");
			// writer = new FileWriter(
			// "/export/home/hadoop/wuzj3/en-decrypt/data/zhibiao.sql");
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(sb.toString());
			bw.write("commit;");
			bw.close();
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			log.info(
					"FileNotFoundException.test04方法加密失败！,e:{}  and reader:{}  and writer:{}",
					e, reader, writer);
		}
		catch (IOException e)
		{
			log.info("IOException.test04方法加密失败！,e:{}  and reader:{}  and writer:{}", e,
					reader, writer);
		}
	}

	/**
	 * 本地读取数据临时使用
	 * @throws Exception
	 */
	public static void test05() throws Exception
	{
		InputStreamReader reader = null;
		FileWriter writer = null;
		try
		{
			// read file content from file
			StringBuffer sb = new StringBuffer("");
			// reader = new InputStreamReader(
			// new FileInputStream(
			// new File(
			// "C:\\Users\\Administrator\\Desktop\\temp\\20190227\\test02\\zhibiao.csv")),
			// "gbk");
			prop = new Properties();
			// 这里的填写的参数是配置文件的相对路径
			ClassPathResource classPathResource = new ClassPathResource(
					"message.properties", Encrypt.class);
			prop.load(new InputStreamReader(classPathResource.getInputStream(), "gbk")); // 文件流的编码方式
			System.out.println(prop.getProperty("originalFilePath"));
			reader = new InputStreamReader(new FileInputStream(new File(
					prop.getProperty("originalFilePath"))), "gbk");
			// reader = new InputStreamReader(new FileInputStream(new File(
			// "/home/linkage/wuzj3/en_de/result/zhibiao.csv")), "gbk");
			// reader = new InputStreamReader(new FileInputStream(
			// new File("/export/home/hadoop/wuzj3/en-decrypt/data/zhibiao.csv")),
			// "gbk");
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null)
			{
				String strs[] = str.split(",");
				sb.append(strs[1]+"@!"+AESEncryptUtil.encrypt(strs[0])+"@!"+strs[0]+"@!"+strs[2]+ "\r\n");
				System.out.println(str);
			}
			br.close();
			reader.close();
			// write string to file
			// writer = new FileWriter(
			// "C:\\Users\\Administrator\\Desktop\\temp\\20190227\\test02\\zhibiao.sql");
			writer = new FileWriter(prop.getProperty("resultFilePath"));
			// writer = new FileWriter("/home/linkage/wuzj3/en_de/result/zhibiao.sql");
			// writer = new FileWriter(
			// "/export/home/hadoop/wuzj3/en-decrypt/data/zhibiao.sql");
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(sb.toString());
//			bw.write("commit;");
			bw.close();
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			log.error(
					"FileNotFoundException.test05方法加密失败！,e:{}  and reader:{}  and writer:{}",
					e, reader, writer);
		}
		catch (IOException e)
		{
			log.error("IOException.test05方法加密失败！,e:{}  and reader:{}  and writer:{}", e,
					reader, writer);
		}
	}
}
