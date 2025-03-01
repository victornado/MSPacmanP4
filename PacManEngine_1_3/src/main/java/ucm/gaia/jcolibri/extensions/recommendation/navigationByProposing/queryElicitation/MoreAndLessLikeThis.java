/**
 * WeightedMoreLikeThis.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 02/11/2007
 */
package ucm.gaia.jcolibri.extensions.recommendation.navigationByProposing.queryElicitation;

import java.util.HashSet;
import java.util.List;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.FilterBasedRetrieval.FilterConfig;
import ucm.gaia.jcolibri.method.retrieve.FilterBasedRetrieval.predicates.NotEqualTo;
import ucm.gaia.jcolibri.util.AttributeUtils;

/**
 * The Less+More Like combines both MoreLikeThis and LessLikeThis. It copies
 * the values of the selected case into the query and returns a FilterConfig
 * object with the negative conditions.
 * That way, this method should be used together with FilteredKNNRetrieval.
 * <p>See:
 * <p>L. McGinty and B. Smyth. Comparison-based recommendation. In ECCBR'02: 
 * Proceedings of the 6th European Conference on Advances in Case-Based
 * Reasoning, pages 575-589, London, UK, 2002. Springer-Verlag.
 * 
 * @see MoreLikeThis
 * @see LessLikeThis
 * @author Juan A. Recio-Garcia
 * @author Developed at University College Cork (Ireland) in collaboration with Derek Bridge.
 * @version 1.0
 *
 */
public class MoreAndLessLikeThis implements ComparisonQueryElicitation
{ 
    /******************************************************************************/
    /**                           STATIC METHODS                                 **/
    /******************************************************************************/
    
    /**
     * The Less+More Like combines both MoreLikeThis and LessLikeThis. It copies
     * the values of the selected case into the query and returns a FilterConfig
     * object with the negative conditions.
     */
    public static void moreAndLessLikeThis(CBRQuery query, CBRCase selectedCase, List<CBRCase> proposedCases, FilterConfig filterConfig)
    {
	for(Attribute at: AttributeUtils.getAttributes(selectedCase.getDescription()))
	{
	    Object selectedValue = AttributeUtils.findValue(at, selectedCase);
	    AttributeUtils.setValue(at, query, selectedValue);
	    HashSet<Object> alternatives = new HashSet<Object>();
	    for(CBRCase c: proposedCases)
	    {
		Object value = AttributeUtils.findValue(at, c);
		alternatives.add(value);
	    }
	    if(alternatives.size()!=1)
		return;
	    Object value = alternatives.iterator().next();
	    if(selectedValue==null)
	    {
		if(value == null)
		    return;
		else
		    filterConfig.addPredicate(at, new NotEqualTo(value));
	    }else if(!selectedValue.equals(value))
		filterConfig.addPredicate(at, new NotEqualTo(value));    
	}
	    
	    
    }
    
    
    /******************************************************************************/
    /**                           OBJECT METHODS                                 **/
    /******************************************************************************/

    private FilterConfig _filterConfig;
    
    public MoreAndLessLikeThis(FilterConfig filterConfig)
    {
	_filterConfig = filterConfig;
    }
    
    /**
     * The Less+More Like combines both MoreLikeThis and LessLikeThis. It copies
     * the values of the selected case into the query and returns a FilterConfig
     * object with the negative conditions.
     */
    public void reviseQuery(CBRQuery query, CBRCase selectedCase, List<CBRCase> proposedCases)
    {
	moreAndLessLikeThis(query, selectedCase, proposedCases,_filterConfig);
    }
}
