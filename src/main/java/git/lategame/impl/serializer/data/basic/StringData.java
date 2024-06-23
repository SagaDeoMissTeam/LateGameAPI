package git.lategame.impl.serializer.data.basic;

import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringData implements IData {

    public String value;

    public StringData(String value){
        this.value = value;
    }

    @Override
    public byte getID() {
        return DATA_STRING;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeUTF(value);
    }

    @Override
    public void read(DataInput input) throws IOException {
        value = input.readUTF();
    }

    @Override
    public String toString() {
        return "StringData{" +
                "value=\"" + value + '\"' +
                '}';
    }
}
