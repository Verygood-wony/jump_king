// ���α׷��� ������ ����ϴ� Ŭ����
package ������Ʈ;

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
	
	public GameMusic(String name, boolean isloop) {		// ������ �ҷ��´�.
		try {
			this.isloop = isloop;
			isend = false;
			file = new File(GameScreen.class.getResource("../����/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getTime() {		// ������ ����ð�
		if(player==null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() {		// ���� ����
		isloop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {			// ������ ����
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while(isloop);
			isend = true;			// ���ѹݺ��� �ƴ� �� �뷡�� ������ true
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
