/**
 * Classe de pixel noir et blanc
 *
 * @author Tarek Ould Bachir
 * @date : 2011-09-10
 */
public class BWPixel extends AbstractPixel
{

      boolean pixel; // donnee du pixel

      /**
       * Constructeur par defaut (pixel blanc)
       */
      BWPixel()
      {
	    this(true);
      }

      /**
       * Constructeur par parametre
       *
       * @param pixel : valeur du pixel
       */
      BWPixel(boolean pixel)
      {
	    this.pixel = pixel;
      }

      /**
       * Renvoie la valeur du pixel
       */
      public boolean getPixel()
      {
	    return pixel;
      }

      /**
       * Assigne une valeur au pixel
       *
       * @param pixel: valeur a assigner
       */
      public void setPixel(boolean pixel)
      {
	    this.pixel = pixel;
      }

      /**
       * Renvoie un pixel copie de type noir et blanc
       */
      public BWPixel toBWPixel()
      {
	    return new BWPixel(this.pixel);
      }

      /**
       * Renvoie un pixel copie de type tons de gris
       */
      public GrayPixel toGrayPixel()
      {
	    GrayPixel gp = new GrayPixel(
		    pixel ? 255 : 0);
	    return gp;
      }

      /**
       * Renvoie un pixel copie de type couleurs
       */
      @Override
      public ColorPixel toColorPixel()
      {
	    int pixelVal = (pixel ? 255 : 0);
	    int[] rgb = new int[3];
	    rgb[0] = rgb[1] = rgb[2] = pixelVal;
	    ColorPixel cp = new ColorPixel(rgb);
	    return cp;
      }

      /**
       * Renvoie le negatif du pixel
       */
      public AbstractPixel Negative()
      {
	    return new BWPixel(!this.pixel);
      }

      /**
       * Convertit le pixel en String (sert a ecrire dans un fichier
       * (avec un espace supplémentaire en fin)s
       */
      public String toString()
      {
	    return ((Integer) (pixel ? 1 : 0)).toString() + " ";
      }
}
