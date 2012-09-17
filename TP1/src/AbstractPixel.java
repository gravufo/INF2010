
/**
 * Classe abstraite de pixel
 *
 * @author Tarek Ould Bachir
 * @date : 2011-09-10
 */
public abstract class AbstractPixel
{

      public abstract BWPixel toBWPixel();

      public abstract GrayPixel toGrayPixel();

      public abstract ColorPixel toColorPixel();

      public abstract AbstractPixel Negative();

      public abstract String toString();
}
