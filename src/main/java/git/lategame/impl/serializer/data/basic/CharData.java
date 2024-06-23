package git.lategame.impl.serializer.data.basic;

import git.lategame.impl.serializer.data.IData;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CharData implements IData {

    public char c;
    public CharData(){

    }
    public CharData(char c){
        this.c = c;
    }

    @Override
    public byte getID() {
        return DATA_CHAR;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getID());
        dataOutput.writeChar(c);
    }

    @Override
    public void read(DataInput input) throws IOException {
        this.c = input.readChar();
    }

    @Override
    public String toString() {
        return "CharData{" +
                "c=" + c +
                '}';
    }
}
