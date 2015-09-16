package sqlOperation;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.table.DefaultTableModel;

import sqlOperation.Condition.DatabaseCondition;
import sqlOperation.Condition.DatabaseRowInfo;
import sqlOperation.Condition.UpdateCondition;

public class SQLHelper implements SQLHelperImpli {
	LinkToMySQL linkToMySQL;
	String databaseName;
	private Statement statement;

	public SQLHelper() {
		linkToMySQL = new LinkToMySQL();
	}

	@Override
	public void deleteInfo(String tableName, DatabaseCondition condition)
			throws RemoteException {
		try {
			// 执行SQL语句
			statement = linkToMySQL.connection.createStatement();
			statement.executeUpdate("delete from " + tableName + "  where "
					+ condition.getCondition());
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	@Override
	public DefaultTableModel getTable(String tableName, String columns,
			DatabaseCondition condition) throws RemoteException {

		ResultSet resultSet;

		DefaultTableModel tableModel = new DefaultTableModel();

		try {
			// 执行SQL语句
			statement = linkToMySQL.connection.createStatement();
			if (condition.getCondition().equals(""))
				resultSet = statement.executeQuery("select " + columns
						+ " from " + tableName);
			else{
				resultSet = statement.executeQuery("select " + columns
						+ " from " + tableName + "  where " + condition.getCondition());
			}

			Vector columnHeads = new Vector();
			Vector rows = new Vector();
			// 获取字段的名称
			ResultSetMetaData rsmd = resultSet.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++)
				columnHeads.addElement(rsmd.getColumnName(i));
			// 获取记录集
			while (resultSet.next()) {
				rows.addElement(getNextRow(resultSet, rsmd));
			}
			// 在表格中显示查询结果
			tableModel = new DefaultTableModel(rows, columnHeads);
			// 刷新Table
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return tableModel;
	}

	private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd)
			throws SQLException, RemoteException {
		Vector currentRow = new Vector();
		for (int i = 1; i <= rsmd.getColumnCount(); ++i)
			currentRow.addElement(rs.getString(i));
		// 返回一条记录
		return currentRow;
	}

	public void insertSelectedColumn(String tableName,ArrayList<String> columnNames , DatabaseRowInfo info) throws RemoteException{
		tableName+="(";
		tableName+=columnNames.get(0);
		for (int i = 1; i < columnNames.size(); i++) {
			tableName+=","+columnNames.get(i);
		}
		tableName+=")";
		insert(tableName, info);
	}
	
	
	@Override
	public void insert(String tableName, DatabaseRowInfo info) throws RemoteException {
		try {
			// 执行SQL语句
			statement = linkToMySQL.connection.createStatement();
			statement.executeUpdate(" insert into "+tableName+" values("+info.getRowInfo()+")");
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	@Override
	public void updateInfo(String tableName, UpdateCondition updateInfo,
			DatabaseCondition conditionInfo) throws RemoteException {
		try {
			// 执行SQL语句
			statement = linkToMySQL.connection.createStatement();
			statement.executeUpdate("update  "+tableName+" set "+updateInfo.getCondition()+" where " +conditionInfo.getCondition());

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	@Override
	public int getAutoNumber(String tableName) {
		try {
			// 执行SQL语句
			statement = linkToMySQL.connection.createStatement();
			ResultSet result = statement.executeQuery("select @@IDENTITY as Number");
			result.next();
			int rs = result.getInt("Number");
			return rs;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return 0;
		}
	}
}
