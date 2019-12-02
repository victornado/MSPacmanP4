/**
 * MoreLikeThis.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 02/11/2007
 */
package ucm.gaia.jcolibri.extensions.recommendation.navigationByProposing.queryElicitation;


import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;
import ucm.gaia.jcolibri.util.CopyUtils;

import java.util.List;

/**
 * Replaces current query with the description of the selected case.
 * <p>See:
 * <p>L. McGinty and B. Smyth. Comparison-based recommendation. In ECCBR'02: 
 * Proceedings of the 6th European Conference on Advances in Case-Based
 * Reasoning, pages 575-589, London, UK, 2002. Springer-Verlag.
 * 
 * @author Juan A. Recio-Garcia
 * @author Developed at University College Cork (Ireland) in collaboration with Derek Bridge.
 * @version 1.0
 *
 */
public class MoreLikeThis implements ComparisonQueryElicitation
{
    
    /******************************************************************************/
    /**                           STATIC METHODS                                 **/
    /******************************************************************************/
    
    /**
     * Replaces current query with the description of the selected case.
     */
    public static void moreLikeThis(CBRQuery query, CBRCase selectedCase)
    {
	CaseComponent cc = CopyUtils.copyCaseComponent(selectedCase.getDescription());
	query.setDescription(cc);	
    }
    
    /******************************************************************************/
    /**                           OBJECT METHODS                                 **/
    /******************************************************************************/

    
    /**
     * Replaces current query with the description of the selected case.
     */
    public void reviseQuery(CBRQuery query, CBRCase selectedCase, List<CBRCase> proposedCases)
    {
	moreLikeThis(query, selectedCase);
    }
}
