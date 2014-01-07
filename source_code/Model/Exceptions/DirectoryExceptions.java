/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 23/04/13
 * Time: 00:08
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Exceptions;

/**
 * Exception for directory
 */
public class DirectoryExceptions extends Exception {
    /**
     * Default constructor
     */
    public DirectoryExceptions(){
        super();
    }

    /**
     * Constructor with message in parameter
     * @param message String that represents
     */
    public DirectoryExceptions(String message){
        super(message);
    }
}
