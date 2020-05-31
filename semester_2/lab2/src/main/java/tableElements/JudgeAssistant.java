package tableElements;

public class JudgeAssistant extends Person {
    private final int judgeId;

    JudgeAssistant(int id, String name, String surname, int judgeId) {
        super(id, name, surname);
        this.judgeId = judgeId;
    }

    public int getJudgeId() { return judgeId; }
}
