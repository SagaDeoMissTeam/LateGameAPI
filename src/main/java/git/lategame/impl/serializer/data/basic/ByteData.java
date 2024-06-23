package git.lategame.impl.serializer.data.basic;


import git.lategame.impl.serializer.data.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteData implements IData {

    public byte b;

    public ByteData(){}

    public ByteData(byte b){
        this.b = b;
    }

    @Override
    public byte getID() {
        return DATA_BYTE;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeByte(b);
    }

    @Override
    public void read(DataInput input) throws IOException {
        b = input.readByte();
    }

    @Override
    public String toString() {
        return "ByteData{" +
                "b=" + b +
                '}';
    }
}
