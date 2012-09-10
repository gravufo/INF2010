/**
 * Fichier principal pour les exercices 1 et 2
 *
 * @author Tarek Ould Bachir (remplacer par votre nom)
 * @date : 2011-09-10
 */
public class Main
{

      /**
       * Fonction principale
       *
       * @param args (non utilise)
       */
      public static void main(String[] args)
      {
	    /**
	     * Exercice 1
	     */
	    PixelMap pmc = new PixelMap("ed.ppm");
	    PixelMap pmg = pmc.toGrayImage();
	    PixelMap pmb = pmc.toBWImage();


	    String wName = "Edsger Dijkstra (original)";
	    new DisplayImageWindow(wName, pmc, 50, 50);

	    wName = "Edsger Dijkstra (gris)";
	    new DisplayImageWindow(wName, pmg, 50 + 50, 50 + 50);

	    wName = "Edsger Dijkstra (B&W)";
	    new DisplayImageWindow(wName, pmb, 50 + 100, 50 + 100);


	    /**
	     * Exercice 2
	     */
	    PixelMapPlus pmp = new PixelMapPlus("./ed.ppm");
	    PixelMapPlus hpmp = new PixelMapPlus(pmp);
	    hpmp.halveHW();
	    PixelMapPlus gpmp = new PixelMapPlus(hpmp);
	    gpmp.convertToGrayImage();
	    PixelMapPlus bwpmp = new PixelMapPlus(hpmp);
	    bwpmp.convertToBWImage();
	    PixelMapPlus npmp = new PixelMapPlus(hpmp);
	    npmp.negate();

	    pmp.insert(hpmp, 0, 0);
	    pmp.insert(gpmp, pmp.getHeight() / 2, 0);
	    pmp.insert(bwpmp, 0, pmp.getWidth() / 2);
	    pmp.insert(npmp, pmp.getHeight() / 2, pmp.getWidth() / 2);

	    wName = "Edsger Dijkstra";
	    new DisplayImageWindow(wName, pmp);

      }
}
