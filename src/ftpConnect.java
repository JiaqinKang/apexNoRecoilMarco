

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.net.MalformedURLException;

/**
 * FTP工具类 基于Apache commons-net-3.6.jar 实现
 *
 * @author PC
 */
public class ftpConnect {

    // ftp服务器地址
    public String hostname = "";

    // ftp服务器端口号默认为21
    public Integer port = 21;

    // ftp登录账号
    public String username = "";

    // ftp登录密码fileName
    public String password = "";

    public FTPClient ftpClient = null;

    /**
     * 初始化ftp服务器
     */

    ftpConnect(String hostname, Integer port, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }




    /**
     * 连接FTP服务器
     * 默认连接成功
     */
    public boolean loginFtp() {
        boolean loginResult = true;
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            System.out.println("连接ftp服务器:" + this.hostname + ":" + this.port);

            // 连接登录FTP服务器
            ftpClient.connect(hostname, port);
            loginResult = ftpClient.login(username, password);
            if (!loginResult) {
                System.out.println("connect failed:" + this.hostname + ":" + this.port);
                return loginResult;
            }

            System.out.println("connect successfu:" + this.hostname + ":" + this.port);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginResult;
    }

    /**
     * 上传文件
     *
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return 成功与否
     */
    public boolean uploadFile(String pathname, String fileName, String originfilename) {
        boolean flag = false;
        InputStream inputStream = null;
        try {
            loginFtp();

            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            mkdir(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 上传文件
     *
     * @param pathname ftp服务保存地址
     * @param fileName 上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public boolean uploadFile(String pathname, String fileName,
                              InputStream inputStream) {
        boolean flag = false;
        try {
            System.out.println("开始上传文件");
            loginFtp();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            mkdir(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        } catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 改变工作路径
     * @param directory 工作路径
     * @return
     */
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");

            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    /**
     * 创建目录
     * 如果FTP服务器已存在该目录，则不创建；否则，创建该目录
     * @param remoteDir 远程目录
     * @return 成功与否
     * @throws IOException
     */
    public boolean mkdir(String remoteDir) throws IOException {
        boolean success = true;
        String directory = remoteDir + "/";

        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory =
                        new String(remoteDir.substring(start, end).getBytes("GBK"), "ISO-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    /**
     * 判断ftp服务器文件是否存在
     * @param path
     * @return
     * @throws IOException
     */
    public boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建文件目录
     * @param dir 待创建目录
     * @return 成功与否
     */
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 下载文件
     * @param pathname FTP服务器文件目录
     * @param filename 文件名称
     * @param localpath 下载后的文件路径
     * @return
     */
    public boolean downloadFile(String pathname, String filename, String localpath) {
        boolean flag = false;
        boolean fileExist = false;
        OutputStream os = null;
        try {
            // 连接FTP服务
            loginFtp();

            System.out.println("开始下载文件");

            // 默认FTP登录到FTP根目录下，切换目录到待下载文件目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {

                // 查找待下载文件
                if (filename.equalsIgnoreCase(file.getName())) {
                    fileExist = true;
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);

                    // 从远程服务将文件写回到本地流
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();

            if (fileExist) {
                flag = true;
                System.out.println("下载文件成功");
            } else {
                System.out.println("下载目录未找到该文件，文件名" + pathname + "/" + filename);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 删除文件
     * @param pathname FTP服务器保存目录
     * @param filename 要删除的文件名称
     * @return 成功与否
     */
    public boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        try {

            loginFtp();

            System.out.println("开始删除文件");

            // 切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            System.out.println("删除文件成功");
        } catch (Exception e) {
            System.out.println("删除文件失败");
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

}
