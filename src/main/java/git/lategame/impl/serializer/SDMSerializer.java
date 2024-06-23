package git.lategame.impl.serializer;


import git.lategame.impl.serializer.data.IData;
public interface SDMSerializer<T extends IData> {

    T serialize();
    void deserialize(T data);
}
