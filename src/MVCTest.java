import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.junit.Before;
import org.junit.Test;

public class MVCTest {
	private mainClass mainclass;
	private CalcMVC mainMVC;
	private JTabbedPane MAIN = new JTabbedPane();
	private CalcModel model = new CalcModel(MAIN);
	private GraphModel graphModel = new GraphModel("");
	private CalcController controller;
	private GraphController graphControl;
	private CalcView view;
	private FavouritesPanel panel2;
	private GraphView graphView;
	
	@Before
	public void setup(){
		mainclass = new mainClass();
		mainMVC = new CalcMVC();
		model = new CalcModel(MAIN);
		 
		 controller = new CalcController(model, graphModel, MAIN);
		 graphControl = new GraphController(model, graphModel);
		
	    panel2 = new FavouritesPanel(MAIN, controller);
	    CalcView calculatorView = new CalcView(controller, MAIN);
	    GraphView graphView = new GraphView(graphControl);
	    
	    MAIN.addTab("Calculator", calculatorView);
	    MAIN.addTab("Graph", graphView);

	    MAIN.addTab("Favourites", panel2);
	}
	

	@Test
	//10.0.0
	public void TestMVC() {
		String[] args = new String[20];
		mainMVC.main(args);
		
	}
	
	@Test
	//10.0.1
	public void TestMainClass() {
		mainclass.init();
		ChangeEvent evt = new ChangeEvent("Clicked");
		System.out.println(MAIN.getChangeListeners());
		 ChangeListener[] listeners = MAIN.getChangeListeners();
		    if (listeners != null && listeners.length > 0) {
		        evt = new ChangeEvent(evt);
		        for (ChangeListener listener : listeners) {
		            listener.stateChanged(evt);
		        }
		    }
		    
		MAIN.setSelectedIndex(1);
		MAIN.setSelectedIndex(2);
		MAIN.setSelectedIndex(0);
		
		MAIN.getRootPane();
	
		
	}
	
	@Test 
	public void FavouriteTests() {
		panel2.addToFavourites("Sin(x)");
		panel2.clearList();
		panel2.getCounter();
		JComboBox<String> box = panel2.getBox();
		ItemEvent evt = new ItemEvent(box, 0, ItemEvent.SELECTED, 0);
		
		ItemListener[] listeners = box.getItemListeners();
		  if (listeners != null && listeners.length > 0) {
		        evt = new ItemEvent(box, 0, evt, 0);
		        for (ItemListener listener : listeners) {
		            listener.itemStateChanged(evt);
		        }
		    }
	}
	
	@Test 
	public void GraphTests() {
		ChartPanel graph = graphModel.getChart();
		graphControl.save();
		graphControl.updateGraph();
	}
}
