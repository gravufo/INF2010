/**
 * Classe de pixel en tons de gris
 *
 * @author Tarek Ould Bachir (remplacer par votre nom)
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
      @Override
      public BWPixel toBWPixel()
      {
	    boolean isBlack = true;

	    if (pixel <= 127)
	    {
		  isBlack = false;
	    }

	    return new BWPixel(isBlack);
      }

      /**
       * Renvoie un pixel copie de type tons de gris
       */
      @Override
      public GrayPixel toGrayPixel()
      {
	    return new GrayPixel(pixel);
      }

      /**
       * Renvoie un pixel copie de type couleurs
       */
      @Override
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
      @Override
      public AbstractPixel Negative()
      {
	    return new GrayPixel(255 - this.pixel);
      }

      /**
       * Convertit le pixel en String (sert a ecrire dans un fichier
       * (avec un espace supplémentaire en fin)s
       */
      @Override
      public String toString()
      {
	    return ((Integer) (pixel)).toString() + " ";
      }
}
