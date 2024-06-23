package git.lategame.impl.config;

import git.lategame.impl.serializer.SDMSerializer;
import git.lategame.impl.serializer.data.IData;
import git.lategame.impl.serializer.data.KeyData;
import git.lategame.impl.serializer.data.ListData;

import java.util.ArrayList;
import java.util.List;

public class ConfigFile implements SDMSerializer<KeyData> {


    public List<String> ping_users = new ArrayList<>();
    public String message = "";
    public long duDate = 0;

    public ConfigFile(){}

    public ConfigFile addPingUser(String user){
        this.ping_users.add(user);
        return this;
    }

    @Override
    public KeyData serialize() {
        KeyData data = new KeyData();
        ListData<IData> USERS = new ListData<>();

        for (String pingUser : ping_users) {
            USERS.addValue(IData.valueOf(pingUser));
        }

        data.put("ping_users", USERS);

        if(!message.isEmpty())
            data.put("message", message);


        return data;
    }

    @Override
    public void deserialize(KeyData data) {
        if(data.contains("message")){
            this.message = data.getData("message").asString();
        }

        if(data.contains("ping_users")) {
            ListData<IData> USERS = data.getData("ping_users").asList();
            ping_users.clear();
            for (IData datum : USERS.data) {
                ping_users.add(datum.asString());
            }
        }
    }

    @Override
    public String toString() {
        return "ConfigFile{" +
                "ping_users=" + ping_users +
                ", message='" + message + '\'' +
                '}';
    }
}
