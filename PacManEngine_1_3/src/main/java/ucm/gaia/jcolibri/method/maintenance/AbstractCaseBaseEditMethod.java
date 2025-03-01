package ucm.gaia.jcolibri.method.maintenance;


import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.method.reuse.classification.KNNClassificationConfig;

import java.util.Collection;

/**
 * Provides the ability to run a case base editing algorithm on
 * a case base.
 * 
 * @author Lisa Cummins
 * @author Derek Bridge
 * 18/05/07
 */
public abstract class AbstractCaseBaseEditMethod {

	/**
	 * Simulates a case base editing algorithm, returning the cases
	 * that would be deleted by the algorithm.
	 * @param cases The group of cases on which to perform editing.
	 * @param simConfig The similarity configuration for these cases.
	 * @return the list of cases that would be deleted by the algorithm.
	 */
	public abstract Collection<CBRCase> retrieveCasesToDelete(Collection<CBRCase> cases, KNNClassificationConfig simConfig);
	
	/**
	 * Runs a case base editing algorithm on the given case base.
	 * @param caseBase The case base on which to perform editing.
	 * @param simConfig The similarity configuration for the case
	 * base.
	 */
	public void edit(CBRCaseBase caseBase, KNNClassificationConfig simConfig)
	{	Collection<CBRCase> casesToDelete = retrieveCasesToDelete(caseBase.getCases(), simConfig);
		caseBase.forgetCases(casesToDelete);
	}
}