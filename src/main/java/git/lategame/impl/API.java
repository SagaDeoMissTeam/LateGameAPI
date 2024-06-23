package git.lategame.impl;

import git.lategame.Main;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class API {

    public static void test(){
        GitLabApi gitLabApi = new GitLabApi("https://git.lategame.net/", Main.projectToken.accessToken);


        try {
            Project project = gitLabApi.getProjectApi().getProject(Main.projectToken.projectID);

            String projectName = project.getName();



//            gitLabApi.getIssuesApi().createIssue(Main.projectToken.projectID, issue.getTitle(), issue.getDescription());

            List<Pipeline> pipelines = gitLabApi.getPipelineApi().getPipelines(Main.projectToken.projectID);
            Pipeline pipeline = pipelines.get(0);


            StringBuilder pingedUsers = new StringBuilder();

            for (int i = 0; i < Main.configFile.ping_users.size(); i++) {
                String pingUser = Main.configFile.ping_users.get(i);

                if(!pingUser.startsWith("@")) pingUser = "@" + pingUser;
                pingedUsers.append(pingUser).append(" ");

            }



            String message = Main.configFile.message;

            byte[] utf8 = message.getBytes(StandardCharsets.UTF_8);

            message = new String(utf8, StandardCharsets.UTF_8);

            Issue issue = gitLabApi.getIssuesApi().createIssue(Main.projectToken.projectID,
                    "Тестовый билд " + pipeline.getWebUrl(),
                    message + "\n" + pingedUsers.toString());


            if(Main.configFile.duDate != 0) {
                // Получение текущей даты
                Date today = new Date();

                // Создание календаря для работы с датами
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);
                calendar.add(Calendar.DAY_OF_MONTH, (int) Main.configFile.duDate);

                gitLabApi.getIssuesApi().getIssue(Main.projectToken.projectID, issue.getId()).setDueDate(calendar.getTime());

            }



        } catch (GitLabApiException e) {
            e.printStackTrace();
        }

    }
}
