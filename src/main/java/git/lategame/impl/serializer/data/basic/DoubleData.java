package git.lategame.impl.serializer.data.basic;

import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleData implements IData {

    public double d;

    public DoubleData(){

    }

    public DoubleData(double d){
        this.d = d;
    }

    @Override
    public byte getID() {
        return DATA_DOUBLE;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeDouble(d);
    }

    @Override
    public void read(DataInput input) throws IOException {
        this.d = input.readDouble();
    }

    @Override
    public String toString() {
        return "DoubleData{" +
                "d=" + d +
                '}';
    }
}
