package git.lategame.impl.serializer.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListData<T extends IData> implements IData{

    public List<T> data = new ArrayList<>();

    public ListData(){

    }
    public ListData(List<T> data){
        this.data = data;
    }

    public ListData<T> addValue(T v){
        data.add(v);
        return this;
    }

    @Override
    public byte getID() {
        return DATA_LIST;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeInt(data.size());
        for (T datum : data) {
            datum.write(dataOutput);
        }
    }

    @Override
    public void read(DataInput input) throws IOException {
        int size = input.readInt();
        List<T> tList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            IData v = IData.createByByte(input.readByte());
            if(v != null) {
                v.read(input);
                tList.add((T) v);
            }
        }
        this.data = tList;
    }

    @Override
    public String toString() {
        return "ListData{" +
                "data=" + data +
                '}';
    }
}
