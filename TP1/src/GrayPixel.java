
/**
 * Classe de pixel en tons de gris
 *
 * @author Tarek Ould Bachir (Christian Artin, Pavel Brzezinski)
 * @date : 2011-09-10
 */
public class GrayPixel extends AbstractPixel
{

      int pixel; // donnee du pixel

      /**
       * Constructeur par defaut (pixel blanc)
       */
      GrayPixel()
      {
	    this.pixel = 255;
      }

      /**
       * Constructeur par parametre
       *
       * @param pixel : valeur du pixel
       */
      GrayPixel(int pixel)
      {
	    this.pixel = pixel;
      }

      /**
       * Renvoie la valeur du pixel
       */
      public int getPixel()
      {
	    return pixel;
      }

      /**
       * Assigne une valeur au pixel
       *
       * @param pixel: valeur a assigner
       */
      public void setPixel(int pixel)
      {
	    this.pixel = pixel;
      }

      /**
       * Renvoie un pixel copie de type noir et blanc
       */
      public BWPixel toBWPixel()
      {
	    boolean isWhite = true; // Pixel blanc --> 1

	    if (pixel <= 127)
	    {
		  isWhite = false; // Pixel noir --> 0
	    }

	    return new BWPixel(isWhite);
      }

      /**
       * Renvoie un pixel copie de type tons de gris
       */
      public GrayPixel toGrayPixel()
      {
	    return new GrayPixel(pixel);
      }

      /**
       * Renvoie un pixel copie de type couleurs
       */
      public ColorPixel toColorPixel()
      {
	    int[] temp =
	    {
		  this.pixel, this.pixel, this.pixel
	    };
	    return new ColorPixel(temp);
      }

      /**
       * Renvoie le negatif du pixel (255-pixel)
       */
      public AbstractPixel Negative()
      {
	    return new GrayPixel(255 - this.pixel);
      }

      /**
       * Convertit le pixel en String (sert a ecrire dans un fichier
       * (avec un espace supplémentaire en fin)s
       */
      public String toString()
      {
	    return ((Integer) (pixel)).toString() + " ";
      }
}
