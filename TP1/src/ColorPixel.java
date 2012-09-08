/**
 * Classe de pixel en couleurs (remplacer par votre nom)
 *
 * @author Tarek Ould Bachir
 * @date : 2011-09-10
 */
public class ColorPixel extends AbstractPixel
{

      public int[] rgb; // donnees de l'image

      /**
       * Constructeur par defaut (pixel blanc)
       */
      ColorPixel()
      {
	    rgb = new int[3];
	    rgb[0] = 255;
	    rgb[1] = 255;
	    rgb[2] = 255;
      }

      /**
       * Assigne une valeur au pixel
       *
       * @param rgb: valeurs a assigner
       */
      ColorPixel(int[] rgb)
      {
	    this.rgb = new int[3];
	    this.rgb[0] = rgb[0];
	    this.rgb[1] = rgb[1];
	    this.rgb[2] = rgb[2];
      }

      /**
       * Renvoie un pixel copie de type noir et blanc
       */
      @Override
      public BWPixel toBWPixel()
      {
	    double moyenne = (rgb[0] + rgb[1] + rgb[2]) / 3;
	    boolean isBlack;

	    if (moyenne <= 127)
	    {
		  isBlack = false;
	    }
	    else
	    {
		  isBlack = true;
	    }

	    return (new BWPixel(isBlack));
      }

      /**
       * Renvoie un pixel copie de type tons de gris
       */
      @Override
      public GrayPixel toGrayPixel()
      {
	    return new GrayPixel((rgb[0] + rgb[1] + rgb[2]) / 3);
      }

      /**
       * Renvoie un pixel copie de type couleurs
       */
      @Override
      public ColorPixel toColorPixel()
      {
	    return new ColorPixel(rgb);
      }

      /**
       * Renvoie le negatif du pixel (255-pixel)
       */
      @Override
      public AbstractPixel Negative()
      {
	    int[] negatif =
	    {
		  255 - rgb[0], 255 - rgb[1], 255 - rgb[2]
	    };

	    return new ColorPixel(negatif);
      }

      /**
       * Convertit le pixel en String (sert a ecrire dans un fichier
       * (avec un espace supplémentaire en fin)s
       */
      @Override
      public String toString()
      {
	    return ((Integer) rgb[0]).toString() + " "
		    + ((Integer) rgb[1]).toString() + " "
		    + ((Integer) rgb[2]).toString() + " ";
      }
}
