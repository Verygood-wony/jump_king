// 스테이지안의 포탈 클래스
package 프로젝트;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class GamePortal implements ActionListener {
	private int x, y;
	private int count = 0;
	private static final int X_SIZE = 102, Y_SIZE = 142;
	private Image img = new ImageIcon(GameFrame.class.getResource("../이미지/포탈/포탈"+count+".png")).getImage();
	
	public Timer timer;
	
	public GamePortal(int x, int y) {
		this.x = x;
		this.y = y;
		timer = new Timer(70, this);
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	// 포탈의 이미지를 자연스럽게 변경하여 gif같은 느낌을 줌
		if(count==7) {
			img = new ImageIcon(GameFrame.class.getResource("../이미지/포탈/포탈"+count+".png")).getImage();
			count = 0;
		}
		else {
			img = new ImageIcon(GameFrame.class.getResource("../이미지/포탈/포탈"+count+".png")).getImage();
			count++;
		}
	}
	
	public boolean collision() {		// 플레이어가 포탈에 충돌(닿았는가)
		return GameFrame.screen.player.getBounds().intersects(getBounds());
	}
	
	public Rectangle getBounds() {		// 포탈크기에 해당하는 사각형 반환
		return new Rectangle(x, y, X_SIZE, Y_SIZE);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, X_SIZE, Y_SIZE, null);
	}
}
