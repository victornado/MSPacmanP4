/**
 * DoubleEditor.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 02/11/2007
 */
package ucm.gaia.jcolibri.method.gui.editors;

import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

/**
 * Parameter Editor for Double values.
 * 
 * @author Juan A. Recio-Garcia
 * @version 1.0
 * @see ParameterEditor
 */
public class DoubleEditor extends JFormattedTextField implements
		ParameterEditor {
	private static final long serialVersionUID = 1L;

	
	/**
	 *  Creates a new instance
	 */
	public DoubleEditor() {
		setValue(new Double(0));
	}

	/**
	 * Returns a Double object
	 */
	public Object getEditorValue() {
	    try{
		return new Double(getText());
	    }catch(Exception e){}
	    return null;
	}

	/**
	 * Returns the JComponent
	 */
	public JComponent getJComponent() {
		return (JComponent) this;
	}

	/**
	 * Receives a Double value
	 */
	public void setEditorValue(Object defaultValue) {
	    	if(defaultValue==null)
	    	{
	    	    this.setText("");
	    	    return;
	    	} 
		if (!(defaultValue instanceof Double))
			return;
		Double value = (Double) defaultValue;
		this.setValue(value);
	}

	/**
	 * Does nothing
	 */
	public void setAllowedValues(Collection<Object> allowedValues)
	{
	    //any
	}
}
