package git.lategame.impl.serializer.data.basic;

import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortData implements IData {
    public short aShort;

    public ShortData(){

    }
    public ShortData(short aShort){
        this.aShort = aShort;
    }

    @Override
    public byte getID() {
        return DATA_SHORT;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeShort(aShort);
    }

    @Override
    public void read(DataInput input) throws IOException {
        this.aShort = input.readShort();
    }

    @Override
    public String toString() {
        return "ShortData{" +
                "aShort=" + aShort +
                '}';
    }
}
