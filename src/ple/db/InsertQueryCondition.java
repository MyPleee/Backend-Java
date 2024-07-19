package ple.db;

import java.util.ArrayList;
import java.util.HashMap;

public class InsertQueryCondition {
    private String tableName;
    private ArrayList<HashMap<String, String>> insertConditions;

    public InsertQueryCondition() {
        this.insertConditions = new ArrayList<>();
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * INSERT 절 컬럼과 값 추가
     * 
     * @param column
     * @param value
     */
    public void addInsertValue(String column, String value) {
        HashMap<String, String> insertCondition = new HashMap<>();
        insertCondition.put(column, value);
        this.insertConditions.add(insertCondition);
    }
    
    public String getQuery() {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(this.tableName).append(" (");

        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> insertCondition : insertConditions) {
            for (String column : insertCondition.keySet()) {
                columns.add(column);
                values.add("'" + insertCondition.get(column) + "'");
            }
        }

        query.append(String.join(", ", columns));
        query.append(") VALUES (");
        query.append(String.join(", ", values));
        query.append(");");

        return query.toString();
    }

    @Override
    public String toString() {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(this.tableName).append(" (");

        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> insertCondition : insertConditions) {
            for (String column : insertCondition.keySet()) {
                columns.add(column);
                values.add("'" + insertCondition.get(column) + "'");
            }
        }

        query.append(String.join(", ", columns));
        query.append(")").append("\n");
        query.append("VALUES (");
        query.append(String.join(", ", values));
        query.append(");");

        return query.toString();
    }


    public static void main(String[] args) {
        InsertQueryCondition iqc = new InsertQueryCondition();
        iqc.setTableName("USERS");

        // INSERT 값 추가
        iqc.addInsertValue("name", "John Doe");
        iqc.addInsertValue("age", "30");
        iqc.addInsertValue("email", "john.doe@example.com");

        // 최종 쿼리 출력
        System.out.println(iqc.toString());
        System.out.println(iqc.getQuery());
    }
   
}
