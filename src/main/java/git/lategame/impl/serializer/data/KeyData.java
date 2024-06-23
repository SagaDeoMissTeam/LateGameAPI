package git.lategame.impl.serializer.data;

import git.lategame.impl.serializer.data.basic.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KeyData implements IData{

    public Map<String, IData> DATA = new HashMap<>();
    public KeyData(){}

    public boolean isEmpty(){
        return DATA.isEmpty();
    }

    public KeyData put(String key, IData data){
        this.DATA.put(key,data);
        return this;
    }
    public KeyData put(String key, String data){
        this.DATA.put(key,new StringData(data));
        return this;
    }

    public KeyData put(String key, short data){
        this.DATA.put(key,new ShortData(data));
        return this;
    }
    public KeyData put(String key, int data){
        this.DATA.put(key,new IntData(data));
        return this;
    }
    public KeyData put(String key, float data){
        this.DATA.put(key,new FloatData(data));
        return this;
    }
    public KeyData put(String key, double data){
        this.DATA.put(key,new DoubleData(data));
        return this;
    }
    public KeyData put(String key, long data){
        this.DATA.put(key,new LongData(data));
        return this;
    }
    public KeyData put(String key, char data){
        this.DATA.put(key,new CharData(data));
        return this;
    }
    public KeyData put(String key, boolean data){
        this.DATA.put(key,new BoolData(data));
        return this;
    }
    public KeyData put(String key, byte data){
        this.DATA.put(key,new ByteData(data));
        return this;
    }

    public IData getData(String key){
        return DATA.get(key);
    }

    public boolean contains(String key){
        return DATA.containsKey(key);
    }

    @Override
    public byte getID() {
        return DATA_KEY;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeInt(DATA.size());
        for (Map.Entry<String, IData> kvEntry : DATA.entrySet()) {
            dataOutput.writeUTF(kvEntry.getKey());
            kvEntry.getValue().write(dataOutput);
        }
    }

    @Override
    public void read(DataInput input) throws IOException {
        int size = input.readInt();
        Map<String, IData> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            String key = input.readUTF();
            IData value = IData.createByByte(input.readByte());
            if(value != null) {
                value.read(input);
                map.put(key, value);
            }
        }

        DATA = map;
    }

    @Override
    public String toString() {
        return "KeyData{" +
                "DATA=" + DATA +
                '}';
    }
}
