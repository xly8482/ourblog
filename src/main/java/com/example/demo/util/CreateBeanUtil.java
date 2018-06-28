/*
 * 此类主要用来根据输入的bean名称，dao，service路径,生成与bena相关的,entity、dao、doaImpl、service、serviceImpl五个类，
 * 因为这五个类的内容比较固定，避免每次手动书写
 */
package com.example.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CreateBeanUtil
{

    static final String lineSeparator = System.getProperty("line.separator");

    /**
     * @param beanName 实体名称
     * @param beanPath 实体路径
     * @param daoPath dao层的路径
     * @param servicePath service 层的路径
     */
    public static void createBean(String beanName, String beanPath, String daoPath, String servicePath)
    {

        String root = System.getProperty("user.dir");
        String com = "src.main.java";
        File bean = new File(root + File.separator + replaceStr(com) + File.separator + replaceStr(beanPath) + File.separator + beanName + ".java");
        File dao = new File(root + File.separator + replaceStr(com) + File.separator + replaceStr(daoPath) + File.separator + beanName + "Dao.java");
        File daoImpl =
            new File(root + File.separator + replaceStr(com) + File.separator + replaceStr(daoPath) + File.separator + beanName + "DaoImpl.java");
        File service =
            new File(root + File.separator + replaceStr(com) + File.separator + replaceStr(servicePath) + File.separator + beanName + "Service.java");
        File serviceImpl = new File(
            root + File.separator + replaceStr(com) + File.separator + replaceStr(servicePath) + File.separator + beanName + "ServiceImpl.java");

        // bean
        newFile(bean);
        try
        {
            createBean(beanName, beanPath, bean);
            System.out.println("create bean success!");
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        // dao
        newFile(dao);
        try
        {
            createDao(beanName, beanPath, daoPath, bean, dao);
            System.out.println("create dao success!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // daoImpl
        newFile(daoImpl);
        try
        {
            createDaoImpl(beanName, beanPath, daoPath, bean, daoImpl);
            System.out.println("create daoImpl success!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // service
        newFile(service);
        try
        {
            createService(beanName, beanPath, servicePath, bean, service);
            System.out.println("create service success!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // serviceImpl
        newFile(serviceImpl);
        try
        {
            createServiceImpl(beanName, beanPath, servicePath, bean, serviceImpl);
            System.out.println("create serviceImpl success!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void createServiceImpl(String beanName, String beanPath, String servicePath, File bean, File serviceImpl) throws IOException
    {
        writeFileContent(serviceImpl, "package " + servicePath + ";"); // package
        writeFileContent(serviceImpl, lineSeparator); // 换行
        writeFileContent(serviceImpl, "import " + beanPath + "." + beanName + ";"); // 导包
        writeFileContent(serviceImpl, "import org.springframework.stereotype.Service;"); // 导包
        writeFileContent(serviceImpl, "import javax.transaction.Transactional;"); // 导包
        writeFileContent(serviceImpl, "import org.springframework.beans.factory.annotation.Autowired;"); // 导包
        writeFileContent(serviceImpl, lineSeparator); // 换行
        writeFileContent(serviceImpl, "@Service");
        writeFileContent(serviceImpl, "@Transactional");
        writeFileContent(serviceImpl, "public class " + beanName + "ServiceImpl implements " + beanName + "Service {");
        writeFileContent(serviceImpl, lineSeparator); // 换行
        writeFileContent(serviceImpl, "}");
    }

    private static void createService(String beanName, String beanPath, String servicePath, File bean, File service) throws IOException
    {
        writeFileContent(service, "package " + servicePath + ";"); // package
        writeFileContent(service, lineSeparator); // 换行
        writeFileContent(service, "import " + beanPath + "." + beanName + ";"); // 导包
        writeFileContent(service, lineSeparator); // 换行
        writeFileContent(service, "public interface " + beanName + "Service {");
        writeFileContent(service, lineSeparator); // 换行
        writeFileContent(service, "}");
    }

    private static void createDaoImpl(String beanName, String beanPath, String daoPath, File bean, File daoImpl) throws IOException
    {
        writeFileContent(daoImpl, "package " + daoPath + ";"); // package
        writeFileContent(daoImpl, lineSeparator); // 换行
        writeFileContent(daoImpl, "import " + beanPath + "." + beanName + ";"); // 导包
        writeFileContent(daoImpl, "import org.springframework.stereotype.Repository;"); // 导包
        writeFileContent(daoImpl, lineSeparator); // 换行
        writeFileContent(daoImpl, "@Repository");
        writeFileContent(daoImpl,
            "public class " + beanName + "DaoImpl extends AbstractBaseDaoImpl<" + beanName + ", String> implements " + beanName + "Dao {");
        writeFileContent(daoImpl, lineSeparator); // 换行
        writeFileContent(daoImpl, "}");
    }

    private static void createDao(String beanName, String beanPath, String daoPath, File bean, File dao) throws IOException
    {
        writeFileContent(dao, "package " + daoPath + ";"); // package
        writeFileContent(dao, lineSeparator); // 换行
        writeFileContent(dao, "import " + beanPath + "." + beanName + ";"); // 导包
        writeFileContent(dao, lineSeparator); // 换行
        writeFileContent(dao, "public interface " + beanName + "Dao extends BaseDao<" + beanName + ", String>{");
        writeFileContent(dao, lineSeparator); // 换行
        writeFileContent(dao, "}");
    }

    private static void createBean(String beanName, String beanPath, File bean) throws IOException
    {
        writeFileContent(bean, "package " + beanPath + ";"); // package
        writeFileContent(bean, lineSeparator); // 换行
        writeFileContent(bean, "import java.io.Serializable;"); // 导包
        writeFileContent(bean, "import javax.persistence.*;"); // 导包
        writeFileContent(bean, lineSeparator); // 换行
        writeFileContent(bean, "@Entity");
        writeFileContent(bean, "@Table(name = \"" + beanName.toLowerCase() + "\")");
        writeFileContent(bean, "public class " + beanName + " implements Serializable {");
        writeFileContent(bean, lineSeparator); // 换行
        writeFileContent(bean, "}");
    }

    private static String replaceStr(String str)
    {
        return str.replace(".", "\\");
    }

    private static boolean writeFileContent(File file, String newstr) throws IOException
    {
        Boolean bool = false;
        String filein = newstr.equals(lineSeparator) ? newstr : newstr + lineSeparator; // 如果内容是换行则只要一个换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try
        {
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            // 文件原有内容
            while ((temp = br.readLine()) != null)
            {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 不要忘记关闭
            if (pw != null)
            {
                pw.close();
            }
            if (fos != null)
            {
                fos.close();
            }
            if (br != null)
            {
                br.close();
            }
            if (isr != null)
            {
                isr.close();
            }
            if (fis != null)
            {
                fis.close();
            }
        }
        return bool;
    }

    // 如果文件存在,则删除之,再创建一个新的空文件,如果不存在,则直接创建新文件
    private static File newFile(File existFile)
    {
        try
        {
            if (existFile.exists())
            {
                existFile.delete();
            }
            existFile.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return existFile;
    }

//    public static void main(String[] args)
//    {
//        createBean("Trade", "com.example.demo.entity", "com.example.demo.dao", "com.example.demo.service");
//    }
}
