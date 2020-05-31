package tableElements;

import java.util.Date;

public class Case {
    private final Integer id;
    private final String yearId;
    private final String plaintiff;
    private final String defendant;
    private final String textOfCase;
    private Integer courtId;
    private Integer judgeId;
    private Integer isCorrect;
    private String dateOfTrial;
    private String judgment;
    private Boolean closed;

    public Case(Integer id, String yearId, String plaintiff, String defendant, String textOfCase,
                Integer courtId, Integer judgeId, Integer isCorrect, String dateOfTrial, String judgment,
                Boolean closed) {
        this.id = id;
        this.yearId = yearId;
        this.plaintiff = plaintiff;
        this.defendant = defendant;
        this.textOfCase = textOfCase;
        this.courtId = courtId;
        this.judgeId = judgeId;
        this.isCorrect = isCorrect;
        this.dateOfTrial = dateOfTrial;
        this.judgment = judgment;
        this.closed = closed;
    }

    public Integer getId() { return id; }
    public String getYearId() { return yearId; }
    public String getPlaintiff() { return plaintiff; }
    public String getDefendant() { return defendant; }
    public String getTextOfCase() { return textOfCase; }
    public Integer getCourtId() { return courtId; }
    public Integer getJudgeId() { return judgeId; }
    public Integer getIsCorrect() { return isCorrect; }
    public String getDateOfTrial() { return dateOfTrial; }
    public String getJudgment() { return judgment; }
    public Boolean getClosed() { return closed; }

    public void setCourtId(Integer courtId) { this.courtId = courtId; }
    public void setJudgeId(Integer judgeId) { this.judgeId = judgeId; }
    public void setClosed(Boolean closed) { this.closed = closed; }
    public void setIsCorrect(Integer isCorrect) {this.isCorrect = isCorrect; }
    public void setDateOfTrial(String date) { this.dateOfTrial = date; }
    public void setJudgment(String judgment) { this.judgment = judgment; }

    public String getRegSql() {
        String sqlParameters = "INSERT INTO cases (",
        sqlValues = "VALUES (";
        boolean added = false;
        if (yearId != null) {
            sqlParameters += " year_id";
            sqlValues += " '" + yearId.toString() + "'";
            added = true;
        }
        if (plaintiff != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " plaintiff";
            sqlValues += " '" + plaintiff + "'";
            added = true;
        }

        if (defendant != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " defendant";
            sqlValues += " '" + defendant + "'";
            added = true;
        }

        if (textOfCase != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " text_of_case";
            sqlValues += " '" + textOfCase + "'";
            added = true;
        }

        if (courtId != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " court_id";
            sqlValues += " " + courtId;
            added = true;
        }

        if (judgeId != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " judge_id";
            sqlValues += " " + judgeId;
            added = true;
        }

        if (isCorrect != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " is_correct";
            sqlValues += " " + isCorrect;
            added = true;
        }

        if (dateOfTrial != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " date_of_trial";
            sqlValues += " '" + dateOfTrial + "'";
            added = true;
        }

        if (judgment != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " judgment";
            sqlValues += " '" + judgment + "'";
            added = true;
        }

        if (closed != null) {
            if (added) {
                sqlParameters += ", ";
                sqlValues += ", ";
            }
            sqlParameters += " closed";
            sqlValues += " '" + closed.toString() + "'";
            added = true;
        }

        sqlParameters += ")";
        sqlValues += ");";
        return sqlParameters + " " + sqlValues;
    }

    @Override
    public String toString() {
        return
                id.toString()+ " " +
                yearId + " "+
                plaintiff + " " +
                defendant + " " +
                textOfCase + " " +
                courtId.toString() + " " +
                judgeId.toString() + " " +
                isCorrect + " " +
                dateOfTrial + " " +
                judgment + " " +
                closed.toString();
    }

    public String toTable() {
        String ans = toTableAssistant();

        if (closed == null || !closed)
            ans += "<th> no </th>";
        else
            ans += "<th> yes </th>";
        return ans;
    }

    public String toTableAssistant() {
        String ans = "<th>" + id + "</th>" +
                "<th>" + yearId + "</th>" +
                "<th>" + plaintiff + "</th>" +
                "<th>" + defendant + "</th>" +
                "<th>" + textOfCase + "</th>";
        if (isCorrect == 0)
            ans += "<th> Haven't been checked </th>";
        else if (isCorrect == 1)
            ans += "<th> correct </th>";
        else
            ans += "<th> incorrect </th>";

        if (dateOfTrial == null)
            ans += "<th> not given </th>";
        else
            ans += "<th>" + dateOfTrial + "</th>";

        if (judgment == null)
            ans += "<th> not given </th>";
        else
            ans += "<th>" + judgment + "</th>";

        return ans;
    }
}
