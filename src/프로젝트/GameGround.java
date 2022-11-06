// ���� �� ������ ����ϴ� Ŭ����
package ������Ʈ;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class GameGround {
	private int x, y, x_size, y_size, stage, num;
	
	public GameGround(int x, int y, int x_size, int y_size, int stage, int num) {
		this.x = x;
		this.y = y;
		this.x_size = x_size;
		this.y_size = y_size;
		this.stage = stage;
		this.num = num;
	}
	
	public boolean collision2() {		// �÷��̾ ������ ��������� �浹�ϴ��� �˻�(���� ���� �˻�)
		// �������� ���� ������(�÷��̾ ������ �Ʒ��� �浹)
		if((GameFrame.screen.player.y<this.y) && (GameFrame.screen.player.x+50>this.x+10) && (GameFrame.screen.player.x<this.x+this.x_size-10) && collision()) {
			GameFrame.screen.player.y = this.y - 50;
			return true;
		}
		// ���ʿ��� ���������� �浹 (ĳ���Ͱ� ������ ���ʿ� �浹)
		if((GameFrame.screen.player.y+50>this.y) && (GameFrame.screen.player.y<this.y+this.y_size) && (GameFrame.screen.player.x<this.x) && collision()) {
			GameFrame.screen.player.x = this.x - 50;
			GameFrame.screen.player.JUMPING_X *= -1;
			GameFrame.screen.player.JUMPING_X_REVERSE *= -1;
			GameFrame.screen.player.vy = 0;
			return false;
		}
		// �����ʿ��� �������� �浹 (ĳ���Ͱ� ������ �����ʿ� �浹)
		if((GameFrame.screen.player.y+50>this.y) && (GameFrame.screen.player.y<this.y+this.y_size) && (GameFrame.screen.player.x+50>this.x+this.x_size) && collision()) {
			GameFrame.screen.player.x = this.x + this.x_size;
			GameFrame.screen.player.JUMPING_X *= -1;
			GameFrame.screen.player.JUMPING_X_REVERSE *= -1;
			GameFrame.screen.player.vy = 0;
			return false;
		}
		// �÷��̾ �Ʒ��� �������� �����߿� �����浹
		if((GameFrame.screen.player.y>this.y) && (GameFrame.screen.player.x+50>this.x) && (GameFrame.screen.player.x<this.x+this.x_size) && collision()) {
			GameFrame.screen.player.y = this.y + this.y_size;
			GameFrame.screen.player.vy = 0;
			return false;
		}
		else {
			return false;
		}
	}
	
	public boolean collision() {		// �÷��̾�� �浹
		return GameFrame.screen.player.getBounds().intersects(getBounds());
	}
	
	public Rectangle getBounds() {		// ���ǻ�������  �簢������ ��ȯ
		return new Rectangle(x, y, x_size, y_size);
	}
	
	public void draw(Graphics g) {
		Image img = new ImageIcon(GameFrame.class.getResource("../��������"+stage+"�̹���/����"+num+".png")).getImage();
		g.drawImage(img, x, y, x_size, y_size, null);
	}
}
