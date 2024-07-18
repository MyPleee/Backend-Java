package ple.db;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectQueryCondition {
    String tableName;
    
    private ArrayList<HashMap<String, String>> whereConditions;
    private ArrayList<String> whereOperators;
    private boolean hasWhereClause;
    
    private ArrayList<HashMap<String, String>> orderByConditions;
    private boolean hasOrderByClause;

    private ArrayList<String> inClauseColumns;
    private ArrayList<ArrayList<String>> inClauseValues;
    private ArrayList<String> inClauseOperators;
    private boolean hasInClause;

    private ArrayList<HashMap<String, String>> likeConditions;
    private ArrayList<String> likeOperators;
    private boolean hasLikeClause;

    private SelectQueryCondition() {}

    public SelectQueryCondition(String tableName) {
    	this.tableName = "SELECT * FROM " + tableName + " ";
    	
        this.whereConditions = new ArrayList<>();
        this.whereOperators = new ArrayList<>();
        
        this.orderByConditions = new ArrayList<>();
        
        this.inClauseColumns = new ArrayList<>();
        this.inClauseValues = new ArrayList<>();
        this.inClauseOperators = new ArrayList<>();
        
        this.likeConditions = new ArrayList<>();
        this.likeOperators = new ArrayList<>();
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

    /**
     * ORDER BY 절 컬럼 및 정렬 방식 추가
     * 
     * @param column
     * @param order 방식 (ASC/DESC)
     */
    public void setOrderBy(String column, String order) {
        HashMap<String, String> orderByCondition = new HashMap<>();
        orderByCondition.put(column, order);
        this.orderByConditions.add(orderByCondition);
        this.hasOrderByClause = true;
    }

    /**
     * IN 절 연산자와 컬럼, 값 설정
     * 
     * @param operator
     * @param inClauseColumn
     * @param inValues
     */
    public void setInClauseCondition(String operator, String inClauseColumn, ArrayList<String> inValues) {
        this.inClauseOperators.add(operator);
        this.inClauseColumns.add(inClauseColumn);
        this.inClauseValues.add(inValues);

        this.hasInClause = true;
    }



    /**
     * LIKE 절 컬럼과 값 추가
     * 
     * @param operator 논리 연산자 (AND/OR)
     * @param column
     * @param value
     */
    public void addLikeCondition(String operator, String column, String value) {
        HashMap<String, String> likeCondition = new HashMap<>();
        likeCondition.put(column, value);
        this.likeConditions.add(likeCondition);
        this.likeOperators.add(operator);
        
        this.hasLikeClause = true;
    }
    
    private void appendWhereConditions(StringBuilder query, ArrayList<HashMap<String, String>> conditions, ArrayList<String> operators) {
        for (int i = 0; i < conditions.size(); i++) {
        	
            for (String column : conditions.get(i).keySet()) {
            
            	if (i < conditions.size()) {
            		if (!operators.get(i).equals("")) {
            			query.append(" ").append(operators.get(i)).append(" ");
            		}
                    
                }
            	query.append(column)
            			.append(" = ")
            			.append("'")
            			.append(conditions.get(i).get(column))
            			.append("'");
                

            
            }
        }
    }

    private void appendLikeConditions(StringBuilder query, ArrayList<HashMap<String, String>> conditions, ArrayList<String> operators) {
        for (int i = 0; i < conditions.size(); i++) {
        	
            for (String column : conditions.get(i).keySet()) {
            
            	if (i < conditions.size()) {
            		if (!operators.get(i).equals("")) {
            			query.append(" ").append(operators.get(i)).append(" ");
            		}
                    
                }
            	query.append(column)
            			.append(" LIKE ")
            			.append("'")
            			.append(conditions.get(i).get(column))
            			.append("'");
            
            }
        }
    }
    
    /*
    private String inClauseColumn;
    private ArrayList<ArrayList<String>> inClauseValues;
    private ArrayList<String> inClauseOperators;
     */
    private void appendInConditions(StringBuilder query, ArrayList<String> column, ArrayList<ArrayList<String>> values, ArrayList<String> operators) {
    	
    	for (int i = 0; i <values.size(); i++) {
    		
    		if (!operators.get(i).equals("")) {
    			query.append(" ").append(operators.get(i)).append(" ");
    		}
            query.append("").append(column.get(i)).append(" IN (");
           
            ArrayList<String> inClauseValue = values.get(i);
            
            for (int j = 0; j < inClauseValue.size(); j++) {
                
            	query.append("'").append(inClauseValue.get(j)).append("'");
            	if (j < inClauseValue.size() - 1) {
                    query.append(", ");
                }
            	
            }
            query.append(")");
    	}
    }
    
    public String getQuery() {
    	StringBuilder query = new StringBuilder(this.tableName);

        if (hasWhereClause) {
            query.append("WHERE ");
            this.appendWhereConditions(query, whereConditions, whereOperators);
        }

        if (hasInClause) {
            if (!hasWhereClause) {
            	query.append("WHERE ");
            } 
        	this.appendInConditions(query, inClauseColumns, inClauseValues, inClauseOperators);
        }

        if (hasLikeClause) {
            if (!hasWhereClause && !hasInClause) {
            	query.append("WHERE ");
            } 
            this.appendLikeConditions(query, likeConditions, likeOperators);
        }

        if (hasOrderByClause) {
            query.append(" ORDER BY ");
            
            for (int i = 0; i < orderByConditions.size(); i++) {
                for (String column : orderByConditions.get(i).keySet()) {
                    query.append(column).append(" ").append(orderByConditions.get(i).get(column));
                }
                if (i < orderByConditions.size() - 1) {
                    query.append(", ");
                }
            }
            
        }

        query.append(";");
        
        return query.toString();
    }

    @Override
    public String toString() {
    	StringBuilder query = new StringBuilder(this.tableName);

        if (hasWhereClause) {
            query.append("\nWHERE ");
            this.appendWhereConditions(query, whereConditions, whereOperators);
        }

        if (hasInClause) {
            if (!hasWhereClause) {
            	query.append("\nWHERE ");
            } else if (hasWhereClause) {
            	query.append("\n");
            } 
            this.appendInConditions(query, inClauseColumns, inClauseValues, inClauseOperators);
        }

        if (hasLikeClause) {
        	
            if (!hasWhereClause && !hasInClause) {
            	query.append("\nWHERE ");
            } else if (hasInClause) {
            	query.append("\n");
            }
        	
            this.appendLikeConditions(query, likeConditions, likeOperators);
        }

        if (hasOrderByClause) {
            query.append("\nORDER BY ");
            for (int i = 0; i < orderByConditions.size(); i++) {
                for (String column : orderByConditions.get(i).keySet()) {
                    query.append(column).append(" ").append(orderByConditions.get(i).get(column));
                }
                if (i < orderByConditions.size() - 1) {
                    query.append(", ");
                }
            }
            
        }

        query.append(";");
        
        return query.toString();
    }

    /*
    public static void main(String[] args) {
    	SelectQueryCondition qc = new SelectQueryCondition("TableName ");
        
        // WHERE 조건 추가
        //qc.addWhereCondition("", "name", "John Doe");
        //qc.addWhereCondition("OR", "age", "30" );
        qc.addWhereCondition("", "age", "30" );

        // ORDER BY 조건 추가
        qc.setOrderBy("created_at", "ASC");
        //qc.setOrderBy("username", "DESC");

        // IN 조건 추가
        
        ArrayList<String> intValues1 = new ArrayList<>();
        intValues1.add("qewr");
        intValues1.add("qwer");
        qc.setInClauseCondition("AND", "111", intValues1);
        
        ArrayList<String> intValues2 = new ArrayList<>();
        intValues2.add("active");
        intValues2.add("pending");
        qc.setInClauseCondition("AND", "22", intValues2);


        // LIKE 조건 추가
        qc.addLikeCondition("AND", "description", "%example%");
        qc.addLikeCondition("AND", "note", "%test%");

        // 최종 쿼리 출력
        System.out.println(qc.toString());
        System.out.println(qc.getQuery());
    }
    */
}
