// 프로그램의 음악을 담당하는 클래스
package 프로젝트;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class GameMusic extends Thread {
	
	public Player player;
	public boolean isloop;
	public File file;
	public FileInputStream fis;
	public BufferedInputStream bis;
	public boolean isend;
	
	public GameMusic(String name, boolean isloop) {		// 음악을 불러온다.
		try {
			this.isloop = isloop;
			isend = false;
			file = new File(GameScreen.class.getResource("../음악/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getTime() {		// 음악의 진행시간
		if(player==null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() {		// 음악 종료
		isloop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {			// 음악을 실행
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while(isloop);
			isend = true;			// 무한반복이 아닐 때 노래가 끝나면 true
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
