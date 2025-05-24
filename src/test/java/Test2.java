import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test2 {

    public static void main(String args[]) {
        copyFolder("f:/a", "f:/b");

    }
        public static void copyFolder(String oldPath, String newPath) {
            try {
                // 如果文件夹不存在，则建立新文件夹
                (new File(newPath)).mkdirs();
                //读取整个文件夹的内容到file字符串数组，下面设置一个游标i，不停地向下移开始读这个数组
                File filelist = new File(oldPath);
                String[] file = filelist.list();
                //要注意，这个temp仅仅是一个临时文件指针
                //整个程序并没有创建临时文件
                File temp = null;
                for (int i = 0; i < file.length; i++) {
                    //如果oldPath以路径分隔符/或者\结尾，那么则oldPath/文件名就可以了
                    //否则要自己oldPath后面补个路径分隔符再加文件名
                    //谁知道你传递过来的参数是f:/a还是f:/a/啊？
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }

                    //如果游标遇到文件
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath
                                + "/" + "rename_" + (temp.getName()).toString());
                        byte[] bufferarray = new byte[1024 * 64];
                        int prereadlength;
                        while ((prereadlength = input.read(bufferarray)) != -1) {
                            output.write(bufferarray, 0, prereadlength);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    //如果游标遇到文件夹
                    if (temp.isDirectory()) {
                        copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                    }
                }
            } catch (Exception e) {
                System.out.println("复制整个文件夹内容操作出错");
            }
        }





}
