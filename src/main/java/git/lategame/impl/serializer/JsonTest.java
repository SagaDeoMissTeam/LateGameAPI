package git.lategame.impl.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import git.lategame.impl.config.ConfigFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class JsonTest {

    public static void test(){

        ConfigFile file = new ConfigFile();
        file.message = "Some Text";
        file.addPingUser("@someUser");
        file.addPingUser("@someUser2");
        file.addPingUser("@someUser3");

        try {
            String json = new ObjectMapper().writeValueAsString(file);

            BufferedWriter writer = new BufferedWriter(new FileWriter("test.json"));
            writer.write(json);
            writer.close();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
