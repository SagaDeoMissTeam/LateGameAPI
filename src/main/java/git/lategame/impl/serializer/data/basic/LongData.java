package git.lategame.impl.serializer.data.basic;

import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongData implements IData {

    public long l;

    public LongData(){}
    public LongData(long l){
        this.l = l;
    }

    @Override
    public byte getID() {
        return DATA_LONG;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeLong(l);
    }

    @Override
    public void read(DataInput input) throws IOException {
        l = input.readLong();
    }

    @Override
    public String toString() {
        return "LongData{" +
                "l=" + l +
                '}';
    }
}
