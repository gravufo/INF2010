
/**
 * Interface des operations sur PixelMap
 *
 * @author Tarek Ould Bachir
 * @date : 2011-09-10
 */
public interface ImageOperations
{

      public void negate();

      public void convertToBWImage();

      public void convertToGrayImage();

      public void convertToColorImage();

      public void halveHW();

      public void insert(PixelMap pm, int row0, int col0);

      public void rotate(boolean clockWise);

      public void resize(int h, int w);

      public void translate(int colOffset, int rowOffset);
}
