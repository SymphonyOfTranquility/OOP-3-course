package tableElements;

public class JudgeAssistant extends Person {
    private final Integer judgeId;

    public JudgeAssistant(Integer id, String name, String surname, String login, Integer judgeId) {
        super(id, name, surname, login);
        this.judgeId = judgeId;
    }

    public Integer getJudgeId() { return judgeId; }

    public String toTable() {
        return  "<th>" + getId() + "</th>" +
                "<th>" + getName() + "</th>" +
                "<th>" + getSurname() + "</th>" +
                "<th>" + getLogin() + "</th>";
    }
}
