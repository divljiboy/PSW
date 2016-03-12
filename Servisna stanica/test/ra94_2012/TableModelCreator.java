package ra94_2012;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import gui.tables.forms.RecordForm;
import model.Record;
import model.TableColumn;
import model.TableModel;
import model.TableSchema;

/**
 * Helper class. Creates a {@link TableModel} for testing the {@link RecordForm} class.
 * The created model has all data types using distinct input fields.
 * 
 * @author Isidora Skulec
 *
 */
public class TableModelCreator {
	
	private static TableModel timestampModel;
	private static TableModel dateModel;
	private static TableModel realModel;
	private static TableModel integerModel;
	private static TableModel charShortModel;
	private static TableModel charLongModel;
	private static TableModel referenceModel;
	
	/**
	 * Creates models used by {@link RecordForm} class to generate forms.
	 * The {@code primary} boolean flag determines whether the field is simulating a primary key in the database.
	 * The {@code nullable} boolean flag determines whether the field is simulating a field which can hold a {@code null} value in the database.
	 * 
	 * @param primary
	 * @param nullable
	 * @return {@link ArrayList}
	 */
	public static ArrayList<TableModel> createModel(boolean primary, boolean nullable) {
		
		//First create models. One for each type.
		ArrayList<TableModel> tableModels = new ArrayList<>();
		
			//TIMESTAMP
			timestampModel = new TableModel();
				TableColumn timestampColumn = new TableColumn();
					timestampColumn.setType(Types.TIMESTAMP);
					timestampColumn.setSize(10);
					timestampColumn.setPrimaryKey(primary);
					timestampColumn.setNullable(nullable);
					
				TableSchema timestampSchema = new TableSchema();
					timestampSchema.getColumns().add(timestampColumn);
				timestampModel.setTableSchema(timestampSchema);
			
				Record timestampRecord = new Record();
					Timestamp t = new Timestamp(123456789);
					timestampRecord.getValues().put(timestampColumn, t);
				timestampModel.getRecords().add(timestampRecord);
					
			tableModels.add(timestampModel);
				
			
			//TIME
			TableModel timeModel = new TableModel();
				TableColumn timeColumn = new TableColumn();
					timeColumn.setType(Types.TIME);
					timeColumn.setSize(10);
					timeColumn.setPrimaryKey(primary);
					timeColumn.setNullable(nullable);
					
				TableSchema timeSchema = new TableSchema();
					timeSchema.getColumns().add(timeColumn);
				timeModel.setTableSchema(timeSchema);
			
				Record timeRecord = new Record();
					timeRecord.getValues().put(timeColumn, new Time(123456789));
				timeModel.getRecords().add(timeRecord);
				
			tableModels.add(timeModel);
			
			
			
			//DATE
			dateModel = new TableModel();
				TableColumn dateColumn = new TableColumn();
					dateColumn.setType(Types.DATE);
					dateColumn.setSize(10);
					dateColumn.setPrimaryKey(primary);
					dateColumn.setNullable(nullable);
					
				TableSchema dateSchema = new TableSchema();
					dateSchema.getColumns().add(dateColumn);
				dateModel.setTableSchema(dateSchema);
			
				Record dateRecord = new Record();
					Date d = new Date(123456789);
					dateRecord.getValues().put(dateColumn, d);
				dateModel.getRecords().add(dateRecord);
				
			tableModels.add(dateModel);
			
			//BIT, BOOLEAN
				//Does not validate
			
			//DOUBLE, FLOAT, REAL - same input method
			realModel = new TableModel();
				TableColumn realColumn = new TableColumn();
					realColumn.setType(Types.REAL);
					realColumn.setSize(10);
					realColumn.setPrimaryKey(primary);
					realColumn.setNullable(nullable);
					realColumn.setDecimalDigits(10);
					
				TableSchema realSchema = new TableSchema();
					realSchema.getColumns().add(realColumn);
				realModel.setTableSchema(realSchema);
				
				Record realRecord = new Record();
					realRecord.getValues().put(realColumn, 123.456789);
				realModel.getRecords().add(realRecord);
				
			tableModels.add(realModel);
			
			//NUMERIC, DECIMAL
				//same input method as real/integer
			
			//INTEGER, SMALLINT, BIGINT, TINYINT - same input method
			integerModel = new TableModel();
				TableColumn integerColumn = new TableColumn();
					integerColumn.setType(Types.INTEGER);
					integerColumn.setSize(10);
					integerColumn.setPrimaryKey(primary);
					integerColumn.setNullable(nullable);
					
				TableSchema integerSchema = new TableSchema();
					integerSchema.getColumns().add(integerColumn);
				integerModel.setTableSchema(integerSchema);
			
				Record integerRecord = new Record();
					integerRecord.getValues().put(integerColumn, 123456789);
				integerModel.getRecords().add(integerRecord);
					
				
			tableModels.add(integerModel);
			
			//CHAR, NCHAR, VARCHAR, NVARCHAR, LONGVARCHAR, LONGNVARCHAR
				//Long input
				charLongModel = new TableModel();
					TableColumn charLongColumn = new TableColumn();
						charLongColumn.setType(Types.CHAR);
						charLongColumn.setSize(1000);
						charLongColumn.setPrimaryKey(primary);
						charLongColumn.setNullable(nullable);
						
					TableSchema charLongSchema = new TableSchema();
						charLongSchema.getColumns().add(charLongColumn);
					charLongModel.setTableSchema(charLongSchema);
				
					Record charLongRecord = new Record();
						charLongRecord.getValues().put(charLongColumn, "123456789");
					charLongModel.getRecords().add(charLongRecord);
					
				tableModels.add(charLongModel);
				
				//Short input
				charShortModel = new TableModel();
					TableColumn charShortColumn = new TableColumn();
						charShortColumn.setType(Types.CHAR);
						charShortColumn.setSize(10);
						charShortColumn.setPrimaryKey(primary);
						charShortColumn.setNullable(nullable);
						
					TableSchema charShortSchema = new TableSchema();
						charShortSchema.getColumns().add(charShortColumn);
					charShortModel.setTableSchema(charShortSchema);
				
					Record charShortRecord = new Record();
						charShortRecord.getValues().put(charShortColumn, "123456789");
					charShortModel.getRecords().add(charShortRecord);
				
				tableModels.add(charShortModel);
				
			
			//REFERENCING (the ComboBox one)
			referenceModel = new TableModel();
				TableColumn referenceColumn = new TableColumn();
					referenceColumn.setType(Types.INTEGER);
					referenceColumn.setSize(10);
					referenceColumn.setPrimaryKey(primary);
					referenceColumn.setNullable(nullable);
					
				TableSchema referenceSchema = new TableSchema();
						referenceSchema.getColumns().add(referenceColumn);
					referenceModel.setTableSchema(referenceSchema);
			
				Record referenceRecord = new Record();
					referenceRecord.getValues().put(referenceColumn, 123456789);
				referenceModel.getRecords().add(referenceRecord);
				
			tableModels.add(referenceModel);
							
			return tableModels;
	}

	/**
	 * Getter method for {@link TableModel} with timestamp field.
	 * @return {@link TableModel}
	 */
	public static TableModel getTimestampModel() {
		return timestampModel;
	}

	/**
	 * Setter method for {@link TableModel} with timestamp field.
	 * @param timestampModel
	 */
	public static void setTimestampModel(TableModel timestampModel) {
		TableModelCreator.timestampModel = timestampModel;
	}

	/**
	 * Getter method for {@link TableModel} with date field.
	 * @return {@link TableModel}
	 */
	public static TableModel getDateModel() {
		return dateModel;
	}

	/**
	 * Setter method for {@link TableModel} with date field.
	 * @param dateModel
	 */
	public static void setDateModel(TableModel dateModel) {
		TableModelCreator.dateModel = dateModel;
	}

	/**
	 * Getter method for {@link TableModel} with real number field.
	 * @return {@link TableModel}
	 */
	public static TableModel getRealModel() {
		return realModel;
	}

	/**
	 * Setter method for {@link TableModel} with real number field.
	 * @param realModel
	 */
	public static void setRealModel(TableModel realModel) {
		TableModelCreator.realModel = realModel;
	}

	/**
	 * Getter method for {@link TableModel} with integer number field.
	 * @return {@link TableModel}
	 */
	public static TableModel getIntegerModel() {
		return integerModel;
	}

	/**
	 * Setter method for {@link TableModel} with integer number field.
	 * @param integerModel
	 */
	public static void setIntegerModel(TableModel integerModel) {
		TableModelCreator.integerModel = integerModel;
	}

	/**
	 * Getter method for {@link TableModel} with char text field.
	 * @return {@link TableModel}
	 */
	public static TableModel getCharShortModel() {
		return charShortModel;
	}

	/**
	 * Setter method for {@link TableModel} with char text field.
	 * @param charShortModel
	 */
	public static void setCharShortModel(TableModel charShortModel) {
		TableModelCreator.charShortModel = charShortModel;
	}

	/**
	 * Getter method for {@link TableModel} with long char text field.
	 * @return {@link TableModel}
	 */
	public static TableModel getCharLongModel() {
		return charLongModel;
	}

	/**
	 * Setter method for {@link TableModel} with long char text field.
	 * @param charLongModel
	 */
	public static void setCharLongModel(TableModel charLongModel) {
		TableModelCreator.charLongModel = charLongModel;
	}

	/**
	 * Getter method for {@link TableModel} with field referencing other field.
	 * @return {@link TableModel}
	 */
	public static TableModel getReferenceModel() {
		return referenceModel;
	}

	/**
	 * Setter method for {@link TableModel} with field referencing other field.
	 * @param referenceModel
	 */
	public static void setReferenceModel(TableModel referenceModel) {
		TableModelCreator.referenceModel = referenceModel;
	}
	
	
}
