package viewService;

import common.gameInfo.NewMapInfo;
import enums.TimeCommand;
import vos.MapInitialVO;
import vos.MapRefreshInfoVO;

public interface MapService {
	// 地图初始化
	public void mapInitial(NewMapInfo newMapInfo);

	// 地图变更
	public void mapModify(MapRefreshInfoVO vo);
	
	// 时间控制
//	public void timeModify(TimeCommand timeCommand);
}
