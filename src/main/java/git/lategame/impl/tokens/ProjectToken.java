package git.lategame.impl.tokens;

public class ProjectToken implements IToken {

    public final String accessToken;
    public int projectID = -1;

    public ProjectToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public String getAccessToken() {
        return "";
    }
}
