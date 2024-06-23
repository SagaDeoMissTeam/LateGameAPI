package git.lategame.impl.serializer.data.basic;


import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class BoolData implements IData {

    public boolean v;

    public BoolData(){

    }
    public BoolData(boolean v){
        this.v = v;
    }

    @Override
    public byte getID() {
        return DATA_BOOLEAN;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeBoolean(v);
    }

    @Override
    public void read(DataInput input) throws IOException {
        this.v = input.readBoolean();
    }

    @Override
    public String toString() {
        return "BoolData{" +
                "v=" + v +
                '}';
    }
}
