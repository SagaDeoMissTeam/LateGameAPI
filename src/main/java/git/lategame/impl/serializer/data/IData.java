package git.lategame.impl.serializer.data;


import git.lategame.impl.serializer.data.basic.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface IData {

    byte DATA_STRING = 0;
    byte DATA_INT = 2;
    byte DATA_LONG = 4;
    byte DATA_BYTE = 5;
    byte DATA_CHAR = 6;
    byte DATA_DOUBLE = 7;
    byte DATA_FLOAT = 8;
    byte DATA_BOOLEAN = 9;
    byte DATA_LIST = 1;
    byte DATA_MAP = 3;
    byte DATA_KEY = 10;
    byte DATA_SHORT = 11;


    byte getID();

    void write(DataOutput dataOutput) throws IOException;
    void read(DataInput input) throws IOException;


    default ListData<IData> asList(){
        return (ListData<IData>) this;
    }
    default MapData<IData,IData> asMap(){
        return (MapData<IData, IData>) this;
    }
    default KeyData asKeyMap(){
        return (KeyData) this;
    }

    default String asString(){
        return ((StringData)this).value;
    }
    default long asLong(){
        return ((LongData)this).l;
    }
    default int asInt(){
        return ((IntData)this).value;
    }
    default byte asByte(){
        return ((ByteData)this).b;
    }
    default char asChar(){
        return ((CharData)this).c;
    }
    default double asDouble(){
        return ((DoubleData)this).d;
    }
    default float asFloat(){
        return ((FloatData)this).f;
    }
    default boolean asBool(){
        return ((BoolData)this).v;
    }
    default short asShort(){
        return ((ShortData)this).aShort;
    }

    static IData valueOf(String v){
        return new StringData(v);
    }
    static IData valueOf(long v){
        return new LongData(v);
    }
    static IData valueOf(int v){
        return new IntData(v);
    }
    static IData valueOf(byte v){
        return new ByteData(v);
    }
    static IData valueOf(char v){
        return new CharData(v);
    }
    static IData valueOf(double v){
        return new DoubleData(v);
    }
    static IData valueOf(float v){
        return new FloatData(v);
    }
    static IData valueOf(short v){
        return new ShortData(v);
    }
    static IData valueOf(boolean v){
        return new BoolData(v);
    }

    static IData createByByte(byte b){
        if(b == DATA_STRING){
            return new StringData("");
        } else if(b == DATA_INT){
            return new IntData();
        } else if(b == DATA_LONG){
            return new LongData();
        } else if(b == DATA_BYTE){
            return new ByteData();
        } else if(b == DATA_CHAR){
            return new CharData();
        } else if(b == DATA_DOUBLE){
            return new DoubleData();
        } else if(b == DATA_FLOAT){
            return new FloatData();
        } else if(b == DATA_BOOLEAN){
            return new BoolData();
        } else if(b == DATA_LIST){
            return new ListData<>();
        } else if(b == DATA_MAP){
            return new MapData<>();
        } else if(b == DATA_KEY){
            return new KeyData();
        }
        return null;
    }
}
