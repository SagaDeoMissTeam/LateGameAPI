package git.lategame.impl.serializer.data.basic;

import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntData implements IData {

    public int value;

    public IntData(){}

    public IntData(int value){
        this.value = value;
    }

    @Override
    public byte getID() {
        return IData.DATA_INT;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeInt(value);
    }

    @Override
    public void read(DataInput input) throws IOException {
        value = input.readInt();
    }

    @Override
    public String toString() {
        return "IntData{" +
                "value=" + value +
                '}';
    }
}
