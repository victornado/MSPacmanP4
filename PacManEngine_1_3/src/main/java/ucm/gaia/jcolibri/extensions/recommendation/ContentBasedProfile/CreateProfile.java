/**
 * CreateProfile.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc�a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 02/11/2007
 */
package ucm.gaia.jcolibri.extensions.recommendation.ContentBasedProfile;


import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.connector.xmlutils.QuerySerializer;

/**
 * Stores an user profile (query object) into a XML file.
 * 
 * @author Juan A. Recio-Garcia
 * @author Developed at University College Cork (Ireland) in collaboration with Derek Bridge.
 * @version 1.0
 * @see QuerySerializer
 */
public class CreateProfile
{
    /**
     * Stores a query into a XML file
     * @param query to store
     * @param filename of the XML file
     */
    public static void createProfile(CBRQuery query, String filename)
    {
	QuerySerializer.serializeQuery(query, filename);
    }
}
