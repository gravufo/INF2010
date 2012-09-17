
/**
 * Classe PixelMapPlus Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM Implemente les methodes de
 * ImageOperations
 *
 * @author : Tarek Ould Bachir (remplacer par votre nom)
 * @date : 2011-09-10
 */
public class PixelMapPlus extends PixelMap implements ImageOperations
{

      /**
       * Constructeur creant l'image a partir d'un fichier
       *
       * @param fileName : Nom du fichier image
       */
      PixelMapPlus(String fileName)
      {
	    super(fileName);
      }

      /**
       * Constructeur copie
       *
       * @param type  : type de l'image a creer (BW/Gray/Color)
       * @param image : source
       */
      PixelMapPlus(PixelMap image)
      {
	    super(image);
      }

      /**
       * Constructeur copie (sert a changer de format)
       *
       * @param type  : type de l'image a creer (BW/Gray/Color)
       * @param image : source
       */
      PixelMapPlus(ImageType type, PixelMap image)
      {
	    super(type, image);
      }

      /**
       * Constructeur servant a allouer la memoire de l'image
       *
       * @param type : type d'image (BW/Gray/Color)
       * @param h    : hauteur (height) de l'image
       * @param w    : largeur (width) de l'image
       */
      PixelMapPlus(ImageType type, int h, int w)
      {
	    super(type, h, w);
      }

      /**
       * Genere le negatif d'une image
       */
      public void negate()
      {
	    AbstractPixel[][] newImage = new AbstractPixel[height][width];

	    for (int i = 0; i < height; i++)
	    {
		  for (int j = 0; j < width; j++)
		  {
			newImage[i][j] = imageData[i][j].Negative();
		  }
	    }
	    imageData = newImage;
      }

      /**
       * Convertit l'image vers une image en noir et blanc
       */
      public void convertToBWImage()
      {
	    for (int i = 0; i < height; i++)
	    {
		  for (int j = 0; j < width; j++)
		  {
			imageData[i][j] = imageData[i][j].toBWPixel();
		  }
	    }
      }

      /**
       * Convertit l'image vers un format de tons de gris
       */
      public void convertToGrayImage()
      {
	    for (int i = 0; i < height; i++)
	    {
		  for (int j = 0; j < width; j++)
		  {
			imageData[i][j] = imageData[i][j].toGrayPixel();
		  }
	    }
      }

      /**
       * Convertit l'image vers une image en couleurs
       */
      public void convertToColorImage()
      {
	    for (int i = 0; i < height; i++)
	    {
		  for (int j = 0; j < width; j++)
		  {
			imageData[i][j] = imageData[i][j].toColorPixel();
		  }
	    }
      }

      /**
       * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0) dans
       * le sens des aiguilles d'une montre (clockWise == true) ou dans le sens
       * inverse des aiguilles d'une montre (clockWise == false). Les pixels
       * vides
       * sont blancs.
       *
       * @param clockWise : Direction de la rotation
       */
      public void rotate(boolean clockWise)
      {
	    int rotation = 10;
	    double angle; // Angle de rotation en radians
	    // Image temporaire
	    AbstractPixel[][] newImage = new AbstractPixel[height][width];

	    // Conversion de l'angle en radians
	    if (clockWise)
	    {
		  angle = Math.toRadians(rotation);
	    }
	    else
	    {
		  angle = Math.toRadians(-rotation);
	    }

	    for (int i = 0; i < height; i++)
	    {
		  for (int j = 0; j < width; j++)
		  {
			// On remplit la nouvelle image de pixels blancs initialement
			newImage[i][j] = new BWPixel(true);
		  }
	    }

	    for (int i = 0; i < height; i++)
	    {
		  for (int j = 0; j < width; j++)
		  {
			// Calcul de la nouvelle position selon la formule
			int newI = (int) (Math.cos(angle) * i - Math.sin(angle) * j);
			int newJ = (int) (Math.sin(angle) * i + Math.cos(angle) * j);

			if (newI < height && newI >= 0 && newJ < width && newJ >= 0)
			{
			      // Assignation des pixels a la nouvelle position
			      newImage[newI][newJ] = imageData[i][j];
			}
		  }
	    }
	    // On assigne l'image temporaire a l'image permanente
	    imageData = newImage;
      }

      /**
       * Reduit de moitie la longueur et la largeur de l'image
       *
       * @param clockWise : Direction de la rotation
       */
      public void halveHW()
      {
	    // On cree une image temporaire
	    AbstractPixel[][] newImage = new AbstractPixel[height / 2][width / 2];

	    for (int i = 0; i < height; i += 2)
	    {
		  for (int j = 0; j < width; j += 2)
		  {
			// On assigne l'ancien pixel a la nouvelle position
			newImage[i / 2][j / 2] = imageData[i][j];
		  }
	    }

	    // Assignation de la nouvelle taille permanente
	    height /= 2;
	    width /= 2;

	    // Assignation de l'image temporaire a l'image permanente pour affichage
	    imageData = newImage;
      }

      /**
       * Insert pm dans l'image a la position row0 col0
       */
      public void insert(PixelMap pm, int row0, int col0)
      {
	    for (int i = 0; i < pm.height && i + row0 < height; i++)
	    {
		  for (int j = 0; j < pm.width && j + col0 < width; j++)
		  {
			// On assigne le pixel a la nouvelle position
			imageData[i + row0][j + col0] = pm.getPixel(i, j);
		  }
	    }
      }

      /**
       * Redimentionne l'image
       */
      public void resize(int h, int w)
      {
	    // On cree une image temporaire
	    AbstractPixel[][] newImage = new AbstractPixel[h][w];

	    for (int i = 0; i < h; i++)
	    {
		  for (int j = 0; j < w; j++)
		  {
			// Si on est encore dans la nouvelle taille ET l'ancienne
			if (i < h && i < height && j < w && j < width)
			{
			      newImage[i][j] = imageData[i][j]; // anciens pixels
			}
			else
			{
			      // Sinon on ajoute des pixels blanc
			      newImage[i][j] = new BWPixel(true);
			}
		  }
	    }
	    height = h;
	    width = w;
	    imageData = newImage;
      }

      /**
       * Effectue une translation de l'image
       */
      public void translate(int rowOffset, int colOffset)
      {
	    // Creation d'une image temporaire
	    AbstractPixel[][] newImage = new AbstractPixel[height][width];

	    for (int i = 0; i < height; i++)
	    {
		  for (int j = 0; j < width; j++)
		  {
			// Si la position est encore dans le cadre visible
			if ((i + rowOffset >= 0 && i + rowOffset < height)
				&& (j + colOffset >= 0 && j + colOffset < width))
			{
			      // On assigne le pixel courant
			      newImage[i + rowOffset][j + colOffset] = imageData[i][j];
			}
			else
			{
			      // Sinon on assigne un pixel blanc
			      newImage[i][j] = new BWPixel(true);
			}
		  }
	    }

	    // On assigne l'image temporaire a l'image permanente
	    imageData = newImage;
      }
}
