/**
 * TextEditor.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 02/11/2007
 */
package ucm.gaia.jcolibri.method.gui.editors;

import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.apache.logging.log4j.LogManager;
import ucm.gaia.jcolibri.connector.TypeAdaptor;
import ucm.gaia.jcolibri.datatypes.Text;

/**
 * Parameter Editor for Text values.
 * 
 * @author Juan A. Recio-Garcia
 * @version 1.0
 * @see ParameterEditor
 */
public class TextEditor extends JScrollPane implements ParameterEditor
{
    private static final long serialVersionUID = 1L;

    private JTextArea tarea = new JTextArea();

    /**
     * Creates a new instance
     */
    public TextEditor()
    {
	super();
	tarea = new JTextArea();
	this.setViewportView(tarea);
	this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    }

    /**
     * Returns a Text object
     */
    public Object getEditorValue()
    {
	if (tarea.getText().length() == 0)
	    return null;

	try
	{
	    TypeAdaptor ta = (TypeAdaptor) Text.class.newInstance();
	    ta.fromString(tarea.getText());
	    return ta;
	} catch (Exception e)
	{
	    LogManager.getLogger(this.getClass()).error(e);

	}
	return null;
    }

    /**
     * Returns the JComponent
     */
    public JComponent getJComponent()
    {
	return (JComponent) this;
    }

   /**
    * Receives a Text value
    */
    public void setEditorValue(Object value)
    {
	if (value == null)
	{
	    tarea.setText("");
	    return;
	}

	tarea.setText(value.toString());

    }

   /** 
    * Does nothing
    */
    public void setAllowedValues(Collection<Object> allowedValues)
    {
	// ANY
    }

}
