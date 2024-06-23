package git.lategame.impl.serializer.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapData<K extends IData, V extends IData> implements IData{

    public Map<K,V> dataMap = new HashMap<>();

    public MapData(){}

    public MapData(Map<K,V> dataMap){
        this.dataMap = dataMap;
    }

    public MapData<K,V> putValue(K key, V value){
        this.dataMap.put(key,value);
        return this;
    }

    public V get(K key){
        return dataMap.get(key);
    }

    @Override
    public byte getID() {
        return DATA_MAP;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException{
        dataOutput.writeByte(getID());
        dataOutput.writeInt(dataMap.size());
        for (Map.Entry<K, V> kvEntry : dataMap.entrySet()) {
            kvEntry.getKey().write(dataOutput);
            kvEntry.getValue().write(dataOutput);
        }
    }

    @Override
    public void read(DataInput input) throws IOException {
        int size = input.readInt();
        Map<K, V> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            IData key = IData.createByByte(input.readByte());
            if(key != null) {
                key.read(input);
                IData value = IData.createByByte(input.readByte());
                if(value != null) {
                    value.read(input);
                    map.put((K) key, (V) value);
                }
            }
        }

        dataMap = map;
    }

    @Override
    public String toString() {
        return "MapData{" +
                "dataMap=" + dataMap +
                '}';
    }
}
