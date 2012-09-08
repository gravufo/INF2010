/**
 * Fichier principal pour l'exercice 3
 * @author Tarek Ould Bachir (remplacer par votre nom)
 * @date : 2011-09-10
 */

import java.util.Scanner;
import java.util.Random;

public class FormValidator {

	/**
	 * Fonction principale
	 * @param args (non utilise)
	 */
	public static void main(String[] args)
	{
		// Longueur du code
		final int codeLength = 5;

		// Generation du code
		String code = generateCode( codeLength );
		if( code == null ) return;

		// Generation des transformations a appliquer
		int[] transform = generateTransform( codeLength );
		if( transform == null ) return;

		// Lecture des images correspondant aux codes et tranformations
		PixelMapPlus[] pm =  new PixelMapPlus[ codeLength ];
		for(int i=0; i<codeLength; i++)
		{
			String letter = code.substring(i, i+1);
			String fileName = "./alphabet/" + letter + ".ppm";
			pm[ i ] = new PixelMapPlus( fileName );

			switch( transform[i] )
			{
				case 1:
					pm[i].rotate( true );
					break;
				case 2:
					pm[i].rotate( false );
					break;
				case 3:
					pm[i].negate();
					break;
				case 4:
					pm[i].rotate( true );
					pm[i].negate();
					break;
				case 5:
					pm[i].rotate( false );
					pm[i].negate();
					break;
				case 6:
					int oldh = pm[i].height, oldw = pm[i].width;
					pm[i].halveHW();
					pm[i].resize(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate(true);
					break;
				case 7:
					oldh = pm[i].height; oldw = pm[i].width;
					pm[i].halveHW();
					pm[i].resize(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate(false);
					break;
				case 8:
					oldh = pm[i].height; oldw = pm[i].width;
					pm[i].halveHW();
					pm[i].resize(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate(true);
					pm[i].negate();
					break;
				case 9:
					oldh = pm[i].height; oldw = pm[i].width;
					pm[i].halveHW();
					pm[i].resize(oldh, oldw);
					pm[i].translate(oldw/4, oldh/4);
					pm[i].rotate(false);
					pm[i].negate();
					break;
				default: ; // 0
			}
		}

		// Creation de l'image cible a afficher
		PixelMapPlus codedImage = new PixelMapPlus(PixelMap.ImageType.Color,
				pm[0].height, codeLength*pm[0].width);

		// Insertion des images du code
		for(int i=0; i<codeLength; i++)
			codedImage.inset(pm[ i ], 0, i*pm[i].width);

		// Demander le code a l'usager
		String wName = "Code a entrer";
		DisplayImageWindow di = new DisplayImageWindow(wName, codedImage, true);

		System.out.print("Entrer code:");
		Scanner in = new Scanner(System.in);
		String response = in.nextLine();

		// Validation
		if( response.equals( code ) )
			System.out.println("Acces accorde");
		else
			System.out.println("Acces refuse");

		in.close();
		di.dispose();
	}

	/**
	 * Genere et retourne un string de longueur length avec des caracteres aleatoires
	 * choisis entre A et Z
	 * @param length : longueur de la chaine de caractere a generer (inferieur ou egal a 10)
	 */
	private static String generateCode(int length)
	{
		if (length > 10) return null;

		char[] charKey = new char[ length ];

		Random generator = new Random( System.nanoTime() );

		// compl�ter
	}

	/**
	 * Genere et retourne plusieurs nombres aleatoires entre 0 et 5
	 * @param nb : longueur de la chaine de caractere a generer (inferieur ou egal a 10)
	 */
	private static int[] generateTransform(int nb)
	{
		if (nb > 10) return null;

		int[] charTransform = new int[ nb ];

		Random generator = new Random( System.nanoTime() );

		// compl�ter
	}

}
