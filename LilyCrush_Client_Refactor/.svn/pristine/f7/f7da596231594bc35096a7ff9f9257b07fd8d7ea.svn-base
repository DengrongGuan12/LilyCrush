package enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ImageType {
	image0 (0),
	image1 (1),
	image2 (2),
	image3 (3),
	image4 (4),
	image5 (5);
	
	 private static final Map<Integer,ImageType> search=new HashMap<Integer,ImageType>();       
  //把有的关联项放到Map里      
     static{      
        for(ImageType type:EnumSet.allOf(ImageType.class)){      
            search.put(type.getI(), type);      
         }      
   }      
   private int i;      
   ImageType(int i){      
        this.i=i;      
     }
     public int getI(){      
         return i;      
	     }      
	    //查找      
   public static ImageType get(int i){      
         return search.get(i);      
  }      
}
