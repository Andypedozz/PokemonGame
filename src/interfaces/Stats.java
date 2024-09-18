package interfaces;

public interface Stats {
	
	int getHp();
	int getAttack();
	int getDefense();
	int getSpeed();
    int getSpAttack();
	int getSpDefense();
    void setActualHp(int actualHp);
	int getActualHp();
	void setActualAttack(int actualAttack);
    int getActualAttack();
	void setActualDefense(int actualDefense);
	int getActualDefense();
	void setActualSpeed(int actualSpeed);
	int getActualSpeed();
	void setActualSpAttack(int actualSpAttack);
    int getActualSpAttack();
    void setActualSpDefense(int actualSpDefense);
	int getActualSpDefense();
	void setPrecision(int precision);
	int getPrecision();
	void setAvoidance(int avoidance);
	int getAvoidance();
	Stats duplicate();
}
