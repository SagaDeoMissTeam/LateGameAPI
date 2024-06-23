package git.lategame.impl.serializer;


import git.lategame.impl.serializer.data.IData;
import java.io.*;
import java.nio.file.Paths;

public class DataIO {


    public static void write(IData data, String path){
        try {
            String resourcePath = Paths.get(path).toString();
            System.out.println(resourcePath);

            FileOutputStream outputStream = new FileOutputStream(resourcePath);
            DataOutput output = new DataOutputStream(outputStream);
            data.write(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(IData data, DataOutput output){
        try {
            data.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IData read(DataInput input){
        try {
            IData readListData = IData.createByByte(input.readByte());
            readListData.read(input);
            return readListData;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IData read(String path){

        try {
            String resourcePath = Paths.get(path).toString();
            FileInputStream stream = new FileInputStream(new File(resourcePath));

            DataInputStream input = new DataInputStream(stream);
            IData readListData = IData.createByByte(input.readByte());
            readListData.read(input);
            return readListData;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
