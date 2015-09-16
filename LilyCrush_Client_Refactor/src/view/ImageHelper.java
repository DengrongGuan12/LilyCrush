package view;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



import view.component.PNGFilter;

import enums.Avatar;
import enums.ImageType;
import enums.Prop;
import enums.VisualEffects;

/**
 * 用于对图片缓存的获取～
 *
 */
public class ImageHelper {
	
	private final static int imageNum = 6;
	
	private static ListOfType[] imgList = new ListOfType[imageNum];
	// Java默认文件路径是项目的根目录，所以要手动加上bin及其以下的路径
	private static final String basePath = "images/";

	//静态初始化块
	static {
		for (int i = 1; i < imageNum; i++) {
			String path = basePath + "image" + i + "/";

			BufferedImage[] normalEffectList;
			BufferedImage[] squareBombList;
			BufferedImage baseImg = null;
			BufferedImage[] crossBombList;

			String normalPath = path + "NormalEffect";
			String squarePath = path + "SquareEffect";
			String crossPath = path + "CrossEffect";
			// 查找所有的图片
			File normalDic = new File(normalPath);
			File squareDic = new File(squarePath);
			File crossDic = new File(crossPath);

			File[] normalFiles = normalDic.listFiles(new PNGFilter());
			int normalLength = normalFiles.length;
			normalEffectList = new BufferedImage[normalLength];

			File[] squareFiles = squareDic.listFiles(new PNGFilter());
			int squareLength = squareFiles.length;
			squareBombList = new BufferedImage[squareLength];

			File[] crossFiles = crossDic.listFiles(new PNGFilter());
			int crossLength = crossFiles.length;
			crossBombList = new BufferedImage[crossLength];

			normalPath += "/";
			squarePath += "/";
			crossPath += "/";

			int j = 0;

			try {

				baseImg = ImageIO
						.read(new File(path + "base.png"));

				for (j = 0; j < normalLength; j++) {
					String movePath = normalPath + "tupian" + (j + 1) + ".png";
					normalEffectList[normalLength - j - 1] = ImageIO
							.read(new File(movePath));
				}
				for (j = 0; j < squareLength; j++) {
					String movePath = squarePath + "tupian" + (j + 1)
							+ ".png";

					squareBombList[squareLength - j - 1] = ImageIO
							.read(new File(movePath));
				}
				for (j = 0; j < crossLength; j++) {
					String movePath = crossPath + "tupian" + (j + 1) + ".png";
					crossBombList[crossLength - j - 1] = ImageIO.read(new File(
							movePath));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			ListOfType l = new ListOfType(normalEffectList, squareBombList,
					crossBombList, baseImg);
			imgList[i - 1] = l;
		}
		
		//TODO 道具B的路径
		String path = basePath + "game/";		
		BufferedImage[] normalEffectList  = new BufferedImage[1];
		try {
			BufferedImage baseImg =baseImg = ImageIO
					.read(new File(path + "手里剑.png"));
			normalEffectList[0] = ImageIO.read(new File(path + "手里剑.png"));
			ListOfType l = new ListOfType(normalEffectList , normalEffectList,
					normalEffectList, baseImg);
			imgList[imageNum- 1] = l;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage[] getImgList(ImageType type,
			VisualEffects effect) {

		// 利用enum中自己写的getI()来定位imgList
		ListOfType list = imgList[type.getI()];

		switch (effect) {
		case normal:
			return list.getNormalList();
		case crossBomb:
			return list.getCrossList();
		case squareBomb:
			return list.getSquareList();
		default:
			return null;
		}
	}
	
	//由于头像不是频繁获取的图像，所以不需要放入缓存中
	public static ImageIcon getAvatar(Avatar type , int Width , int Height){
		
		
		String path = basePath + "avatar" + "/"+"avatar"+ImgPathHelper.getAvatarPath(type)+ ".gif";
		BufferedImage img = null;
			try {
			 img = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return resize(Width,Height,img);
	}
	
	public static ImageIcon getProp(Prop prop){
		String path = basePath+"prop/prop"+prop.getI()+".jpg";
		BufferedImage img = null;
		try {
		 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return resize(227,225,img);
	}
	
	public static Image getInviteListBkg(){
		String path = basePath+"room/listPanel.png";
		BufferedImage img = null;
		try {
		 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resize(100,300,img).getImage();
	}
	
	public static ImageIcon getChooseIcon(){
		String path = basePath+"room/choosed.png";
		BufferedImage img = null;
		try {
		 img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resize(37,35,img);
	}
	
	public static ImageIcon getGifIcon(String filename){
		String path = basePath + "others" + "/"+filename+ ".gif";
		BufferedImage img = null;
		try {
			 img = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return new ImageIcon(img);
	}
	
	private  static ImageIcon resize(int WIDTH,int HEIGHT, BufferedImage img){
		
		int type = img.getType() == 0? BufferedImage.TYPE_INT_ARGB : img.getType();
		
		BufferedImage resizedImage = new BufferedImage(WIDTH, HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(img, 0, 0, WIDTH, WIDTH, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		ImageIcon icon = new ImageIcon(resizedImage);
		return icon;
	}

	public static BufferedImage getBaseImg(ImageType type) {
		return imgList[type.getI()].getBaseImg();
	}
	
}

class ListOfType {
	BufferedImage[] normalEffectList;
	BufferedImage[] squareBombList;
	BufferedImage[] crossBombList;
	BufferedImage baseImg;

	public ListOfType(BufferedImage[] normalList, BufferedImage[] squareList,
			BufferedImage[] crossList, BufferedImage baseImg) {
		this.normalEffectList = normalList;
		this.squareBombList = squareList;
		this.crossBombList = crossList;
		this.baseImg = baseImg;
	}

	public BufferedImage[] getNormalList() {
		return normalEffectList;
	}

	public BufferedImage[] getSquareList() {
		return squareBombList;
	}

	public BufferedImage[] getCrossList() {
		return crossBombList;
	}

	public BufferedImage getBaseImg() {
		return baseImg;
	}
	
}