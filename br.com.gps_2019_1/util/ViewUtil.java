package util;

import java.awt.Font;

import javax.swing.ImageIcon;

import com.itextpdf.text.Image;

public interface ViewUtil {
	
	
	public static interface Fonts{
		
		public static interface Times{
			
			public static final Font TIMES_PEQUENO = new Font("Times New Roman", Font.PLAIN, 13);
			public static final Font TIMES_PEQUENO_I = new Font("Times New Roman", Font.ITALIC, 13);
			public static final Font TIMES_PEQUENO_B = new Font("Times New Roman", Font.BOLD, 13);
			public static final Font TIMES_PEQUENO_B_I = new Font("Times New Roman", Font.BOLD+Font.ITALIC, 13);
			
			public static final Font TIMES_TITULO = new Font("Times New Roman", Font.PLAIN, 20);
			public static final Font TIMES_TITULO_I = new Font("Times New Roman", Font.ITALIC, 20);
			public static final Font TIMES_TITULO_B = new Font("Times New Roman", Font.BOLD, 20);
			public static final Font TIMES_TITULO_B_I = new Font("Times New Roman", Font.BOLD+Font.ITALIC, 20);
			
		}
		
		public static interface Arial{
			
			public static final Font ARIAL_PEQUENO = new Font("Arial", Font.PLAIN, 13);
			public static final Font ARIAL_PEQUENO_I = new Font("Arial", Font.ITALIC, 13);
			public static final Font ARIAL_PEQUENO_B = new Font("Arial", Font.BOLD, 13);
			public static final Font ARIAL_PEQUENO_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 13);
			
			public static final Font ARIAL_MEDIO = new Font("Arial", Font.PLAIN, 15);
			public static final Font ARIAL_MEDIO_I = new Font("Arial", Font.ITALIC, 15);
			public static final Font ARIAL_MEDIO_B = new Font("Arial", Font.BOLD, 15);
			public static final Font ARIAL_MEDIO_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 15);
			
			public static final Font ARIAL_TITULO = new Font("Arial", Font.PLAIN, 20);
			public static final Font ARIAL_TITULO_I = new Font("Arial", Font.ITALIC, 20);
			public static final Font ARIAL_TITULO_B = new Font("Arial", Font.BOLD, 20);
			public static final Font ARIAL_TITULO_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 20);
			
			public static final Font ARIAL_TITULO_GIGANTE = new Font("Arial", Font.PLAIN, 26);
			public static final Font ARIAL_TITULO_GIGANTE_I = new Font("Arial", Font.ITALIC, 26);
			public static final Font ARIAL_TITULO_GIGANTE_B = new Font("Arial", Font.BOLD, 26);
			public static final Font ARIAL_TITULO_GIGANTE_B_I = new Font("Arial", Font.BOLD+Font.ITALIC, 26);
			
			
		}
		
	} 
		
	public static interface Icones{
			
			public static ImageIcon getIcon(String nome) {
				String caminho = "image\\" + nome +".png";
				ClassLoader classLoader = Image.class.getClassLoader();
				return new ImageIcon(classLoader.getResource(caminho));
			}
			public static ImageIcon getIcon(String nome, String formato) {
				String caminho = "image\\" + nome +"."+formato;
				ClassLoader classLoader = Image.class.getClassLoader();
				return new ImageIcon(classLoader.getResource(caminho));
			}
			
			public static ImageIcon getIconImage(String path) {
				ClassLoader classLoader = Image.class.getClassLoader();
				return new ImageIcon(classLoader.getResource(path));
			}
			
			//Inserir constantes de Icones aqui ex
			
			public static final ImageIcon IMAGEM_LOGO_ITEXT = getIcon("iText_placeholder");
			
			
	}
	
}
