package git.lategame;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import git.lategame.impl.API;
import git.lategame.impl.config.ConfigFile;
import git.lategame.impl.serializer.DataIO;
import git.lategame.impl.serializer.JsonTest;
import git.lategame.impl.tokens.ProjectToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static ProjectToken projectToken = null;
    public static ConfigFile configFile = new ConfigFile();

    public static void main(String[] args) {

        Paths.init();

        int projectID = -1;

        if(args.length > 0){

            for (int i = 0; i < args.length; i++) {
                if(args[i].startsWith("!") && projectToken == null){
                    projectToken = new ProjectToken(args[i].substring(1));
                } else {
                    try {
                        projectID = Integer.parseInt(args[i]);
                        break;
                    } catch (Exception e){
                        System.out.println("Ошибка при чтении ID проекта");
                        e.printStackTrace();
                    }
                }
            }

        }

        if(projectID == -1) {
            System.out.println("ID проекта не получен");
            return;
        }

        if(projectToken == null) {
            System.out.println("Invalid token. Double-check and try again. (Postscript. The token must be specified with the hashtag (#someToken))");
            System.out.println("-------------------------------------------------------");
            System.out.println("Некорректный токен. Перепроверьте и попробуйте ещё раз. (Поскриптум. Токен должен указываться с хештегом (#someToken))");
        } else {

            projectToken.setProjectID(projectID);

            API.test();

        }

    }

    public static class Paths {
        public static List<String> PATHS = new ArrayList<>();

        public static void register(String path){
            PATHS.add(path);
        }
        public static void init(){
            register("LageGitAPI.json");


            for (String path : PATHS) {
                File file = new File(path);
                if(!file.exists()) {
                    try {
                        file.createNewFile();

                        ConfigFile file1 = new ConfigFile();
                        file1.message = "Some Text";
                        file1.addPingUser("@someUser");
                        file1.addPingUser("@someUser2");
                        file1.addPingUser("@someUser3");

                        Main.configFile = file1;

                        String json = new ObjectMapper().writeValueAsString(file1);

                        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                        writer.write(json);
                        writer.close();

                    } catch (IOException e) {
                        System.out.println("Ошибка доступа к IO системе. Не удалось создать файл !");
                        e.printStackTrace();
                    }
                } else {

                    try {

                        StringBuilder builder = new StringBuilder();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

                        reader.lines().forEach(builder::append);

                        reader.close();

                        Main.configFile = new ObjectMapper().readValue(builder.toString(), ConfigFile.class);

                    } catch (FileNotFoundException | JsonProcessingException e) {
                        System.out.println("Ошибка доступа к IO системе. Не удалось создать файл !");
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

        }

    }

}