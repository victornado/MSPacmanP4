package ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local;


import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

/**
 * This function returns 1 if both individuals are equal, otherwise returns 0
 */
public class Equal implements LocalSimilarityFunction {

	/**
	 * Applies the similarity function.
	 * 
	 * @param o1
	 *            Object.
	 * @param o2
	 *            Object.
	 * @return the result of apply the similarity function.
	 */
    public double compute(Object o1, Object o2) throws NoApplicableSimilarityFunctionException {
        if ((o1 == null) || (o2 == null))
            return 0;
        return o1.equals(o2) ? 1 : 0;
    }
    
    /** Applicable to any class */
	public boolean isApplicable(Object o1, Object o2)
	{
		return true;
	}
}
