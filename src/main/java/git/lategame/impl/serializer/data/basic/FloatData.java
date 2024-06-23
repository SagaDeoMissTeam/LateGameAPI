package git.lategame.impl.serializer.data.basic;

import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatData implements IData {

    public float f;

    public FloatData(){

    }
    public FloatData(float f){
        this.f = f;
    }

    @Override
    public byte getID() {
        return DATA_FLOAT;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeFloat(f);
    }

    @Override
    public void read(DataInput input) throws IOException {
        this.f = input.readFloat();
    }

    @Override
    public String toString() {
        return "FloatData{" +
                "f=" + f +
                '}';
    }
}
