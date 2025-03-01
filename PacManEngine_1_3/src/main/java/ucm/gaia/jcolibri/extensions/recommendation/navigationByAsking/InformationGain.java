/**
 * InformationGain.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 29/10/2007
 */
package ucm.gaia.jcolibri.extensions.recommendation.navigationByAsking;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.util.AttributeUtils;

import java.util.*;


/**
 * Selects an attribute with the highest information gain.
 * <p>See:
 * <p>R. Bergmann. Experience Management: Foundations, Development Methodology, 
 * and Internet-Based Applications. Springer-Verlag New York, Inc.,Secaucus,  
 * NJ, USA, 2002.
 * <p>
 * S. Schulz. CBR-works: A state-of-the-art shell for case-based application
 * building. In E. Melis, editor, Proceedings of the 7th German Workshop on
 * Case-Based Reasoning, GWCBR'99, W�rzburg, Germany, pages 166-175.
 * University of W�rzburg, 1999.
 * 
 * @author Juan A. Recio-Garcia
 * @author Developed at University College Cork (Ireland) in collaboration with Derek Bridge.
 * @version 1.0
 *
 */
public class InformationGain implements SelectAttributeMethod {
    /******************************************************************************/
    /**                           STATIC METHODS                                 **/
    /******************************************************************************/

    private static ArrayList<Attribute> asked;
        
    /**
     * Selects an attribute with the highest information gain.
     * @param cases are the working cases
     * @param init indicates if this is the first time that the algorithm is executed.
     * This way, in following iterations past chosen attributes are not computed.
     * @param completeSetOfCases is the original case set used when there are not attributes
     * with any information gain. This way, the method starts again with all the cases. 
     * @return the selected attribute or null if there are not more attributes to ask.
     * @throws ExecutionException
     */
    public static Attribute getMoreIGattribute(List<CBRCase> cases, boolean init, List<CBRCase> completeSetOfCases) throws ExecutionException
    {
	if(cases.isEmpty())
	{
	    cases.addAll(completeSetOfCases);
	    init = true;
	}
	if(init)
	    asked = new ArrayList<Attribute>();
	if(asked ==null)
	    throw new ExecutionException("InformationGain method must be initialized each cycle");
	CBRCase acase = cases.iterator().next();
	List<Attribute> atts = AttributeUtils.getAttributes(acase.getDescription());
	atts.remove(acase.getDescription().getIdAttribute());
	
	atts.removeAll(asked);
	if(atts.isEmpty())
	{
	    asked = new ArrayList<Attribute>();
	    atts = AttributeUtils.getAttributes(acase.getDescription());
	    atts.remove(acase.getDescription().getIdAttribute());
	}
	
	System.out.println("Computing IG for "+cases.size()+" cases");
	
	double maxIG = 0;
	Attribute maxIGatt = null;
	for(Attribute a: atts)
	{
	    double ig = computeIG(a,cases);
	    System.out.println("IG "+a.getName()+" --> "+ig);
	    if(ig>maxIG)
	    {
		maxIG = ig;
		maxIGatt = a;
	    }
	}
	
	asked.add(maxIGatt);
	return maxIGatt;
    }
    
    /**
     * Computes the IG for an attribute
     */
    private static double computeIG(Attribute a, List<CBRCase> cases)
    {
	Hashtable<Object,HashSet<CBRCase>> clases = new Hashtable<Object,HashSet<CBRCase>>();
	for(CBRCase c: cases)
	{
	    Object value = AttributeUtils.findValue(a, c.getDescription());
	    HashSet<CBRCase> set = clases.get(value);
	    if(set==null)
	    {
		set = new HashSet<CBRCase>();
		clases.put(value, set);
	    }
	    set.add(c);
	}
	
	double casesSize = cases.size();
	double res = 0;
	for(Enumeration<HashSet<CBRCase>> en = clases.elements(); en.hasMoreElements();)
	{
	    double setSize = en.nextElement().size();
	    double div = setSize/casesSize;
	    res+= div * (Math.log(div)/Math.log(2));
	}
	return -res;
    }

    /******************************************************************************/
    /**                           OBJECT METHODS                                 **/
    /******************************************************************************/
    
    /**
     * Original set of cases, used when there is not IG
     */
    private List<CBRCase> completeSetOfCases;
    
    /**
     * Constructor. 
     * @param completeset is the original set of cases, used when there is not IG
     */
    public InformationGain(List<CBRCase> completeset)
    {
	completeSetOfCases = completeset;
	asked = new ArrayList<Attribute>();
    }
    
    /**
     * Selects the attribute to be asked
     * @param cases list of working cases
     * @param query is the current query
     * @return selected attribute
     * @throws ExecutionException
     */
    public Attribute getAttribute(List<CBRCase> cases, CBRQuery query) throws ExecutionException
    {
	    return getMoreIGattribute(cases, false, completeSetOfCases);

    }
}
