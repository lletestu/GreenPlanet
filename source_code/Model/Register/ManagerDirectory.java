/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 22/04/13
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
package GreenPlanet.Model.Register;
import java.io.File;

/**
 * Class allows to create and delete directory in this project
 */
public class ManagerDirectory {
    /**
     * Name of the directory
     */
    private static String nameDirectory="SaveData";
    /**
     * Path name of the directory
     */
    private static File pathFile = new File(nameDirectory);
    /**
     * Absolute path of the directory
     */
    private static String absolutePath = pathFile.getAbsolutePath()+"/";

    /**
     * This method allows to create a new directory
     */
    public static void createDirectory(){
        boolean bool = false;

        try{
            if (!pathFile.exists()){

            bool = new File(nameDirectory).mkdirs();

            }

        }catch(Exception e){

        }
    }

    /**
     * This method allows to delete a directory
     * @param path represents the file path for directory
     * @return boolean if the directory is well delete
     */
    public static boolean deleteDirectory(File path) {
        boolean resultat = true;

        if( path.exists() ) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    resultat &= deleteDirectory(files[i]);
                }
                else {
                    resultat &= files[i].delete();
                }
            }
        }
        resultat &= path.delete();
        return( resultat );
    }

    /**
     * Getter of the path directory
     * @return File that represent the path
     */
    public static File getPathFile() {
        return pathFile;
    }

    /**
     * Getter of the absolute path
     * @return String that represents absolute pathfor directory
     */
    public static String getAbsolutePath() {
        return absolutePath;
    }

    /**
     * This method check if the directory is already created in this project
     * @return boolean to indicate if the directory is created or not
     */
    public static boolean directoryExist(){
        return pathFile.exists();
    }
}
