//package sqlOperation;
//
//
//import java.rmi.RemoteException;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Vector;
//
//import javax.swing.table.DefaultTableModel;
//
//import useDataBaseService.GetMultiTableImpli;
//
//
//
//public class GetMultiTable implements GetMultiTableImpli{
//	LinkToMySQL linkToMySQL;
//	DefaultTableModel tableModel;
//	String databaseName;
//	
//	private Statement statement;   
//	private ResultSet resultSet;   
//	
//	public GetMultiTable() {
//		linkToMySQL=new LinkToMySQL();
//	}
//	
//	public static void main(String[] args) throws RemoteException{
//		GetMultiTable get = new GetMultiTable();
//		ArrayList<String> table = new ArrayList<String>();
//		ArrayList<String> column = new ArrayList<String>();
//		ArrayList<String> condition = new ArrayList<String>();
//		
//		DefaultTableModel t ;
//		
//		table.add("course");
//		table.add("choose");
//		
//		column.add("course.*");
//		
//		condition.add("course.courseId = choose.courseId");
//		
//		t = get.getTable(table, column, condition);
//		System.out.println(t.getValueAt(0, 0));
//	}
//	
//	//方法的用法
//	//无限制条件，condition即""，注意，多表查询至少有一个"t1.a = t2.a"的语句
//	//tablename就是表格名字，columns是你需要的栏位（栏位之间用逗号隔开），condition是筛选条件 , 多个筛选条件之间用" and "来连接
//	//比如从course表中找courseID和courseName，并且courseID是12 那么就getTble("course","courseID,courseName","courseID=12")
//	//多个表的情况下，使用“.”来指明哪个表的元素，如course.courseId , choose.stuId,如通过stuId返回课程时，
//	//完整语句为 selece course.courseName, course.courseId from course,choose where choose.stuId = '121250118' and choose.courseId = course.courseId
//	//对应的tableName的ArrayList为course 和choose , columns的List为course.courseId和course.courseName ,condition的List为choose.stuId = '121250118'和choose.courseId = course.courseId
//	//column要获得所有栏位，即如"course.*"
//	//尤其要注意的是 上面的12是int型所以直接就=12（判断相等一个=不是两个），如果是string的话那就要='12'(注意是单引号，在sql语句中单引号来表示string)
//	public DefaultTableModel getTable(ArrayList<String> tableNameList,ArrayList<String> columnList,ArrayList<String> conditionList) throws RemoteException {
//		String tableName = "";
//		String column =  "";
//		String condition = "";
//		for(int i =0 ; i<tableNameList.size()-1 ; i++){
//			tableName+=tableNameList.get(i)+",";
//		}
//		tableName+=tableNameList.get(tableNameList.size()-1);
//		
//		for(int i =0 ; i<columnList.size()-1 ; i++){
//			column+=columnList.get(i)+",";
//		}
//		column+=columnList.get(columnList.size()-1);
//		
//		for(int i =0 ; i<conditionList.size()-1 ; i++){
//			condition+=conditionList.get(i)+" and ";
//		}
//		if(conditionList.size() == 0)
//			condition+="";
//		else
//			condition+=conditionList.get(conditionList.size()-1);
//		
//		try {   
//			//执行SQL语句 
//			statement = linkToMySQL.connection.createStatement();   
//			if(conditionList.size() == 0)
//				resultSet = statement.executeQuery( "select "+column +" from "+tableName);   
//			else
//			resultSet = statement.executeQuery( "select "+column +" from "+tableName+"  where " +condition);   
//			//在表格中显示查询结果  
//			displayResultSet(resultSet);   
//			}   
//			catch ( SQLException sqlex ) {   
//			sqlex.printStackTrace();   
//			}   
//		
//		return tableModel;
//		
//	}
//	
//	
//	private void displayResultSet( ResultSet rs )   
//			throws SQLException,RemoteException   
//			{   
//			
//			Vector columnHeads = new Vector();   
//			Vector rows = new Vector();   
//			try {   
//			//获取字段的名称  
//			ResultSetMetaData rsmd = rs.getMetaData();   
//			for ( int i = 1; i <= rsmd.getColumnCount(); i++ )   
//			columnHeads.addElement( rsmd.getColumnName( i ) );   
//			//获取记录集  
//			while ( rs.next() ) {   
//			rows.addElement( getNextRow( rs, rsmd ) );   
//			System.out.println(getNextRow( rs, rsmd ));
//			} ;   
//			//在表格中显示查询结果  
//			 tableModel=new DefaultTableModel(rows,columnHeads);
//			//刷新Table  
//			}   
//			catch ( SQLException sqlex ) {   
//			sqlex.printStackTrace();   
//			}   
//			}   
//	
//
//            private Vector getNextRow( ResultSet rs,    ResultSetMetaData rsmd )   throws SQLException,RemoteException  {   
//                     Vector currentRow = new Vector();   
//                     for ( int i = 1; i <= rsmd.getColumnCount(); ++i )   
//                     currentRow.addElement( rs.getString( i ) );   
//                    //返回一条记录   
//                    return currentRow;   
//                  }   
//}
