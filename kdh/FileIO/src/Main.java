import java.io.*;

public class Main {
    final static String rootPath = ".\\INPUT";

    public static void main(String[] args) {
        //String dir = ".\\INPUT";
        fileCheck(rootPath);
        System.out.println("Hello world!");
    }

    public static void fileCheck(String path){

        File file = new File(path);
        //System.out.println(file.getAbsolutePath() + " " + file.length());

        for (File f: file.listFiles())
        {
            if(f.isDirectory()){
                fileCheck(f.getPath());

            }else{
                System.out.println(f.getPath() + " " + f.length());
                if(f.length() > 1024 * 4){
                    String partPath = path.substring(rootPath.length());
                    copyFile(partPath, f.getName());
                }
            }
        }

    }

    public static void copyFile(String partPath, String fileName){

        final int BUFFER_SIZE = 1024;

        try {
            File dir = new File(".\\OUTPUT" + partPath);

            if(!dir.exists()){
                dir.mkdirs();
            }

            InputStream inputStream = new FileInputStream(rootPath + partPath +"\\"+ fileName);
            OutputStream outputStream = new FileOutputStream(".\\OUTPUT" + partPath+"\\"+ fileName);

            byte[] buffer = new byte[BUFFER_SIZE];
            int readLen;

            while ((readLen = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, readLen);
            }
            inputStream.close();
            outputStream.close();

        }catch (IOException ex){

        }

    }
}