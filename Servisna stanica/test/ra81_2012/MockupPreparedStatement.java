package ra81_2012;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class MockupPreparedStatement implements PreparedStatement {

	String sql;
	Object[] params;
	
	public MockupPreparedStatement(String sql) {
		this.sql = sql;
		int fromIndex = -1;
		int count = 0;
		do {
			fromIndex = sql.indexOf('?', fromIndex + 1);
			if (fromIndex != -1) {
				count++;
			}
		} while (fromIndex != -1);
		params = new Object[count];
	}
	
	@Override
	public void addBatch(String arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearBatch() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] executeBatch() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResultSet executeQuery(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFetchDirection() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFetchSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxRows() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetType() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCursorName(String arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEscapeProcessing(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxFieldSize(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxRows(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setQueryTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBatch() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearParameters() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		MockupResultSet resultSet = new MockupResultSet();
		if (sql.equals("SELECT tbl1.Col1, tbl1.Col2, tbl1.Col3 FROM Test AS tbl1")) {
			for (int i=0; i < 2; i++) {
				ArrayList<Object> record = new ArrayList<>();
				record.add("R" + (i+1) + "NPNC1");
				record.add("R" + (i+1) + "NPNC2");
				record.add("R" + (i+1) + "NPNC3");
				resultSet.records.add(record);
			}
			resultSet.size = resultSet.records.size();
		}
		else if (sql.equals("SELECT tbl1.Col1, tbl1.Col2, tbl1.Col3 FROM Test AS tbl1 WHERE tbl1.Col1 LIKE ?")) {
			for (int i = 0; i < 1; i++) {
				ArrayList<Object> record = new ArrayList<>();
				record.add("R" + (i+1) + "NPWC1");
				record.add("R" + (i+1) + "NPWC2");
				record.add("R" + (i+1) + "NPWC3");
				resultSet.records.add(record);
			}
			resultSet.size = resultSet.records.size();	
		}
		else if (sql.equals("SELECT tbl1.Col1, tbl1.Col2, tbl1.Col3 FROM Test AS tbl1 WHERE (tbl1.Col1 = ?)")) {
			for (int i = 0; i < 2; i++) {
				ArrayList<Object> record = new ArrayList<>();
				record.add("RefVal");
				record.add("R" + (i+1) + "WPNC2");
				record.add("R" + (i+1) + "WPNC3");
				resultSet.records.add(record);
			}
			resultSet.size = resultSet.records.size();	
		}
		else if (sql.equals("SELECT tbl1.Col1, tbl1.Col2, tbl1.Col3 FROM Test AS tbl1 WHERE (tbl1.Col1 = ?) and (tbl1.Col2 LIKE ?)")) {
			for (int i = 0; i < 1; i++) {
				ArrayList<Object> record = new ArrayList<>();
				record.add("RefVal");
				record.add("R" + (i+1) + "WPWC2");
				record.add("R" + (i+1) + "WPWC3");
				resultSet.records.add(record);
			}
			resultSet.size = resultSet.records.size();	
		}
		return resultSet;
	}

	@Override
	public int executeUpdate() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setArray(int arg0, Array arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAsciiStream(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBinaryStream(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBinaryStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBlob(int arg0, Blob arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBlob(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBlob(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBoolean(int arg0, boolean arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setByte(int arg0, byte arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBytes(int arg0, byte[] arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacterStream(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacterStream(int arg0, Reader arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setClob(int arg0, Clob arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setClob(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setClob(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDate(int arg0, Date arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDate(int arg0, Date arg1, Calendar arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDouble(int arg0, double arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFloat(int arg0, float arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInt(int arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLong(int arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNCharacterStream(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNClob(int arg0, NClob arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNClob(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNClob(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNString(int arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNull(int arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNull(int arg0, int arg1, String arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setObject(int arg0, Object arg1) throws SQLException {
		params[arg0-1] = arg1;
	}

	@Override
	public void setObject(int arg0, Object arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setObject(int arg0, Object arg1, int arg2, int arg3) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRef(int arg0, Ref arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRowId(int arg0, RowId arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSQLXML(int arg0, SQLXML arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setShort(int arg0, short arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setString(int arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTime(int arg0, Time arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTime(int arg0, Time arg1, Calendar arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTimestamp(int arg0, Timestamp arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setURL(int arg0, URL arg1) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUnicodeStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub

	}

}
