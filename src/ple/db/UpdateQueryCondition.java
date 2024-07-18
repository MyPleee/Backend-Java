package ple.db;


import java.util.ArrayList;
import java.util.HashMap;

public class UpdateQueryCondition {
    private String startQuery;
    
    private HashMap<String, String> setValues;
    
    private ArrayList<HashMap<String, String>> whereConditions;
    private ArrayList<String> whereOperators;
    private boolean hasWhereClause;

    public UpdateQueryCondition(String startQuery) {
        this.startQuery = startQuery;
        this.setValues = new HashMap<>();
        this.whereConditions = new ArrayList<>();
        this.whereOperators = new ArrayList<>();
        this.hasWhereClause = false;
    }

    /**
     * SET 절 값 설정
     * 
     * @param column
     * @param value
     */
    public void set(String column, String value) {
        this.setValues.put(column, value);
    }

    /**
     * WHERE 절 컬럼과 값 추가
     * 
     * @param operator 논리 연산자 (AND/OR)
     * @param column
     * @param value
     */
    public void addWhereCondition(String operator, String column, String value) {
        HashMap<String, String> whereCondition = new HashMap<>();
        whereCondition.put(column, value);
        this.whereConditions.add(whereCondition);
        this.whereOperators.add(operator);
        this.hasWhereClause = true;
    }

    private void appendSetValues(StringBuilder query) {
        query.append("SET ");
        int count = 0;
        for (String column : this.setValues.keySet()) {
            query.append(column).append(" = '").append(this.setValues.get(column)).append("'");
            if (count < this.setValues.size() - 1) {
                query.append(", ");
            }
            count++;
        }
    }

    private void appendWhereConditions(StringBuilder query) {
        query.append("WHERE ");
        for (int i = 0; i < this.whereConditions.size(); i++) {
            HashMap<String, String> condition = this.whereConditions.get(i);
            String operator = this.whereOperators.get(i);
            for (String column : condition.keySet()) {
                query.append(column).append(" = '").append(condition.get(column)).append("'");
                if (i < this.whereConditions.size() - 1) {
                    query.append(" ").append(operator).append(" ");
                }
            }
        }
    }

    public String getQuery() {
        StringBuilder query = new StringBuilder(this.startQuery);
        
        query.append(" ");
        this.appendSetValues(query);
        if (hasWhereClause) {
            query.append(" ");
            this.appendWhereConditions(query);
        }
        query.append(";");
        return query.toString();
    }

    @Override
    public String toString() {
        StringBuilder query = new StringBuilder();
        
        query.append(" ");
        this.appendSetValues(query);
        
        if (hasWhereClause) {
            query.append("\n");
            this.appendWhereConditions(query);
        }
        query.append(";");
        
        return query.toString();
    }

    public static void main(String[] args) {
        UpdateQueryCondition uqc = new UpdateQueryCondition("UPDATE TableName");
        
        // SET 절 추가
        uqc.set("column1", "value1");
        uqc.set("column2", "value2");

        // WHERE 절 추가
        uqc.addWhereCondition("AND", "id", "1");

        // 최종 쿼리 출력
        System.out.println(uqc.toString());
        System.out.println(uqc.getQuery());
    }
}
