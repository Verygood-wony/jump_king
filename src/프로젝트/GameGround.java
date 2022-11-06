// 게임 내 발판을 담당하는 클래스
package 프로젝트;

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
	
	public boolean collision2() {		// 플레이어가 발판의 어느쪽으로 충돌하는지 검사(정상 착지 검사)
		// 점프에서 발판 착지시(플레이어가 위에서 아래로 충돌)
		if((GameFrame.screen.player.y<this.y) && (GameFrame.screen.player.x+50>this.x+10) && (GameFrame.screen.player.x<this.x+this.x_size-10) && collision()) {
			GameFrame.screen.player.y = this.y - 50;
			return true;
		}
		// 왼쪽에서 오른쪽으로 충돌 (캐릭터가 발판의 왼쪽에 충돌)
		if((GameFrame.screen.player.y+50>this.y) && (GameFrame.screen.player.y<this.y+this.y_size) && (GameFrame.screen.player.x<this.x) && collision()) {
			GameFrame.screen.player.x = this.x - 50;
			GameFrame.screen.player.JUMPING_X *= -1;
			GameFrame.screen.player.JUMPING_X_REVERSE *= -1;
			GameFrame.screen.player.vy = 0;
			return false;
		}
		// 오른쪽에서 왼쪽으로 충돌 (캐릭터가 발판의 오른쪽에 충돌)
		if((GameFrame.screen.player.y+50>this.y) && (GameFrame.screen.player.y<this.y+this.y_size) && (GameFrame.screen.player.x+50>this.x+this.x_size) && collision()) {
			GameFrame.screen.player.x = this.x + this.x_size;
			GameFrame.screen.player.JUMPING_X *= -1;
			GameFrame.screen.player.JUMPING_X_REVERSE *= -1;
			GameFrame.screen.player.vy = 0;
			return false;
		}
		// 플레이어가 아래서 위쪽으로 점프중에 발판충돌
		if((GameFrame.screen.player.y>this.y) && (GameFrame.screen.player.x+50>this.x) && (GameFrame.screen.player.x<this.x+this.x_size) && collision()) {
			GameFrame.screen.player.y = this.y + this.y_size;
			GameFrame.screen.player.vy = 0;
			return false;
		}
		else {
			return false;
		}
	}
	
	public boolean collision() {		// 플레이어와 충돌
		return GameFrame.screen.player.getBounds().intersects(getBounds());
	}
	
	public Rectangle getBounds() {		// 발판사이즈의  사각형으로 반환
		return new Rectangle(x, y, x_size, y_size);
	}
	
	public void draw(Graphics g) {
		Image img = new ImageIcon(GameFrame.class.getResource("../스테이지"+stage+"이미지/발판"+num+".png")).getImage();
		g.drawImage(img, x, y, x_size, y_size, null);
	}
}
