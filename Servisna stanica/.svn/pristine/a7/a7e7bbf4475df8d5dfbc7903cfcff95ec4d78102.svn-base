package ra94_2012;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;

import gui.tables.Table;
import gui.tables.forms.ComboBox;
import gui.tables.forms.RecordForm;
import model.Record;
import model.TableColumn;
import model.TableModel;

/**
 * Test class for {@link RecordForm}.
 * 
 * @author Isidora Škulec
 * @see JUnit4
 * @see RecordForm
 *
 */
public class RecordFormTest {

	// For getter/setter testing
	private RecordForm singleForm;
	private TableModel singleModel;
	private Record singleRecord;
	private HashMap<TableColumn, JComponent> singleInputs;
	private HashMap<TableColumn, ComboBox> singleComboBoxes;

	// For validation testing
	private ArrayList<RecordForm> formsTTa = new ArrayList<>();
	private ArrayList<RecordForm> formsTFa = new ArrayList<>();
	private ArrayList<RecordForm> formsFTa = new ArrayList<>();
	private ArrayList<RecordForm> formsFFa = new ArrayList<>();

	private ArrayList<RecordForm> formsTTm = new ArrayList<>();
	private ArrayList<RecordForm> formsTFm = new ArrayList<>();
	private ArrayList<RecordForm> formsFTm = new ArrayList<>();
	private ArrayList<RecordForm> formsFFm = new ArrayList<>();

	/**
	 * Set up method for {@link RecordFormTest}. Creates several test
	 * {@link TableModel}s with different properties using
	 * {@link TableModelCreator#createModel(boolean, boolean)}, test
	 * {@link Table}, test {@link Record} used for creating and testing several
	 * different {@link RecordForm}s.
	 * 
	 * @throws Exception
	 * @see TableModel
	 * @see RecordForm
	 * @see Table
	 * @see Record
	 * @see TableModelCreator
	 * @see TableModelCreator#createModel(boolean, boolean)
	 */
	@Before
	public void setUp() throws Exception {
		ArrayList<TableModel> tmTT = TableModelCreator.createModel(true, true);
		ArrayList<TableModel> tmTF = TableModelCreator.createModel(true, false);
		ArrayList<TableModel> tmFT = TableModelCreator.createModel(false, true);
		ArrayList<TableModel> tmFF = TableModelCreator.createModel(false, false);
		RecordForm.setMainFrame(null);

		// Dummy table to be focused.
		DefaultTableModel model = new DefaultTableModel();
		Table table = new Table();
		table.setModel(model);
		// Create a couple of columns
		model.addColumn("Col1");
		model.addColumn("Col2");
		// Append a row
		model.addRow(new Object[] { "v1", "v2" });
		table.setRowSelectionInterval(0, 0);
		Table.setFocusedTable(table);

		// For non validation tests only one form is needed.
		singleModel = tmTT.get(0);
		singleForm = new RecordForm(singleModel, true);

		singleRecord = new Record();
		singleForm.setRecord(singleRecord);

		singleInputs = new HashMap<>();
		TableColumn singleColumn = new TableColumn();
		singleInputs.put(singleColumn, new JTextField());
		singleForm.setInputs(singleInputs);

		singleComboBoxes = new HashMap<>();
		TableColumn singleRefColumn = new TableColumn();
		singleRefColumn.getReferencedTableColumns().add(new TableColumn());
		singleComboBoxes.put(singleRefColumn, new ComboBox());
		singleForm.setComboBoxes(singleComboBoxes);

		// Primary and nullable
		for (TableModel m : tmTT) {
			RecordForm formA = new RecordForm(m, true);
			RecordForm formM = new RecordForm(m, false);

			formsTTa.add(formA);
			formsTTm.add(formM);
		}

		// Primary and not nullable
		for (TableModel m : tmTF) {
			RecordForm formA = new RecordForm(m, true);
			RecordForm formM = new RecordForm(m, false);

			formsTFa.add(formA);
			formsTFm.add(formM);
		}

		// Not primary and nullable
		for (TableModel m : tmFT) {
			RecordForm formA = new RecordForm(m, true);
			RecordForm formM = new RecordForm(m, false);

			formsFTa.add(formA);
			formsFTm.add(formM);
		}

		// Not primary and not nullable
		for (TableModel m : tmFF) {
			RecordForm formA = new RecordForm(m, true);
			RecordForm formM = new RecordForm(m, false);

			formsFFa.add(formA);
			formsFFm.add(formM);
		}
	}

	/**
	 * Tear down method for {@link RecordFormTest} that disposes of test
	 * {@link RecordForm}s.
	 * 
	 * @throws Exception
	 * @see JUnit4
	 * @see RecordForm
	 */
	@After
	public void tearDown() throws Exception {
		singleForm.dispose();
		for (RecordForm form : formsTTa)
			form.dispose();
		for (RecordForm form : formsTFa)
			form.dispose();
		for (RecordForm form : formsFTa)
			form.dispose();
		for (RecordForm form : formsFFa)
			form.dispose();
		for (RecordForm form : formsTTm)
			form.dispose();
		for (RecordForm form : formsTFm)
			form.dispose();
		for (RecordForm form : formsFTm)
			form.dispose();
		for (RecordForm form : formsFFm)
			form.dispose();
	}

	/**
	 * Test method for {@link RecordForm#validateFields()}, by calling it for
	 * every form and checking its result based on {@link TableModel}s
	 * properties.
	 * 
	 * @see RecordForm
	 * @see RecordForm#validateFields()
	 * @see TableModel
	 */
	@Test
	public final void testValidateFields() {
		// TRUE
		// Not primary and nullable
		for (RecordForm f : formsFTa) {
			Assert.assertTrue(f.validateFields());
		}
		for (RecordForm f : formsFTm) {
			Assert.assertTrue(f.validateFields());
		}

		// Modify setting
		for (RecordForm f : formsTTm) {
			Assert.assertTrue(f.validateFields());
		}
		for (RecordForm f : formsTFm) {
			Assert.assertTrue(f.validateFields());
		}
		for (RecordForm f : formsFFm) {
			Assert.assertTrue(f.validateFields());
		}

		// FALSE
		// Add setting
		for (RecordForm f : formsTTa) {
			Assert.assertFalse(f.validateFields());
		}
		for (RecordForm f : formsTFa) {
			Assert.assertFalse(f.validateFields());
		}
		for (RecordForm f : formsFFa) {
			Assert.assertFalse(f.validateFields());
		}
	}

	/**
	 * Tests {@link RecordForm#getTableModel()} by comparing it with default,
	 * initially set test {@link TableModel}.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getTableModel()
	 * @see TableModel
	 */
	@Test
	public final void testGetTableModel() {
		Assert.assertSame(singleModel, singleForm.getTableModel());
	}

	/**
	 * Tests {@link RecordForm#setTableModel(TableModel)} by comparing the
	 * result of {@link RecordForm#getTableModel()} with the newly set
	 * {@link TableModel}.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getTableModel()
	 * @see RecordForm#setTableModel(TableModel)
	 * @see TableModel
	 */
	@Test
	public final void testSetTableModel() {
		singleForm.setTableModel(null);
		Assert.assertSame(null, singleForm.getTableModel());
		singleForm.setTableModel(singleModel);
		Assert.assertSame(singleModel, singleForm.getTableModel());
	}

	/**
	 * Tests {@link RecordForm#getInputs()} by comparing its result with
	 * initially set {@link HashMap} of {@link JComponent}s.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getInputs()
	 * @see HashMap
	 * @see JComponent
	 */
	@Test
	public final void testGetInputs() {
		Assert.assertSame(singleInputs, singleForm.getInputs());
	}

	/**
	 * Tests {@link RecordForm#setInputs(HashMap)} by comparing the result of
	 * {@link RecordForm#getInputs()} with newly set {@link HashMap} of
	 * {@link JComponent}s.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getInputs()
	 * @see RecordForm#setInputs(HashMap)
	 * @see HashMap
	 * @see JComponent
	 */
	@Test
	public final void testSetInputs() {
		singleForm.setInputs(null);
		Assert.assertSame(null, singleForm.getInputs());
		singleForm.setInputs(singleInputs);
		Assert.assertSame(singleInputs, singleForm.getInputs());
	}

	/**
	 * Tests {@link RecordForm#getComboBoxes()} by comparing its result with
	 * initially set {@link HashMap} of {@link ComboBox}es.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getComboBoxes()
	 * @see HashMap
	 * @see ComboBox
	 */
	@Test
	public final void testGetComboBoxes() {
		Assert.assertSame(singleComboBoxes, singleForm.getComboBoxes());
	}

	/**
	 * Tests {@link RecordForm#setComboBoxes(HashMap)} by comparing the result
	 * of {@link RecordForm#getComboBoxes()} with newly set {@link HashMap} of
	 * {@link ComboBox}es.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getComboBoxes()
	 * @see RecordForm#setComboBoxes(HashMap)
	 * @see HashMap
	 * @see ComboBox
	 */
	@Test
	public final void testSetComboBoxes() {
		singleForm.setComboBoxes(null);
		Assert.assertSame(null, singleForm.getComboBoxes());
		singleForm.setComboBoxes(singleComboBoxes);
		Assert.assertSame(singleComboBoxes, singleForm.getComboBoxes());
	}

	/**
	 * Tests {@link RecordForm#getRecord()} by comparing its result with
	 * initially set {@link Record}.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getRecord()
	 * @see Record
	 */
	@Test
	public final void testGetRecord() {
		Assert.assertSame(singleRecord, singleForm.getRecord());
	}

	/**
	 * Tests {@link RecordForm#setRecord(Record)} by comparing the result of
	 * {@link RecordForm#getRecord()} with newly set {@link Record}.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getRecord()
	 * @see RecordForm#setRecord(Record)
	 * @see Record
	 */
	@Test
	public final void testSetRecord() {
		singleForm.setRecord(null);
		Assert.assertSame(null, singleForm.getRecord());
		singleForm.setRecord(singleRecord);
		Assert.assertSame(singleRecord, singleForm.getRecord());
	}


	/**
	 * Tests {@link RecordForm#getMainFrame()} by comparing its result with
	 * newly set {@link JFrame}.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getMainFrame()
	 * @see RecordForm#setMainFrame(JFrame)
	 * @see JFrame
	 */
	@Test
	public final void testGetMainFrame() {
		Assert.assertSame(null, RecordForm.getMainFrame());
		JFrame mf = new JFrame();
		RecordForm.setMainFrame(mf);
		Assert.assertSame(mf, RecordForm.getMainFrame());
	}

	/**
	 * Tests {@link RecordForm#setMainFrame(JFrame)} by comparing the result of
	 * {@link RecordForm#getMainFrame()} with newly set {@link JFrame}.
	 * 
	 * @see RecordForm
	 * @see RecordForm#getMainFrame()
	 * @see RecordForm#setMainFrame(JFrame)
	 * @see JFrame
	 */
	@Test
	public final void testSetMainFrame() {
		JFrame mf = new JFrame();
		RecordForm.setMainFrame(mf);
		Assert.assertSame(mf, RecordForm.getMainFrame());
	}

}
