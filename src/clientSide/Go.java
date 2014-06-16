package clientSide;

public class Go {
	private boolean go;
	
	Go(boolean go){
		this.go = go;
	}
	public boolean getGo(){
		return go;
	}
	public void setGoFalse(){
		go = false;
	}
	public void setGoTrue(){
		go = true;
	}
}
