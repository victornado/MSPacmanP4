/**
 * CombineQueryAndCasesMethod.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 05/01/2007
 */
package ucm.gaia.jcolibri.method.reuse;


import java.util.*;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.util.CopyUtils;

/**
 * Method to combine the description of a query with the other components of a case: solution, result and justification of solution.
 * @author Juan A. Recio-Garcia
 * @version 2.0
 */
public class CombineQueryAndCasesMethod {

	/**
	 * Combienes some cases with a query. 
	 * This method creates a new copy for each case and overwrites their description with the description of the query.
	 */
	public static List<CBRCase> combine(CBRQuery query, Collection<CBRCase> cases)
	{
		ArrayList<CBRCase> res = new ArrayList<CBRCase>();
		
		for(CBRCase orig: cases)
		{
			try {
				CBRCase copy = orig.getClass().newInstance();
				
				copy.setDescription(CopyUtils.copyCaseComponent(query.getDescription()));
				copy.setSolution(CopyUtils.copyCaseComponent(orig.getSolution()));
				copy.setJustificationOfSolution(CopyUtils.copyCaseComponent(orig.getJustificationOfSolution()));
				copy.setResult(CopyUtils.copyCaseComponent(orig.getResult()));
				
				res.add(copy);
				
				
			} catch (Exception e) {
				org.apache.commons.logging.LogFactory.getLog(CombineQueryAndCasesMethod.class).error("Error combining cases and query", e);
			} 
		}	
		return res;
	}
}
