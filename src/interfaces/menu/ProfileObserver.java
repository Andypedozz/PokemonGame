package interfaces.menu;

import java.util.List;

public interface ProfileObserver {
	
	void initProfile();
	
	List<String> getAccountInfo(int select);

	void back();
}
