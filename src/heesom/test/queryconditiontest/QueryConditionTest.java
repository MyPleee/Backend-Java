package heesom.test.queryconditiontest;

import java.util.ArrayList;

import ple.db.SelectQueryCondition;

public class QueryConditionTest {
	public static void main(String[] args) {
		
		QueryConditionTest.selectQueryConditionTest();
    
	}
	
	private static void selectQueryConditionTest() {
    	SelectQueryCondition qc = new SelectQueryCondition("TableName ");
        
        // WHERE 조건 추가
        qc.addWhereCondition("", "name", "John Doe");
        qc.addWhereCondition("OR", "age", "30" );
        qc.addWhereCondition("AND", "age", "30" );

        // ORDER BY 조건 추가
        qc.setOrderBy("created_at", "ASC");
        qc.setOrderBy("username", "DESC");

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

	
}
