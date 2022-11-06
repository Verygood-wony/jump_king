// ������������ ��Ż Ŭ����
package ������Ʈ;

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
	private Image img = new ImageIcon(GameFrame.class.getResource("../�̹���/��Ż/��Ż"+count+".png")).getImage();
	
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
	public void actionPerformed(ActionEvent e) {	// ��Ż�� �̹����� �ڿ������� �����Ͽ� gif���� ������ ��
		if(count==7) {
			img = new ImageIcon(GameFrame.class.getResource("../�̹���/��Ż/��Ż"+count+".png")).getImage();
			count = 0;
		}
		else {
			img = new ImageIcon(GameFrame.class.getResource("../�̹���/��Ż/��Ż"+count+".png")).getImage();
			count++;
		}
	}
	
	public boolean collision() {		// �÷��̾ ��Ż�� �浹(��Ҵ°�)
		return GameFrame.screen.player.getBounds().intersects(getBounds());
	}
	
	public Rectangle getBounds() {		// ��Żũ�⿡ �ش��ϴ� �簢�� ��ȯ
		return new Rectangle(x, y, X_SIZE, Y_SIZE);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, X_SIZE, Y_SIZE, null);
	}
}
