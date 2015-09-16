package viewService_Driver;

import java.util.ArrayList;

import common.gameInfo.NewMapInfo;
import enums.ImageType;
import enums.TimeCommand;
import viewService.MapService;
import vos.MapInitialVO;
import vos.Location;
import vos.MapRefreshInfoVO;
import enums.*;

public class SingleGameUI_Driver {
	int map[][];
	
	public void mapInitial_Drive(MapService mapService){
		map = new int[9][9];
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				map[i][j]=(int)(Math.random()*5);
			}
		}
		NewMapInfo newMapInfo = new NewMapInfo(map);
		mapService.mapInitial(newMapInfo);
	}
	
//	public void timeStart_Dirve(MapService mapService){
//		TimeCommand timeCommand = TimeCommand.start;
//		mapService.timeModify(timeCommand);
//	}
	
//	public void timePause_Drive(MapService mapService){
//		TimeCommand timeCommand = TimeCommand.pause;
//		mapService.timeModify(timeCommand);
//	}
	
//	public void timeContinue_Drive(MapService mapService){
//		TimeCommand timeCommand = TimeCommand.proceed;
//		mapService.timeModify(timeCommand);
//	}
	
	public void mapModify_Drive(MapService mapService){
		ArrayList<Location> crushList = new ArrayList<Location>();
		crushList.add(new Location(3,2));
		
		ArrayList<Location> newList = new ArrayList<Location>();
		newList.add(new Location(8,2));
		
		Location dragged = new Location(2,2);
		
		MapRefreshInfoVO vo = new MapRefreshInfoVO(crushList,
				VisualEffects.normal, newList, Direction.right, dragged, 0,
				DropMode.horizontal);
		mapService.mapModify(vo);
	}
}
